import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Filtros extends UnicastRemoteObject implements FiltrosCliente {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static int limitar(int numero, int limInferior, int limSuperior) {
        return numero < limInferior ? limInferior : numero > limSuperior ? limSuperior : numero;
    }

    public static int aEscalaGrises(int pixel) {
        return (((pixel >> 0) & 0xFF) + ((pixel >> 8) & 0xFF) + ((pixel >> 16) & 0xFF)) / 3;
    }

    public static void filtrar(
        int[][] img,
        int[][] resultado,
        boolean[][] kernel,
        Function<ArrayList<Boolean>, Boolean> reductor,
        BiFunction<Boolean, Boolean, Boolean> combinador
    ) {

        int w = img[0].length, h = img.length;
        ArrayList<ArrayList<Boolean>> convertida = new ArrayList<>();
        for(int y = 0; y < h; y++) {
            ArrayList<Boolean> fila = new ArrayList<>();
            for(int x = 0; x < w; x++)
                fila.add(aEscalaGrises(img[y][x]) > 0x80);
            convertida.add(fila);
        }
        
        int mitad = kernel.length / 2;
        for(int y = 0; y < h; y++) for(int x = 0; x < w; x++) {
            ArrayList<Boolean> escogidos = new ArrayList<>();
            for(int i = limitar(y - mitad, 0, h - 1), a = i - y + mitad;
                i <= limitar(y + mitad, 0, h - 1); i++, a++)
                for(int j = limitar(x - mitad, 0, w - 1), b = j - x + mitad;
                    j <= limitar(x + mitad, 0, w - 1); j++, b++)
                    escogidos.add(combinador.apply(
                        convertida.get(i).get(j),
                        kernel[a][b]
                    ));
            resultado[y][x] = reductor.apply(escogidos) ? 0xFFFFFFFF : 0x00000000;
        }
    }

    public static void erosion(int[][] img, int[][] resultado, boolean[][] kernel) {
        filtrar(
            img,
            resultado,
            kernel,
            l -> l.stream().allMatch(x -> x),
            (i, k) -> i || !k
        );
    }

    public static void dilatacion(int[][] img, int[][] resultado, boolean[][] kernel) {
        filtrar(
            img,
            resultado,
            kernel,
            l -> l.stream().anyMatch(x -> x),
            (i, k) -> i && k
        );
    }

    public static void deteccionBordes(int[][] img, int[][] resultado, boolean[][] kernel) {

        int w = img[0].length, h = img.length;
        int[][] erosionada = new int[h][w];
        int[][] dilatada = new int[h][w];
        erosion(img, erosionada, kernel);
        dilatacion(img, dilatada, kernel);
        for(int y = 0; y < h; y++) for(int x = 0; x < w; x++)
            resultado[y][x] = erosionada[y][x] == dilatada[y][x] ? 0xFF000000 : 0xFFFFFFFF;
    }

    public static void deteccionBordesConcurrente(int[][] img, int[][] resultado, boolean[][] kernel) throws InterruptedException {

        int w = img[0].length, h = img.length;
        int[][] erosionada = new int[h][w];
        int[][] dilatada = new int[h][w];
        Thread hiloErosionada = new Thread(() -> erosion(img, erosionada, kernel));
        Thread hiloDilatada = new Thread(() -> dilatacion(img, dilatada, kernel));
        hiloErosionada.start();
        hiloDilatada.start();
        hiloErosionada.join();
        hiloDilatada.join();
        for(int y = 0; y < h; y++) for(int x = 0; x < w; x++)
            resultado[y][x] = erosionada[y][x] == dilatada[y][x] ? 0xFF000000 : 0xFFFFFFFF;
    }
    
    public void deteccionBordesParalelo(
        int[][] img,
        int[][] resultado,
        boolean[][] kernel,
        FiltrosInterfaz server)
        throws InterruptedException {
        
        int w = img[0].length, h = img.length;
        erosionada = new int[h][w];
        dilatada = new int[h][w];
        Thread hiloErosionada = new Thread(() -> erosion(img, erosionada, kernel));
        Thread hiloDilatada = new Thread(() -> {
            try {
                server.procesarDilatacion(img, dilatada, kernel);
            } catch(RemoteException ex) {
                ex.printStackTrace();
            }
        });
        hiloErosionada.start();
        hiloDilatada.start();
        hiloErosionada.join();
        hiloDilatada.join();
        for(int y = 0; y < h; y++) for(int x = 0; x < w; x++)
            resultado[y][x] = erosionada[y][x] == dilatada[y][x] ? 0xFF000000 : 0xFFFFFFFF;
    }

    @Override
    public void recibirDilatada(int[][] img) throws RemoteException {
        dilatada = img;
    }

    @Override
    public void recibitErosionada(int[][] img) throws RemoteException {
        erosionada = img;
    }

    int[][] dilatada;
    int[][] erosionada;

    public Filtros() throws RemoteException { }

    public static int[][] imagenAMatriz(BufferedImage img) {
        
        int w = img.getWidth(), h = img.getHeight();
        int[][] salida = new int[h][w];
        for(int y = 0; y < h; y++) for(int x = 0; x < w; x++)
            salida[y][x] = img.getRGB(x, y);
        return salida;
    }

    public static BufferedImage matrizAImagen(int[][] img) {

        int w = img[0].length, h = img.length;
        BufferedImage salida = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        for(int y = 0; y < h; y++) for(int x = 0; x < w; x++)
            salida.setRGB(x, y, img[y][x]);
        return salida;
    }

    public static void main(String[] args) {
        
        try {
            
            JFileChooser escogerArchivo = new JFileChooser(".");
            if(escogerArchivo.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) return;
            int[][] entrada = imagenAMatriz(ImageIO.read(escogerArchivo.getSelectedFile()));
            int w = entrada[0].length, h = entrada.length;
            int[][] salida = new int[h][w];
            boolean[][] kernel = new boolean[][] {{false, true, false}, {true, true, true}, {false, true, false}};
            String resultados = "";

            long t1 = System.nanoTime();
            deteccionBordes(entrada, salida, kernel);
            long t2 = System.nanoTime();
            resultados += "Secuencial: " + (t2 - t1) / 1e+6 + "ms\n";
            ImageIO.write(matrizAImagen(salida), "png", new File("bordesSec.png"));
            salida = new int[h][w];

            t1 = System.nanoTime();
            deteccionBordesConcurrente(entrada, salida, kernel);
            t2 = System.nanoTime();
            resultados += "Concurrente: " + (t2 - t1) / 1e+6 + "ms\n";
            ImageIO.write(matrizAImagen(salida), "png", new File("bordesConc.png"));
            salida = new int[h][w];

            String dirServer = JOptionPane.showInputDialog(null, "Escriba la direccion del server:", "192.168.0.7:1234");
            FiltrosInterfaz server = (FiltrosInterfaz)Naming.lookup("//" + dirServer + "/filtros");
            Filtros cliente = new Filtros();
            server.registrarCliente(cliente);
            t1 = System.nanoTime();
            cliente.deteccionBordesParalelo(entrada, salida, kernel, server);
            t2 = System.nanoTime();
            resultados += "Paralelo: " + (t2 - t1) / 1e+6 + "ms";
            ImageIO.write(matrizAImagen(salida), "png", new File("bordesPar.png"));

            JOptionPane.showMessageDialog(null, resultados, "Resultados", JOptionPane.INFORMATION_MESSAGE);
            UnicastRemoteObject.unexportObject(cliente, true);
            
        } catch(Exception ex) { }
    }
}

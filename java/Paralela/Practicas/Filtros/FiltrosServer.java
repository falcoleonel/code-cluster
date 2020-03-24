import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

public class FiltrosServer extends UnicastRemoteObject implements FiltrosInterfaz {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public FiltrosCliente cliente;

    public FiltrosServer() throws RemoteException { }

    @Override
    public void procesarDilatacion(int[][] img, int[][] resultado, boolean[][] kernel) throws RemoteException {
        Filtros.dilatacion(img, resultado, kernel);
        System.out.println("Procesando dilatacion para una imagen de " + img[0].length + "x" + img.length);
        cliente.recibirDilatada(resultado);
    }

    @Override
    public void procesarErosion(int[][] img, int[][] resultado, boolean[][] kernel) throws RemoteException {
        Filtros.erosion(img, resultado, kernel);
        System.out.println("Procesando erosion para una imagen de " + img[0].length + "x" + img.length);
        cliente.recibitErosionada(resultado);
    }

    @Override
    public void registrarCliente(FiltrosCliente cliente) throws RemoteException {
        this.cliente = cliente;
    }

    public static void main(String[] args) {
        try {

            int puerto = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el puerto:", "1234"));
            FiltrosServer server = new FiltrosServer();
            String dirServer = "//"
                + InetAddress.getLocalHost().getHostAddress()
                + ":" + puerto + "/filtros";
            LocateRegistry.createRegistry(puerto);
            Naming.rebind(dirServer, server);
            System.out.println("Server iniciado en: " + dirServer);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

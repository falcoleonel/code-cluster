import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.io.FileNotFoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class SudokuForm extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        new SudokuForm().setVisible(true);
    }


    public SudokuForm() {
        inicializar();
    }

    public static Point derechaDe(JComponent c, int x) {
        return new Point(c.getX() + c.getWidth() + x, c.getY());
    }

    public static Point abajoDe(JComponent c, int y) {
        return new Point(c.getX(), c.getY() + c.getHeight() + y);
    }

    public JTable tabla;
    public JButton btnResolverSec;
    public JButton btnResolverConc;
    public JButton btnResolverPar;
    public JButton btnLeerArchivo;
    public JLabel tiempoSec;
    public JLabel tiempoConc;
    public JLabel tiempoPar;

    private void inicializar() {

        setTitle("Resuelve sudokus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dimVentana = new Dimension(300, 350);
        Dimension dimPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimVentana);
        setLocation(
            (dimPantalla.width - dimVentana.width) / 2, 
            (dimPantalla.height - dimVentana.height) / 2
        );

        setLayout(null);

        DefaultTableModel modelo = new DefaultTableModel(9, 9);
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                modelo.setValueAt(0, i, j);
        tabla = new JTable(modelo);
        tabla.setLocation(20, 30);
        tabla.setSize(142, 142);

        btnResolverSec = new JButton("Secuencial");
        btnResolverSec.setLocation(abajoDe(tabla, 10));
        btnResolverSec.setSize(btnResolverSec.getPreferredSize());

        btnResolverConc = new JButton("Concurrente");
        btnResolverConc.setLocation(abajoDe(btnResolverSec, 10));
        btnResolverConc.setSize(btnResolverConc.getPreferredSize());

        btnResolverPar = new JButton("Paralelo");
        btnResolverPar.setLocation(abajoDe(btnResolverConc, 10));
        btnResolverPar.setSize(btnResolverConc.getPreferredSize());

        btnLeerArchivo = new JButton("Leer");
        btnLeerArchivo.setLocation(abajoDe(btnResolverPar, 10));
        btnLeerArchivo.setSize(btnLeerArchivo.getPreferredSize());

        btnLeerArchivo.addActionListener(e -> {
            
            JFileChooser escogerArchivo = new JFileChooser(".");
            if(escogerArchivo.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
            try {
                int[][] sudoku = ConcurrentSudokuSolver.readSudoku(escogerArchivo.getSelectedFile());
                for(int i = 0; i < 9; i++)
                    for(int j = 0; j < 9; j++)
                        modelo.setValueAt(sudoku[i][j], i, j);
            } catch(FileNotFoundException ex) {}
        });


        tiempoSec = new JLabel();
        tiempoSec.setLocation(derechaDe(btnResolverSec, 10));
        tiempoSec.setSize(100, 20);

        tiempoConc = new JLabel();
        tiempoConc.setLocation(derechaDe(btnResolverConc, 10));
        tiempoConc.setSize(100, 20);

        tiempoPar = new JLabel();
        tiempoPar.setLocation(derechaDe(btnResolverPar, 10));
        tiempoPar.setSize(100, 20);

        
        BiConsumer<JLabel, Function<int[][], ArrayList<int[][]>>> resolver = (label, metodo) -> {
            
            label.setText("Computando...");
            int[][] sudoku = new int[9][9];
            for(int i = 0; i < 9; i++)
                for(int j = 0; j < 9; j++)
                    sudoku[i][j] = Integer.parseInt(modelo.getValueAt(i, j).toString());
            
            long t1 = System.nanoTime();
            ArrayList<int[][]> soluciones = metodo.apply(sudoku);
            long t2 = System.nanoTime();
            
            label.setText(String.valueOf((t2 - t1) / 1e+6) + "ms");
            soluciones.forEach(s -> JOptionPane.showMessageDialog(
                null,
                ConcurrentSudokuSolver.sudokuToString(s),
                "Solucion",
                JOptionPane.INFORMATION_MESSAGE
            ));
        };

        btnResolverSec.addActionListener(e ->
            resolver.accept(tiempoSec, s -> SudokuSolver.solutions(s, 1, 10))
        );
        btnResolverConc.addActionListener(e ->
            resolver.accept(tiempoConc, s -> ConcurrentSudokuSolver.solutions(s, 1, 10))
        );
        btnResolverPar.addActionListener(e -> {
            try {
                
                String dirServer = JOptionPane.showInputDialog(
                    this,
                    "Escriba la dirección del server:",
                    "127.0.0.1:1234"
                );

                ISudoku server = (ISudoku)Naming.lookup("//" + dirServer + "/Sudoku");
                resolver.accept(tiempoPar, s -> {

                    ArrayList<int[][]> soluciones = new ArrayList<>();

                    Solucionador local = new Solucionador(su ->
                        ConcurrentSudokuSolver.solutions(su, 1, 5),
                        s
                    );
                    Solucionador remoto = new Solucionador(su -> {
                            try {
                                return server.solutionsConc(su, 5, 10);
                            } catch(RemoteException ex) { ex.printStackTrace(); }
                            return new ArrayList<>();
                        },
                        s
                    );

                    local.start();
                    remoto.start();

                    try {
                        local.join();
                        remoto.join();
                    } catch(InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    soluciones.addAll(local.resultado);
                    soluciones.addAll(remoto.resultado);

                    return soluciones;
                });

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error al procesar",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
        

        /*
        add(tabla);
        add(btnResolverSec);
        add(btnResolverConc);
        add(btnLeerArchivo);
        add(tiempoSec);
        add(tiempoConc);
        */

        Stream.of(getClass().getFields()).forEach(p -> {
            try {
                Object o = p.get(this);
                if(o instanceof JComponent)
                    add((JComponent)o);
            } catch(IllegalAccessException ex) { }
        });
    }
}
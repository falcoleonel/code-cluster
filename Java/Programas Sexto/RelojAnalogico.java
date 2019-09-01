import javax.swing.JFrame;

public class RelojAnalogico {
    private static void crearPantalla() {
        JFrame frame = new JFrame("Reloj Analogico con Swing");

        Reloj reloj = new Reloj();

        frame.getContentPane().add(reloj);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(302, 302);
        frame.setVisible(true);
        frame.isAlwaysOnTop();
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                crearPantalla();
            }
        });
    }
}

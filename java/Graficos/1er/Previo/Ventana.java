import java.awt.event.*;
import javax.swing.*;

public class Ventana extends JFrame {

    private static final long serialVersionUID = 1L;

    public Ventana() {
        super("Primer Frame");
        setSize(360, 360);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        Ventana frame = new Ventana();
        frame.setVisible(true);
    }
}

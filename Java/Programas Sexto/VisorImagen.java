import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class VisorImagen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JScrollPane panel;
    private Pantalla pantalla;

    public VisorImagen(String archivo) {
        super("visor Imagen");
        Image img = Toolkit.getDefaultToolkit().getImage(archivo);
        pantalla = new Pantalla(img);
        panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(panel);
        panel.setViewportView(pantalla);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }
}

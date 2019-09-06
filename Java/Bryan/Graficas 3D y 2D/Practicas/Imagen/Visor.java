import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
//import package.pantalla;
//import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;


public class Visor extends JFrame{
    private static final long serialVersionUID = 2L;
    private JScrollPane panel;
    private Pantalla pantalla;

    public Visor (String file){
        super("Visor de Imagen");

        Image img =Toolkit.getDefaultToolkit().getImage(file);
        pantalla = new Pantalla(img);
        panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        getContentPane().add(panel);
        panel.setViewportView(pantalla);
        setViewportView(pantalla);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,300);
        show();
    }
    public static void main(String[] args) {
        new Visor("i_love_java.jpg");
    }
}
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;


 class Pantalla extends JPanel {

    private Image imagen;

    public Pantalla(Image img) {
                this.imagen = img;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Dimension tam = new Dimension(imagen.getWidth(this), imagen.getHeight(this));
        setPreferredSize(tam);
        setMinimumSize(tam);
        setMaximumSize(tam);
        setSize(tam);
        update(g);
    }

    public void update(Graphics g){
        g.drawImage(imagen, 0, 0, this);
    }
}
class VisorImagen extends JFrame {

    private JScrollPane panel;
    private Pantalla pantalla;
    
    public VisorImagen(String archivo){
        super("Visor imagen");
    
        System.out.println(archivo);
        Image img = Toolkit.getDefaultToolkit().getImage(archivo);
        pantalla = new Pantalla(img);
        panel = new JScrollPane ( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
    
        getContentPane().add(panel);
        panel.setViewportView(pantalla);
    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,300);
        setVisible(true);
    }    
    
    public static void main(String[] args) {
        VisorImagen frame = new VisorImagen(args[0]);
       
        frame.setVisible(true);

      }
    
}







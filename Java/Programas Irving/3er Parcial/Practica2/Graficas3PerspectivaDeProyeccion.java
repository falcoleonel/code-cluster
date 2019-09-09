
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Graficas3PerspectivaDeProyeccion extends JFrame {
    BufferedImage buffer;
    Pixel admin;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Graficas3PerspectivaDeProyeccion();
    }
    
    public Graficas3PerspectivaDeProyeccion(){
        super("Proyeccion de Perspectiva");
        setSize(600, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        admin = new Pixel(buffer);
        this.setVisible(true);
    }

    public void paint(Graphics g){
        Pixel.paintBackground(Color.WHITE);
        Painting.CuboPerspectivaProyeccion(30, 0, 30, 60, Color.GREEN);
        g.drawImage(buffer, 0, 0, this);
    }
}

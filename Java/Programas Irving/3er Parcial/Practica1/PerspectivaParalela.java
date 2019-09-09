
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class PerspectivaParalela extends JFrame {
    private BufferedImage buffer;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new PerspectivaParalela();
    }
    
    private PerspectivaParalela() {
        super("Perspectiva Paralela");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Pixel admin = new Pixel(buffer);
        this.setVisible(true);
        this.update(this.getGraphics());
    }

    public void paint(Graphics g){
        Pixel.paintBrackground(Color.WHITE);
        Painting.CuboParalelaProyeccion(0, 0, 0,50, Color.GREEN);
        g.drawImage(buffer, 0, 0, this);
        repaint();
    }
    
}

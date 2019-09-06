import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.sun.prism.paint.Color;

public class Ventana extends JFrame{
    private BufferedImage buffer;
    private Graphics graPixel;
    
    public static void main(String[] args) {
        Ventana h = new Ventana();
        Color col = new Color(10, 10, 255,0);
        h.putPixel(100, 100, asd);
    }

    public Ventana(){
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }
    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
    
}
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Pixel extends JFrame{
    private BufferedImage buffer;
    private Graphics graPixel;
    public Pixel(){
        int lng = 400, hg = 400;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(lng, hg);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1,BufferedImage.TYPE_INT_RGB);
        graPixel = buffer.getGraphics();
    }

    // ------------------Methot to draw a pixel------------------------------------
    public void putPixel(int x, int y, Color color) {
        graPixel.setColor(color);
        graPixel.drawLine(0, 0, 1, 1);
        this.getGraphics().drawImage(buffer, x, y, this);
    }
}
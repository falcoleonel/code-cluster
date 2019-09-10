import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class BresenhamCirculo extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public BresenhamCirculo()
    {
        int ancho = 500, alto = 500;

        setSize( ancho, alto );

        setTitle("Bresenham Circulo");
        setLocationRelativeTo( null );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        BresenhamCirculo circulo =  new BresenhamCirculo();
        circulo.drawCircle(250, 250, 100, new Color(123, 36, 28 ));
        circulo.drawCircle(250, 250, 100, new Color(123, 36, 28 ));
        circulo.drawCircle(250, 250, 50, new Color(46, 134, 193));
        circulo.drawCircle(250, 250, 150, new Color(46, 134, 193));

    }

    public void drawCircle(int cx, int cy, double r, Color color)
    {
        double p = 5d/4d - r;
        int y, x = 0, dy, dx = 1;

        r = Math.abs(r);
        dy = -2 * (int)r; // Utiliza las diferencias para saber cual está más cercas del punto

        if (r % 1 == 0) p = 1 - r; //si R es entero, p0 = 1 - r
        
        y = (int) r;
        
        //desde x=0 hasta x=y
        while (x <= y) {

            //octantes
            putPixel(cx + x, cy + y, color);
            putPixel(cx - x, cy + y, color);
            putPixel(cx + x, cy - y, color);
            putPixel(cx - x, cy - y, color);
            putPixel(cx + y, cy + x, color);
            putPixel(cx - y, cy + x, color);
            putPixel(cx + y, cy - x, color);
            putPixel(cx - y, cy - x, color);

            //Formula
            if (p >= 0) {

                dy += 2;
                p += dy; 
                y--;
            }

            dx += 2;
            p += dx;
            x++;
        }
    }

    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }


}
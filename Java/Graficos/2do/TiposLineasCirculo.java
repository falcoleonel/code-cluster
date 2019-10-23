import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class TiposLineasCirculo extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public TiposLineasCirculo()
    {
        int ancho = 800, alto = 600;

        setSize( ancho, alto );

        setTitle("Circulo discontinuo");
        setLocationRelativeTo( null );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        TiposLineasCirculo circulo =  new TiposLineasCirculo();
        circulo.drawCircle(400, 300, 70, new Color(0, 153, 0));
        circulo.drawCircle(400, 300, 50, new Color(0, 153, 0));
        circulo.drawCircle(400, 300, 200, new Color(0, 153, 0));
        circulo.drawCircle(400, 300, 220, new Color(0, 153, 0));
    }

    public void drawCircle(int cx, int cy, double r, Color color)
    {
        // linea discontinua
        double p =  5d/4d - r;
        int y, mascara = 0, x = 0, dy = -2 * (int)r, dx = 1;
        r = Math.abs(r);

        if (r % 1 == 0) p = 1 - r;
        y = (int) r;
        
        while (x <= y) {

            if (mascara < 4) {
                //octantes
                putPixel(cx + x, cy + y, color);
                putPixel(cx - x, cy + y, color);
                putPixel(cx + x, cy - y, color);
                putPixel(cx - x, cy - y, color);
                putPixel(cx + y, cy + x, color);
                putPixel(cx - y, cy + x, color);
                putPixel(cx + y, cy - x, color);
                putPixel(cx - y, cy - x, color);
            }
            //Formula Bresenham
            if (p >= 0) {
                dy += 2;
                p += dy; 
                y--;
            }
            dx += 2;
            p += dx;
            x++;
            mascara = (mascara < 7) ? mascara + 1 : 0 ;
        }
    }
    
    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }


}

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class TiposLineasCirculo extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public TiposLineasCirculo()
    {
        int ancho = 500, alto = 500;

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
        circulo.drawCircle(250, 250, 100, new Color(123, 36, 28 ));
        circulo.drawCircle(250, 250, 100, new Color(46, 134, 193));
        circulo.drawCircle(250, 250, 50, new Color(123, 36, 28 ));
        circulo.drawCircle(250, 250, 150, new Color(123, 36, 28 ));
        circulo.drawCircle(250, 250, 200, new Color(46, 134, 193));

    }

    public void drawCircle(int cx, int cy, double r, Color color)
    {
        // línea discontinua
        double p =  5d/4d - r;
        int y, mascara = 0, x = 0, dy = -2 * (int)r, dx = 1;
        r = Math.abs(r);

        if (r % 1 == 0) p = 1 - r;
        y = (int) r;
        
        while (x <= y) {

            // 1111000 se pintan 4 pixeles y se saltan 3
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
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class TiposLineas extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public TiposLineas()
    {
        int ancho = 800, alto = 600;

        setSize( ancho, alto );
        setTitle("linea discontinua");
        setLocationRelativeTo( null );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        TiposLineas linea =  new TiposLineas();
        linea.drawLine(100, 100, 375, 100, new Color(0, 102, 204));
        linea.drawLine(700, 100, 425, 100, new Color(0, 102, 204));

        linea.drawLine(125, 360, 125, 600, new Color(0, 102, 204));
        linea.drawLine(675, 600, 675, 360, new Color(0, 102, 204));

        linea.drawLine(50, 250, 150, 380, new Color(0, 102, 204));
        linea.drawLine(650, 380, 750, 250, new Color(0, 102, 204));

    }

    public void drawLine (int x1, int y1, int x2, int y2, Color color)
    {   
        // linea discontinua
        int p, a, b, x = x1, y = y1, mascara = 0;

        int dx = (x2 - x1);
        int dy = (y2 - y1);

        int stepy = (dy < 0) ? -1 : 1;
        int stepx = (dx < 0) ? -1 : 1;

        dy *= stepy;
        dx *= stepx;

        if(dx > dy) {
            p = 2 * dy - dx;
            a = 2 * dy;
            b = 2 * (dy - dx);

            while (x != x2)
            {
                if (p < 0)
                    p += a;
                else {
                    y += stepy;
                    p += b;
                }
                if (mascara < 4) {
                    putPixel(x, y, color); 
                }
                x += stepx;
                mascara = (mascara < 7) ? mascara + 1 : 0 ;
            }
        }
        else {

            p = 2 * dx - dy;
            a = 2 * dx;
            b = 2 * (dx - dy);

            while (y != y2)
            {
                if (p < 0) 
                    p += a;
                else {
                    x += stepx;
                    p += b;
                }
                if (mascara < 4) {
                    putPixel(x, y, color); 
                }
                y += stepy;
                mascara = (mascara < 7) ? mascara + 1 : 0 ;
            }
        }
    }

    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }
}

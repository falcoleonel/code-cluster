import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class TiposLineas extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public TiposLineas()
    {
        int ancho = 500, alto = 500;

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
        linea.drawLine(20, 100, 200, 100, new Color(123, 36, 28));
        linea.drawLine(20, 100, 200, 100, new Color(123, 36, 28));
        linea.drawLine(400, 100, 250, 100, new Color(46, 134, 193));

        linea.drawLine(125, 160, 125, 300, new Color(46, 134, 193));
        linea.drawLine(320, 300, 320, 160, new Color(123, 36, 28));

        linea.drawLine(50, 350, 150, 480, new Color(123, 36, 28));
        linea.drawLine(300, 480, 400, 350, new Color(46, 134, 193));

    }

    public void drawLine (int x1, int y1, int x2, int y2, Color color)
    {   
        // línea discontinua
        int p, a, b, x = x1, y = y1, mascara = 0;

        int dx = (x2 - x1);
        int dy = (y2 - y1);

        int stepy = (dy < 0) ? -1 : 1;
        int stepx = (dx < 0) ? -1 : 1;

        dy *= stepy;
        dx *= stepx;
        // 1111000 se pintan 4 pixeles y se saltan 3

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
                    //Formula
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
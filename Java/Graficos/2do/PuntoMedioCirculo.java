import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class PuntoMedioCirculo extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public PuntoMedioCirculo()
    {
        int ancho = 500, alto = 500;

        setSize( ancho, alto );

        setTitle("Punto Medio");
        setLocationRelativeTo( null );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        PuntoMedioCirculo puntoMedioCirculo =  new PuntoMedioCirculo();
        puntoMedioCirculo.drawCircle(250, 250, 100, new Color(123, 36, 28 ));
        puntoMedioCirculo.drawCircle(250, 250, 100, new Color(123, 36, 28 ));
        puntoMedioCirculo.drawCircle(250, 250, 50, new Color(46, 134, 193));
        puntoMedioCirculo.drawCircle(250, 250, 150, new Color(46, 134, 193));

    }

    public void drawCircle(int h, int k, double r, Color color)
    {
        //centro en (0,0)
        // determinar el pixel más cercano a la circunferencia
        int x = 0, y = (int)r;
        double p = 5d/4d - r; 
        
        if (r % 1 == 0) p = 1 - r; //si R es entero, p0 = 1 - r

        //desde x=0 hasta x=y
        while( x < y)
        {
            //octantes
            putPixel(  x + h, y + k, color);
            putPixel( - x + h, y + k, color);
            putPixel(  x + h, -y + k, color);
            putPixel( - x + h, -y + k, color);
            putPixel( y + h, x + k, color);
            putPixel( -y + h, x + k, color);
            putPixel( y + h, -x + k, color);
            putPixel( -y + h, -x + k, color);

            if(p < 0)
            {
                p = p + ( 2 * x ) + 3;
            } else
            {
                p = p + ( 2 * ( x - y ) ) + 5;
                y--;
            }              
            x++;
        }
        
    }

    private void putPixel( int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }


}
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class SimetriaOchoLados extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public SimetriaOchoLados()
    {
        int ancho = 500, alto = 500;

        setSize( ancho, alto );

        setTitle("Simetria Ocho Lados");
        setLocationRelativeTo( null );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        SimetriaOchoLados simetriaOchoLados =  new SimetriaOchoLados();
        simetriaOchoLados.drawCircle(250, 250, 100, new Color(123, 36, 28 ));
        simetriaOchoLados.drawCircle(250, 250, 100, new Color(123, 36, 28 ));
        simetriaOchoLados.drawCircle(250, 250, 50, new Color(46, 134, 193));
        simetriaOchoLados.drawCircle(250, 250, 150, new Color(46, 134, 193));

    }

    public void drawCircle(int x1, int y1, int r, Color color)
    {
        // reducir el cálculo
        // primer octante es simétrico al segundo a través de una diagonal
        int x = 0;
        int y = 0;
         
        putPixel( x, y + r, color);
        putPixel( x, y - r, color);
        putPixel( x + r, y, color);
        putPixel( x - r, y, color);
        x = 1;

        y = (int)(Math.sqrt(r * r - x * x));
        
        //x=0 hasta x=y podemos pintar todo el circulo
        while( x < y )
        {
            //octantes
            putPixel(x1 + x, y1 + y, color);
            putPixel(x1 + x, y1 - y, color);
            putPixel(x1 - x, y1 + y, color);
            putPixel(x1 - x, y1 - y, color);
            putPixel(x1 + y, y1 + x, color);
            putPixel(x1 + y, y1 - x, color);
            putPixel(x1 - y, y1 + x, color);
            putPixel(x1 - y, y1 - x, color);          
            x += 1;           
            y = (int)(Math.sqrt(r * r - x * x)); 
        } 
        
    }

    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }


}
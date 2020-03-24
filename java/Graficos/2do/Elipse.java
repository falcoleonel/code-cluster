import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Elipse extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public Elipse()
    {
        int ancho = 500, alto = 500;

        setSize( ancho, alto );

        setTitle("Elipse");
        setLocationRelativeTo( null );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        Elipse elipse =  new Elipse();
        elipse.drawOval(250, 250, 30, 60, new Color(123, 36, 28 ));
        elipse.drawOval(250, 250, 30, 60, new Color(123, 36, 28 ));
        elipse.drawOval(250, 250, 60, 30, new Color(123, 36, 28 ));
        // elipse.drawOval(250, 250, 20, 50, new Color(46, 134, 193));
        elipse.drawOval(250, 250, 20, 70, new Color(46, 134, 193));
        elipse.drawOval(250, 250, 50, 20, new Color(46, 134, 193));

    }

    public void drawOval(int cx, int cy, int rx, int ry, Color color)
    {
        double lr = ( rx > ry) ? rx : ry ; //El radio más largo
        double max = Math.PI * .5; //Límite de t pi/5 (45)

        double x, y, step = 1.0 / lr; //Pasos 1/radio más largo

        for (double t = 0; t <= max; t += step)
        {
            //incremento del ángulo suficientemente pequeño
            //para evitar los huecos
            x = Math.round(rx * Math.sin(t));
            y = Math.round(ry * Math.cos(t));
            // Cuadrantes
            putPixel(cx + (int)x, cy + (int)y, color);
            putPixel(cx - (int)x, cy + (int)y, color);
            putPixel(cx + (int)x, cy - (int)y, color);
            putPixel(cx - (int)x, cy - (int)y, color);
        }
        
    }

    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }


}
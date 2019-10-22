import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class CoordenadasPolaresCirculo extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public CoordenadasPolaresCirculo()
    {
        int ancho = 500, alto = 500;

        setSize( ancho, alto );

        setTitle("Coordenadas Polares Circulo");
        setLocationRelativeTo( null );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        CoordenadasPolaresCirculo circulo =  new CoordenadasPolaresCirculo();
        // circulo.drawCircle(250, 250, 100, new Color(123, 36, 28 ));
        circulo.drawCircle(250, 250, 100, new Color(123, 36, 28 ));
        circulo.drawCircle(250, 250, 50, new Color(46, 134, 193));
        circulo.drawCircle(250, 250, 150, new Color(46, 134, 193));

    }

    public void drawCircle(int cx, int cy, int r, Color color) {
        double step, y, x;

        r = Math.abs(r);
        step = 5 / (double) r;

        for (double t = cx - r; t <= cx + r; t += step) {
            //incremento del ángulo suficientemente pequeño
            //para evitar los huecos
            y = (r * Math.cos(t));
            x = (r * Math.sin(t));

            putPixel(cx + (int) x, (int) Math.round(cy + y), color);
            putPixel(cx + (int) x, (int) Math.round(cy - y), color);
        
        }
        
    }

    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }


}
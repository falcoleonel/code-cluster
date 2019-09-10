import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class GrosorCirculo extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public GrosorCirculo()
    {
        int ancho = 500, alto = 500;

        setSize( ancho, alto );

        setTitle("Circulo grosor");
        setLocationRelativeTo( null );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        GrosorCirculo circulo =  new GrosorCirculo();
        circulo.drawCircle(250, 250, 100, 1, new Color(46, 134, 193));
        circulo.drawCircle(250, 250, 100, 1, new Color(46, 134, 193));
        circulo.drawCircle(250, 250, 150, 2, new Color(123, 36, 28 ));
        circulo.drawCircle(250, 250, 115, 3, new Color(123, 36, 28 ));
        circulo.drawCircle(250, 250, 200, 1, new Color(46, 134, 193));

    }

    public void drawCircle(int cx, int cy, int r, int ancho, Color color)
    {
        //Bresenham
        double p = 5d/4d - r;
        int y, x = 0, dy = -2 * r, dx = 1;
        r = Math.abs(r);

        if (r % 1 == 0) p = 1 - r;
        y = r;
        
        for (int i = 1; i <= ancho; i++) {
            putPixel(cx + i, cy + r, color);
            putPixel(cx + i, cy - r, color);
            putPixel(cx + i + r, cy, color);
            putPixel(cx + i - r, cy, color);
            putPixel(cx, cy + r + i, color);
            putPixel(cx, cy - r + i, color);
            putPixel(cx + r, cy + i, color);
            putPixel(cx - r, cy + i, color);
            putPixel(cx - i, cy + r, color);
            putPixel(cx - i, cy - r, color);
            putPixel(cx - i + r, cy, color);
            putPixel(cx - i - r, cy, color);
            putPixel(cx, cy + r - i, color);
            putPixel(cx, cy - r - i, color);
            putPixel(cx + r, cy - i, color);
            putPixel(cx - r, cy - i, color);
        }

        while (x <= y) {

            if (p >= 0) {
                y--;
                dy += 2;
                p += dy;
            }
            dx += 2;
            p += dx;
            x++;

            putPixel(cx + x, cy + y, color);
            putPixel(cx - x, cy + y, color);
            putPixel(cx + x, cy - y, color);
            putPixel(cx - x, cy - y, color);
            putPixel(cx + y, cy + x, color);
            putPixel(cx - y, cy + x, color);
            putPixel(cx + y, cy - x, color);

            for (int j = 1; j <= ancho; j++) {
                putPixel(cx + x + j, cy + y, color);
                putPixel(cx - x + j, cy + y, color);
                putPixel(cx + x + j, cy - y, color);
                putPixel(cx - x + j, cy - y, color);
                putPixel(cx + y + j, cy + x, color);
                putPixel(cx - y + j, cy + x, color);
                putPixel(cx + y + j, cy - x, color);
                putPixel(cx - y + j, cy - x, color);
                putPixel(cx + x - j, cy + y, color);
                putPixel(cx - x - j, cy + y, color);
                putPixel(cx + x - j, cy - y, color);
                putPixel(cx - x - j, cy - y, color);
                putPixel(cx + y - j, cy + x, color);
                putPixel(cx - y - j, cy + x, color);
                putPixel(cx + y - j, cy - x, color);
                putPixel(cx - y - j, cy - x, color);
                putPixel(cx + x, cy + y - j, color);
                putPixel(cx - x, cy + y - j, color);
                putPixel(cx + x, cy - y - j, color);
                putPixel(cx - x, cy - y - j, color);
                putPixel(cx + y, cy + x - j, color);
                putPixel(cx - y, cy + x - j, color);
                putPixel(cx + y, cy - x - j, color);
                putPixel(cx - y, cy - x - j, color);
                putPixel(cx + x, cy + y + j, color);
                putPixel(cx - x, cy + y + j, color);
                putPixel(cx + x, cy - y + j, color);
                putPixel(cx - x, cy - y + j, color);
                putPixel(cx + y, cy + x + j, color);
                putPixel(cx - y, cy + x + j, color);
                putPixel(cx + y, cy - x + j, color);
                putPixel(cx - y, cy - x + j, color);
            }
        }
        
    }

    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }


}
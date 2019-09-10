import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class GrosorLineas extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public GrosorLineas()
    {
        int ancho = 500, alto = 500;

        setSize( ancho, alto );

        setTitle("Grosor Lineas");
        setLocationRelativeTo( null );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        GrosorLineas linea =  new GrosorLineas();
        linea.drawLine(20, 100, 200, 100, 2, new Color(123, 36, 28));
        linea.drawLine(20, 100, 200, 100, 2, new Color(123, 36, 28));
        linea.drawLine(400, 100, 250, 100, 2, new Color(46, 134, 193));

        linea.drawLine(125, 160, 125, 300, 2, new Color(46, 134, 193));
        linea.drawLine(320, 300, 320, 160, 2, new Color(123, 36, 28));

        linea.drawLine(50, 350, 150, 480, 2, new Color(123, 36, 28));
        linea.drawLine(300, 480, 400, 350, 2, new Color(46, 134, 193));

    }

    public void drawLine (int x1, int y1, int x2, int y2, int grosor, Color color)
    {   
        //DDA
        //x y a double para poder hacer diagonales
        double m, xinc, yinc, x = (double) x1, y = (double) y1;
        int steps = 0, dx = x2 - x1, dy = y2 - y1;
       
        m =  (x2 == x1) ? 10 : ((double)y2 - (double)y1) / ((double)x2 - (double)x1);
        
        //Se itera la diferencia del mayor, el redondeo es muy lento
        if ( Math.abs(dx) > Math.abs(dy)) 
            steps = Math.abs(dx);
        else 
            steps = Math.abs(dy);

        xinc = (double)dx / (double)steps;
        yinc = (double)dy / (double)steps;

        for (int k = 1; k <= steps; k++)
        {
            putPixel((int)x, (int)y, color);

            for (int j = 1; j <= grosor; j++) {
                putPixel((int)x, (int) y + j, color);
                putPixel((int)x, (int) y - j, color);
            }
            //Si la pendiente es mayor que 1,se usan secciones horizontales
            if (m >= 1) {
                for (int j = 1; j <= grosor; j++) {
                    putPixel((int)x, (int) y + j, color);
                    putPixel((int)x, (int) y - j, color);
                }
                if (m > 2) {
                    for (int h = 1; h <= grosor; h++) {
                        putPixel((int)x + h, (int)y, color);
                        putPixel((int)x - h, (int)y, color);
                    }
                }
            }
            //Si la pendiente es menor que 1, para cada posición de x pintamos en una
            //sección vertical en pixeles
            else {
                for (int l = 1; l <= grosor; l++) {
                    putPixel((int)x+l, (int)y, color);
                    putPixel((int)x-l, (int)y, color);
                }
            }

            x += xinc;
            y += yinc;
        }
    }



    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }


}
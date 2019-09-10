import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class PuntoMedio extends JFrame
{	
	private BufferedImage buffer;
    private Graphics graPixel;

	public PuntoMedio()
	{
        int ancho = 500, alto = 500;

        setSize( ancho, alto );

        setTitle("Algoritmo PuntoMedio");
        setLocationRelativeTo( null );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
	}

	public static void main( String[] args)
    {
    	PuntoMedio puntoMedio = new PuntoMedio();

        puntoMedio.drawLine(20, 100, 200, 100, new Color(123, 36, 28));
        puntoMedio.drawLine(400, 100, 250, 100, new Color(46, 134, 193));

        puntoMedio.drawLine(125, 160, 125, 300, new Color(46, 134, 193));
        puntoMedio.drawLine(320, 300, 320, 160, new Color(123, 36, 28));

        puntoMedio.drawLine(50, 350, 150, 450, new Color(123, 36, 28));
        puntoMedio.drawLine(300, 450, 400, 350, new Color(46, 134, 193));

    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color)
    {
    	int p, a, b, x = x1, y = y1;

        //Utiliza las diferencias para saber cual está más cercas del punto
        int dx = (x2 - x1);
        int dy = (y2 - y1);

        //Calcula los pasos en base la mayor diferencia, en valor absoluto
        int steps = ( Math.abs(dx) > Math.abs(dy) ) ? Math.abs(dx) : Math.abs(dy);

        //Calcula los pasos en x y en y 
        int stepy = (dy < 0) ? -1 : 1;
        int stepx = (dx < 0) ? -1 : 1;

        dy *= stepy;
        dx *= stepx;

        if(dx > dy) {
            p = 2 * dy - dx;

            a = 2 * dy;
            b = 2 * (dy - dx);

            //Iterar con los pasos de acuerdo a la mayor diferencia

            for (int k = 0; k < steps; k++)
            {
                putPixel(x, y, color); 

                if (p < 0) 
                    p += a;
                else {
                    y += stepy;
                    p += b;
                }
                
                x += stepx;
            }
        }
        else {
            p = 2 * dx - dy;

            a = 2 * dx;
            b = 2 * (dx - dy);

            //Iterar con los pasos de acuerdo a la mayor diferencia
            for (int k = 0; k < steps; k++)
            {
                putPixel(x, y, color); 

                if (p < 0) 
                    p += a;
                else {
                    x += stepx;
                    p += b;
                }

                y += stepy;
            }
        }
    	
    }

    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }


}
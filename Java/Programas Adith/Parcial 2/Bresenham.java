import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Bresenham extends JFrame
{
    private BufferedImage buffer;
    private Graphics graPixel;
    
    public Bresenham()
    {
    	int alto = 500, ancho = 500;

        setTitle("Algoritmo Bresenham");
        setSize( ancho, alto );
        setLocationRelativeTo( null );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
    	Bresenham bresenham = new Bresenham();

        bresenham.drawLine(20, 100, 200, 100, new Color(123, 36, 28));
        bresenham.drawLine(400, 100, 250, 100, new Color(46, 134, 193));

        bresenham.drawLine(125, 160, 125, 300, new Color(46, 134, 193));
        bresenham.drawLine(320, 300, 320, 160, new Color(123, 36, 28));

        bresenham.drawLine(50, 350, 150, 480, new Color(123, 36, 28));
        bresenham.drawLine(300, 480, 400, 350, new Color(46, 134, 193));
    }

    public void drawLine (int x1, int y1, int x2, int y2, Color color)
    {   
        //Aritmetica entera 
        int p, a, b, x = x1, y = y1;

        //Utiliza las diferencias para saber cual está más cercas del punto 
        int dx = (x2 - x1);
        int dy = (y2 - y1);

        //Calcula los pasos en x y en y 
        int stepy = (dy < 0) ? -1 : 1;
        int stepx = (dx < 0) ? -1 : 1;

        dy *= stepy;
        dx *= stepx;

        //Calcula a partir del anterior
        // Itera en el mayor 

        if(dx > dy) {
            //Se obtiene el valor de p !Important
            p = 2 * dy - dx;

            a = 2 * dy;
            b = 2 * (dy - dx);

            //Hasta que llegue al punto
            while (x != x2)
            {
                    //Formula
                if (p < 0)
                    p += a;
                else {
                    y += stepy;
                    p += b;
                }

                putPixel(x, y, color); 
                //Se hacen los pasos en x
                x += stepx;
            }
        }
        else {

            //Se obtiene el valor de p !Important
            p = 2 * dx - dy;

            a = 2 * dx;
            b = 2 * (dx - dy);

            //Hasta que llegue al punto
            while (y != y2)
            {
                    //Formula
                if (p < 0) 
                    p += a;
                else {
                    x += stepx;
                    p += b;
                }

                putPixel(x, y, color); 
                //Se hacen los pasos en y
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

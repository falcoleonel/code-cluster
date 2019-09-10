import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class RecorteLineas extends JFrame
{   

    private BufferedImage buffer;
    private Graphics graPixel;

    int []area = {-1,-1, -1,-1};

    public RecorteLineas()
    {     	
        int ancho = 500, alto = 500;

        setSize( ancho, alto );
        setTitle("Recorte de Lineas");
        setResizable( false );

        setLocationRelativeTo( null );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }   
    
    public static void main(String[] args)
    {
        RecorteLineas linea = new RecorteLineas();
       
        linea.drawRectangle(35, 65, 400, 400,new Color(123, 36, 28 ));
        linea.drawRectangle(35, 65, 400, 400,new Color(123, 36, 28 ));
        linea.setArea(35, 65, 400, 400);

        linea.drawLine(20, 100, 200, 100, new Color(123, 36, 28));
        linea.drawLine(400, 100, 250, 100, new Color(46, 134, 193));

        linea.drawLine(125, 160, 125, 300, new Color(46, 134, 193));
        linea.drawLine(320, 300, 320, 160, new Color(123, 36, 28));

        linea.drawLine(50, 350, 150, 450, new Color(123, 36, 28));
        linea.drawLine(300, 450, 400, 350, new Color(46, 134, 193));

    }
    
    public void drawRectangle(int x1, int y1, int x2, int y2,  Color color) {
        int temp = 0;

        //empezar desde el valor más pequeño hasta el más grande
        if (x1 > x2) {
            temp = x1;
            x1 = x2;
            x2 = temp;
        }

        if (y1 > y2) {
            temp = y1;
            y1 = y2;
            y2 = temp;
        }

        //Iterar de x1 a x2 con los mismos valores de y
        for(int x = x1; x <= x2; x++){
            putPixel(x, y1, color); 
            putPixel(x, y2, color); 
        }

        //Iterar de y1 a y2 con los mismos valores de x
        for(int y = y1; y <= y2; y++){
            putPixel(x1, y, color); 
            putPixel(x2, y, color); 
        }

    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        //Bresenham
        int p, a, b, x = x1, y = y1;

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
                if( x > area[0] && x < area[1] && y > area[2] && y < area[3] )
                    putPixel(x, y, color); 
                x += stepx;
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
                if( x > area[0] && x < area[1] && y > area[2] && y < area[3] )
                    putPixel(x, y, color); 
                y += stepy;
            }
        }
        
    }

    public void setArea(int x1, int y1, int x2, int y2) {
        this.area[0] = x1;
        this.area[1] = x2;
        this.area[2] = y1;
        this.area[3] = y2;
    }
    
    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }
}

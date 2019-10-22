import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Rectangulo extends JFrame
{   

    private BufferedImage buffer;
    private Graphics graPixel;

    public Rectangulo()
    {     	
        int ancho = 500, alto = 500;

        setSize( ancho, alto );
        setTitle("Rectangulo");
        setResizable( false );

        setLocationRelativeTo( null );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }   
    
    public static void main(String[] args)
    {
        Rectangulo rectangulo = new Rectangulo();
       
        rectangulo.drawRectangle(100, 100, 50, 130, new Color(238, 238, 238 ));
        rectangulo.drawRectangle(100, 100, 50, 130, new Color(123, 36, 28 ));
        rectangulo.drawRectangle(200, 200, 130, 150, new Color(46, 134, 193));
        rectangulo.drawRectangle(300, 300, 80, 50, new Color(123, 36, 28));
        rectangulo.drawRectangle(400, 400, 150, 80, new Color(46, 134, 193));

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
    
    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }
}

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Basico extends JFrame
{
    private Graphics graPixel;
    private BufferedImage buffer;
    
    public Basico() 
    {     	
        int ancho = 500, alto = 500;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Modelo Basico");
        setSize( ancho, alto );
        setResizable( false );
        setLocationRelativeTo( null );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

    }   


   
    public static void main(String[] args) 
    {
        Basico basico = new Basico();  
        basico.drawLine(50, 100, 200, 100, new Color(123, 36, 28));

        basico.drawLine(400, 100, 250, 100, new Color(46, 134, 193));


        basico.drawLine(125, 160, 125, 200, new Color(46, 134, 193));
        basico.drawLine(320, 300, 320, 160, new Color(123, 36, 28));


        basico.drawLine(50, 350, 150, 500, new Color(123, 36, 28));
        basico.drawLine(400, 100, 250, 100, new Color(46, 134, 193));
    }
   
    private void putPixel(int x, int y, Color color)
    {

        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);  
    }
    
    public void drawLine(int x1, int y1,int x2, int y2, Color color)
    {   

        //Se utiliza el modelo para sacar la pendiente  y = mx + b
        double y = 0;
        
        double m = ( y2 - y1 )/ ( ( x2 - x1 ) == 0 ? 1 :( x2 - x1 ));
        double b = y1 - (m * x1);

        //Toma desde la x más grande hasta la más pequeña

        for( int x = x1; x <= x2; x++ )
        {
            y = ( m * x ) + b;    
            putPixel( x,(int) Math.round(y), color );
        } 

    }
   
}

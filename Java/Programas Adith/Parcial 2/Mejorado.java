import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Mejorado extends JFrame
{   

    private BufferedImage buffer;
    private Graphics graPixel;

    public Mejorado()
    {     	
        int ancho = 500, alto = 500;

        setSize( ancho, alto );
        setTitle("Modelo Mejorado");
        setResizable( false );

        setLocationRelativeTo( null );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }   
    
    public static void main(String[] args)
    {
        Mejorado mejorado = new Mejorado();
       
        mejorado.drawLine(20, 100, 200, 100, new Color(123, 36, 28));
        mejorado.drawLine(400, 100, 250, 100, new Color(46, 134, 193));

        mejorado.drawLine(125, 160, 125, 300, new Color(46, 134, 193));
        mejorado.drawLine(320, 300, 320, 160, new Color(123, 36, 28));


        mejorado.drawLine(50, 350, 150, 500, new Color(123, 36, 28));
        mejorado.drawLine(300, 450, 400, 350, new Color(46, 134, 193));

    }
    
    public void drawLine(int x1 , int y1 ,int x2, int y2, Color color)
    {

        //Es igual que el básico pero con casos de evaluación en 
        //la diferencia de x y
        int dx = x2 - x1 ;
    	int dy = y2 - y1 ;
        
    	float y = 0 ;
        
    	if(dx != 0){
            //Si es diferente m y b se calculan igual, se itera en x
            float m = ( y2 - y1  ) / ( ( x2 - x1  ) == 0 ? 1 :( x2 - x1  ));
            float b = y1  - (m * x1 );
            
            //Si la diferencia es mayor a 0 se itera desde x1 a x2
            if( dx > 0 )
            {
                for( int x = x1 ; x <= x2; x++ )
                {
                    y = ( m * x ) + b;    
                    putPixel( x, Math.round(y), color );
                } 
            }
            
            //Si la diferencia es menor a 0 se itera desde x2 a x1
            else
            {
                for(int x = x1 ; x >= x2; x--)
                {
                    y = ( m * x ) + b;    
                    putPixel( x, Math.round(y), color );
		          } 
            }
        } else
        {
            //Se itera en y
            float m = ( y2 - y1 ) / ( ( x2 - x1 ) == 0 ? 1 :( x2 - x1 ) );
           
            //De y1 a y2
            if(dy > 0)
            {
                for(y = y1 ; y <= y2; y++)
                {              
                    putPixel(x1 ,Math.round(y), color);
                }
            }
	
            //De y2 a y1
            else
            {
        		for(y = y1 ; y >=y2 ; y--)
        		{              
                    putPixel(x1 ,Math.round(y), color);
        		} 
            }      
        }
	
    }
    
    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }
}

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class DDA extends JFrame
{	
	private BufferedImage buffer;
    private Graphics graPixel;

	public DDA()
	{
		int alto = 500, ancho = 500;

        setTitle("Algoritmo DDA");
        setSize( ancho, alto );
        setLocationRelativeTo( null );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
	}

	public static void main( String[] args )
    {     
    	DDA dda = new DDA();
        dda.drawLine(10, 100, 200, 50, new Color(123, 36, 28));
        dda.drawLine(400, 100, 250, 100, new Color(46, 134, 193));

        dda.drawLine(125, 160, 125, 300, new Color(46, 134, 193));
        dda.drawLine(320, 300, 320, 160, new Color(123, 36, 28));


        dda.drawLine(50, 350, 150, 450, new Color(123, 36, 28));
        dda.drawLine(300, 450, 400, 350, new Color(46, 134, 193));
    }   


    public void drawLine (int x1,int y1,int x2,int y2, Color color)
    {
        //Algoritmo en el que se elimina b 
        //Diferencias
    	int dx = x2 - x1;
		int dy = y2 - y1;
		int steps = 0;

        //Se itera la diferencia del mayor, el redondeo es muy lento
		if ( Math.abs(dx) > Math.abs(dy)) 
            steps = Math.abs(dx);
		else 
            steps = Math.abs(dy);

		int xinc = dx / steps;
		int yinc = dy / steps;

		int x = x1;
		int y = y1;

        //Se crea el primer pixel
		putPixel( Math.round( x ), Math.round( y ), color);

        //Se hacen las iteraciones
		for (int k = 1; k <= steps; k++ )
		{
			x = x + xinc;
		 	y = y + yinc;
		 	putPixel( Math.round( x ), Math.round( y ), color);
		}
    }

    public void putPixel( int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);      
    }

}
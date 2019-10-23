import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Figuras extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public Figuras()
    {
        int ancho = 800, alto = 600;

        setSize( ancho, alto );
        setTitle("Figuras");
        setLocationRelativeTo( null );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        Figuras figura =  new Figuras();
    
        figura.drawLine(75, 80, 155, 160, new Color(0, 153, 0));
        figura.drawLine(155, 160, 370, 160, new Color(0, 102, 204));
        figura.drawLine(705, 80, 625, 160, new Color(0, 102, 204));
        figura.drawLine(625, 160, 410, 160, new Color(0, 153, 0));

        figura.drawCircle(388, 220, 41, new Color(0, 153, 0));
        figura.drawCircle(388, 220, 29, new Color(0, 102, 204));
        figura.drawCircle(388, 220, 17, new Color(0, 153, 0));
        figura.drawCircle(388, 220, 5, new Color(0, 153, 0));

        figura.drawRectangle(100, 180, 340, 400, new Color(0, 153, 0));
        figura.drawRectangle(440, 180, 680, 400, new Color(0, 153, 0));

        figura.drawOval(388, 500, 110, 50, new Color(0, 102, 204));
        figura.drawOval(388, 500, 90, 40, new Color(0, 153, 0));
        figura.drawOval(388, 500, 70, 30, new Color(0, 102, 204));
        figura.drawOval(388, 500, 50, 20, new Color(0, 102, 204));
    }

    //metodos
    public void drawCircle(int x1, int y1, int R, Color color)
    {
        int x = 0;
        int y = 0;
         
        putPixel( x, y+R, color);
        putPixel( x, y-R, color);
        putPixel( x+R, y, color);
        putPixel( x-R, y, color);
        x = 1;
        y = (int)(Math.sqrt(R * R - x * x ) + 0.5);
        
        while( x < y )
        {
            putPixel(x1 + x, y1 + y, color);
            putPixel(x1 + x, y1 - y, color);
            putPixel(x1 - x, y1 + y, color);
            putPixel(x1 - x, y1 - y, color);
            putPixel(x1 + y, y1 + x, color);
            putPixel(x1 + y, y1 - x, color);
            putPixel(x1 - y, y1 + x, color);
            putPixel(x1 - y, y1 - x, color);          
            x+=1;           
            y = (int)(Math.sqrt(R * R - x * x ) + 0.5); 
        } 
        
    }

    public void drawLine (int x1,int y1,int x2,int y2, Color color)
    {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = 0;

        if ( Math.abs(dx) > Math.abs(dy)) 
            steps = Math.abs(dx);
        else 
            steps = Math.abs(dy);

        int xinc = dx / steps;
        int yinc = dy / steps;

        int x = x1;
        int y = y1;
        putPixel( Math.round( x ), Math.round( y ), color);

        for (int k = 1; k <= steps; k++ )
        {
            x = x + xinc;
            y = y + yinc;
            putPixel( Math.round( x ), Math.round( y ), color);
        }
    }

    public void drawOval(int cx, int cy, int rx, int ry, Color color) {

        double lr = ( rx > ry) ? rx : ry ; 
        double max = Math.PI * .5; 

        double x, y, step = 1.0 / lr;

        for (double t = 0; t <= max; t += step)
        {
            x = Math.round(rx * Math.sin(t));
            y = Math.round(ry * Math.cos(t));
            putPixel(cx + (int)x, cy + (int)y, color);
            putPixel(cx - (int)x, cy + (int)y, color);
            putPixel(cx + (int)x, cy - (int)y, color);
            putPixel(cx - (int)x, cy - (int)y, color);
        
        }
    }

    private void putPixel(int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }

    public void drawRectangle(int x1, int y1, int x2, int y2,  Color color) {
        int temp = 0;

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

        for(int x = x1; x <= x2; x++){
            putPixel(x, y1, color); 
            putPixel(x, y2, color); 
        }
        for(int y = y1; y <= y2; y++){
            putPixel(x1, y, color); 
            putPixel(x2, y, color); 
        }
    }
}

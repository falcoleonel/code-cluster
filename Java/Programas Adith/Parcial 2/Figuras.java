import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Figuras extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;

    public Figuras()
    {
        int ancho = 500, alto = 500;

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

        figura.drawLine(25, 140, 105, 220, new Color(46, 134, 193));
        figura.drawLine(25, 140, 105, 220, new Color(46, 134, 193));
        figura.drawLine(125, 180, 205, 180, new Color(123, 36, 28 ));
        figura.drawLine(225, 220, 305, 140, new Color(46, 134, 193));
        figura.drawLine(405, 180, 325, 180,new Color(123, 36, 28 ));

        figura.drawCircle(65, 370, 41, new Color(46, 134, 193));
        figura.drawCircle(65, 370, 29, new Color(123, 36, 28 ));
        figura.drawCircle(65, 370, 17, new Color(46, 134, 193));
        figura.drawCircle(65, 370, 5, new Color(123, 36, 28 ));

        figura.drawRectangle(130, 340, 230, 400, new Color(46, 134, 193));
        figura.drawRectangle(150, 360, 210, 380, new Color(123, 36, 28 ));

        figura.drawOval(325, 370, 68, 35, new Color(46, 134, 193));
        figura.drawOval(325, 370, 57, 25, new Color(123, 36, 28 ));
        figura.drawOval(325, 370, 46, 15, new Color(46, 134, 193));
        figura.drawOval(325, 370, 34, 5, new Color(123, 36, 28 ));

    }

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

        double lr = ( rx > ry) ? rx : ry ; //El radio más largo
        double max = Math.PI * .5; //Límite de t pi/5 (45)

        double x, y, step = 1.0 / lr; //Pasos 1/radio más largo

        for (double t = 0; t <= max; t += step)
        {
            //incremento del ángulo suficientemente pequeño
            //para evitar los huecos
            x = Math.round(rx * Math.sin(t));
            y = Math.round(ry * Math.cos(t));
            // Cuadrantes
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


}
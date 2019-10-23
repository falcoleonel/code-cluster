import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class RecorteCirculo extends JFrame
{   
    private BufferedImage buffer;
    private Graphics graPixel;
    int []area = {-1,-1, -1,-1};

    public RecorteCirculo()
    {
        int ancho = 800, alto = 600;

        setSize( ancho, alto );
        setTitle("Recorte circulo");
        setLocationRelativeTo( null );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main( String[] args)
    {
        RecorteCirculo circulo =  new RecorteCirculo();

        circulo.drawRectangle(150, 150, 1,5,new Color(238, 238, 238)); 
        //area delimitada
        circulo.drawRectangle(150, 150, 650, 450,new Color(0, 102, 204)); 
        circulo.setArea(150, 150, 650, 450);

        circulo.drawCircle(400, 300, 80, new Color(0, 102, 204)); 
        circulo.drawCircle(400, 300, 45, new Color(0, 102, 204)); 
        circulo.drawCircle(400, 300, 145, new Color(0, 102, 204)); 
        //fuera del area delimitada
        circulo.drawCircle(400, 300, 200, new Color(0, 153, 0));
        circulo.drawCircle(400, 300, 260, new Color(0, 153, 0));
    }


    public void drawCircle(int h, int k, double r, Color color)
    {
        //Punto  Medio
        int x = 0, y = (int)r;
        double p = 5d/4d - r; 
        
        if (r % 1 == 0) p = 1 - r; //si R es entero, p0 = 1 - r
        while( x < y)
        {
            if( x + h > area[0] && x + h < area[1] && y + k > area[2] && y + k < area[3] ) putPixel(  x + h, y + k, color);
            if( -x + h > area[0] && -x + h < area[1] && y + k > area[2] && y + k < area[3] ) putPixel( - x + h, y + k, color);
            if( x + h > area[0] && x + h < area[1] && -y + k > area[2] && -y + k < area[3] ) putPixel(  x + h, -y + k, color);
            if( -x + h > area[0] && -x + h < area[1] && -y + k > area[2] && -y + k < area[3] ) putPixel( - x + h, -y + k, color);
            if( y + h > area[0] && y + h < area[1] && x + k > area[2] && x + k < area[3] ) putPixel( y + h, x + k, color);
            if( -y + h > area[0] && -y + h < area[1] && x + k > area[2] && x + k < area[3] ) putPixel( -y + h, x + k, color);
            if( y + h > area[0] && y + h < area[1] && -x + k > area[2] && -x + k < area[3] ) putPixel( y + h, -x + k, color);
            if( -y + h > area[0] && -y + h < area[1] && -x + k > area[2] && -x + k < area[3] ) putPixel( -y + h, -x + k, color);

            if(p < 0)
            {
                p = p + ( 2 * x ) + 3;
            } else
            {
                p = p + ( 2 * ( x - y ) ) + 5;
                y--;
            }              
            x++;
        }
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

    public void setArea(int x1, int y1, int x2, int y2) {
        this.area[0] = x1;
        this.area[1] = x2;
        this.area[2] = y1;
        this.area[3] = y2;
    }

    private void putPixel( int x, int y, Color color)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);        
    }
}

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class CartecianasCirculo extends JFrame
{
    private Graphics graPixel;
    private BufferedImage buffer;
    
    public CartecianasCirculo() 
    {       
        int ancho = 500, alto = 500;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Modelo CartecianasCirculo");
        setSize( ancho, alto );
        setResizable( false );
        setLocationRelativeTo( null );
        setVisible( true );

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

    }   


   
    public static void main(String[] args) 
    {
        CartecianasCirculo circulo = new CartecianasCirculo();
       
        circulo.drawCircle(100, 100, 50, new Color(238, 238, 238 ));
        circulo.drawCircle(100, 100, 50, new Color(123, 36, 28 ));
        circulo.drawCircle(200, 200, 130, new Color(46, 134, 193));
        circulo.drawCircle(300, 300, 80, new Color(123, 36, 28));
        circulo.drawCircle(400, 400, 150, new Color(46, 134, 193));
    }
   
    private void putPixel(int x, int y, Color color)
    {

        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);  
    }
    
    public void drawCircle(int cx, int cy, int radio, Color color) {
        float dif, step;

        radio = Math.abs(radio);
        step = 1 / (float) radio;

        //Formula simple del teorema de pitagoras, en base a catetos 
        //se obtiene hipotenusa
        //Lo malo es el cuadrado

        for (float x = cx - radio; x <= cx + radio; x += step) {

            dif = Math.sqrt(Math.pow(radio, 2) - Math.pow(x - cx, 2));

            putPixel((int) x, (int) Math.round(cy + dif), color); 
            putPixel((int) x, (int) Math.round(cy - dif), color); 
        }
    }
   
}

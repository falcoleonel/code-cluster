package rectangulo;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;


public class Rectangulo extends JFrame
{
    private Imagen img;
    public Rectangulo()
    {
        setSize(500,500);
        setTitle("Pedro");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Crear imagen
        img=new Imagen(500,500);
        img.dibujarRectangulo(100,100, 200, 400, Color.GREEN);
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(img,0,0,this);
    }
    public static void main(String[] args)
    {
        Rectangulo rectangulo = new Rectangulo();
    }
}
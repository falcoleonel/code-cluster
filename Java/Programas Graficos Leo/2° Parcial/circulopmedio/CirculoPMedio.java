package circulopmedio;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;


public class CirculoPMedio extends JFrame
{
    private Imagen img;
    public CirculoPMedio()
    {
        setSize(500,500);
        setTitle("Pedro");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Crear imagen
        img = new Imagen(500, 500);
        img.dibujarCirculoPMedio(150, 150, 50.5, Color.GREEN);
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(img,0,0,this);
    }
    public static void main(String[] args)
    {
        CirculoPMedio circuloPMedio = new CirculoPMedio();
    }
}
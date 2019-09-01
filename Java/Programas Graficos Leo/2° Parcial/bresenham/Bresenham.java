package bresenham;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import bresenham.Imagen;

public class Bresenham extends JFrame
{
    private Imagen img;
    public Bresenham()
    {
        setSize(500,500);
        setTitle("Bresinn");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Crear imagen
        img=new Imagen(500,500);
        img.dibujarBresenham(100,100, 200, 400, Color.GREEN);
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(img,0,0,this);
    }
    public static void main(String[] args)
    {
        Bresenham lineaBresenham= new Bresenham();
    }
}
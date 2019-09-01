package simetriaocho;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;


public class SimetriaOcho extends JFrame
{
    private Imagen img;
    public SimetriaOcho()
    {
        setSize(500,500);
        setTitle("Pedro");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Crear imagen
        img = new Imagen(500, 500);
        img.dibujarSimetriaOcho(250, 250, 50, Color.GREEN);
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(img,0,0,this);
    }
    public static void main(String[] args)
    {
        SimetriaOcho simetriaocho = new SimetriaOcho();
    }
}
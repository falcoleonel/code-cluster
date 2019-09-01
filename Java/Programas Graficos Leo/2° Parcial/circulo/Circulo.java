package circulo;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import circulo.Imagen;

public class Circulo extends JFrame
{
    private Imagen img;
    public Circulo()
    {
        setSize(500,500);
        setTitle("Pedro");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Crear imagen
        img=new Imagen(500,500);
        img.dibujarCirculo(100,150, 150, 150, Color.GREEN);
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(img,0,0,this);
    }
    public static void main(String[] args)
    {
        Circulo circulo = new Circulo();
    }
}
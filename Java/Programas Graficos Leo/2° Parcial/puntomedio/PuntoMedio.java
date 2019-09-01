package puntomedio;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import puntomedio.Imagen;


public class PuntoMedio extends JFrame
{
    private Imagen img;
    public PuntoMedio()
    {
        setSize(500,500);
        setTitle("Pedro");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Crear imagen
        img=new Imagen(500,500);
        img.dibujarPuntoMedio(100,100, 200, 400, Color.GREEN);
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(img,0,0,this);
    }
    public static void main(String[] args)
    {
        PuntoMedio lineaPuntoMed= new PuntoMedio();
    }
}
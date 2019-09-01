package lineamodmejorado;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import lineamodmejorado.Imagen;

public class ModeloMejorado extends JFrame
{
    private Imagen img;
    public ModeloMejorado()
    {
        setSize(500,500);
        setTitle("Dale");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Crear imagen
        img=new Imagen(500,500);
        
        img.dibujarLineaMejorada(100,50,200,150,Color.GREEN);
        img.dibujarLineaMejorada(100,150,100,250,Color.GREEN);
        img.dibujarLineaMejorada(100,350,200,250,Color.GREEN);
        img.dibujarLineaMejorada(100,450,200,450,Color.GREEN);

        img.dibujarLineaMejorada(400,50,300,150,Color.GREEN);
        img.dibujarLineaMejorada(400,150,400,250,Color.GREEN);
        img.dibujarLineaMejorada(400,350,300,250,Color.GREEN);
        img.dibujarLineaMejorada(400,450,300,450,Color.GREEN);
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(img,0,0,this);
    }
    public static void main(String[] args)
    {
        ModeloMejorado lineamejorada= new ModeloMejorado();
    }
}
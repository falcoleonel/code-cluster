package t_lineas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class Ventana extends JFrame{
    
    int x1,x2,y1,y2;
    
    public Imagen img;

    public Ventana(String[] args){
        //Crear ventana
        setSize(500,500);
        setTitle("Un Pixel");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Crear imagen
        img = new Imagen(500,500);
        //img.dibujarPxl(250, 250, Color.GREEN);
        //img.drawLine(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Color.ORANGE);
        img.drawLine(100, 100, 300, 300, Color.ORANGE,Integer.parseInt(args[0]),Integer.parseInt(args[1]));
    }
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
    /*
    
        @Override
    public static void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        System.out.println(x+","+y);//these co-ords are relative to the component
    }
    @Override
    public void mouseDragged(MouseEvent e) {
     x2=e.getX();
     y2=e.getY();
     repaint();
     p.repaint();
    }*/
   

    public static void main(String[] args) {
        System.out.println("abriendo ventana....");
        Ventana ventana = new Ventana(args);
    }
}
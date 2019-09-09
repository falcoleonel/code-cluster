

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;

public class Graficas3Rotacion3D extends JFrame implements KeyListener {
    private BufferedImage buffer;
    private ArrayList<Point3D> cubo, cubo2;
    private Point3D coord, dist;
    private int grados;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Graficas3Rotacion3D();
    }
    
     private Graficas3Rotacion3D (){
        super("Rotacion");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        this.setVisible(true);
        this.addKeyListener(this);
        buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Pixel admin = new Pixel(buffer);
        coord = new Point3D(-20, -20, -0);
        dist = new Point3D(50, 50, 50);
        cubo = Dibujo.getPoints(coord, dist);
        cubo2 = Dibujo.getPoints(coord, dist);
        grados = 0;
        repaint();
    }

   

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
    	if(e.getKeyCode()==37)//izq
        {
            grados+=10;
            cubo2 = Point3D.rotacionX(cubo, grados);
        }
        else if(e.getKeyCode()==39)//der
        {
            grados-=10;
            cubo2 = Point3D.rotacionX(cubo, grados);
        }
        else if(e.getKeyCode()==38)//arr
        {
            grados -=10;
            cubo2 = Point3D.rotacionY(cubo, grados);
        }
        else if(e.getKeyCode()==40)//abajo
        {
            grados+=10;
            cubo2 = Point3D.rotacionY(cubo, grados);        
        }   
        else if(e.getKeyCode()==83)//at
        {
            grados-=10;
            cubo2 = Point3D.rotacionZ(cubo, grados);       
        }   
        else if(e.getKeyCode()==90)//ade
        {
            grados+=10;
            cubo2 = Point3D.rotacionZ(cubo, grados);       
        }  
        repaint();
    }

    public void keyReleased(KeyEvent e){}
    
     public void paint(Graphics g){
        Pixel.fondo(Color.white);
        Dibujo.CuboRotado(cubo2, Color.GREEN);
        g.drawImage(buffer, 0, 0, this);
    }
}

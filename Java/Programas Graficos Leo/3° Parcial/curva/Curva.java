package curva;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.*;

import javax.lang.model.util.ElementScanner6;
import javax.swing.*;

public class Curva extends JFrame implements ActionListener,KeyListener
{
    Imagen img,bg;
    Dimension dimension;
    Timer timer;
    Boolean bnd=false,rotar=false;
    double xc,yc,zc,distP=.00000;    
    double z1=25,z2=-25,anguloy=0;
    double xg,yg,zg;
    static Vertices vertices;
    static Coordenadas perspectiva;

    public Curva()
    {
        timer = new Timer(80, this);
        timer.start();
        dimension= new Dimension(500,500);
        setSize((int)dimension.getWidth(),(int)dimension.getHeight());
        setTitle("Hector");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.black);
        
        //Crear imagen
        img = new Imagen(this.getWidth(),this.getHeight());

        //Configurar perspectiva
        perspectiva=new Coordenadas();
        perspectiva.x=250;
        perspectiva.y=250;
        perspectiva.z=250;
    
        xc=(double)perspectiva.x;
        yc=(double)perspectiva.y;
        zc=(double)perspectiva.z;

        //Configurar Vertices
        vertices=new Vertices();

        setVisible(true);

    }
    public void paint(Graphics g)
    {
        Graphics2D g2dimg=img.createGraphics();
        g2dimg.clearRect(0, 0, dimension.width, dimension.height);
        
        Graphics2D g2d = (Graphics2D) g;
        ConstruirCurva(0, 500, Color.CYAN);
        g2d.drawImage(img, 0, 0, Color.BLACK, this);
    }
    public static void main(String[] args)
    {
        Curva curva = new Curva();
        curva.addKeyListener(curva);
        curva.img.dibujarCurva(0, 8, Color.cyan);
        
    }
    public void ConstruirCurva(int inicio,int fin,Color c)
    {
        int x,y,z;
        double t;
        Coordenadas anteriores=new Coordenadas();
        anteriores.x=(int)xc;
        anteriores.y=(int)yc;
        anteriores.z=(int)zc;
        for(double i=(double)inicio;i<fin;)
        {
            t=i*Math.PI;
            x=(int)(anteriores.x+(Math.cos(t)+distP));
            y=(int)(anteriores.y+(Math.sin(t)+distP));
            z=(int)(anteriores.z+(t)+distP);
            img.dibujarLinea(anteriores.x, anteriores.y, x, y, c);
            
            anteriores.x=x;
            anteriores.y=y;
            anteriores.z=z;
            i=i+.5;//(1/2);
        }
    }
    public void actionPerformed(ActionEvent e)
    {  
        if(!rotar)
            return;
        if(anguloy==360)
            anguloy=0;
        anguloy=anguloy+10;
        repaint();
    }
    public void keyPressed(KeyEvent e) 
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                yc=yc-10;
            break;
            case KeyEvent.VK_DOWN:
                yc=yc+10;
            break;
            case KeyEvent.VK_LEFT:
                xc=xc-10;
            break;
            case KeyEvent.VK_RIGHT:
                xc=xc+10;
            break;
            case KeyEvent.VK_P:
                if(bnd)
                    bnd=false;
                else
                    bnd=true;
            break;
            case KeyEvent.VK_E:
                distP=distP+0.5;
            break;
            case KeyEvent.VK_D:
                distP=distP-0.5;
            break;
            case KeyEvent.VK_R:
            if(!rotar)
            {
                rotar=true;
                //if(anguloy==360)
                //    anguloy=0;
                //anguloy+=10;
            }
            else
            {
                anguloy=0;
                rotar=false;
            }
            break;
            default:
            break;
        }
        repaint();
    }
 
    public void keyReleased(KeyEvent e) {}
 
    public void keyTyped(KeyEvent e) {}

}
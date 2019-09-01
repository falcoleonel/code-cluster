package proyecto;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.*;

import javax.swing.*;

public class Proyecto extends JFrame implements ActionListener,MouseListener,MouseMotionListener
{
    Imagen img,bg;
    Timer timer;
    int w=0,h=0;
    int x=50,y=50,difxy=1,paso=1;
    int bgColor;

    double r=50;
    double scale=1;
    double delta=0.09;

    public Proyecto()
    {
        bgColor=0;
        timer = new Timer(8, this);
        timer.start();
        setSize(500,500);
        setTitle("Oswaldo");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.black);
        //Crear imagen
        img = new Imagen(110,110);
        bg=new Imagen(this.getWidth(),this.getHeight());
        Graphics2D graphics= img.createGraphics();
        Graphics2D graphics2d=bg.createGraphics();
        graphics.setComposite(AlphaComposite.Clear);
        graphics2d.setColor(Color.black);
        graphics2d.fillRect(0, 0,img.getWidth(),img.getHeight());
        //img.dibujarCirculoPolar(50,50,50, Color.blue);
        img.dibujarCirculoPMedio(50,50, r, Color.blue);
        img.dibujarCirculoPMedio(50, 50, r-5, Color.cyan);
        img.dibujarCirculoPMedio(50, 50, r-10, Color.green);
        img.dibujarCirculoPMedio(50, 50, r-15, Color.red);
        img.dibujarCirculoPMedio(50, 50, r-20, Color.black);

    }
    public void paint(Graphics g)
    {
        //g.drawImage(img, w, h, this);
        //g.drawImage(img, 500-w, 500-h, this);
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(bg, 0,0,Color.BLACK,this);
        if(bgColor==0)
        {
            g2d.drawImage(bg, 0,0,Color.BLACK,this);
        }
        else
        {
            //g2d.drawImage(bg, 0,0,Color.BLACK,this);
            //g2d.translate(w/2, h/2);
            //g2d.scale(scale,scale);
            //g2d.drawImage(img, 250, 250, this);
            g2d.drawImage(img, w, h, this);
            g2d.drawImage(img,400-w, 400-h, this);
        }
        
        bgColor=1;
    }
    public static void main(String[] args)
    {
        Proyecto proyecto = new Proyecto();
        proyecto.addMouseListener(proyecto);
        proyecto.addMouseMotionListener(proyecto);
        
    }
    public void actionPerformed(ActionEvent e)
    {
        if(scale>1)
        delta= -delta;
        if(scale<.5)
        delta= -delta;
        scale += delta;
        if(x>400||x<50)
        difxy= -difxy;
        x=x -difxy;
        y=y-difxy;
        if(paso==1)
        {
            h=h+1;
            w=w+1;
            if(w==400&&h==400)
            {
                paso=2;
            }
        }
        else if(paso==2)
        {
            h=h-1;
            if(h==0)
            {
                paso=3;
            }
        }
        else if(paso==3)
        {
            w=w-1;
            h=h+1;
            if(w==0)
            {
                paso=4;
            }
        }
        else if(paso==4)
        {
            h=h-1;
            if(h==0)
            {
                paso=1;
            }
        }
        repaint();
    }
    public void mousePressed(MouseEvent e) 
    { 
    } 
    public void mouseClicked(MouseEvent e)
    { 
        bgColor=0;
        w=0;
        h=0; 
        paso=1;
    }
    public void mouseExited(MouseEvent e) 
    { }
    public void mouseEntered(MouseEvent e)
    { }
    public void mouseReleased(MouseEvent e)
    {  }
    public void mouseDragged(MouseEvent e)
    {  }
    public void mouseMoved(MouseEvent e)
    {  }
}
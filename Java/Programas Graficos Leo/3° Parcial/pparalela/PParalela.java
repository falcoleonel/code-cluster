package pparalela;

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

public class PParalela extends JFrame implements ActionListener,KeyListener
{
    Imagen img,bg;
    Dimension dimension;
    Timer timer;
    Boolean bnd=false,rotar=false;
    double xc,yc,zc,distP=25;    
    double z1=25,z2=-25,anguloy=0;
    double xg,yg,zg;
    static Vertices vertices;
    static Coordenadas perspectiva;

    public PParalela()
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
        ConstruirCubo();
        img.dibujarCubo(vertices, Color.green);
        g2d.drawImage(img, 0, 0, Color.BLACK, this);
    }
    public static void main(String[] args)
    {
        PParalela pparalela = new PParalela();
        pparalela.addKeyListener(pparalela);
        pparalela.img.dibujarCubo(vertices,Color.green);
        
    }
    public void ConstruirCubo()
    {
        if(bnd)
        {
            CuboPerspectiva();
            GirarY(anguloy);
            GirarZ(anguloy);
            
        }
        else
        {
            CuboParalelo();
            GirarY(anguloy);
            GirarZ(anguloy);
        }
    }
    public void CuboParalelo()
    {
        vertices.v1.x=(int)((xc-distP)+(z1/zc));
        vertices.v1.y=(int)((yc-distP)+(z1/zc));
        vertices.v1.z=(int)((zc+distP)+((vertices.v1.x-(xc-distP))/vertices.v1.x));
        vertices.v2.x=(int)((xc+distP)+(z1/zc));
        vertices.v2.y=(int)((yc-distP)+(z1/zc));
        vertices.v2.z=(int)((zc+distP)+((vertices.v2.x-(xc+distP))/vertices.v2.x));
        vertices.v3.x=(int)((xc+distP)+(z1/zc));
        vertices.v3.y=(int)((yc+distP)+(z1/zc));
        vertices.v3.z=(int)((zc+distP)+((vertices.v3.x-(xc+distP))/vertices.v3.x));
        vertices.v4.x=(int)((xc-distP)+(z1/zc));
        vertices.v4.y=(int)((yc+distP)+(z1/zc));
        vertices.v4.z=(int)((zc+distP)+((vertices.v4.x-(xc-distP))/vertices.v4.x));

        vertices.v5.x=(int)((xc-distP)+(z2/zc));
        vertices.v5.y=(int)((yc-distP)+(z2/zc));
        vertices.v5.z=(int)((zc-distP)+((vertices.v5.x-(xc-distP))/vertices.v5.x));
        vertices.v6.x=(int)((xc+distP)+(z2/zc));
        vertices.v6.y=(int)((yc-distP)+(z2/zc));
        vertices.v6.z=(int)((zc-distP)+((vertices.v6.x-(xc+distP))/vertices.v6.x));
        vertices.v7.x=(int)((xc+distP)+(z2/zc));
        vertices.v7.y=(int)((yc+distP)+(z2/zc));
        vertices.v7.z=(int)((zc-distP)+((vertices.v7.x-(xc+distP))/vertices.v7.x));
        vertices.v8.x=(int)((xc-distP)+(z2/zc));
        vertices.v8.y=(int)((yc+distP)+(z2/zc));
        vertices.v8.z=(int)((zc-distP)+((vertices.v8.x-(xc-distP))/vertices.v8.x));
    }
    public void CuboPerspectiva()
    {   
        vertices.v1.x=(int)((xc-distP)+((-distP)*((z1)/(zc-z1))));
        vertices.v1.y=(int)((yc-distP)+((-distP)*((z1)/(zc-z1))));
        vertices.v1.z=(int)((zc+distP));
        vertices.v2.x=(int)((xc+distP)+((+distP)*((z1)/(zc-z1))));
        vertices.v2.y=(int)((yc-distP)+((-distP)*((z1)/(zc-z1))));
        vertices.v2.z=(int)((zc+distP));
        vertices.v3.x=(int)((xc+distP)+((+distP)*((z1)/(zc-z1))));
        vertices.v3.y=(int)((yc+distP)+((+distP)*((z1)/(zc-z1))));
        vertices.v3.z=(int)((zc+distP));
        vertices.v4.x=(int)((xc-distP)+((-distP)*((z1)/(zc-z1))));
        vertices.v4.y=(int)((yc+distP)+((+distP)*((z1)/(zc-z1))));
        vertices.v4.z=(int)((zc+distP));

        vertices.v5.x=(int)((xc-distP)+((-distP)*((z2)/(zc-z2))));
        vertices.v5.y=(int)((yc-distP)+((-distP)*((z2)/(zc-z2))));
        vertices.v5.z=(int)((zc-distP));
        vertices.v6.x=(int)((xc+distP)+((+distP)*((z2)/(zc-z2))));
        vertices.v6.y=(int)((yc-distP)+((-distP)*((z2)/(zc-z2))));
        vertices.v6.z=(int)((zc-distP));
        vertices.v7.x=(int)((xc+distP)+((+distP)*((z2)/(zc-z2))));
        vertices.v7.y=(int)((yc+distP)+((+distP)*((z2)/(zc-z2))));
        vertices.v7.z=(int)((zc-distP));
        vertices.v8.x=(int)((xc-distP)+((-distP)*((z2)/(zc-z2))));
        vertices.v8.y=(int)((yc+distP)+((+distP)*((z2)/(zc-z2))));
        vertices.v8.z=(int)((zc-distP));
    }
    public void CalcGiroY(Coordenadas v,double radianes)
    {
        xg=(v.x-xc)*(Math.cos(radianes))-(v.z-zc)*(Math.sin(radianes));
        yg=v.y-yc;
        zg=(v.x-xc)*(Math.sin(radianes))+(v.z-zc)*(Math.cos(radianes));
    }
    public void CalcGiroZ(Coordenadas v,double radianes)
    {
        xg=(v.x-xc)*(Math.cos(radianes))-(v.y-yc)*(Math.sin(radianes));
        yg=(v.x-xc)*(Math.sin(radianes))+(v.y-yc)*(Math.cos(radianes));
        zg=v.z-zc;
    }
    public void GirarY(Double anguloLocal)
    {
        double radianes=Math.toRadians(anguloLocal);
        //xc=perspectiva.x*(Math.cos(radianes))-perspectiva.x*(Math.sin(radianes));
        //yc=perspectiva.y*(Math.sin(radianes))+perspectiva.y*(Math.cos(radianes));
        CalcGiroY(vertices.v1,radianes);
        vertices.v1.x=(int)(xg+xc);
        vertices.v1.y=(int)(yg+yc);
        vertices.v1.z=(int)(zg+zc);
        CalcGiroY(vertices.v2,radianes);
        vertices.v2.x=(int)(xg+xc);
        vertices.v2.y=(int)(yg+yc);
        vertices.v2.z=(int)(zg+zc);
        CalcGiroY(vertices.v3,radianes);
        vertices.v3.x=(int)(xg+xc);
        vertices.v3.y=(int)(yg+yc);
        vertices.v3.z=(int)(zg+zc);
        CalcGiroY(vertices.v4,radianes);
        vertices.v4.x=(int)(xg+xc);
        vertices.v4.y=(int)(yg+yc);
        vertices.v4.z=(int)(zg+zc);
//
        CalcGiroY(vertices.v5,radianes);
        vertices.v5.x=(int)(xg+xc);
        vertices.v5.y=(int)(yg+yc);
        vertices.v5.z=(int)(zg+zc);
        CalcGiroY(vertices.v6,radianes);
        vertices.v6.x=(int)(xg+xc);
        vertices.v6.y=(int)(yg+yc);
        vertices.v6.z=(int)(zg+zc);
        CalcGiroY(vertices.v7,radianes);
        vertices.v7.x=(int)(xg+xc);
        vertices.v7.y=(int)(yg+yc);
        vertices.v7.z=(int)(zg+zc);
        CalcGiroY(vertices.v8,radianes);
        vertices.v8.x=(int)(xg+xc);
        vertices.v8.y=(int)(yg+yc);
        vertices.v8.z=(int)(zg+zc);
    }
    public void GirarZ(Double anguloLocal)
    {
        double radianes=Math.toRadians(anguloLocal);
        //xc=perspectiva.x*(Math.cos(radianes))-perspectiva.x*(Math.sin(radianes));
        //yc=perspectiva.y*(Math.sin(radianes))+perspectiva.y*(Math.cos(radianes));
        CalcGiroZ(vertices.v1,radianes);
        vertices.v1.x=(int)(xg+xc);
        vertices.v1.y=(int)(yg+yc);
        vertices.v1.z=(int)(zg+zc);
        CalcGiroZ(vertices.v2,radianes);
        vertices.v2.x=(int)(xg+xc);
        vertices.v2.y=(int)(yg+yc);
        vertices.v2.z=(int)(zg+zc);
        CalcGiroZ(vertices.v3,radianes);
        vertices.v3.x=(int)(xg+xc);
        vertices.v3.y=(int)(yg+yc);
        vertices.v3.z=(int)(zg+zc);
        CalcGiroZ(vertices.v4,radianes);
        vertices.v4.x=(int)(xg+xc);
        vertices.v4.y=(int)(yg+yc);
        vertices.v4.z=(int)(zg+zc);
//
        CalcGiroZ(vertices.v5,radianes);
        vertices.v5.x=(int)(xg+xc);
        vertices.v5.y=(int)(yg+yc);
        vertices.v5.z=(int)(zg+zc);
        CalcGiroZ(vertices.v6,radianes);
        vertices.v6.x=(int)(xg+xc);
        vertices.v6.y=(int)(yg+yc);
        vertices.v6.z=(int)(zg+zc);
        CalcGiroZ(vertices.v7,radianes);
        vertices.v7.x=(int)(xg+xc);
        vertices.v7.y=(int)(yg+yc);
        vertices.v7.z=(int)(zg+zc);
        CalcGiroZ(vertices.v8,radianes);
        vertices.v8.x=(int)(xg+xc);
        vertices.v8.y=(int)(yg+yc);
        vertices.v8.z=(int)(zg+zc);
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
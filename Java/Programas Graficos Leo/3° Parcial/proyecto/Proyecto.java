package proyecto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Thread;
import javax.swing.*;

public class Proyecto extends JFrame implements Runnable,ActionListener,KeyListener
{
    private Imagen img;
    private Timer timer;
    private Graphics graPixel;
    private int x,y,z;
    private double angx,angy,angz,dist;
    private boolean bndx,bndy,bndz;
    public Proyecto()
    {
        timer = new Timer(80, this);
        timer.start();
        setTitle("Perez");
        setSize(1000, 1000);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        img = new Imagen(1000,1000);
        graPixel = (Graphics2D) img.createGraphics();    
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //Thread thr = new Thread(this);
        //thr.start();
        x=500;
        y=500;
        z=100;
        dist=5;
        setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        Dimension d = getSize();
        Graphics2D g2d= (Graphics2D) img.getGraphics();
        g2d.clearRect(0, 0, d.width, d.height);
        dibujarSuperficie(x, y, z, 0, Color.green);
        g2d = (Graphics2D)g;
        g2d.clearRect(0, 0, d.width, d.height);
        g2d.drawImage(img,0,0,Color.BLACK,this);
        System.out.println("\nx:"+x+"\ny:"+y+"\nangz:"+angz+"\nangy:"+angy);
    }
    public static void main(String[] args)
    {
        Proyecto proyecto = new Proyecto();
    }

    //#region Métodos

    public void dibujarSuperficie(int xs,int ys,int zs,int angulo, Color c)
    {
        int xant=0, yant=0;
        for(double i=-90;i<=90;i=i+0.5)
        {
            for (double j=-90 ;j<=90;j=j+1) 
            {
                xs=(int)(j);
                //xs=(int)((i-3*Math.sin(i))*dist);
                //Supuestamente huevoide pero crea una superficie
                ys=(int)(Math.sqrt(Math.abs(1-(i*i*dist/10)-(j*j*dist)/10)));                
                //ys=(int)(Math.sqrt(Math.abs(1+(Math.pow(i,2)/5)-Math.pow(j,2))));
                //xs=(int)((2+Math.cos(i))*Math.cos(j)*dist);
                //ys=(int)((2+Math.cos(i))*Math.sin(j)*dist);
                zs=(int)i;         
                //xant=rotarZx(xant, yant);
                //yant=rotarZy(xant, yant);
                int xaux=xs, yaux=ys;
                xs=rotarZx(xs, ys);
                ys=rotarZy(xaux, ys);
                xs=rotarYx(xs, zs);
                zs=rotarYz(xaux, zs);
                ys=rotarXy(ys, zs);
                zs=rotarXz(yaux, zs);
                //img.dibujarPxl(xs,ys, c);
                if(j>=0)
                {
                    //img.dibujarPxl(x-xs,y-ys, c);
                    //img.dibujarPxl(xorig-xs,xorig-ys, c);
                    img.dibujarLinea(x+xant,y+yant,x+xs, y+ys,new Color(((int)i*350)));
                    //img.dibujarLinea(x+xant,y+yant,x-xs, y-ys, new Color((int)j*350));                   
                    //img.dibujarLinea(x-xant,y-yant,x-xs, y-ys, new Color((int)i*350));
                    //img.dibujarLinea(x-xant,y-yant,x-xs, y-ys, c);
                    //img.dibujarLinea(x+xant,y-yant,x+xs, y-ys, c);
                    //img.dibujarLinea(x-xant,y+yant,x-xs, y+ys, c);
                }
                else
                {
                    img.dibujarLinea(x+xs,y+ys,x+xs, y+ys, new Color(((int)i*350)));
                }
                xant=xs;
                yant=ys;
                
                if(angulo<360)
                    angulo=angulo+(int)i;
                else
                    angulo=0;
            }
            
        }
    }
    //--Rotación Z
    public int rotarZx(int xr,int yr)
    {
        return xr=(int)((double)xr*(Math.cos(Math.toRadians(angz)))-(double)yr*(Math.sin(Math.toRadians(angz))));
    }
    public int rotarZy(int xr,int yr)
    {
        return yr=(int)((double)xr*(Math.sin(Math.toRadians(angz)))+(double)yr*(Math.cos(Math.toRadians(angz))));
    }
    //--Rotacion Y
    public int rotarYx(int xr,int zr)
    {
        return xr=(int)(-(double)zr*(Math.sin(Math.toRadians(angy)))+(double)xr*(Math.cos(Math.toRadians(angy))));
    }
    public int rotarYz(int xr,int zr)
    {
        return zr=(int)((double)zr*(Math.cos(Math.toRadians(angy)))+(double)xr*(Math.sin(Math.toRadians(angy))));
    }
    //--Rotación X
    public int rotarXy(int yr,int zr)
    {
        return yr=(int)(-(double)yr*(Math.cos(Math.toRadians(angx)))-(double)zr*(Math.sin(Math.toRadians(angx))));
    }
    public int rotarXz(int yr,int zr)
    {
        return zr=(int)((double)yr*(Math.sin(Math.toRadians(angy)))+(double)zr*(Math.cos(Math.toRadians(angx))));
    }
    //#endregion Metodos

    //#region Eventos
    public void actionPerformed(ActionEvent e) {
        if(bndz)
        {
            if(angz==360)
                angz=0;
            else
                angz=angz+5;
        }
        if(bndy)
        {
            if(angy==360)
                angy=0;
            else
                angy=angy+5;
        }
        if(bndx)
        {
            if(angx==360)
                angx=0;
            else
                angx=angx+5;
        }
        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        Dimension d=getSize();
        
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if(y-(dist*2)>0)
                    y=y-10;
            break;
            case KeyEvent.VK_DOWN:
                if(y+dist<d.height)
                    y=y+10;
            break;
            case KeyEvent.VK_LEFT:
                if(x-(dist*2)>0)    
                    x=x-10;
            break;
            case KeyEvent.VK_RIGHT:
                if(x+dist<d.width)    
                    x=x+10;
            break;
            case KeyEvent.VK_E:
                if(x<d.width|x-(dist*2)>0|y<d.height|y-(dist*2)>0)    
                    dist=dist+1;
            break;
            case KeyEvent.VK_D:
                dist=dist-1;
            break;
            case KeyEvent.VK_W:
                if(angz==360)
                    angz=0;
                else
                    angz=angz+5;
                if(!bndz)
                    bndz=true;
                else
                    bndz=false;
            break;
            case KeyEvent.VK_S:
                if(angy==360)
                    angy=0;
                else
                    angy=angy+5;
                if(!bndy)
                    bndy=true;
                else
                    bndy=false;
            break;
            case KeyEvent.VK_X:
                if(angx==360)
                    angx=0;
                else
                    angx=angx+5;
                if(!bndx)
                    bndx=true;
                else
                    bndx=false;
            break;
            default:
            break;
        }
        if(x+dist>=d.width|y+dist>=d.height)
        {   if(x+dist>=d.width)
                x=x-10;
            if(y+dist>=d.height)
                y=y-10;
            repaint();
            return;
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }@Override
    public void run() {
    }
}
    //#endregion Eventos
    
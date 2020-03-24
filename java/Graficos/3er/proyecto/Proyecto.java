import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Thread;
import javax.swing.*;

public class Proyecto extends JFrame implements Runnable,ActionListener,KeyListener
{
    private Imagen figura;
    private Timer timer;
    private Graphics graPixel;
    private int x,y,z;
    private double angx,angy,angz,dist;
    private boolean bndx,bndy,bndz;
    public Proyecto()
    {
        timer = new Timer(80, this);
        timer.start();
        setTitle("Proyecto");
        setSize(1000, 1000);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        figura = new Imagen(1000,1000);
        graPixel = (Graphics2D) figura.createGraphics();    
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        x=500;
        y=500;
        z=100;
        dist=5;
        setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        Graphics2D g2d= (Graphics2D) figura.getGraphics();
        g2d.clearRect(0, 0, d.width, d.height);
        dibujarSuperficie(x, y, z, 0);
        g2d = (Graphics2D)g;
        g2d.clearRect(0, 0, d.width, d.height);
        g2d.drawImage(figura,0,0,Color.BLACK,this);
    }
    public static void main(String[] args)
    {
        Proyecto proyecto = new Proyecto();
    }

    public void dibujarSuperficie(int xs,int ys,int zs,int angulo)
    {
        int xant=0, yant=0;
        for(double i=-90;i<=90;i=i+1)
        {
            for (double j=-90 ;j<=90;j=j+1) 
            {
                xs=(int)(j); 
                ys=(int)(Math.sqrt(2000+(i*i*dist/3)+(j*j*dist/3)));      
                zs=(int)i;  

                int xaux=xs, yaux=ys;
                xs=rotarZx(xs, ys);
                ys=rotarZy(xaux, ys);
                xs=rotarYx(xs, zs);
                zs=rotarYz(xaux, zs);
                ys=rotarXy(ys, zs);
                zs=rotarXz(yaux, zs);

                figura.dibujarLinea(x+xs,y+ys,x+xs, y+ys, new Color(52, 37, 164));
            
                xant=xs;
                yant=ys;
            }
            
        }
    }
    //Rotacion Z
    public int rotarZx(int xr,int yr)
    {
        return xr=(int)((double)xr*(Math.cos(Math.toRadians(angz)))-(double)yr*(Math.sin(Math.toRadians(angz))));
    }
    public int rotarZy(int xr,int yr)
    {
        return yr=(int)((double)xr*(Math.sin(Math.toRadians(angz)))+(double)yr*(Math.cos(Math.toRadians(angz))));
    }
    //Rotacion Y
    public int rotarYx(int xr,int zr)
    {
        return xr=(int)(-(double)zr*(Math.sin(Math.toRadians(angy)))+(double)xr*(Math.cos(Math.toRadians(angy))));
    }
    public int rotarYz(int xr,int zr)
    {
        return zr=(int)((double)zr*(Math.cos(Math.toRadians(angy)))+(double)xr*(Math.sin(Math.toRadians(angy))));
    }
    //Rotacion X
    public int rotarXy(int yr,int zr)
    {
        return yr=(int)(-(double)yr*(Math.cos(Math.toRadians(angx)))-(double)zr*(Math.sin(Math.toRadians(angx))));
    }
    public int rotarXz(int yr,int zr)
    {
        return zr=(int)((double)yr*(Math.sin(Math.toRadians(angy)))+(double)zr*(Math.cos(Math.toRadians(angx))));
    }

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
                if(!bndz)
                    bndz=true;
                else
                    bndz=false;
            break;
            case KeyEvent.VK_S:
                if(!bndy)
                    bndy=true;
                else
                    bndy=false;
            break;
            case KeyEvent.VK_X:
                if(!bndx)
                    bndx=true;
                else
                    bndx=false;
            break;
            default:
            break;
        }

        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void run() {
    }
    }

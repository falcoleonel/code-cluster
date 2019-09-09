

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Graficas3CurvaExplicita extends JFrame implements KeyListener{

    private BufferedImage buffer;
    private  ArrayList<Point3D> p = new ArrayList<>();
    private Point3D coord, dist;
    private int grados;
    public double angle = 0;
    
    private Graficas3CurvaExplicita(){
        super("Curva Explicita");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        calcularpuntos();
        Pixel admin = new Pixel(buffer);
        this.setVisible(true);
        addKeyListener(this);
        this.setFocusable(true);
    }
    
    public void calcularpuntos()
    {
       double n,m,x,y,z;
       double limiteSuperior= 2*Math.PI;
       for(x=0; x<= 200; x+=1){
       
          
           y=(Math.sin(x/30))*30;
           z=(Math.sin(x/30)+Math.cos(y/40))*20;
           
           p.add(new Point3D((int) x, (int) y, (int) z));
           
           
       
       
       }
       
       
    }
    

//     double u, v;
//        double x, y, z; 
//        for ( v = -1 * (Math.PI/2) ; v <= Math.PI/2; v+=.005) 
//            {
//        for(u = 0; u <= (2* Math.PI); u+=.005) {
//            
//                x = 50*(2* Math.cos(u));
//                y = 50*(2 *Math.cos(v) );
//                z = 30 *(2 * Math.sin(v));
//            p.add(new Point3D((int)x, (int)z, (int)y));
//            }
//          
//        }
//    
    
    
    public void paint(Graphics g){
       
        Pixel.fondo(Color.WHITE);
       
        Dibujo.CurvaExplicita(p, Color.BLUE);
        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
       
        if(e.getKeyCode() == 38)
        {           
            angle += .08;
           
             for (int i = 0; i < p.size(); i++) {
                 double yn = (p.get(i).y * Math.cos(angle)) - (p.get(i).z * Math.sin(angle));
                 double zn = (p.get(i).y * Math.sin(angle)) + (p.get(i).z* Math.cos(angle));
                 p.get(i).y= (int) yn;
                 p.get(i).z = (int) zn;
                
            }
            repaint();
        }
        
        if(e.getKeyCode() == 40)
        {           
            angle -= .08;
           
             for (int i = 0; i < p.size(); i++) {
                 double yn = (p.get(i).y * Math.cos(angle)) - (p.get(i).z * Math.sin(angle));
                 double zn = (p.get(i).y  * Math.sin(angle)) + (p.get(i).z * Math.cos(angle));
                 p.get(i).y = (int) yn;
                 p.get(i).z= (int) zn;
                
            }
            repaint();
        }
        
         if(e.getKeyCode() == 37)
        {           
            angle += .08;
           
             for (int i = 0; i < p.size(); i++) {
                 double xn = (p.get(i).x* Math.cos(angle)) - (p.get(i).z * Math.sin(angle));
                 double zn = (p.get(i).x * Math.sin(angle)) + (p.get(i).z * Math.cos(angle));
                 p.get(i).x = (int) xn;
                 p.get(i).z = (int) zn;
                
            }
            repaint();
        }
         
         
         if(e.getKeyCode() == 39)
        {           
            angle -= .08;
           
             for (int i = 0; i < p.size(); i++) {
                 double xn = (p.get(i).x * Math.cos(angle)) - (p.get(i).z * Math.sin(angle));
                 double zn = (p.get(i).x  * Math.sin(angle)) + (p.get(i).z * Math.cos(angle));
                 p.get(i).x = (int) xn;
                 p.get(i).z = (int) zn;
                
            }
            repaint();
            
        }
         
          if(e.getKeyCode() == 83)
        {           
            angle -= .08;
           
             for (int i = 0; i < p.size(); i++) {
                 double xn = (p.get(i).x * Math.cos(angle)) - (p.get(i).y * Math.sin(angle));
                 double yn = (p.get(i).y  * Math.cos(angle)) + (p.get(i).x * Math.sin(angle));
                 p.get(i).x = (int) xn;
                 p.get(i).y = (int) yn;
                
            }
            repaint();
            
        }
              if(e.getKeyCode() == 90)
        {           
            angle -= .08;
           
             for (int i = 0; i < p.size(); i++) {
                 double xn = (p.get(i).x * Math.cos(angle)) - (p.get(i).y * Math.sin(angle));
                 double yn = (p.get(i).y  * Math.cos(angle)) + (p.get(i).x * Math.sin(angle));
                 p.get(i).x = (int) xn;
                 p.get(i).y = (int) yn;
                
            }
            repaint();
            
        }
         

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
       public static void main(String[] args) {
        new Graficas3CurvaExplicita();
    }
    
}

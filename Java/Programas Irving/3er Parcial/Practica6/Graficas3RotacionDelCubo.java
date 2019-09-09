import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Graficas3RotacionDelCubo  extends JFrame {

    private BufferedImage buffer;
    private ArrayList<Point3D> cubo, cubo2;
    private Point3D coord, dist;
    private int grados,r,g,b;
    private Color color;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Graficas3RotacionDelCubo();
    }
    
    public Graficas3RotacionDelCubo(){
        super("Rotacion del cubo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        this.setVisible(true);
        buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Pixel admin = new Pixel(buffer);
        
        //coordenadas iniciales 
        coord = new Point3D(-20, -20, -20);
        dist = new Point3D(50, 50, 50);
        cubo = Dibujo.getPoints(coord, dist);
        cubo2 = Dibujo.getPoints(coord, dist);
        grados = 0;
        
        //rgb para dibujar 
        r=25;
        g=25;
        b=255;
        girar();
        
          
    }

    public void girar()
    {
        while(true) {
            
            for(int i = 0; i < 270 ; i++ ) {
                grados +=1;
                cubo2 = Point3D.rotacionZ(cubo, grados);
                try {
                    Thread.sleep (15);
                } catch (Exception e) {}
                if(i<255)
                color=new Color(r,g,b);
                repaint();
            }
            
            
            for(int i = 0; i < 270 ; i++ ) {
                grados -=1;
                cubo2 = Point3D.rotacionX(cubo, grados);
                try {
                    Thread.sleep (15);
                } catch (Exception e) {}
                if(i<255)
                color=new Color(r,g,b);
                repaint();
            }
            
            for(int i = 0; i < 270 ; i++ ) {
                grados -=1;
                cubo2 = Point3D.rotacionY(cubo, grados);
                try {
                    Thread.sleep (15);
                } catch (Exception e) {}
                if(i<255)
                color=new Color(r,g,b);
                repaint();
            }
            
            
            for(int i = 0; i < 270 ; i++ ) {
                grados -=1;
                cubo2 = Point3D.rotacionZ(cubo, grados);
                try {
                    Thread.sleep (15);
                } catch (Exception e) {}
                if(i<255)
                color=new Color(r,g,b);
                repaint();
            }
            
            
            
            for(int i = 0; i < 270 ; i++ ) {
                grados +=1;
                cubo2 = Point3D.rotacionY(cubo, grados);
                try {
                    Thread.sleep (15);
                } catch (Exception e) {}
                if(i<255)
                color=new Color(r,g,b);
                repaint();
            }
           
            for(int i = 0; i < 270 ; i++ ) {
                grados +=1;
                cubo2 = Point3D.rotacionX(cubo, grados);
                try {
                    Thread.sleep (15);
                } catch (Exception e) {}
                if(i<255)
                color=new Color(r,g,b);
                repaint();
            }
            
            
        }
    }

    public void paint(Graphics g){
        Pixel.fondo(Color.RED);
        Dibujo.CuboRotado(cubo2,Color.black);
        g.drawImage(buffer, 0, 0, this);
    }
}

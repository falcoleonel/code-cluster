

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CilindroMov extends JFrame implements KeyListener {
    private BufferedImage buffer;
    private Point3D coord, dist;
    private int grados;
    ArrayList<Point3D> puntos = new ArrayList<>();
    ArrayList<Point3D> puntosRotados = new ArrayList<>();
    


    public static void main(String[] args) {
        new CilindroMov();
    }

    private CilindroMov(){
        super("Cilindro en movimiento");
        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Pixel admin = new Pixel(buffer);
        this.setVisible(true);
        grados=20;
    }

    public void paint(Graphics g){
        double t,j;
        double x, y, z;
        int potencia=2;
        
        double lInf=-0*Math.PI, lSup=1.5*Math.PI, lInf1 = 0*Math.PI, lSup1 = 1.5*Math.PI ;
        
        double cambio=0;
       int h=0;
        while(true)
        {
            puntos = new ArrayList<>();
            Pixel.fondo(Color.GRAY);
            for(t = lInf; t <= lSup; t+=.1) {
                for(j = lInf1; j <= lSup1+20; j+=.1) {
                    x =60 *(2 + Math.pow(Math.cos(cambio+t),potencia))*(Math.cos(cambio+j));
                    y =40 *(2 + Math.pow(Math.cos(cambio+t),potencia))*(Math.sin(cambio+j));
                    z =60 * t;
                    puntos.add(new Point3D((int)x, (int)z, (int)y));
                    x =60 *(2 + Math.pow(Math.cos(cambio+t-.1),potencia))*(Math.cos(cambio+j));
                    y =40 *(2 + Math.pow(Math.cos(cambio+t-.1),potencia))*(Math.sin(cambio+j));
                    z =60 * (t-.1);
                    puntos.add(new Point3D((int)x, (int)z, (int)y));
                }
            }
            
            cambio+=.30;
           // Algoritmos.UnirPuntos(puntos, Color.BLUE);
            Algoritmos.UnirPuntos(puntos, Color.BLUE);
            Algoritmos.UnirPuntos(puntosRotados, Color.BLUE);
            g.drawImage(buffer, 0, 0, this);
            h+=1;
        }
        
    }
     public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
    	if(e.getKeyCode()==37)//izquierda
        {
//            grados+=10;
//            cubo2 = Point3D.rotacionX(cubo, grados);
        }
        else if(e.getKeyCode()==39)//derecha
        {
            grados-=10;
            puntosRotados = Point3D.rotacionX(puntos, grados);
        }
        else if(e.getKeyCode()==38)//arriba
        {
//            grados -=10;
//            cubo2 = Point3D.rotacionY(cubo, grados);
        }
        else if(e.getKeyCode()==40)//abajo
        {
//            grados+=10;
//            cubo2 = Point3D.rotacionY(cubo, grados);        
        }   
        else if(e.getKeyCode()==83)//atras
        {
//            grados-=10;
//            cubo2 = Point3D.rotacionZ(cubo, grados);       
        }   
        else if(e.getKeyCode()==90)//adelante
        {
//            grados+=10;
//            cubo2 = Point3D.rotacionZ(cubo, grados);       
        }  
        repaint();
    }

    public void keyReleased(KeyEvent e){}

}


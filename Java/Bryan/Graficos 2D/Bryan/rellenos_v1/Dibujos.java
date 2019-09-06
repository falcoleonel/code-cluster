package rellenos;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.corba.se.impl.orbutil.graph.GraphImpl;

/**
 * Dibujos
 */
public class Dibujos {

    int X,Y;
    int x,y;
    Color color =new Color(0, 0, 0);
    Imagen imagen;
    Graphics2D g;
    
    public Dibujos(int x, int y) {
        imagen = new Imagen(x, y);
            //Generacion de Imagen
        g = imagen.createGraphics();
        //g.drawImage(imagen, 0, 0,a);
    }

    public void leerCoordenadas(MouseEvent ev) {
        if (!ev.isMetaDown()) {
            X = ev.getX();
            Y = ev.getY();
        }
    }
    /*
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }*/
    public void pintar(MouseEvent ev,int grosor) {
        if (!ev.isMetaDown()) {

        g.drawImage(imagen, 0, 0,((JPanel)ev.getSource()));
            
        //g.setStroke(new BasicStroke(grosor) );
        //g.setColor(color);
        
        int x = ev.getX();
        int y = ev.getY();
        //g.drawLine(X, Y, x, y);
        
        //g.dispose();
        
        X = x;
        Y = y;
        imagen.dibujarPxl(X, Y, color);
        System.out.println(Y);
    }
        else{
        Graphics2D g = ((Graphics2D)((JPanel)ev.getSource()).getGraphics());
        //Graphics2D g = JPanel(ev.getSource())
        g.setStroke(new BasicStroke(grosor) );
        //g.setColor(Color.WHITE);

        int x = ev.getX();
        int y = ev.getY();
        g.drawLine(X, Y, X, Y);

        g.dispose();

        X = x;
        Y = y;
        }
    }
    /*
    public void rellenar() {
        
    }*/
    
}
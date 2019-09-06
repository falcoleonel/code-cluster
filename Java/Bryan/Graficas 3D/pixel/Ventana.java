package pixel;

import java.awt.*;
import javax.swing.*;

public class Ventana extends JFrame{
    
    public Figures2d img;

    public Ventana(int w, int h){
        //Crear ventana
        setSize(w,h);
        setTitle("Figuritas");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Crear imagen
        img = new Figures2d(this.getWidth(),this.getHeight());
    }

    public void paint(Graphics g){
        g.drawImage((Image)img, 0, 0, Color.BLACK,this);
    }
    public void Figures2d() {
        lines();
        circles();
        squares();
        elipses();
    }
    public void lines() {
        //Al derecho
        this.img.drawLine(50, 50, 150, 150, Color.BLUE);
        this.img.drawLine(200, 100, 300, 100, Color.BLUE);
        //Al Reves
        this.img.drawLine(450, 50, 350, 150, Color.RED);
        this.img.drawLine(600, 100, 500, 100, Color.RED);
    }
    public void circles() {
        this.img.drawCirc(100, 300, 15, Color.RED);
        this.img.drawCirc(100, 300, 30, Color.MAGENTA);
        this.img.drawCirc(100, 300, 60, Color.GREEN);
        this.img.drawCirc(100, 300, 90, Color.pink);
        //img.drawElipse(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Color.ORANGE);
    }
    public void squares() {
        this.img.drawRect(250, 250, 450, 350, Color.WHITE);
        this.img.drawRect(425, 325, 275, 275, Color.WHITE);
    }
    public void elipses() {
        this.img.drawElipse(cartesiana_x(600), cartesiana_y(150), 20, 10, Color.BLUE);
        this.img.drawElipse(600, 300, 100, 50, Color.BLUE);
        this.img.drawElipse(600, 300, 60, 30, Color.BLUE);
    }
    public int cartesiana_x(int coordenada_x) {
        return coordenada_x;
    }
    public int cartesiana_y(int coordenada_y) {
        return this.getHeight() - coordenada_y;
    }
    public void clear() {
        this.img=new Figures2d(this.getWidth(),this.getHeight());
    }
    public static void main(String[] args) {
        System.out.println("abriendo ventana....");
        Ventana ventana = new Ventana(1200,650);
        ventana.Figures2d();
        ventana.img.dibujarPxl(600, 300, Color.red);
    }
}
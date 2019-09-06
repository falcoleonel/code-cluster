package figuras;

import java.awt.*;
import javax.swing.*;

public class Ventana extends JFrame{
    
    public Imagen img;

    public Ventana(){
        //Crear ventana
        setSize(850,450);
        setTitle("Figuritas");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Crear imagen
        img = new Imagen(850,450);
        
        figures(img);
    }
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
    public static void main(String[] args) {
        System.out.println("abriendo ventana....");
        Ventana ventana = new Ventana();
    }
    public void figures(Imagen img) {
        lines(img);
        circles(img);
        squares(img);
        elipses(img);
    }
    public void lines(Imagen img) {
        //Al derecho
        img.drawLine(50, 50, 150, 150, Color.BLUE);
        img.drawLine(200, 100, 300, 100, Color.BLUE);
        //Al Reves
        img.drawLine(450, 50, 350, 150, Color.RED);
        img.drawLine(600, 100, 500, 100, Color.RED);
    }
    public void circles(Imagen img) {
        img.drawCirc(100, 300, 15, Color.RED);
        img.drawCirc(100, 300, 30, Color.MAGENTA);
        img.drawCirc(100, 300, 60, Color.GREEN);
        img.drawCirc(100, 300, 90, Color.pink);
        //img.drawElipse(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Color.ORANGE);
    }
    public void squares(Imagen img) {
        img.drawRect(250, 250, 450, 350, Color.WHITE);
        img.drawRect(425, 325, 275, 275, Color.WHITE);
    }
    public void elipses(Imagen img) {
        img.drawElipse(600, 300, 30, 10, Color.GREEN);
        img.drawElipse(600, 300, 50, 20, Color.GREEN);
        img.drawElipse(600, 300, 70, 30, Color.GREEN);
        img.drawElipse(600, 300, 100, 50, Color.GREEN);
    }
}
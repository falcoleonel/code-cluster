package circulo_grosor;

import java.awt.*;
import javax.swing.*;

public class Ventana extends JFrame{
    
    public Imagen img;

    public Ventana(String[] args){
        //Crear ventana
        setSize(500,500);
        setTitle("Un Pixel");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Crear imagen
        img = new Imagen(500,500);
        //img.dibujarPxl(250, 250, Color.GREEN);
        //img.drawCirc(250, 250, 50, Color.ORANGE, Integer.parseInt(args[0]));
        img.drawCirc(250, 250, 50, Color.ORANGE, 10);
    }
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
    public static void main(String[] args) {
        System.out.println("abriendo ventana....");
        Ventana ventana = new ventana(args);
    }
}
package grosor_linea;

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

        img.drawLine(100, 100, 300, 100, Color.ORANGE,Integer.parseInt(args[0]));
        img.drawLine(400, 100, 400, 300, Color.ORANGE,Integer.parseInt(args[0]));
        
        img.drawLine(100, 200, 200, 300, Color.ORANGE,Integer.parseInt(args[0]));
        img.drawLine(100, 450, 425, 425, Color.ORANGE,Integer.parseInt(args[0]));

    }
    //img.dibujarPxl(250, 250, Color.GREEN);
    //img.drawLine(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Color.ORANGE,Integer.parseInt(args[4]),Integer.parseInt(args[5]));
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
    public static void main(String[] args) {
        System.out.println("abriendo ventana....");
        Ventana ventana = new Ventana(args);
    }
}
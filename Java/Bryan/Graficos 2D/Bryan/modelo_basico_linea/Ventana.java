package modelo_basico_linea;

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
        img.drawLine(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Color.ORANGE);
    }
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
    public static void main(String[] args) {
        System.out.println("abriendo ventana....");
        Ventana ventana = new Ventana(args);
    }
}
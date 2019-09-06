package scanline;

import java.awt.*;
import javax.swing.*;

public class Ventana extends JFrame{
    
    public Imagen img;

    public Ventana(){
        //Crear ventana
        setSize(500,500);
        setTitle("Un Pixel");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Crear imagen
        img = new Imagen(500,500);
        //img.dibujarPxl(250, 250, Color.ORANGE);
        img.drawRect(100, 100, 250, 250, Color.ORANGE);
        img.draw_fill_Rect(100, 100, 250, 250, Color.ORANGE);
        img.drawCirc(350, 350, 100, Color.ORANGE);


    }
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
    public static void main(String[] args) {
        System.out.println("abriendo ventana....");
        Ventana ventana = new Ventana();
    }
}
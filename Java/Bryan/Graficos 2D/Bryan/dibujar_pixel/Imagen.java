package dibujar_pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Imagen extends BufferedImage{

    public Imagen (int w, int h){
        super(w,h,BufferedImage.TYPE_INT_RGB);
    }

    public void dibujarPxl(int x, int y, Color c){
        setRGB(x, y, c.getRGB());
    }
    
    //______Modelo Basico________
    public void drawLine(int x0, int y0, int x1, int y1, Color c) {
        //img.drawLine(     50,     250,    450,    250,    Color.ORANGE);
     
        float y = 0;

        float m = (y1 - y0) / ((x1 - x0) == 0 ? 1 : (x1 - x0));
        float b = y0 - (m * x0);
        
        for (int x = x0; x <= x1; x++) {
            y = (m * x) + b; // calculate y
            dibujarPxl(x, Math.round(y), c);
        }
    }
    /*
    public float cal_M(int x0, int y0, int x1, int y1) {
        return (y1 - y0) / ((x1 - x0) <= 0 ? 1 : (x1 - x0));
    }

    public float cal_B(int x0, int y0, int x1, int y1) {
        return y0 - (cal_M(x0, y0, x1, y1) * x0);
    }
    //______Modelo Basico_______*/

}
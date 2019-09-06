package basico_incremental;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.lang.Math;

public class Imagen extends BufferedImage{

    public Imagen (int w, int h){
        super(w,h,BufferedImage.TYPE_INT_RGB);
    }

    public void dibujarPxl(int x, int y, Color c){
        setRGB(x, y, c.getRGB());
    }
    
    //______Modelo Basico_______
    public void drawLine(int x0, int y0, int x1, int y1, Color c) {
        //img.drawLine(     50,     250,    450,    250,    Color.ORANGE);
        int dx = x1-x0;
        int dy = y1-y0;

        float steps = (Math.abs(dx)>Math.abs(dy))?Math.abs(dx):Math.abs(dy);
        System.out.println(steps);

        float xinc = dx/steps;
        float yinc = dy/steps;

        System.out.println("xinc:"+xinc);
        System.out.println("yinc:"+yinc);

        int x = x0;
        int y = y0;

        dibujarPxl(x, y, Color.ORANGE);

        for(int k =1; k<=steps; k++){
            x = Math.round(x+xinc);
            y = Math.round(y+yinc);
            dibujarPxl(x,y, Color.ORANGE);
        }


    }
}
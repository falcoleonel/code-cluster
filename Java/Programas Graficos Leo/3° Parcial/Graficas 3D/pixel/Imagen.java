package pixel;

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

}
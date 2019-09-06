package circulo_p_medio;

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
    
    public void drawCirc(int _x, int _y, int radio, Color c) {
            
            int x, y, p;

                x = 0;
                y = radio;
                p = 1 - radio;

            while (x <= y){
                
                draw_octante(x,y,_x,_y);
    
                x = x + 1;
                if (p < 0)
                  p = p + 2*x + 1;
                else {
                  y = y - 1;
                  p = p + 2*(x - y) + 1;
                }
            }
    }

    public void draw_octante(int x, int y, int _x, int _y) {
                dibujarPxl((_x+x),(_y+y), Color.ORANGE);
                dibujarPxl((_x-x),(_y-y), Color.ORANGE);
                dibujarPxl((_x+y),(_y+x), Color.GREEN);
                dibujarPxl((_x-y),(_y-x), Color.GREEN);
                dibujarPxl((_x-x),(_y+y), Color.RED);
                dibujarPxl((_x+x),(_y-y), Color.RED);
                dibujarPxl((_x-y),(_y+x), Color.MAGENTA);
                dibujarPxl((_x+y),(_y-x), Color.MAGENTA);            
    }
    
    public void drawRect(int x0, int y0, int x1, int y1, Color c) {
        
        drawLine(x0, y0, x0, y1, c);
        drawLine(x0, y0, x1, y0, c);

        drawLine(x1, y0, x1, y1, c);
        drawLine(x0, y1, x1, y1, c);

    }
    public void drawLine(int x0, int y0, int x1, int y1, Color c) {
        
            dibujarPxl(x0, y0, c);

        int dx = Math.abs(x1-x0);
        int dy = Math.abs(y1-y0);

        int[] x ={x0,x1};
        int[] y ={y0,y1};

        if (dx >= dy)
            bresenhamX(x, y, dx, dy, c);
        else
            bresenhamY(x, y, dx, dy, c);
    }
    public void bresenhamX(int[] x,int[] y,int dx,int dy,Color c) {
        int i, j, k;
            i = 2 * dy - dx;
            j = 2 * dy;
            k = 2 * (dy - dx);

            //si !(x0 < x1), invertir X's y Y's
        if (!(x[0] < x[1])) {
            swap(x);
            swap(y);
        }
        
        while(x[0] <= x[1]){
            dibujarPxl(x[0], y[0], c);
                if (i<0)
                    i+=j;
                else{
                    if (y[0] < y[1])
                        ++y[0];
                    else
                        --y[0];
                    i += k;
                }
                ++x[0];
            }
    }
    public void bresenhamY(int[] x,int[] y,int dx,int dy,Color c) {
        int i, j, k;
            i = 2 * dx - dy;
            j = 2 * dx;
            k = 2 * (dx - dy);

            //si !(x0 < x1), invertir X's y Y's
        if (!(y[0] < y[1])) {
            swap(x);
            swap(y);
        }
        
        while(y[0] <= y[1]){
            dibujarPxl(x[0], y[0], c);
                if (i<0)
                    i+=j;
                else{
                    if (x[0] > x[1])
                        --x[0];
                    else
                        ++x[0];
                    i += k;
                }
                ++y[0];
                //dibujarPxl(x[0], y[0], c);   
            }
    }
    public void swap(int[] x) {
        int temp = x[0];
        x[0] = x[1];
        x[1] = temp;
    }
}
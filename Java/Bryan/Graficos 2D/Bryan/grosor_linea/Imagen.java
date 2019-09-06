package grosor_linea;

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
    /**
     * 
     public void drawLine(int x0, int y0, int x1, int y1, Color c,int draw,int noDraw) {
         
         dibujarPxl(x0, y0, c);
         
         int dx = Math.abs(x1-x0);
        int dy = Math.abs(y1-y0);

        int[] x ={x0,x1};
        int[] y ={y0,y1};
        
        if (dx >= dy)
        bresenhamX(x, y, dx, dy, c, draw, noDraw);
        else
        bresenhamY(x, y, dx, dy, c, draw, noDraw);
    }
    */

    public void drawLine(int x0, int y0, int x1, int y1, Color c, int grosor) {
        
            dibujarPxl(x0, y0, c);

        int dx = Math.abs(x1-x0);
        int dy = Math.abs(y1-y0);

        int[] x ={x0,x1};
        int[] y ={y0,y1};

        if (dx >= dy)
            bresenhamX(x, y, dx, dy, c, grosor);
        else
            bresenhamY(x, y, dx, dy, c, grosor);
    }
    /*
    public void bresenhamX(int[] x,int[] y,int dx,int dy,Color c, int draw, int noDraw) {
        int i, j, k;
            i = 2 * dy - dx;
            j = 2 * dy;
            k = 2 * (dy - dx);
        
        int[] a_={0,0};

            //si !(x0 < x1), invertir X's y Y's
        if (!(x[0] < x[1])) {
            swap(x);
            swap(y);
        }
        
        while(x[0] <= x[1]){
        
            partialdraw(x[0],y[0],a_,draw,noDraw,c);

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

    public void bresenhamY(int[] x,int[] y,int dx,int dy,Color c,int draw,int noDraw) {
        int i, j, k;
            i = 2 * dx - dy;
            j = 2 * dx;
            k = 2 * (dx - dy);

            int[] a_={0,0};

            //si !(x0 < x1), invertir X's y Y's
        if (!(y[0] < y[1])) {
            swap(x);
            swap(y);
        }
        
        while(y[0] <= y[1]){
            partialdraw(x[0],y[0],a_,draw,noDraw,c);
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
    */
    public void bresenhamX(int[] x,int[] y,int dx,int dy,Color c,int max) {
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
            
            if (max != 1) {
                for (int w = 0; w < max; w++){
                    dibujarPxl(x[0], y[0]+w, c);
                    dibujarPxl(x[0], y[0]-w, c);
                }
            }else
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
    public void bresenhamY(int[] x,int[] y,int dx,int dy,Color c,int max) {
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
            if (max != 1) {
                for (int w = 0; w < max; w++){
                    dibujarPxl(x[0]+w, y[0], c);
                    dibujarPxl(x[0]-w, y[0], c);
                }
            }else
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

    public void partialdraw(int x,int y,int[] a_,int draw,int noDraw,Color c){
        if (a_[0]<draw) {
            dibujarPxl(x, y, c);
            a_[0]++;
            a_[1]=0;
        }else if (a_[1]<noDraw) {
            a_[1]++;
        } else {
            a_[0]=0;
        }
    }

    public void swap(int[] x) {
        int temp = x[0];
        x[0] = x[1];
        x[1] = temp;
    }
}
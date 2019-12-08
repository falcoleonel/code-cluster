
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.lang.Math.*;

public class Imagen extends BufferedImage
{
    public Imagen(int w, int h)
    {
        super(w,h,BufferedImage.TYPE_INT_ARGB);
    }
    public void dibujarPxl(int x, int y,Color c)
    {
        setRGB(x,y,c.getRGB());
    }
    public void dibujarLinea(int x0, int y0, int x1, int y1, Color c) {
        
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
            }
    }
    public void swap(int[] x) {
        int temp = x[0];
        x[0] = x[1];
        x[1] = temp;
    }
}

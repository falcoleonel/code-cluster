package curva;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.lang.Math.*;

import pparalela.Vertices;

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
            }
    }
    public void swap(int[] x) {
        int temp = x[0];
        x[0] = x[1];
        x[1] = temp;
    }
    // 
    public void dibujarCubo(Vertices vertices,Color c)
    {
        dibujarLinea(vertices.v1.x, vertices.v1.y,vertices.v2.x, vertices.v2.y, c);
        dibujarLinea(vertices.v2.x, vertices.v2.y,vertices.v3.x, vertices.v3.y, c);
        dibujarLinea(vertices.v3.x, vertices.v3.y,vertices.v4.x, vertices.v4.y, c);
        dibujarLinea(vertices.v4.x, vertices.v4.y,vertices.v1.x, vertices.v1.y, c);

        dibujarLinea(vertices.v5.x, vertices.v5.y,vertices.v6.x, vertices.v6.y, c);
        dibujarLinea(vertices.v6.x, vertices.v6.y,vertices.v7.x, vertices.v7.y, c);
        dibujarLinea(vertices.v7.x, vertices.v7.y,vertices.v8.x, vertices.v8.y, c);
        dibujarLinea(vertices.v8.x, vertices.v8.y,vertices.v5.x, vertices.v5.y, c);

        dibujarLinea(vertices.v1.x, vertices.v1.y,vertices.v5.x, vertices.v5.y, c);
        dibujarLinea(vertices.v2.x, vertices.v2.y,vertices.v6.x, vertices.v6.y, c);
        dibujarLinea(vertices.v3.x, vertices.v3.y,vertices.v7.x, vertices.v7.y, c);
        dibujarLinea(vertices.v4.x, vertices.v4.y,vertices.v8.x, vertices.v8.y, c);

        dibujarLinea(vertices.v1.x, vertices.v1.y,vertices.v3.x, vertices.v3.y, c);
        dibujarLinea(vertices.v2.x, vertices.v2.y,vertices.v4.x, vertices.v4.y, c);

        dibujarLinea(vertices.v1.x, vertices.v1.y,vertices.v8.x, vertices.v8.y, c);
        dibujarLinea(vertices.v5.x, vertices.v5.y,vertices.v4.x, vertices.v4.y, c);
    }

    public void dibujarCurva(int inicio, int fin, Color c)
    {   
        int x,y,z;
        Coordenadas anteriores=new Coordenadas();
        for(int i=inicio;i<fin;i++)
        {
            x=(int)Math.cos(Math.toRadians(i*Math.PI));
            y=(int)Math.sin(Math.toRadians(i*Math.PI));
            z=(int)(i*Math.PI);

            dibujarLinea(anteriores.x, anteriores.y, x, y, c);
            
            anteriores.x=x;
            anteriores.y=y;
            anteriores.z=z;
        }
    }
}

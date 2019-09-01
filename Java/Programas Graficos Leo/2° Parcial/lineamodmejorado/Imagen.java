package lineamodmejorado;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Imagen extends BufferedImage
{
    public Imagen(int w, int h)
    {
        super(w,h,BufferedImage.TYPE_INT_RGB);
    }
    public void dibujarPxl(int x, int y,Color c)
    {
        setRGB(x,y,c.getRGB());
    }
    public void dibujarLineaBasica(int x1,int y1,int x2,int y2, Color c)
    {
        //y=mx+b
        //m=Dy/Dx = y2-y1/x2-x1
        double m = (double)(y2-y1)/(x2-x1);
        double b = y1-(m*x1);

            int x=x1<x2?1:-1;

        while(true)
        {
            int y = (int)((m*x1)+b);
            dibujarPxl(x1,y,c);
            x1+=x;
            if(x1==x2)
                break;
        } 
    }
    public void dibujarLineaMejorada(int x1,int y1,int x2,int y2, Color c)
    {
        double m = (double)(y2-y1)/(x2-x1);
        double b = y1-(m*x1);

        if(x1==x2)
        {
             int y = y1<y2?1:-1;
             while(true)
             {
                 dibujarPxl(x1,y1,c);
                 
                 if(y1==y2)
                 {
                     break;
                 }
                 y1+=y;   
             }
        }
        if(y1==y2)
        {
             
            int x=x1<x2?1:-1;

            while(true)
                {
                    dibujarPxl(x1,y1,c);
                    if(x1==x2)
                    {
                        break;
                    }
                    x1+=x;
                } 
            
        }
        if((m*m)>1)
        {
            int x=x1<x2?1:-1;

        while(true)
            {
                int y = (int)((m*x1)+b);
                dibujarPxl(x1,y,c);
                if(x1==x2)
                {
                    break;
                }
                x1+=x;
            } 
        }
        else
        {
            //x=(y-b)/m
            int y = y1<y2?1:-1;
            while(true)
            {
                int x = (int)((y1-b)/m);
                dibujarPxl(x,y1,c);
                
                if(y1==y2)
                {
                    break;
                }
                y1+=y;   
            }
        }
            
    }
}

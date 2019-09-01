package rectangulo;
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
    public void dibujarBresenham( int x1, int y1, int x2, int y2, Color c)
    {
        if((y2-y1)<(x2-x1))
        {
            dibujarPxl(x1, y1, c);
            
            int A=2*(y2-y1);
            int B=2*(y2-y1)-2*(x2-x1);

            int p=2*(y2-y1)-(x2-x1);

            int x=x1;
            int y=y1;
            
            while (true) 
            {
                if(p<0)
                {
                    x=x+1;
                    dibujarPxl(x, y, c);
                    p=p+A;
                }
                else if(p>0)
                {
                    x=x+1;
                    y=y+1;
                    dibujarPxl(x,y,c);
                    p=p+B;
                }
                if(x>=x2)
                    break;    
            }
        }
        else
        {
            dibujarPxl(x1, y1, c);
            
            int A=2*(x2-x1);
            int B=2*(x2-x1)-2*(y2-y1);

            int p=2*(x2-x1)-(y2-y1);

            int x=x1;
            int y=y1;
            
            while (true) 
            {
                if(p<0)
                {
                    y=y+1;
                    dibujarPxl(x, y, c);
                    p=p+A;
                }
                else if(p>0)
                {
                    x=x+1;
                    y=y+1;
                    dibujarPxl(x,y,c);
                    p=p+B;
                }
                if(y>=x2)
                    break;    
            }
        }
    }
    public void dibujarPuntoMedio( int x1, int y1, int x2, int y2, Color c)
    {
        if((y2-y1)<(x2-x1))
        {
            dibujarPxl(x1, y1, c);
            
            int A=2*(y2-y1);
            int B=2*(y2-y1)-2*(x2-x1);

            int p=2*(y2-y1)-(x2-x1);

            int x=x1;
            int y=y1;
            
            while (true) 
            {
                if(p<0)
                {
                    x=x+1;
                    dibujarPxl(x, y, c);
                    p=p+A;
                }
                else if(p>0)
                {
                    x=x+1;
                    y=y+1;
                    dibujarPxl(x,y,c);
                    p=p+B;
                }
                if(x>=x2)
                    break;    
            }
        }
        else
        {
            dibujarPxl(x1, y1, c);
            
            int A=2*(x2-x1);
            int B=2*(x2-x1)-2*(y2-y1);

            int p=2*(x2-x1)-(y2-y1);

            int x=x1;
            int y=y1;
            
            while (true) 
            {
                if(p<0)
                {
                    y=y+1;
                    dibujarPxl(x, y, c);
                    p=p+A;
                }
                else if(p>=0)
                {
                    x=x+1;
                    y=y+1;
                    dibujarPxl(x,y,c);
                    p=p+B;
                }
                if(y>=x2)
                    break;    
            }
        }
    }
    public void dibujarRectangulo(int x1, int y1, int x2, int y2, Color c)
    {
        
        int x = x1;
        int y = y1;

        while(x!=x2)
        {
            x = x+1;
            dibujarPxl(x,y,c);
        }
        
        while(y!=y2)
        {
            y = y+1;
            dibujarPxl(x,y,c);
        }
        
        while(x!=x1)
        {
            x = x-1;
            dibujarPxl(x,y,c);
        }
        
        while(y!=y1)
        {
            y = y-1;
            dibujarPxl(x,y,c);
        }
    }
}
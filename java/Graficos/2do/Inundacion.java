
import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Inundacion extends JFrame {

    private Image imgPixel;
    private Graphics graPixel;
    private int width = 1000;
    private int height = 500;

    BufferedImage miBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    
    public Inundacion(){ 
        this.setSize(width, height); 
        this.setVisible(true); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgPixel = createImage(1, 1);
        graPixel = imgPixel.getGraphics();
    }
      
        
    public void update(){
          
        drawRectangle(450,400,550,210);
        Inundacion(460,350,"YELLOW");
        drawRectangle(750,400,850,260);
        Inundacion(760,350,"BROWN");

        drawOval(150, 150, 110, 38);
        Inundacion(160, 160,"ORANGE");  

        drawCircle(690, 130, 55); 
        Inundacion(700, 135,"RED");

        this.getGraphics().drawImage(miBuffer, 1, 1, this);        
    }
    
    
    public static void main(String[] args) {     
        new Inundacion();
    }
      
    
    public void paint(Graphics g){
        update();
        miBuffer.getGraphics().fillRect(0, 0, width, height);
    }
    
    
    public void putPixel(int x, int y) {
        miBuffer.setRGB(x, y, graPixel.getColor().getRGB());
    }
    
     
    public void putPixel(int x, int y,String colorPixel) {
        switch (colorPixel)
          {
            case "BLACK": graPixel.setColor(new Color(52, 52, 52 ));
                break;       
            case "RED": graPixel.setColor(new Color(123, 36, 28 ));
                break;        
            case "BLUE": graPixel.setColor(new Color(36, 113, 163));
                break;
            case "GREEN": graPixel.setColor(new Color(20, 143, 119 ));
                break; 
            case "ORANGE": graPixel.setColor(new Color(202, 111, 30 ));
                break;    
            case "YELLOW": graPixel.setColor(new Color(241, 196, 15  ));
                break;  
            case "BROWN": graPixel.setColor(new Color(160, 64, 0 ));
                break;       
          }   
        miBuffer.setRGB(x, y, graPixel.getColor().getRGB());
    }
   
       
    public void drawLine(int x0, int y0, int x1, int y1,int grosor,String colorLinea) {
        int dx = x1-x0;
        int dy = y1-y0;
        double m = (double) dy/dx;
        int steps=0;
        if(Math.abs(dx) > Math.abs(dy)){
            steps = Math.abs(dx);
        }
        else{
            steps = Math.abs(dy);
        }
        double xinc =(double)dx / steps;
        double yinc =(double)dy / steps;
        double x = x0;
        double y=y0;
        putPixel((int)Math.round(x), (int)Math.round(y), colorLinea);
        for(int k=1;  k<=steps; k++)
                {
                    x = x + xinc;
                    y = y + yinc;
                    putPixel((int)Math.round(x),(int)Math.round(y), colorLinea);
                     if(m<1)
                     {
                           boolean dirArriba = true;
                           int yUp = 0;
                           int yDown = 0;
                        for (int yVertical = 0; yVertical < grosor; yVertical++)
                           {
                                if(dirArriba)
                                { 
                                   putPixel((int) Math.round(x),(int) Math.round(y) + yUp, colorLinea);
                                   yUp++;
                                   dirArriba = false;
                                }
                                else
                                 {
                                     
                                    putPixel((int) Math.round(x),(int) Math.round(y) - yDown, colorLinea);
                                    yDown++;
                                    dirArriba = true;
                                 }
                    
                            }
                    }
            }
    }
    
    
    public void drawLine(int x0, int y0, int x1, int y1,int[] miMascara){
       
        int longitudArray = miMascara.length;
        int indiceArray = 0;
        int dx = x1-x0;
        int dy = y1-y0;
        int steps=0;
        if(Math.abs(dx) > Math.abs(dy)){
            steps = Math.abs(dx);
        }
        else{
            steps = Math.abs(dy);
        }
        double xinc =(double)dx / steps;
        double yinc =(double)dy / steps;
        double x = x0;
        double y=y0;
        putPixel((int)Math.round(x), (int)Math.round(y), "BLACK");
        for(int k=1;  k<=steps; k++){
                    x = x + xinc;
                    y = y + yinc;
                    if(indiceArray>=miMascara.length)
                        indiceArray = 0;
                    int pintNoPint = miMascara[indiceArray];
                    if(pintNoPint==1){
                         putPixel((int)Math.round(x),(int)Math.round(y), "BLACK");
                         indiceArray++;
                    }
                    else{
                        indiceArray++;
                        continue;
                    }
                } 
        }
     
    
    public void drawCircle(int x0, int y0, int R){
 
            
        int x = 0;
        int y = R;

        putPixel(x0 + x, y0 + y);
        putPixel(x0 + x, y0 - y);
        putPixel(x0 - x, y0 + y);
        putPixel(x0 - x, y0 - y);
        putPixel(x0 + y, y0 + x);
        putPixel(x0 + y, y0 - x);
        putPixel(x0 - y, y0 + x);          
        putPixel(x0 - y, y0 - x); 
        double p = 1 - R;
        
                 while(x < y){
                     x++;
                if(p<0){
            p = p + 2*x+6;
                    }
                    else{
            y = y - 1;
            p = p +(2*(x-y))+1;
             }  
        putPixel(x0 + x, y0 + y);
        putPixel(x0 + x, y0 - y);
        putPixel(x0 - x, y0 + y);
        putPixel(x0 - x, y0 - y);
        putPixel(x0 + y, y0 + x);
        putPixel(x0 + y, y0 - x);
        putPixel(x0 - y, y0 + x);          
        putPixel(x0 - y, y0 - x); 
          
        }
    
    }

     public void drawDiscCircle(int cx, int cy, double r)
    {
        double p =  5d/4d - r;
        int y, mascara = 0, x = 0, dy = -2 * (int)r, dx = 1;
        r = Math.abs(r);

        if (r % 1 == 0) p = 1 - r;
        y = (int) r;
        
        while (x <= y) {

            if (mascara < 4) {
                putPixel(cx + x, cy + y, "BLUE");
                putPixel(cx - x, cy + y, "BLUE");
                putPixel(cx + x, cy - y, "BLUE");
                putPixel(cx - x, cy - y, "BLUE");
                putPixel(cx + y, cy + x, "BLUE");
                putPixel(cx - y, cy + x, "BLUE");
                putPixel(cx + y, cy - x, "BLUE");
                putPixel(cx - y, cy - x, "BLUE");
            }
            if (p >= 0) {
                dy += 2;
                p += dy; 
                y--;
            }
            dx += 2;
            p += dx;
            x++;
            mascara = (mascara < 7) ? mascara + 1 : 0 ;
        }
    } 
    
    
    public void drawOval(int x0, int y0, int R1, int R2) {  
        int a2= R1 * R1;
        int b2= R2 * R2;
        int fa2= 4*a2, fb2=4*b2;
        int x, y, diferencia;

        for (x=0,y=R2,diferencia=2*b2+a2*(1-2*R2);b2*x<=a2*y; x++) 
        {
            putPixel(x0 + x, y0 + y);
            putPixel(x0 - x, y0 + y);
            putPixel(x0 - x, y0 - y);
            putPixel(x0 + x, y0 - y);

            if (diferencia >=0)
            {
                diferencia +=fa2 * (1-y);
                y--;
            }
            diferencia += b2 *((4*x)+6);  
        }

        for (x=R1 ,y=0 ,diferencia=2*a2+b2*(1-2*R1); a2*y<=b2*x ; y++) 
        {
            putPixel(x0 + x, y0 + y);
            putPixel(x0 - x, y0 + y);
            putPixel(x0 - x, y0 - y);
            putPixel(x0 + x, y0 - y);

            if (diferencia >=0)
            {
                diferencia +=fb2 * (1-x);
                x--;
            }
            diferencia += a2 *((4*y)+6);  
        }
    }   
    
   public void drawRectangle(int x1, int y1, int x2, int y2)
    {
        drawLine(x1,y1, x2,y1,1,"BLACK");
        drawLine(x1,y1, x1,y2,1,"BLACK");
        drawLine(x2,y1, x2,y2,1,"BLACK");
        drawLine(x1,y2, x2,y2,1,"BLACK");
    }
    
    public  void Inundacion(int x,int y, String fillColor)
    {
       
      BufferedImage image = miBuffer;     
      Queue<Point> colaPuntos = new LinkedList<Point>();
      int initialColor = image.getRGB(x,y);
      colaPuntos.add(new Point(x,y));
      while (colaPuntos.size()>0)
      {
        Point p = colaPuntos.remove();  
        if(p.x>0 && p.x <= miBuffer.getWidth() && p.y >0 && p.y <= miBuffer.getHeight())
        {
             if (image.getRGB(p.x,p.y) == initialColor) //Mientras el pixel sea igual al color del punto inicial
                {
                    x = p.x;  y = p.y; 
                    putPixel(x,y,fillColor);
                    colaPuntos.add(new Point(x-1,y));
                    colaPuntos.add(new Point(x+1,y));        
                    colaPuntos.add(new Point(x,y-1));  
                    colaPuntos.add(new Point(x,y+1));  
                
                }
        }
      }
    }
}

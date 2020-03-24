
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


public class Animacion extends JFrame implements Runnable {

    private Image imgPixel;
    private Graphics graPixel;
    private int width = 1000;
    private int height = 850;

    BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    int rotx=0;
    int roty=0;
   
    Thread thr = new Thread(this);
    int trsX = 0;
    int trsX2 = 0;
    int trsXRight = 0;
    int trsY = 0; 

    Point point0 = new Point();
    Point point1 = new Point();
    Point micirc = new Point();
    
    public Animacion(){ 
        this.setSize(width, height); 
        this.setVisible(true); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgPixel = createImage(1, 1);
        graPixel = imgPixel.getGraphics();
        thr.start();
    }

     @Override
    public void run() {
      while(true)
      {
        try {
            Thread.sleep(100);          
        } 
        catch (InterruptedException ex) {
            
        }
         repaint();
  
      }
    }
      
        
    public void update(){
          
        setCity();
        setCar1(trsX, 0, "BLACK");
        setCar2(trsX2, 0, "BLUE");
        setSun(rotx, roty);
        this.getGraphics().drawImage(buffer, 1, 1, this);        
    }
    
    
    public static void main(String[] args) {     
        new Animacion();
    }
      
    
    public void paint(Graphics g){
        trsX += 10; 
        trsX2 -= 10; 
        rotx += 5;
        roty += (rotx > 450) ? 1 : -1;
        update();
        buffer.getGraphics().fillRect(0, 0, width, height);
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
    
    
    public Point traslacionXY(Point miPoint,int xTrasladar, int yTrasladar){
       
        Point miPoint2D = new Point();
       
        int dx = xTrasladar; 
        int dy = yTrasladar; 
        
        int xPrima = (int) miPoint.x + dx;
        int yPrima = (int) miPoint.y + dy;
        
        miPoint2D.x = xPrima;
        miPoint2D.y = yPrima;
        
        return miPoint2D;
    }
    
     
    public void setCar1(int xTras, int yTras, String color){
        
         point0.x = 90;   point0.y = 450;
         point0 = traslacionXY(point0,xTras,yTras);
         point1.x = 330;   point1.y = 450;
         point1 = traslacionXY(point1,xTras,yTras);

        if(point1.x > buffer.getWidth() -10 )
        {
            trsX = 0;
            return;
        }
        else
         drawLine(point0.x, point0.y, point1.x, point1.y,3,color);
         
          
         point0.x = 90;   point0.y = 450;
         point0 = traslacionXY(point0,xTras,yTras);
         point1.x = 90;   point1.y = 530;
         point1 = traslacionXY(point1,xTras,yTras);
         
         drawLine(point0.x, point0.y, point1.x, point1.y,3,color);
                 
           
         point0.x = 330;   point0.y = 450;
         point0 = traslacionXY(point0,xTras,yTras);
         point1.x = 330;   point1.y = 530;
         point1 = traslacionXY(point1,xTras,yTras);
        
         drawLine(point0.x, point0.y, point1.x, point1.y,3,color);
         
                  
         
         point0.x = 90;   point0.y = 530;
         point0 = traslacionXY(point0,xTras,yTras);
         point1.x = 330;   point1.y = 530;
         point1 = traslacionXY(point1,xTras,yTras);
        

         drawLine(point0.x, point0.y, point1.x, point1.y,3,color);
         floodFill(200 + xTras,500 + yTras,"BLUE");
        
                 
         
         point0.x = 150; point0.y = 556; 
         point0 = traslacionXY(point0,xTras,yTras);
         drawCircle(point0.x, point0.y, 25);
         point0.x = 150; point0.y = 556; 
         point0 = traslacionXY(point0,xTras,yTras);
         
   

         drawCircle(point0.x, point0.y, 10);
         floodFill(155 + xTras,570 + yTras,"BLACK");
        
         
         
          point0.x = 280; point0.y = 556; 
         point0 = traslacionXY(point0,xTras,yTras);
         drawCircle(point0.x, point0.y, 25);
         point0.x = 280; point0.y = 556; 
         point0 = traslacionXY(point0,xTras,yTras);
         
         drawCircle(point0.x, point0.y, 10);
         floodFill(284 + xTras,570 + yTras,"BLACK");

        
              
     }

     public void setCar2(int xTras, int yTras, String color){
         
             
        point0.x = 850; point0.y = 650; 
        point0 = traslacionXY(point0, xTras, yTras);
        if(point0.x < 105 )
        {
            trsX2 = 0;
            return;
        }
        else{
            // System.out.println(point0.x);
            drawOval(point0.x, point0.y, 110, 38);
        }
                 
         
         point0.x = 785; point0.y = 706; 
         point0 = traslacionXY(point0,xTras,yTras);
         drawCircle(point0.x, point0.y, 25);
         point0.x = 785; point0.y = 706; 
         point0 = traslacionXY(point0,xTras,yTras);
         drawCircle(point0.x, point0.y, 10);
         
         
        point0.x = 915; point0.y = 706; 
         point0 = traslacionXY(point0,xTras,yTras);
         drawCircle(point0.x, point0.y, 25);
         point0.x = 915; point0.y = 706; 
         point0 = traslacionXY(point0,xTras,yTras);
         drawCircle(point0.x, point0.y, 10);
         
        floodFill(860 + xTras, 660 + yTras,"RED");   
        floodFill(790 + xTras,720 + yTras,"BLACK");
        floodFill(919 + xTras,720 + yTras,"BLACK");
    
  
              
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
    public void setSun(int xTras, int yTras )
    {
      
        micirc.x = 90; micirc.y = 200; 
        micirc = traslacionXY(micirc, xTras, yTras);
        if(micirc.x > buffer.getWidth() -100 )
        {
            rotx = 0;
            roty = 0;
            return;
        }
   
        drawCircle(micirc.x, micirc.y, 55);
        floodFill(143 + xTras, 210   + yTras,"ORANGE");
    }
    
    public  void floodFill(int x,int y, String fillColor)
    {
       
      BufferedImage image = buffer;     
      Queue<Point> colaPuntos = new LinkedList<Point>();
      int initialColor = image.getRGB(x,y);
      colaPuntos.add(new Point(x,y));
      while (colaPuntos.size()>0)
      {
        Point p = colaPuntos.remove();  
        if(p.x>0 && p.x <= buffer.getWidth() && p.y >0 && p.y <= buffer.getHeight())
        {
             if (image.getRGB(p.x,p.y)==initialColor)
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

    public void  setCity()
    {
        drawRectangle(100,400,250,300);

        drawRectangle(0, 400,100,230);
        drawRectangle(100,400,350,220);
        drawRectangle(350,400,450,250);
        drawRectangle(350,400,450,250);
        drawRectangle(450,400,550,210);
        drawRectangle(550,400,650,200);
        drawRectangle(650,400,750,250);
        drawRectangle(750,400,850,260);
        drawRectangle(850,400,990,220);

        floodFill(50,350,"BLACK");
        floodFill(150,350,"BLACK");
        floodFill(255,350,"BLACK");
        floodFill(360,350,"BLACK");
        floodFill(460,350,"BLACK");
        floodFill(560,350,"BLACK");
        floodFill(660,350,"BLACK");
        floodFill(760,350,"BLACK");
        floodFill(900,350,"BLACK");

    }
    

    public void putPixel(int x, int y,String colorPixel) {
        if(x > buffer.getWidth() -10 )
        {
            // initAnimation();
        }
        else
        {
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
        buffer.setRGB(x, y, graPixel.getColor().getRGB());
        }
    }

    public void putPixel(int x, int y) {
        if(x > buffer.getWidth() -10 ){

            // initAnimation();
        }
            
        else
            buffer.setRGB(x, y, graPixel.getColor().getRGB());
    }
    
    
    
}

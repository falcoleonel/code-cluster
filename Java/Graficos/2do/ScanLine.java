
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ScanLine extends JFrame implements MouseListener {
    
    private static Image fondo, ImgPixel;
    private static Graphics graPixel,gFondo ;
    private int newColorIn=0,x1b,y1b;

    private ScanLine() {
        Dimension pantalla= Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocation((pantalla.width-500)/2, (pantalla.height-500)/2 );
        this.setTitle("Scan-Line");
        this.setSize(500,300);
        this.setVisible(true);
        ImgPixel = createImage(1, 1);
        graPixel  = ImgPixel.getGraphics();
        fondo = createImage (pantalla.width, pantalla.height);
        gFondo = fondo.getGraphics();
        this.addMouseListener(this);
    }
    public void paint(Graphics g){
        super.paint(g);
    }

    private void drawPixel(int x, int y){
        graPixel.drawLine(0, 0, 1, 1);
        gFondo.drawImage(ImgPixel, x,y,this);
        this.getGraphics().drawImage(fondo, 0, 0, this);
    }

    private void ScanLine(int x0,int y0, Color c) {
        graPixel.setColor(c);
        x0--;
        y0--;
        int oldColorDeAr = ((BufferedImage) fondo).getRGB(x0, y0);
        int newColorDeAr = ((BufferedImage) fondo).getRGB(x0, y0);
        System.out.println(oldColorDeAr+" "+newColorDeAr);
        for(int y=y0; oldColorDeAr == newColorDeAr; y++) {
            for(int x = x0; oldColorDeAr == newColorDeAr; x++) {
                oldColorDeAr = ((BufferedImage) fondo).getRGB(x+1, y);
                drawPixel(x,y);
            }
            oldColorDeAr = ((BufferedImage) fondo).getRGB(x0-1, y);
            for(int x = x0; oldColorDeAr == newColorDeAr; x--) {
                oldColorDeAr = ((BufferedImage) fondo).getRGB(x-1, y);
                drawPixel(x,y);
            }
            oldColorDeAr =  ((BufferedImage) fondo).getRGB(x0,y+1);
        }
        oldColorDeAr =  ((BufferedImage) fondo).getRGB(x0,y0-1);
        for(int y=y0-1; oldColorDeAr == newColorDeAr; y--) {
            for(int x = x0; oldColorDeAr == newColorDeAr; x++) {
                oldColorDeAr = ((BufferedImage) fondo).getRGB(x+1, y);
                drawPixel(x,y);
            }
            oldColorDeAr = ((BufferedImage) fondo).getRGB(x0-1, y);
            for(int x = x0; oldColorDeAr == newColorDeAr; x--)
            {
                oldColorDeAr = ((BufferedImage) fondo).getRGB(x-1, y);
                drawPixel(x,y);
            }
            oldColorDeAr =  ((BufferedImage) fondo).getRGB(x0,y-1);
        }
    }

    private void rectang(int x0, int y0, int x1, int y1){
        LineaDDA(x0, y0, x1, y0);
        LineaDDA(x0, y0, x0, y1);
        LineaDDA(x0, y1, x1, y1);
        LineaDDA(x1, y0, x1, y1);
    }

    private void LineaDDA(int x0, int y0, int x1, int y1){
        int dx = Math.abs(x1-x0);
        int dy = Math.abs(y1-y0);
        drawPixel(x0,y0);
        if(dx >= dy) {
            int a = 2*dy;
            int b = (2*dy) - (2*dx);
            int pk = (2*dy) - dx;

            int y=y0;
            int stepy;
            if(y0 < y1)
                stepy = 1;
            else
                stepy = -1;
            if(x0 < x1){
                for(int x = x0++; x < x1; x++){
                    if(pk <= 0){
                        drawPixel(x, y);
                        pk += a;
                    }else{
                        y = y + stepy;
                        drawPixel(x, y);
                        pk += b;
                    }
                }
            }else{
                for(int x = x0--; x > x1; x--){
                    if(pk <= 0){
                        drawPixel(x, y);
                        pk += a;
                    }else{
                        y = y + stepy;
                        drawPixel(x, y);
                        pk += b;
                    }
                }
            }
        }else{
            int a = 2*dx;
            int b = (2*dx) - (2*dy);
            int pk = (2*dx) - dy;
            int stepx;
            if(x0 < x1)
                stepx = 1;
            else
                stepx = -1;
            int x=x0;
            if(y0 < y1){
                for(int y = y0++; y < y1; y++){
                    if(pk <= 0){
                        drawPixel(x, y);
                        pk += a;
                    }else{
                        x = x + stepx;
                        drawPixel(x, y);
                        pk += b;
                    }
                }
            }else{
                for(int y = y0--; y > y1; y--){
                    if(pk <= 0){
                        drawPixel(x, y);
                        pk += a;
                    }else{
                        x = x + stepx;
                        drawPixel(x, y);
                        pk += b;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new ScanLine();
    }
    public void mouseClicked(MouseEvent e) {
        
    }
    public void mousePressed(MouseEvent e) {
        x1b = e.getX();
        y1b = e.getY();
    }
    public void mouseReleased(MouseEvent e) {
        graPixel.setColor(Color.GREEN);
        rectang(x1b,y1b,e.getX(),e.getY()); 
        if(x1b>e.getX()&&y1b>e.getY())      
            ScanLine(x1b,y1b,Color.RED);   
        else if(x1b>e.getX()&&y1b<e.getY())    
            ScanLine(x1b,e.getY(),Color.BLUE);     
        else if(x1b<e.getX()&&y1b>e.getY())    
            ScanLine(e.getX(),y1b,Color.BLACK);     
        else if(x1b<e.getX()&&y1b<e.getY())     
            ScanLine(e.getX(),e.getY(),Color.PINK);
    }
    public void mouseEntered(MouseEvent e) {
        
    }
    public void mouseExited(MouseEvent e) {
        
    }
    
}
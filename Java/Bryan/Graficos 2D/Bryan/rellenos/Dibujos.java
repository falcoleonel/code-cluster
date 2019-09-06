package rellenos;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Dibujos
 */
public class Dibujos {

    int X,Y;
    Color color =new Color(0, 0, 0);
    JPanel panel;
    BufferedImage bfi;

    public void leerCoordenadas(MouseEvent ev) {
        if (!ev.isMetaDown()) {
            X = ev.getX();
            Y = ev.getY();
        }
    }
    public void pintar(MouseEvent ev,int grosor) {
        panel=(JPanel)ev.getSource();
            Graphics2D g = ((Graphics2D)panel.getGraphics());
            //Graphics2D g = JPanel(ev.getSource())
            
            g.setStroke(new BasicStroke(grosor) );
            g.setColor(color);

            int x = ev.getX();
            int y = ev.getY();
            g.drawLine(X, Y, x, y);
    
            g.dispose();
    
            X = x;
            Y = y;
    }
    public void rellenar(MouseEvent ev,int grosor) {
        //System.out.println("color.getRGB()"+X);
        System.out.println("getColorValue(X, Y)"+getColorValue(X, Y));
        
        bfi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        

        //flood(ev.getX(), ev.getY(), color.getRGB(),bfi.getRGB(X, Y));
    }

    public void flood(int x, int y, int newColor, int oldColor) {
        //System.out.println("flood Action");
        //panel=(JPanel)(ev.getSource());

        if(x<0) return;
        if(y<0) return;
        if(x>bfi.getWidth()) return;
        if(y>bfi.getHeight()) return;
        
        System.out.println("old"+oldColor);
        System.out.println("new"+newColor);
        
        System.out.println("getRGB"+bfi.getRGB(x, y));

        //oldColor = getColorValue(x, y);
        if (oldColor==newColor) return;

        Graphics2D gtmp = (Graphics2D)bfi.getGraphics();
        gtmp.setColor(color);
        gtmp.drawLine(x, y, x, y);

        newColor = bfi.getRGB(x,y);
        
        flood(x-1, y, newColor, oldColor);
        //flood(x-1, y, newColor);
        //flood(x+1, y, newColor);
        //flood(x, y-1, newColor);
        //flood(x, y+1, newColor);
    }
    private int getColorValue(int x, int y) {
        /*BufferedImage image = getScreenShot(panel);
        return image.getRGB(x, y);*/
        //System.out.println("((BufferedImage)getScreenShot(panel))"+getScreenShot(panel).getRGB(x, y));
        return ((int)getScreenShot().getRGB(x, y));
        //return panel.getComponentAt(x, y).getColorModel()
    }
    private BufferedImage getScreenShot(){

        BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        g.dispose();

        return bi;
    }
}
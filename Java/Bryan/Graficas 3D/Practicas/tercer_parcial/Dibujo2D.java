package tercer_parcial;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Dibujo2D {
    
    public static void dibujarPixel(BufferedImage img, Point p, Color color) {
        if(p.x >= 0 && p.y >= 0 && p.x < img.getWidth() && p.y < img.getHeight())
            img.setRGB(p.x, p.y, color.getRGB());
    }
    
    public static void dibujarRecta(BufferedImage img, Point p1, Point p2, Color color) {

        int dx = Math.abs(p2.x - p1.x), dy = Math.abs(p2.y - p1.y);
        int sx = p1.x < p2.x ? 1 : -1, sy = p1.y < p2.y ? 1 : -1;
        int err = (dx > dy ? dx : -dy) / 2, e2;
        
        while(true) {
            
            dibujarPixel(img, p1, color);
            if(p1.x == p2.x && p1.y == p2.y) break;
            e2 = err;
            
            if(e2 > -dx) { err -= dy; p1.x += sx; }
            if(e2 < dy)  { err += dx; p1.y += sy; }
        }
    }
}

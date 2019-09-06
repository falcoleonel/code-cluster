package pixel;

import java.awt.*;
//import java.awt.image.BufferedImage;
import javax.swing.*;
import java.lang.Math;

public class Figures2d extends Lineas{

    public Figures2d (int w, int h){
        super(w,h);
     }

    public void drawRect(int x0, int y0, int x1, int y1, Color c) {
        drawLine(x0, y0, x0, y1, c);
        drawLine(x0, y0, x1, y0, c);

        drawLine(x1, y0, x1, y1, c);
        drawLine(x0, y1, x1, y1, c);
     }
    public void drawCirc(int _x, int _y, int radio, Color c) {

        int x, y, p;
        x = 0;
        y = radio;
        p = 1 - radio;

        while (x <= y){    
            draw_octante(x,y,_x,_y,c);

            x = x + 1;
            if (p < 0)
              p = p + 2*x + 1;
            else {
              y = y - 1;
              p = p + 2*(x - y) + 1;
             }
         }
     }
    public void draw_octante(int x, int y, int _x, int _y,Color c) {
        draw_octanteA(x,y,_x,_y,c);
        draw_octanteB(x,y,_x,_y,c);
     }
    public void draw_octanteA(int x, int y, int _x, int _y,Color c) {
        dibujarPxl((_x+y),(_y+x), c);
        dibujarPxl((_x-y),(_y-x), c);
        dibujarPxl((_x-y),(_y+x), c);
        dibujarPxl((_x+y),(_y-x), c);    
     }
    public void draw_octanteB(int x, int y, int _x, int _y,Color c) {
        dibujarPxl((_x+x),(_y+y), c);
        dibujarPxl((_x-x),(_y-y), c);
        dibujarPxl((_x-x),(_y+y), c);
        dibujarPxl((_x+x),(_y-y), c);
     }
    
    public void drawElipse(int xc, int yc, int rx, int ry, Color c){
        int x, y, p, px, py;
        int rx2, ry2, tworx2, twory2;
        ry2 = ry*ry;
        rx2 = rx*rx;
        twory2 = 2 * ry2;
        tworx2 = 2 * rx2;
                            /* región 1 */
        x = 0;
        y = ry;
        p = (int)Math.round(ry2 - rx2*ry + 0.25*rx2);
        px = 0;
        py = tworx2*y;

        while (px <= py) { /* se cicla hasta trazar la región 1 */
            draw_octanteB(x,y,xc,yc,c);
            x = x + 1;
            px = px + twory2;
            
            if (p < 0)
                p = p + ry2 + px;
            else {
                y = y - 1;
                py = py - tworx2;
                p = p + ry2 + px - py;
            }
        }
                            /* región 2 */
        p = (int)Math.round(ry2*(x+0.5)*(x+0.5) + rx2*(y-1)*(y-1) - rx2*ry2);
        px = 0;
        py = tworx2*y;

        while (y >= 0) { /* se cicla hasta trazar la región 2 */
            draw_octanteB(x,y,xc,yc,c);
            y = y - 1;
            py = py - tworx2;
            if (p > 0)
                p = p + rx2 - py;
            else {
                x = x + 1;
                px = px + twory2;
                p = p + rx2 + py + px;
            }
        }
    }
}
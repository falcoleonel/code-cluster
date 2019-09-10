

import java.awt.*;
import java.awt.Color;
import java.util.*;

public class Linea {
	//Punto Medio
	
    public static void drawLine(int x0, int y0, int x1, int y1, Color color) {
		int x, y;
        int dx, dy, A, B, pk;
        dx = (x1 - x0);
        dy = (y1 - y0);
		
		//Calcula los pasos en x y en y 
        int stepy = (dy < 0) ? -1 : 1;
        int stepx = (dx < 0) ? -1 : 1;

        dy *= stepy;
        dx *= stepx;

		x = x0;
		y = y0;
		
		Pixel.canvas.putPixel(x, y, color);
		if(dx > dy) {
			pk = (2 * dy) - dx;
			A = 2 * dy;
			B = (2 * dy) - (2 * dx);

			while(x != x1) {
				x = x + stepx;
				if(pk < 0) {
					pk = pk + A;
				}
				else {
					y = y + stepy;
					pk = pk + B;
				}
		
				Pixel.canvas.putPixel(x, y, color);
			}
		}
		else {
			pk = (2 * dx) - dy;
			A = 2 * dx;
			B = (2 * dx) - (2 * dy);
			while(y != y1) {
				y = y + stepy;
				if(pk < 0) {
					pk = pk + A;
				}
				else {
					x = x + stepx;
					pk = pk + B;
				}

				Pixel.canvas.putPixel(x, y, color);
			}
		}
    }
	
}
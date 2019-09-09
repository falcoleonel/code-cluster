
import java.awt.*;
import java.util.ArrayList;

public class Painting {
    private static void LineaBresenham(int x0, int y0, int x1, int y1, Color c) {
        int x, y;
        int diferenciaX, diferenciaY, A, B, pk, stepx, stepy;
        diferenciaX = (x1 - x0);
        diferenciaY = (y1 - y0);
        if(diferenciaY < 0) {
            diferenciaY = -1 * diferenciaY;
            stepy = -1;
        }else
            stepy = 1;
        if(diferenciaX < 0) {
            diferenciaX = -1 * diferenciaX;
            stepx = -1;
        }
        else stepx = 1;
        x = x0; y = y0;
        Pixel.instance.drawPixel(x, y, c);
        if(diferenciaX > diferenciaY) {
            pk = (2 * diferenciaY) - diferenciaX;
            A = 2 * diferenciaY;
            B = (2 * diferenciaY) - (2 * diferenciaX);
            while(x != x1) {
                x = x + stepx;
                if(pk < 0)
                    pk = pk + A;
                else {
                    y = y + stepy;
                    pk = pk + B;
                }
                Pixel.instance.drawPixel(x, y, c);
            }
        } else {
            pk = (2 * diferenciaX) - diferenciaY;
            A = 2 * diferenciaX;
            B = (2 * diferenciaX) - (2 * diferenciaY);
            while(y != y1) {
                y = y + stepy;
                if(pk < 0)
                    pk = pk + A;
                else {
                    x = x + stepx;
                    pk = pk + B;
                }
                Pixel.instance.drawPixel(x, y, c);
            }
        }
    }

    public static void CuboParalelaProyeccion(int x1, int y1, int z1, int large, Color c) {
        ArrayList<Integer> valuesX1 = new ArrayList<>();
        ArrayList<Integer> valuesY1 = new ArrayList<>();
        ArrayList<Integer> valuesX2 = new ArrayList<>();
        ArrayList<Integer> valuesY2 = new ArrayList<>();
        int[] arrX = {0, 1, 1, 0, 0};
        int[] arrY = {0, 0, 1, 1, 0};
        double x, y; double z = 430.0;
        double xp = -60.0; double yp = -60.0; double zp = -86.0;

        for(int cont = 0; cont < arrX.length; cont++) {
            double tempX, tempY;
            //Obtener los lados del cubo, 1er cuadrante
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large);
            //System.out.println(tempX + " - " + tempY);
            //Obtener los valores proyectados en 2D
            x = tempX + (xp * ((z - z1) / zp));
            y = Pixel.height - (tempY + (yp * ((z - z1) / zp)));
            //System.out.println(x + " - " + y);
            valuesX1.add((int)x); valuesY1.add((int)y);
        }
        for(int cont = 0; cont < arrX.length; cont++) {
            double tempX, tempY; double tempZ = z1 + large;
            //Obtener los lados del cubo, 1er cuadrante
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large);
            //System.out.println(tempX + " - " + tempY + " - " + tempZ);
            //Obtener los valores proyectados en 2D
            x = tempX + (xp * ((z - tempZ) / zp));
            y = Pixel.height - (tempY + (yp * ((z - tempZ) / zp)));
            //System.out.println(x + " - " + y);
            valuesX2.add((int)x); valuesY2.add((int)y);
        }
        for(int cont = 1; cont < valuesX1.size(); cont++) {
            Painting.LineaBresenham(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX1.get(cont), valuesY1.get(cont), c); //1er cuadrante
            Painting.LineaBresenham(valuesX2.get(cont - 1), valuesY2.get(cont - 1), valuesX2.get(cont), valuesY2.get(cont), c); //2do cuadrante
            Painting.LineaBresenham(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX2.get(cont - 1), valuesY2.get(cont - 1), c); //Aristas
        }
    }
}

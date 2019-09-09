
import java.awt.Color;
import java.util.*;


public class Dibujo {

  //obtener los puntos de la figura
    public static ArrayList<Point3D> getPoints(Point3D origen, Point3D abertura) {
        ArrayList<Point3D> values = new ArrayList<>();
        int[] arrX = {0, 1, 1, 0};
        int[] arrY = {0, 0, 1, 1};
        int x, y;
        for(int cont = 0; cont < arrX.length; cont++) {
            x = origen.x + (arrX[cont] * abertura.x);
            y = origen.y + (arrY[cont] * abertura.y);
            values.add(new Point3D(x, y, -abertura.z/2));
        }
        for(int cont = 0; cont < arrX.length; cont++) {
            x = origen.x + (arrX[cont] * abertura.x);
            y = origen.y + (arrY[cont] * abertura.y);
            values.add(new Point3D(x, y, abertura.z/2));
        }
        return values;
    }

  
    
    
     private static void LineaBresenham(int x0, int y0, int x1, int y1, Color c) {
        int x, y;
        int diferenciaX, diferenciaY, A, B, pk, stepx, stepy;
        diferenciaX = (x1 - x0);
        diferenciaY = (y1 - y0);
        if(diferenciaY < 0) {
            diferenciaY = -1 * diferenciaY;
            stepy = -1;
        } else
            stepy = 1;
        if(diferenciaX < 0) {
            diferenciaX = -1 * diferenciaX;
            stepx = -1;
        } else
            stepx = 1;
        x = x0;
        y = y0;
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

     
      public static void CurvaExplicita(ArrayList<Point3D> points, Color c) {
        double x3D, y3D, z3D = 400.0;
        double xp = -60.0, yp = -60.0, zp = -86.0;
        ArrayList<Point3D> puntitos = new ArrayList<Point3D>();
        for (Point3D point : points) {
            x3D = point.x + (xp * ((z3D - point.z) / zp));
            y3D = Pixel.height - (point.y + (yp * ((z3D - point.z) / zp)));
            puntitos.add(new Point3D((int) x3D, (int) y3D, 0));
        }
        for(int cont = 3; cont < puntitos.size(); cont++)
        {
             
            relleno(puntitos.get(cont-3).x, puntitos.get(cont-3).y, puntitos.get(cont - 2).x, puntitos.get(cont - 2).y, puntitos.get(cont-1).x, puntitos.get(cont-1).y, new Color((200*cont)/puntitos.size(),200-(200*cont)/puntitos.size(),(150*cont)/puntitos.size()));
            relleno(puntitos.get(cont).x, puntitos.get(cont).y, puntitos.get(cont - 2).x, puntitos.get(cont - 2).y, puntitos.get(cont-1).x, puntitos.get(cont-1).y, new Color((200*cont)/puntitos.size(),200-(200*cont)/puntitos.size(),(150*cont)/puntitos.size()));
            LineaBresenham(puntitos.get(cont-1).x, puntitos.get(cont-1).y, puntitos.get(cont - 3).x, puntitos.get(cont - 3).y, Color.BLACK);
            LineaBresenham(puntitos.get(cont-2).x, puntitos.get(cont-2).y, puntitos.get(cont - 3).x, puntitos.get(cont - 3).y, Color.BLACK);
            LineaBresenham(puntitos.get(cont-2).x, puntitos.get(cont-2).y, puntitos.get(cont).x, puntitos.get(cont).y, Color.black);
        }
    }
      
      
      
    private static void relleno(int xor,int yor,int x0, int y0, int x1, int y1, Color c) {
        int x3D, y3D;
        int diferenciaX, diferenciaY, A, B, pk, stepx, stepy;
        diferenciaX = (x1 - x0);
        diferenciaY = (y1 - y0);
        if(diferenciaY < 0) {
            diferenciaY = -1 * diferenciaY;
            stepy = -1;
        } else
            stepy = 1;
        if(diferenciaX < 0) {
            diferenciaX = -1 * diferenciaX;
            stepx = -1;
        } else
            stepx = 1;
        x3D = x0;
        y3D = y0;
        LineaBresenham(xor,yor,x3D, y3D, c);
        if(diferenciaX > diferenciaY) {
            pk = (2 * diferenciaY) - diferenciaX;
            A = 2 * diferenciaY;
            B = (2 * diferenciaY) - (2 * diferenciaX);
            while(x3D != x1) {
                x3D = x3D + stepx;
                if(pk < 0)
                    pk = pk + A;
                else {
                    y3D = y3D + stepy;
                    pk = pk + B;
                }
            LineaBresenham(xor,yor,x3D, y3D, c);
            }
        } else {
            pk = (2 * diferenciaX) - diferenciaY;
            A = 2 * diferenciaX;
            B = (2 * diferenciaX) - (2 * diferenciaY);
            while(y3D != y1) {
                y3D = y3D + stepy;
                if(pk < 0)
                    pk = pk + A;
                else {
                    x3D = x3D + stepx;
                    pk = pk + B;
                }
            LineaBresenham(xor,yor,x3D, y3D, c);
            }
        }
    }
}
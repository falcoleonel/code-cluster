

import java.awt.Color;
import java.util.ArrayList;


public class Algoritmos {

    private static void LineaBresenham(int x0, int y0, int x1, int y1, Color c) {
        int x, y;
        int diferenciaX, diferenciaY, A, B, pk, stepx, stepy;
        diferenciaX = (x1 - x0);
        diferenciaY = (y1 - y0);
        if (diferenciaY < 0) {
            diferenciaY = -1 * diferenciaY;
            stepy = -1;
        } else {
            stepy = 1;
        }
        if (diferenciaX < 0) {
            diferenciaX = -1 * diferenciaX;
            stepx = -1;
        } else {
            stepx = 1;
        }
        x = x0;
        y = y0;
        Pixel.instance.drawPixel(x, y, c);
        if (diferenciaX > diferenciaY) {
            pk = (2 * diferenciaY) - diferenciaX;
            A = 2 * diferenciaY;
            B = (2 * diferenciaY) - (2 * diferenciaX);
            while (x != x1) {
                x = x + stepx;
                if (pk < 0) {
                    pk = pk + A;
                } else {
                    y = y + stepy;
                    pk = pk + B;
                }
                Pixel.instance.drawPixel(x, y, c);
            }
        } else {
            pk = (2 * diferenciaX) - diferenciaY;
            A = 2 * diferenciaX;
            B = (2 * diferenciaX) - (2 * diferenciaY);
            while (y != y1) {
                y = y + stepy;
                if (pk < 0) {
                    pk = pk + A;
                } else {
                    x = x + stepx;
                    pk = pk + B;
                }
                Pixel.instance.drawPixel(x, y, c);
            }
        }
    }

    private static void Rellenar(int xor, int yor, int x0, int y0, int x1, int y1, Color c) {
        int x, y;
        int diferenciaX, diferenciaY, A, B, pk, stepx, stepy;
        diferenciaX = (x1 - x0);
        diferenciaY = (y1 - y0);
        if (diferenciaY < 0) {
            diferenciaY = -1 * diferenciaY;
            stepy = -1;
        } else {
            stepy = 1;
        }
        if (diferenciaX < 0) {
            diferenciaX = -1 * diferenciaX;
            stepx = -1;
        } else {
            stepx = 1;
        }
        x = x0;
        y = y0;
        LineaBresenham(xor, yor, x, y, c);
        if (diferenciaX > diferenciaY) {
            pk = (2 * diferenciaY) - diferenciaX;
            A = 2 * diferenciaY;
            B = (2 * diferenciaY) - (2 * diferenciaX);
            while (x != x1) {
                x = x + stepx;
                if (pk < 0) {
                    pk = pk + A;
                } else {
                    y = y + stepy;
                    pk = pk + B;
                }
                LineaBresenham(xor, yor, x, y, c);
            }
        } else {
            pk = (2 * diferenciaX) - diferenciaY;
            A = 2 * diferenciaX;
            B = (2 * diferenciaX) - (2 * diferenciaY);
            while (y != y1) {
                y = y + stepy;
                if (pk < 0) {
                    pk = pk + A;
                } else {
                    x = x + stepx;
                    pk = pk + B;
                }
                LineaBresenham(xor, yor, x, y, c);
            }
        }
    }

    public static ArrayList<Point3D> getPoints(Point3D origen, Point3D abertura) {
        ArrayList<Point3D> values = new ArrayList<>();
        int[] arrX = {0, 1, 1, 0};
        int[] arrY = {0, 0, 1, 1};
        int x, y;
        for (int cont = 0; cont < arrX.length; cont++) {
            x = origen.x + (arrX[cont] * abertura.x);
            y = origen.y + (arrY[cont] * abertura.y);
            values.add(new Point3D(x, y, -abertura.z / 2));
        }
        for (int cont = 0; cont < arrX.length; cont++) {
            x = origen.x + (arrX[cont] * abertura.x);
            y = origen.y + (arrY[cont] * abertura.y);
            values.add(new Point3D(x, y, abertura.z / 2));
        }
        return values;
    }

    public static void GetCubo(ArrayList<Point3D> cubePoints, Color c) {
        double x, y, z = 400.0;
        double xp = -60.0, yp = -60.0, zp = -86.0;
        ArrayList<Point3D> puntitos = new ArrayList<>();

        for (Point3D cubePoint : cubePoints) {
            x = cubePoint.x + (xp * ((z - cubePoint.z) / zp));
            y = Pixel.height - (cubePoint.y + (yp * ((z - cubePoint.z) / zp)));
            puntitos.add(new Point3D((int) x, (int) y, 0));
        }
        for (int cont = 0; cont < (puntitos.size() / 2) - 1; cont++) {
            LineaBresenham(puntitos.get(cont).x, puntitos.get(cont).y, puntitos.get(cont + 1).x, puntitos.get(cont + 1).y, c); //1er cuadrante
            LineaBresenham(puntitos.get(cont + 4).x, puntitos.get(cont + 4).y, puntitos.get(cont + 5).x, puntitos.get(cont + 5).y, c); //1er cuadrante
            LineaBresenham(puntitos.get(cont).x, puntitos.get(cont).y, puntitos.get(cont + 4).x, puntitos.get(cont + 4).y, c); //1er cuadrante
        }
        LineaBresenham(puntitos.get(3).x, puntitos.get(3).y, puntitos.get(0).x, puntitos.get(0).y, c); //1er cuadrante
        LineaBresenham(puntitos.get(7).x, puntitos.get(7).y, puntitos.get(4).x, puntitos.get(4).y, c); //1er cuadrante
        LineaBresenham(puntitos.get(3).x, puntitos.get(3).y, puntitos.get(7).x, puntitos.get(7).y, c); //1er cuadrante
    }

    public static void UnirPuntos(ArrayList<Point3D> points, Color c) {
        double x, y, z = 400.0;
        double xp = -60.0, yp = -60.0, zp = -86.0;
        ArrayList<Point3D> puntitos = new ArrayList<Point3D>();
        Color col1, col2, col3;
        for (Point3D point : points) {
            x = point.x + (xp * ((z - point.z) / zp));
            y = Pixel.height - (point.y + (yp * ((z - point.z) / zp)));
            puntitos.add(new Point3D((int) x, (int) y, 0));
        }
        for (int cont = 3; cont < puntitos.size(); cont++) {
            Rellenar(puntitos.get(cont - 3).x, puntitos.get(cont - 3).y, puntitos.get(cont - 2).x, puntitos.get(cont - 2).y, puntitos.get(cont - 1).x, puntitos.get(cont - 1).y, new Color((255 + cont) / puntitos.size(), 255 - (255 * cont) / puntitos.size(), (255 * cont) / puntitos.size()));
//            Rellenar(puntitos.get(cont).x, puntitos.get(cont).y, puntitos.get(cont - 2).x, puntitos.get(cont - 2).y, puntitos.get(cont - 1).x, puntitos.get(cont - 1).y, new Color((100 + cont) / puntitos.size(), 255 - (255 * cont) / puntitos.size(), (255 * cont) / puntitos.size()));
//            Lineas Negras
//            LineaBresenham(puntitos.get(cont-1).x, puntitos.get(cont-1).y, puntitos.get(cont - 3).x, puntitos.get(cont - 3).y, Color.BLACK);
//            LineaBresenham(puntitos.get(cont-2).x, puntitos.get(cont-2).y, puntitos.get(cont - 3).x, puntitos.get(cont - 3).y, Color.BLACK);
//            
//            Lineas de colores
//            LineaBresenham(puntitos.get(cont - 1).x, puntitos.get(cont - 1).y, puntitos.get(cont - 3).x, puntitos.get(cont - 3).y, col1 = new Color(255, 0, 255));
//            LineaBresenham(puntitos.get(cont - 2).x, puntitos.get(cont - 2).y, puntitos.get(cont - 3).x, puntitos.get(cont - 3).y, col2 = new Color(0, 255, 0));
////            
//            Lineas Negras
            LineaBresenham(puntitos.get(cont-1).x, puntitos.get(cont-1).y, puntitos.get(cont - 3).x, puntitos.get(cont - 3).y, Color.BLACK);
            LineaBresenham(puntitos.get(cont-2).x, puntitos.get(cont-2).y, puntitos.get(cont - 3).x, puntitos.get(cont - 3).y, Color.BLACK);
//            
            
            
        }
    }
}

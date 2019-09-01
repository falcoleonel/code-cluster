import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

import javax.swing.*;

public class Dibuja extends BufferedImage {
    Dibuja(int width, int height) {
        super(width, height, BufferedImage.TYPE_INT_RGB);
    }

    /// Metodo para dibujar pixel
    void DibujaPixel(int x, int y, Color color) {
        setRGB(x, y, color.getRGB());
    }

    /// Metodo basico para la linea
    void RectaBasica(int x1, int y1, int x2, int y2, Color color) {
        double m = (double) (y2 - y1) / (x2 - x1); // la pendiente
        double b = -x1 * m + y1;
        int dif = x1 < x2 ? 1 : -1; // para marcar la direccion
        while (true) {
            int y = (int) ((m * x1) + b);
            DibujaPixel(x1, y, color);
            if (x1 == x2)
                break;
            x1 += dif;
        }
    }

    /// Metodo para la linea mejorado
    void RectaBasicaM(int x1, int y1, int x2, int y2, Color color) {
        double m = (double) (y2 - y1) / (x2 - x1);
        double b = -x1 * m + y1;
        if (x1 == x2) {
            int dif = y1 < y2 ? 1 : -1;
            while (true) {
                DibujaPixel(x1, y1, color);
                if (y1 == y2)
                    break;
                y1 += dif;
            }

        }
        // comprobamos m al cuadrado para dictar si se grafica respecto de y o de x

        if ((m * m) > 1) {
            int dif = y1 < y2 ? 1 : -1;
            while (true) {
                int x = (int) ((y1 - b) / m);
                DibujaPixel(x, y1, color);
                if (y1 == y2)
                    break;
                y1 += dif;
            }
        } else {
            int dif = x1 < x2 ? 1 : -1;
            while (true) {
                int y = (int) ((m * x1) + b);
                DibujaPixel(x1, y, color);
                if (x1 == x2)
                    break;
                x1 += dif;
            }
        }
    }

    /// Metodo Bresenham para la linea
    void RectaBresenham(int x1, int y1, int x2, int y2, Color color) {
        if ((y2 - y1) < (x2 - x1)) {
            DibujaPixel(x1, y1, color);

            int A = 2 * (y2 - y1);
            int B = 2 * (y2 - y1) - 2 * (x2 - x1);

            int p = 2 * (y2 - y1) - (x2 - x1);

            int x = x1;
            int y = y1;

            while (true) {
                if (p < 0) {
                    x = x + 1;
                    DibujaPixel(x, y, color);
                    p = p + A;
                } else if (p > 0) {
                    x = x + 1;
                    y = y + 1;
                    DibujaPixel(x, y, color);
                    p = p + B;
                }
                if (x >= x2)
                    break;
            }
        } else {
            DibujaPixel(x1, y1, color);

            int A = 2 * (x2 - x1);
            int B = 2 * (x2 - x1) - 2 * (y2 - y1);

            int p = 2 * (x2 - x1) - (y2 - y1);

            int x = x1;
            int y = y1;

            while (true) {
                if (p < 0) {
                    y = y + 1;
                    DibujaPixel(x, y, color);
                    p = p + A;
                } else if (p > 0) {
                    x = x + 1;
                    y = y + 1;
                    DibujaPixel(x, y, color);
                    p = p + B;
                }
                if (y >= x2)
                    break;
            }
        }
    }

    /// Metodo del punto medio
    void RectaPuntoMedio(int x1, int y1, int x2, int y2, Color color) {
        if ((y2 - y1) < (x2 - x1)) {
            DibujaPixel(x1, y1, color);

            int A = 2 * (y2 - y1);
            int B = 2 * (y2 - y1) - 2 * (x2 - x1);

            int punto = 2 * (y2 - y1) - (x2 - x1);

            int x = x1;
            int y = y1;

            while (true) {
                if (punto < 0) {
                    x = x + 1;
                    DibujaPixel(x, y, color);
                    punto = punto + A;
                } else if (punto > 0) {
                    x = x + 1;
                    y = y + 1;
                    DibujaPixel(x, y, color);
                    punto = punto + B;
                }
                if (x >= x2)
                    break;
            }
        } else {
            DibujaPixel(x1, y1, color);

            int A = 2 * (x2 - x1);
            int B = 2 * (x2 - x1) - 2 * (y2 - y1);

            int punto = 2 * (x2 - x1) - (y2 - y1);

            int x = x1;
            int y = y1;

            while (true) {
                if (punto < 0) {
                    y = y + 1;
                    DibujaPixel(x, y, color);
                    punto = punto + A;
                } else if (punto >= 0) {
                    x = x + 1;
                    y = y + 1;
                    DibujaPixel(x, y, color);
                    punto = punto + B;
                }
                if (y >= x2)
                    break;
            }
        }
    }

    /// Metodo para dibujar rectangulo
    void Rectangulo(int x1, int y1, int x2, int y2, Color color) {
        // puntos que dibujaran el perimetro del rectangulo
        int x = x1;
        int y = y1;
        while (x != x2) {
            x = x + 1;
            DibujaPixel(x, y, color);
        }
        while (y != y2) {
            y = y + 1;
            DibujaPixel(x, y, color);
        }
        while (x != x1) {
            x = x - 1;
            DibujaPixel(x, y, color);
        }
        while (y != y1) {
            y = y - 1;
            DibujaPixel(x, y, color);
        }
    }

    /// Metodo para dibujar circulo
    void Circulo(int xc, int yc, int xE, int yE, Color color) {
        int xT;
        double yT;
        double squareRadio = Math.pow((xE - xc), 2) - Math.pow((yE - yc), 2);
        for (xT = xc - (int) Math.sqrt(squareRadio); xT <= xc + (int) Math.sqrt(squareRadio); xT++) {
            // dibujar semicirculos
            yT = (double) yc + Math.sqrt(squareRadio - Math.pow((xT - xc), 2));
            DibujaPixel(xT, (int) yT, color);
            yT = (double) yc - Math.sqrt(squareRadio - Math.pow((xT - xc), 2));
            DibujaPixel(xT, (int) yT, color);
        }
    }

    /// Metodo para dibujar circulo con coordenadas polares
    void CirculoPolares(int xc, int yc, int radio, Color color) {

        double x, y;
        for (double t = 0; t < 2; t = t + .005) {

            x = xc + radio * Math.sin(t * Math.PI);
            y = yc + radio * Math.cos(t * Math.PI);
            DibujaPixel((int) Math.round(x), (int) Math.round(y), color);
        }
    }
}

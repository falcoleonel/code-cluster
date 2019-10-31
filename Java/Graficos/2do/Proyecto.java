import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Proyecto extends JFrame {

    public static void main(String[] args) {
        new Proyecto().setVisible(true);
    }

    // Método para dibujar pixel
    public static void dibujarPixel(BufferedImage img, int x, int y, Color color) {
        if (x >= 0 && y >= 0 && x < img.getWidth() && y < img.getHeight())
            img.setRGB(x, y, color.getRGB());
    }

    //Método para dibujar circulo con bresenham
    public static void dibujarCirculo(BufferedImage img, int x, int y, int r, int grosor, Color color) {

        int X = 0;
        int Y = r;
        int delta = 2 * (1 - r);
        int error;

        while (Y >= X) {
            for (int w = -(int) Math.floor(grosor / 2f); w < (int) Math.ceil(grosor / 2f); w++)
                for (int h = -(int) Math.floor(grosor / 2f); h < (int) Math.ceil(grosor / 2f); h++) {
                    dibujarPixel(img, x + w + X, y + h + Y, color);
                    dibujarPixel(img, x + w - X, y + h + Y, color);
                    dibujarPixel(img, x + w + X, y + h - Y, color);
                    dibujarPixel(img, x + w - X, y + h - Y, color);
                    dibujarPixel(img, x + w + Y, y + h + X, color);
                    dibujarPixel(img, x + w - Y, y + h + X, color);
                    dibujarPixel(img, x + w + Y, y + h - X, color);
                    dibujarPixel(img, x + w - Y, y + h - X, color);
                }

            error = 2 * (delta + Y) - 1;
            if (delta < 0 && error <= 0) {
                delta += 2 * ++X + 1;
                continue;
            }

            error = 2 * (delta - X) - 1;
            if (delta > 0 && error > 0) {
                delta += 1 - 2 * --Y;
                continue;
            }

            delta += 2 * (++X - Y--);
        }
    }

    // Método dibujar recta con bresenham
    public static void dibujarRecta(BufferedImage img, int x1, int y1, int x2, int y2, int grosor, Color color) {

        int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1, sy = y1 < y2 ? 1 : -1;
        int err = (dx > dy ? dx : -dy) / 2, e2;

        int mitad = (int) Math.floor(grosor / 2);
        while (true) {
            for (int x = -mitad; x <= mitad; x++)
                for (int y = -mitad; y <= mitad; y++)
                    dibujarPixel(img, x1 + x, y1 + y, color);
            if (x1 == x2 && y1 == y2)
                break;
            e2 = err;

            if (e2 > -dx) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dy) {
                err += dx;
                y1 += sy;
            }
        }
    }

    //Método relleno linea a linea
    public static void rellenarRectangulo(BufferedImage img, int x1, int y1, int x2, int y2, Color color) {
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++)
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++)
                dibujarPixel(img, x, y, color);
    }

    //Método relleno linea a linea para circulo con bresenham
    public static void rellenarCirculo(BufferedImage img, int x, int y, int r, Color color) {

        int X = 0;
        int Y = r;
        int delta = 2 * (1 - r);
        int error;

        while (Y >= 0) {

            for (int h = x - X; h <= x + X; h++) {
                dibujarPixel(img, h, y + Y, color);
                dibujarPixel(img, h, y - Y, color);
            }

            error = 2 * (delta + Y) - 1;
            if (delta < 0 && error <= 0) {
                delta += 2 * ++X + 1;
                continue;
            }

            error = 2 * (delta - X) - 1;
            if (delta > 0 && error > 0) {
                delta += 1 - 2 * --Y;
                continue;
            }

            delta += 2 * (++X - Y--);
        }
    }

    // Parametros 
    BufferedImage fondo;
    Graphics2D gf;
    Dimension dimVentana;
    Timer timer;
    double t;

    public Proyecto() {

        t = 0;
        fondo = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
        gf = fondo.createGraphics();
        // Color de Fondo
        gf.setBackground(new Color(33,41,54));
        inicializar();
    }

    private void inicializar() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FFVI - Tren Fantasma");
        setResizable(false);
        Dimension dimPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        dimVentana = new Dimension(800, 600);
        setSize(dimVentana);
        setLocation((dimPantalla.width - dimVentana.width) / 2, (dimPantalla.height - dimVentana.height) / 2);

        timer = new Timer(1000 / 30, e -> { 
            t += 1.0 / 20; // velocidad
            gf.clearRect(0, 0, dimVentana.width, dimVentana.height);
            dibujar(fondo, t);
            repaint();
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, this);
    }

    //Método para calcular valor de X para animaciones que salen de la pantalla
    public static int encerrar(int a, int b, int v) {
        return (v - b) % (b - a) + a + b;
    }

    // Método donde se crean las figuras
    public void dibujar(BufferedImage img, double t) {
        double ct = Math.cos(Math.PI * t);
        double st = Math.sin(Math.PI * t);
        int vn = (int) (30 * t);

        //luna
        rellenarCirculo(img, 335, 110, 60, new Color(137,200,235));
        rellenarCirculo(img, 374, 110, 43, new Color(33,41,54));
        //Arbol 
        dibujarRecta(img, encerrar(-50, 880, 100 - vn), 300, encerrar(-50, 880, 100 - vn), 480, 36, new Color(22,21,26));
        dibujarRecta(img, encerrar(-50, 880, 115 - vn), 380, encerrar(-50, 880, 115 - vn) + 70, 330, 10, new Color(22,21,26));
        dibujarRecta(img, encerrar(-50, 880, 96 - vn), 370, encerrar(-50, 880, 96 - vn) - 60, 320, 10, new Color(22,21,26));

        //Ruedas 
        for (int i = 0; i < 6; i++) {
            rellenarCirculo(img, 130 + 104 * i, 450, 45, new Color(43,38,35));
            dibujarCirculo(img, 130 + 104 * i, 450, 50, 10, Color.DARK_GRAY);
        }

        //Maquinaria
        dibujarRecta(img, 134 + (int) (45 * ct), 440 + (int) (15 * st), 652 + (int) (45 * ct), 440 + (int) (15 * st), 7,
                Color.GRAY);
        dibujarRecta(img, 129 + (int) (45 * ct), 460 + (int) (15 * st), 657 + (int) (45 * ct), 460 + (int) (15 * st), 7,
                Color.GRAY);
        dibujarRecta(img, 137 + (int) (45 * ct), 430 + (int) (15 * st), 137 + (int) (45 * ct), 470 + (int) (15 * st), 7,
                Color.GRAY);
        dibujarRecta(img, 649 + (int) (45 * ct), 430 + (int) (15 * st), 649 + (int) (45 * ct), 470 + (int) (15 * st), 7,
                Color.GRAY);

        //Suelo
        rellenarRectangulo(img, 0, 500, 800, 600, new Color (48,33,29));
        // Vias
        rellenarRectangulo(img, 0, 510, 800, 500, Color.DARK_GRAY);


        //nubes
        rellenarCirculo(img, encerrar(-150, 980, 100 - vn), 110, 50, new Color(64,63,58));
        rellenarCirculo(img, encerrar(-150, 980, 150 - vn), 110, 50, new Color(64,63,58));
        rellenarCirculo(img, encerrar(-150, 980, 70 - vn), 130, 50, new Color(64,63,58));
        rellenarCirculo(img, encerrar(-150, 980, 110 - vn), 90, 50, new Color(64,63,58));

        rellenarCirculo(img, encerrar(-150, 980, 700 - vn), 130, 50, new Color(64,63,58));
        rellenarCirculo(img, encerrar(-150, 980, 770 - vn), 110, 50, new Color(64,63,58));
        rellenarCirculo(img, encerrar(-150, 980, 650 - vn), 80, 50, new Color(64,63,58));
        rellenarCirculo(img, encerrar(-150, 980, 720 - vn), 90, 50, new Color(64,63,58));

        // Cuerpo 
        rellenarRectangulo(img, 80, 250, 660, 400, new Color(144,118,69));
        // Cabina
        rellenarRectangulo(img, 80, 140, 280, 400, new Color(113,90,73));
        // Ventanas
        rellenarRectangulo(img, 100, 170, 260, 240, new Color(153,220,237));
        // Marco
        rellenarRectangulo(img, 175, 170, 185, 240, Color.BLACK);
        // Cara
        rellenarRectangulo(img, 660, 250, 700, 400, new Color(115,87,73));
        // Chimenea
        rellenarRectangulo(img, 600, 250, 645, 140, new Color(166,138,88));

        //humo
        rellenarCirculo(img, encerrar(-100, 700, 300 - (int) (300 * t)), 150 + (int) (50 * ct), 30, Color.GRAY);
        rellenarCirculo(img, encerrar(-100, 700, 459 - (int) (300 * t)), 145 + (int) (45 * st), 35, Color.GRAY);
        rellenarCirculo(img, encerrar(-100, 700, 640 - (int) (300 * t)), 155 + (int) (50 * ct), 40, Color.GRAY);
        rellenarCirculo(img, encerrar(-100, 700, 819 - (int) (300 * t)), 150 + (int) (55 * st), 45, Color.GRAY);
    }
}

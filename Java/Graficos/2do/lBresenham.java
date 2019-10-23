import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class lBresenham extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public lBresenham() {
        int ancho = 800, alto = 600;
        setTitle("Algoritmo Bresenham para lineas");
        setSize(ancho, alto);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main(String[] args) {
        lBresenham bresenham = new lBresenham();

        bresenham.drawLine(55, 100, 200, 100, new Color(0, 153, 0));
        bresenham.drawLine(700, 100, 555, 100, new Color(0, 102, 204));

        bresenham.drawLine(115, 200, 115, 300, new Color(0, 102, 204));
        bresenham.drawLine(700, 300, 700, 200, new Color(0, 153, 0));

        bresenham.drawLine(50, 450, 150, 550, new Color(0, 153, 0));
        bresenham.drawLine(600, 550, 700, 450, new Color(0, 102, 204));
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        int p, a, b, x = x1, y = y1;
        // Utiliza las diferencias para saber cual esta mas cerca del punto
        int dx = (x2 - x1);
        int dy = (y2 - y1);

        // Calcula los pasos en x y en y
        int stepy = (dy < 0) ? -1 : 1;
        int stepx = (dx < 0) ? -1 : 1;

        dy *= stepy;
        dx *= stepx;

        // Calcula a partir del anterior
        // Itera en el mayor
        if (dx > dy) {
            // Se obtiene el valor de p
            p = 2 * dy - dx;
            a = 2 * dy;
            b = 2 * (dy - dx);
            // Hasta que llegue al punto
            while (x != x2) {
                // Formula
                if (p < 0)
                    p += a;
                else {
                    y += stepy;
                    p += b;
                }
                putPixel(x, y, color);
                // Se hacen los pasos en x
                x += stepx;
            }
        } else {
            // Se obtiene el valor de p
            p = 2 * dx - dy;
            a = 2 * dx;
            b = 2 * (dx - dy);

            // Hasta que llegue al punto
            while (y != y2) {
                // Formula
                if (p < 0)
                    p += a;
                else {
                    x += stepx;
                    p += b;
                }

                putPixel(x, y, color);
                // Se hacen los pasos en y
                y += stepy;
            }
        }
    }

    private void putPixel(int x, int y, Color color) {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
}

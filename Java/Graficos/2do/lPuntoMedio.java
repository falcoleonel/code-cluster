import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class lPuntoMedio extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public lPuntoMedio() {
        int ancho = 800, alto = 600;
        setSize(ancho, alto);
        setTitle("Algoritmo Punto Medio para lineas");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main(String[] args) {
        lPuntoMedio puntoMedio = new lPuntoMedio();

        puntoMedio.drawLine(55, 100, 200, 100, new Color(0, 153, 0));
        puntoMedio.drawLine(700, 100, 250, 100, new Color(0, 102, 204));

        puntoMedio.drawLine(115, 200, 115, 300, new Color(0, 102, 204));
        puntoMedio.drawLine(700, 300, 700, 200, new Color(0, 153, 0));

        puntoMedio.drawLine(50, 450, 150, 550, new Color(0, 153, 0));
        puntoMedio.drawLine(600, 550, 700, 450, new Color(0, 102, 204));

    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        int p, a, b, x = x1, y = y1;

        // Utiliza las diferencias para saber cual esta mas cerca del punto
        int dx = (x2 - x1);
        int dy = (y2 - y1);

        // Calcula los pasos en base la mayor diferencia, en valor absoluto
        int steps = (Math.abs(dx) > Math.abs(dy)) ? Math.abs(dx) : Math.abs(dy);

        // Calcula los pasos en x y en y
        int stepy = (dy < 0) ? -1 : 1;
        int stepx = (dx < 0) ? -1 : 1;

        dy *= stepy;
        dx *= stepx;

        if (dx > dy) {
            p = 2 * dy - dx;
            a = 2 * dy;
            b = 2 * (dy - dx);

            // Iterar con los pasos de acuerdo a la mayor diferencia
            for (int k = 0; k < steps; k++) {
                putPixel(x, y, color);
                if (p < 0)
                    p += a;
                else {
                    y += stepy;
                    p += b;
                }
                x += stepx;
            }
        } else {
            p = 2 * dx - dy;
            a = 2 * dx;
            b = 2 * (dx - dy);
            // Iterar con los pasos de acuerdo a la mayor diferencia
            for (int k = 0; k < steps; k++) {
                putPixel(x, y, color);
                if (p < 0)
                    p += a;
                else {
                    x += stepx;
                    p += b;
                }
                y += stepy;
            }
        }
    }

    private void putPixel(int x, int y, Color color) {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
}

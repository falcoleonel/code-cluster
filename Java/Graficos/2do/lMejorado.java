import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class lMejorado extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;

    public lMejorado() {
        int ancho = 800, alto = 600;
        setSize(ancho, alto);
        setTitle("Linea Básico Mejorado");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main(String[] args) {
        lMejorado mejorado = new lMejorado();

        mejorado.drawLine(55, 100, 200, 100, new Color(0, 153, 0));
        mejorado.drawLine(700, 100, 555, 100, new Color(0, 102, 204));

        mejorado.drawLine(115, 200, 115, 300, new Color(0, 102, 204));
        mejorado.drawLine(700, 300, 700, 200, new Color(0, 153, 0));

        mejorado.drawLine(85, 400, 185, 550, new Color(0, 153, 0));
        mejorado.drawLine(550, 500, 650, 350, new Color(0, 102, 204));
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        int dx = x2 - x1;
        int dy = y2 - y1;
        float y = 0;

        if (dx != 0) {
            // Si es diferente m y b se calculan igual, se itera en x
            float m = (y2 - y1) / ((x2 - x1) == 0 ? 1 : (x2 - x1));
            float b = y1 - (m * x1);

            // Si la diferencia es mayor a 0 se itera desde x1 a x2
            if (dx > 0) {
                for (int x = x1; x <= x2; x++) {
                    y = (m * x) + b;
                    putPixel(x, Math.round(y), color);
                }
            }
            // Si la diferencia es menor a 0 se itera desde x2 a x1
            else {
                for (int x = x1; x >= x2; x--) {
                    y = (m * x) + b;
                    putPixel(x, Math.round(y), color);
                }
            }
        } else {
            // Se itera en y
            float m = (y2 - y1) / ((x2 - x1) == 0 ? 1 : (x2 - x1));

            // De y1 a y2
            if (dy > 0) {
                for (y = y1; y <= y2; y++) {
                    putPixel(x1, Math.round(y), color);
                }
            }

            // De y2 a y1
            else {
                for (y = y1; y >= y2; y--) {
                    putPixel(x1, Math.round(y), color);
                }
            }
        }

    }

    private void putPixel(int x, int y, Color color) {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
}

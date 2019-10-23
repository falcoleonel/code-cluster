import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Rectangulo extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;

    public Rectangulo() {
        int ancho = 800, alto = 600;
        setSize(ancho, alto);
        setTitle("Rectangulo");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main(String[] args) {
        Rectangulo rectangulo = new Rectangulo();

        rectangulo.drawRectangle(0, 0, 25, 25, new Color(238, 238, 238));
        rectangulo.drawRectangle(50, 50, 150, 590, new Color(0, 153, 0));
        rectangulo.drawRectangle(150, 150, 250, 590, new Color(0, 102, 204));
        rectangulo.drawRectangle(250, 250, 350, 590, new Color(0, 153, 0));
        rectangulo.drawRectangle(350, 350, 450, 590, new Color(0, 102, 204));
        rectangulo.drawRectangle(450, 450, 550, 590, new Color(0, 153, 0));
        rectangulo.drawRectangle(550, 550, 650, 590, new Color(0, 102, 204));
    }

    public void drawRectangle(int x1, int y1, int x2, int y2, Color color) {
        int temp = 0;
        // empezar desde el valor mas pequeño hasta el mas grande
        if (x1 > x2) {
            temp = x1;
            x1 = x2;
            x2 = temp;
        }

        if (y1 > y2) {
            temp = y1;
            y1 = y2;
            y2 = temp;
        }
        // Iterar de x1 a x2 con los mismos valores de y
        for (int x = x1; x <= x2; x++) {
            putPixel(x, y1, color);
            putPixel(x, y2, color);
        }
        // Iterar de y1 a y2 con los mismos valores de x
        for (int y = y1; y <= y2; y++) {
            putPixel(x1, y, color);
            putPixel(x2, y, color);
        }
    }

    private void putPixel(int x, int y, Color color) {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
}

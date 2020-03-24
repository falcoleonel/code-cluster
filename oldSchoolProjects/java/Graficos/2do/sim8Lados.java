import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class sim8Lados extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public sim8Lados() {
        int ancho = 800, alto = 600;

        setSize(ancho, alto);
        setTitle("Simetria Ocho Lados");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main(String[] args) {
        sim8Lados circulo = new sim8Lados();
        circulo.drawCircle(300, 100, 40, new Color(102, 178, 255));
        circulo.drawCircle(400, 200, 50, new Color(51, 153, 255));
        circulo.drawCircle(300, 335, 60, new Color(0, 128, 255));
        circulo.drawCircle(400, 475, 70, new Color(0, 102, 204));
    }

    public void drawCircle(int x1, int y1, int r, Color color) {
        // sirve para reducir el calculo, primer octante es simétrico al segundo a
        // traves de una diagonal
        int x = 0;
        int y = 0;

        putPixel(x, y + r, color);
        putPixel(x, y - r, color);
        putPixel(x + r, y, color);
        putPixel(x - r, y, color);
        x = 1;
        y = (int) (Math.sqrt(r * r - x * x));
        while (x < y) {
            // octantes
            putPixel(x1 + x, y1 + y, color);
            putPixel(x1 + x, y1 - y, color);
            putPixel(x1 - x, y1 + y, color);
            putPixel(x1 - x, y1 - y, color);
            putPixel(x1 + y, y1 + x, color);
            putPixel(x1 + y, y1 - x, color);
            putPixel(x1 - y, y1 + x, color);
            putPixel(x1 - y, y1 - x, color);
            x += 1;
            y = (int) (Math.sqrt(r * r - x * x));
        }
    }

    private void putPixel(int x, int y, Color color) {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
}

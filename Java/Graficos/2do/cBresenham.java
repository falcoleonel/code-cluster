import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class cBresenham extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public cBresenham() {
        int ancho = 800, alto = 600;

        setSize(ancho, alto);
        setTitle("Bresenham Circulo");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main(String[] args) {
        cBresenham circulo = new cBresenham();
        circulo.drawCircle(300, 100, 40, new Color(102, 178, 255));
        circulo.drawCircle(400, 200, 50, new Color(51, 153, 255));
        circulo.drawCircle(300, 335, 60, new Color(0, 128, 255));
        circulo.drawCircle(400, 475, 70, new Color(0, 102, 204));
    }

    public void drawCircle(int cx, int cy, double r, Color color) {
        double p = 5d / 4d - r;
        int y, x = 0, dy, dx = 1;

        r = Math.abs(r);
        // Utiliza las diferencias para saber cual esta¡ mas cerca del punto
        dy = -2 * (int) r;
        // si R es entero, p0 = 1 - r
        if (r % 1 == 0)
            p = 1 - r;
        y = (int) r;
        // desde x=0 hasta x=y
        while (x <= y) {
            // octantes
            putPixel(cx + x, cy + y, color);
            putPixel(cx - x, cy + y, color);
            putPixel(cx + x, cy - y, color);
            putPixel(cx - x, cy - y, color);
            putPixel(cx + y, cy + x, color);
            putPixel(cx - y, cy + x, color);
            putPixel(cx + y, cy - x, color);
            putPixel(cx - y, cy - x, color);
            // Formula
            if (p >= 0) {
                dy += 2;
                p += dy;
                y--;
            }
            dx += 2;
            p += dx;
            x++;
        }
    }

    private void putPixel(int x, int y, Color color) {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
}

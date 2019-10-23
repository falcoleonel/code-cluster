import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class cPuntoMedio extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public cPuntoMedio() {
        int ancho = 800, alto = 600;

        setSize(ancho, alto);
        setTitle("Punto Medio");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main(String[] args) {
        cPuntoMedio circulo = new cPuntoMedio();
        circulo.drawCircle(300, 100, 40, new Color(102, 178, 255));
        circulo.drawCircle(400, 200, 50, new Color(51, 153, 255));
        circulo.drawCircle(300, 335, 60, new Color(0, 128, 255));
        circulo.drawCircle(400, 475, 70, new Color(0, 102, 204));
    }

    public void drawCircle(int h, int k, double r, Color color) {
        // determinar el pixel mas cercano a la circunferencia
        int x = 0, y = (int) r;
        double p = 5d / 4d - r;
        if (r % 1 == 0)
            // si R es entero, p0 = 1 - r
            p = 1 - r;
        // desde x=0 hasta x=y
        while (x < y) {
            // octantes
            putPixel(x + h, y + k, color);
            putPixel(-x + h, y + k, color);
            putPixel(x + h, -y + k, color);
            putPixel(-x + h, -y + k, color);
            putPixel(y + h, x + k, color);
            putPixel(-y + h, x + k, color);
            putPixel(y + h, -x + k, color);
            putPixel(-y + h, -x + k, color);

            if (p < 0) {
                p = p + (2 * x) + 3;
            } else {
                p = p + (2 * (x - y)) + 5;
                y--;
            }
            x++;
        }
    }

    private void putPixel(int x, int y, Color color) {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
}

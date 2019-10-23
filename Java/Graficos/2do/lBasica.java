import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class lBasica extends JFrame {
    private Graphics graPixel;
    private BufferedImage buffer;

    public lBasica() {
        int ancho = 800, alto = 600;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Linea Básica");
        setSize(ancho, alto);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

    }

    public static void main(String[] args) {
        lBasica basico = new lBasica();

        basico.drawLine(55, 100, 200, 100, new Color(0, 153, 0));
        basico.drawLine(300, 100, 250, 100, new Color(0, 102, 204));

        basico.drawLine(125, 160, 125, 200, new Color(0, 102, 204));
        basico.drawLine(300, 290, 300, 160, new Color(0, 153, 0));

        basico.drawLine(85, 400, 185, 550, new Color(0, 153, 0));
        basico.drawLine(300, 100, 250, 100, new Color(0, 102, 204));
    }

    private void putPixel(int x, int y, Color color) {

        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        // y = mx + b
        double y = 0;
        double m = (y2 - y1) / ((x2 - x1) == 0 ? 1 : (x2 - x1));
        double b = y1 - (m * x1);

        for (int x = x1; x <= x2; x++) {
            y = (m * x) + b;
            putPixel(x, (int) Math.round(y), color);
        }
    }
}

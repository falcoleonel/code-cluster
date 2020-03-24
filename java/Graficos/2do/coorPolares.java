import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class coorPolares extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public coorPolares() {

        int ancho = 800, alto = 600;
        setSize(ancho, alto);
        setTitle("Modelo coordenadas Polares");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main(String[] args) {
        coorPolares circulo = new coorPolares();
        circulo.drawCircle(300, 100, 40, new Color(102, 178, 255));
        circulo.drawCircle(400, 200, 50, new Color(51, 153, 255));
        circulo.drawCircle(300, 335, 60, new Color(0, 128, 255));
        circulo.drawCircle(400, 475, 70, new Color(0, 102, 204));
    }

    public void drawCircle(int cx, int cy, int r, Color color) {
        double step, y, x;
        r = Math.abs(r);
        step = 5 / (double) r;

        for (double t = cx - r; t <= cx + r; t += step) {
            // incremento del angulo suficientemente pequeño para evitar los huecos
            y = (r * Math.cos(t));
            x = (r * Math.sin(t));

            putPixel(cx + (int) x, (int) Math.round(cy + y), color);
            putPixel(cx + (int) x, (int) Math.round(cy - y), color);
        }
    }

    private void putPixel(int x, int y, Color color) {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

}

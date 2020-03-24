import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class coorCartesianas extends JFrame {
    private Graphics graPixel;
    private BufferedImage buffer;

    public coorCartesianas() {
        int ancho = 800, alto = 600;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Modelo coordenadas Cartesianas");
        setSize(ancho, alto);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

    }

    public static void main(String[] args) {
        coorCartesianas circulo = new coorCartesianas();

        circulo.drawCircle(50, 50, 2, new Color(238, 238, 238));
        circulo.drawCircle(300, 100, 40, new Color(102, 178, 255));
        circulo.drawCircle(400, 200, 50, new Color(51, 153, 255));
        circulo.drawCircle(300, 335, 60, new Color(0, 128, 255));
        circulo.drawCircle(400, 475, 70, new Color(0, 102, 204));
    }

    private void putPixel(int x, int y, Color color) {

        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void drawCircle(int cx, int cy, int radio, Color color) {
        double dif;
        float step;

        radio = Math.abs(radio);
        step = 1 / (float) radio;
        // Formula simple del teorema de pitagoras, en base a catetos
        for (float x = cx - radio; x <= cx + radio; x += step) {
            dif = Math.sqrt(Math.pow(radio, 2) - Math.pow(x - cx, 2));

            putPixel((int) x, (int) Math.round(cy + dif), color);
            putPixel((int) x, (int) Math.round(cy - dif), color);
        }
    }

}

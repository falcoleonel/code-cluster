import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class lDDA extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public lDDA() {
        int ancho = 800, alto = 600;
        setTitle("Algoritmo DDA para líneas");
        setSize(ancho, alto);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main(String[] args) {
        lDDA dda = new lDDA();

        dda.drawLine(55, 100, 200, 100, new Color(0, 153, 0));
        dda.drawLine(700, 100, 555, 100, new Color(0, 102, 204));

        dda.drawLine(115, 200, 115, 300, new Color(0, 102, 204));
        dda.drawLine(700, 300, 700, 200, new Color(0, 153, 0));

        dda.drawLine(50, 450, 150, 550, new Color(0, 153, 0));
        dda.drawLine(600, 550, 700, 450, new Color(0, 102, 204));
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        // Se elimina B
        // Diferencias
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = 0;

        // Se itera la diferencia del mayor, el redondeo es muy lento
        if (Math.abs(dx) > Math.abs(dy))
            steps = Math.abs(dx);
        else
            steps = Math.abs(dy);

        int xinc = dx / steps;
        int yinc = dy / steps;

        int x = x1;
        int y = y1;

        // Se crea el primer pixel
        putPixel(Math.round(x), Math.round(y), color);

        // Se hacen las iteraciones
        for (int k = 1; k <= steps; k++) {
            x = x + xinc;
            y = y + yinc;
            putPixel(Math.round(x), Math.round(y), color);
        }
    }

    public void putPixel(int x, int y, Color color) {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

}

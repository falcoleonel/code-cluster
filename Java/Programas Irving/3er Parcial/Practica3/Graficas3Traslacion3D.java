
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import static java.lang.Math.round;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Graficas3Traslacion3D extends JFrame implements KeyListener{
     public Cubo c1;
    private BufferedImage pixel;
    private int x1b, y1b, x0r = 100, y0r = 50, x1r = 350, y1r = 300, unidad, y2, x2;
    double x, y, z = 200.0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Graficas3Traslacion3D();
    }
    
    private Graficas3Traslacion3D() {
        super("Traslacion");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setVisible(true);
        this.addKeyListener(this);
        pixel = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        x2 = 250;
        y2 = 190;
        unidad = 50;
    }

    private void putPixel(int x, int y, Color c) {
        pixel.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(pixel, x, y, this);
    }

    public void cuboPerspectiva(int x1, int y1, int z1, int large, Color c) {
        ArrayList<Integer> valuesX1 = new ArrayList<>();
        ArrayList<Integer> valuesY1 = new ArrayList<>();
        ArrayList<Integer> valuesX2 = new ArrayList<>();
        ArrayList<Integer> valuesY2 = new ArrayList<>();
        int[] arrX = {0, 1, 1, 0, 0};
        int[] arrY = {0, 0, 1, 1, 0};

        double xc = 100;
        double yc = 100;
        double zc = -200.0;
        for (int cont = 0; cont < arrX.length; cont++) {
            double tempX, tempY;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large);
            x = tempX + (xc * ((z - z1) / zc));
            y = y2 - (tempY + (yc * ((z - z1) / zc)));
            valuesX1.add((int) x);
            valuesY1.add((int) y);
        }
        for (int cont = 0; cont < arrX.length; cont++) {
            double tempX, tempY;
            double tempZ = z1 + large;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large);
            x = tempX + (xc * ((z - tempZ) / zc));
            y = y2 - (tempY + (yc * ((z - tempZ) / zc)));
            valuesX2.add((int) x);
            valuesY2.add((int) y);
        }
        for (int cont = 1; cont < valuesX1.size(); cont++) {
            dibujarLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX1.get(cont), valuesY1.get(cont), c);
            dibujarLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), valuesX2.get(cont), valuesY2.get(cont), c);
            dibujarLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX2.get(cont - 1), valuesY2.get(cont - 1), c);
        }
    }

    public void dibujarLinea(int x1, int y1, int x2, int y2, Color c) {

        double m, b;
        double dx = x2 - x1;
        double dy = y2 - y1;
        m = dy / dx;
        b = y1 - (m * x1);
        double y = 0;
        if (Double.isInfinite(m)) {//vertical inevrtido y normal
            if (y1 > y2) {
                int xaux;
                xaux = x1;
                x1 = x2;
                x2 = xaux;

                int yaux;
                yaux = y1;
                y1 = y2;
                y2 = yaux;

                for (int yi = y1; yi < y2; yi++) {
                    putPixel(x1, (int) round(yi), c);

                }
            } else {

                for (int yi = y1; yi < y2; yi++) {
                    putPixel(x1, (int) round(yi), c);

                }

            }

        } else {

            if (x1 > x2) { //horizontal inverso y diagonal
                int xaux;
                xaux = x1;
                x1 = x2;
                x2 = xaux;

                int yaux;
                yaux = y1;
                y1 = y2;
                y2 = yaux;

                for (int x = x1; x < x2; x++) {
                    y = (m * x) + b;
                    putPixel(x, (int) round(y), c);
                }

            } else {
                for (int x = x1; x < x2; x++) {
                    y = (m * x) + b;
                    putPixel(x, (int) round(y), c);
                }

            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        cuboPerspectiva(x2, 0, 30, unidad, Color.GREEN);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37)//izquierda
        {
            x2 -= 10;
        } else if (e.getKeyCode() == 38)//arriba
        {
            y2 -= 10;
        } else if (e.getKeyCode() == 39)//derecha
        {
            x2 += 10;
        } else if (e.getKeyCode() == 40)//abajo
        {
            y2 += 10;
        } else if (e.getKeyCode() == 83)//abajo
        {
            z += 10;
        } else if (e.getKeyCode() == 90)//abajo
        {
            z -= 10;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
    
}

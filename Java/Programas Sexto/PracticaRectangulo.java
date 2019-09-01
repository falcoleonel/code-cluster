import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PracticaRectangulo extends JFrame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> new PracticaRectangulo().setVisible(true));
    }

    Dibuja dibuja;

    public PracticaRectangulo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        dibuja = new Dibuja(800, 600);
        dibuja.Rectangulo(100, 100, 250, 200, Color.cyan);
        dibuja.Rectangulo(105, 105, 375, 305, Color.red);
        dibuja.Rectangulo(110, 110, 510, 410, Color.green);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(dibuja, 0, 0, this);
    }
}

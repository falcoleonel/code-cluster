Bimport java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PracticaPixel extends JFrame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> new PracticaPixel().setVisible(true));
    }

    Dibuja dibuja;

    public PracticaPixel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        dibuja = new Dibuja(800, 600);
        dibuja.DibujaPixel(200, 200, Color.RED);
        dibuja.DibujaPixel(220, 220, Color.GREEN);
        dibuja.DibujaPixel(240, 200, Color.RED);
        dibuja.DibujaPixel(260, 180, Color.GREEN);
        dibuja.DibujaPixel(280, 200, Color.RED);
        dibuja.DibujaPixel(300, 220, Color.GREEN);
        dibuja.DibujaPixel(320, 200, Color.RED);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(dibuja, 0, 0, this);
    }
}

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PracticaLineaBresenham extends JFrame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> new PracticaLineaBresenham().setVisible(true));
    }

    Dibuja dibuja;

    public PracticaLineaBresenham() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        dibuja = new Dibuja(800, 600);
        dibuja.RectaBresenham(100, 100, 200, 400, Color.BLUE);
        dibuja.RectaBresenham(200, 200, 300, 500, Color.CYAN);
        // dibuja.RectaBresenham(700, 400, 650, 500, Color.red);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(dibuja, 0, 0, this);
    }
}

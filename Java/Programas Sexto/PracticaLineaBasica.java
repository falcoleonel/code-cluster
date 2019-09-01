import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PracticaLineaBasica extends JFrame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> new PracticaLineaBasica().setVisible(true));
    }

    Dibuja dibuja;

    public PracticaLineaBasica() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        dibuja = new Dibuja(800, 600);
        dibuja.RectaBasica(100, 100, 200, 100, Color.RED);
        dibuja.RectaBasica(700, 100, 600, 100, Color.BLUE);
        dibuja.RectaBasica(130, 200, 130, 300, Color.GREEN);
        dibuja.RectaBasica(650, 300, 650, 200, Color.YELLOW);
        dibuja.RectaBasica(100, 400, 150, 500, Color.MAGENTA);
        dibuja.RectaBasica(700, 400, 650, 500, Color.CYAN);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(dibuja, 0, 0, this);
    }
}

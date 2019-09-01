import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PracticaPuntoMedio extends JFrame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> new PracticaPuntoMedio().setVisible(true));
    }

    Dibuja dibuja;

    public PracticaPuntoMedio() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        dibuja = new Dibuja(800, 600);
        dibuja.RectaPuntoMedio(100, 100, 200, 100, Color.red);
        dibuja.RectaPuntoMedio(130, 200, 347, 300, Color.green);
        dibuja.RectaPuntoMedio(100, 400, 250, 500, Color.magenta);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(dibuja, 0, 0, this);
    }
}

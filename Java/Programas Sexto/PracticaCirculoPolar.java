import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PracticaCirculoPolar extends JFrame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> new PracticaCirculoPolar().setVisible(true));
    }

    Dibuja dibuja;

    public PracticaCirculoPolar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        dibuja = new Dibuja(800, 600);
        dibuja.CirculoPolares(300, 300, 50, Color.MAGENTA);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(dibuja, 0, 0, this);
    }
}

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PracticaCirculo extends JFrame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> new PracticaCirculoPolar().setVisible(true));
    }

    Dibuja dibuja;

    public PracticaCirculo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        dibuja = new Dibuja(800, 600);
        dibuja.Circulo(450, 350, 350, 350, Color.green);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(dibuja, 0, 0, this);
    }
}

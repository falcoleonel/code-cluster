import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PracticaBasicoMejorado extends JFrame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> new PracticaCirculoPolar().setVisible(true));
    }

    Dibuja dibuja;

    public PracticaBasicoMejorado() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        dibuja = new Dibuja(800, 600);
        dibuja.RectaBasicaM(100, 100, 200, 100, Color.cyan);
        dibuja.RectaBasicaM(700, 100, 600, 100, Color.magenta);
        dibuja.RectaBasicaM(130, 200, 130, 300, Color.yellow);
        dibuja.RectaBasicaM(650, 300, 650, 200, Color.green);
        dibuja.RectaBasicaM(100, 400, 150, 500, Color.blue);
        dibuja.RectaBasicaM(700, 400, 650, 500, Color.red);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(dibuja, 0, 0, this);
    }
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Monito extends JFrame {

    private static final long serialVersionUID = 1L;

    public Monito() {
        super("Frame del monito");
        setSize(360, 360);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        g.drawString(" El monito !!!", 10, 50);
        g.drawArc(50, 60, 50, 50, 0, 360);
        g.drawArc(60, 70, 30, 30, 180, 180);
        g.fillOval(65, 75, 5, 5);
        g.fillOval(80, 75, 5, 5);
        g.drawLine(75, 110, 75, 200);
        g.drawLine(75, 120, 45, 160);
        g.drawLine(75, 120, 105, 160);
        g.drawLine(75, 200, 45, 240);
        g.drawLine(75, 200, 105, 240);
    }

    public static void main(String[] args) {
        Monito frame = new Monito();
        frame.setVisible(true);
    }
}

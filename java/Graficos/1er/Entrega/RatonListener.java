import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;

import javax.swing.*;

public class RatonListener extends JPanel implements MouseListener {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();

    public RatonListener() {
        addMouseListener((MouseListener) this);
        panel.addMouseListener((MouseListener) this);
        frame.addMouseListener((MouseListener) this);
    }

    public static void main(String[] args) {
        RatonListener play = new RatonListener();

        play.setPanel();
    }

    public void setPanel() {

        panel.setLayout(null);
        frame.add(panel);
        frame.setLayout(null);
        panel.setBounds(0, 0, 100, 100);
        frame.setVisible(true);
        panel.setVisible(true);
        panel.setFocusable(true);
        frame.setSize(555, 555);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mousePressed(MouseEvent evt) {
        System.out.println("Raton Presionado");
    }

    public void mouseReleased(MouseEvent evt) {
        System.out.println("Raton Soltado");
    }

    public void mouseClicked(MouseEvent evt) {
        System.out.println("Click de Raton");
    }

    public void mouseEntered(MouseEvent evt) {
        System.out.println("Raton entro en frame");
    }

    public void mouseExited(MouseEvent evt) {
        System.out.println("Raton salio del frame");
    }
}

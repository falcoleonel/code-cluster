import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class KeyListen extends JPanel implements KeyListener {
    private char c = '*';

    public KeyListen() {
        this.setPreferredSize(new Dimension(235, 155));
        addKeyListener(this);

    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawString("Ultima tecla presionada : " + c, 35, 60);
    }

    public void keyPressed(KeyEvent e) {
        c = e.getKeyChar();
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {

    }

    public static void main(String[] s) {
        JFrame f = new JFrame();

        f.getContentPane().add(new KeyListen());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}

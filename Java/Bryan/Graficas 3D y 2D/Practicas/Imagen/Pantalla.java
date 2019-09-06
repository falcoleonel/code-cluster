import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Pantalla extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image imagen;
    public void paint(Graphics g){
        super.paint(g);
        Dimension tam = new Dimension(imagen.getWidth(this),imagen.getHeight(this));
        setPreferredSize(tam);
        setMinimumSize(tam);
        setMaximumSize(tam);
        setSize(tam);
        update(g);
    }
    public void update(Graphics g){
        g.drawImage(imagen, 0, 0, this);
    }
}

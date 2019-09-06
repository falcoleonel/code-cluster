package mini_paint;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * Dibujos
 */
public class Dibujos {

    int X,Y;
    Color color =new Color(0, 0, 0);
    public void leerCoordenadas(MouseEvent ev) {
        if (!ev.isMetaDown()) {
            X = ev.getX();
            Y = ev.getY();
        }
    }
    public void pintar(MouseEvent ev,int grosor) {
        Graphics2D g = ((Graphics2D)((JPanel)ev.getSource()).getGraphics());
        //Graphics2D g = JPanel(ev.getSource())
        g.setStroke(new BasicStroke(grosor) );

        int x = ev.getX();
        int y = ev.getY();
        g.drawLine(X, Y, x, y);

        g.dispose();

        X = x;
        Y = y;
        
    }
}
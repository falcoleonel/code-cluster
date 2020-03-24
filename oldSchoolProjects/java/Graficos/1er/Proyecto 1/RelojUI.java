import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.net.URL;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.PanelUI;

public class RelojUI extends PanelUI {

    private ImageIcon imagenFondo = null;
    private BasicStroke pincel;

    public static ComponentUI createUI(JComponent c) {
        return new RelojUI();
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        // panel transparente
        c.setOpaque(false);
        imagenFondo = createImage("reloj.jpg");
        // pincel que utilizaremos para las manecillas de los minutos y horas
        pincel = new BasicStroke(3f);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        // imagen de fondo
        if (imagenFondo != null) {
            g2d.drawImage(imagenFondo.getImage(), 0, 0, null);
        }

        Reloj reloj = (Reloj) c;
        int hora = reloj.getCalendar().get(Calendar.HOUR);
        int minuto = reloj.getCalendar().get(Calendar.MINUTE);
        int segundo = reloj.getCalendar().get(Calendar.SECOND);

        // dibujar manecillas
        Shape ovalo = new Ellipse2D.Double(0, 0, 167, 164);
        int centerX = (int) (ovalo.getBounds().getWidth() / 2);
        int centerY = (int) (ovalo.getBounds().getHeight() / 2);
        // puntos donde acaban las manecillas del reloj
        int xhoras, yhoras, xmin, ymin, xseg, yseg;
        // calculo de la posicion de las manecillas del reloj
        xseg = (int) (Math.cos(segundo * Math.PI / 30 - Math.PI / 2) * 65 + centerX);
        yseg = (int) (Math.sin(segundo * Math.PI / 30 - Math.PI / 2) * 65 + centerY);
        xmin = (int) (Math.cos(minuto * Math.PI / 30 - Math.PI / 2) * 60 + centerX);
        ymin = (int) (Math.sin(minuto * Math.PI / 30 - Math.PI / 2) * 60 + centerY);
        xhoras = (int) (Math.cos((hora * 30 + minuto / 2) * Math.PI / 180 - Math.PI / 2) * 45 + centerX);
        yhoras = (int) (Math.sin((hora * 30 + minuto / 2) * Math.PI / 180 - Math.PI / 2) * 45 + centerY);
        g.translate(48, 78);

        // dibujamos los numeros y las manecillas

        g.setFont(new Font("TimesRoman", Font.BOLD, 16));
        g.drawString("IX", 10, centerY + 5);
        g.drawString("III", (int) (ovalo.getBounds().getWidth() - 10), centerY + 5);
        g.drawString("XII", centerX - 6, 20);
        g.drawString("VI", centerX - 3, (int) (ovalo.getBounds().getHeight() - 2));
        // segundos
        g.drawLine(centerX, centerY, xseg, yseg);
        // minutos
        g2d.setStroke(pincel);
        g.drawLine(centerX, centerY, xmin, ymin);
        // horas
        g.drawLine(centerX, centerY, xhoras, yhoras);
        g.translate(-47, -78);
    }

    // metodo para recuperar imagen de archivo
    public ImageIcon createImage(String path) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        } else {
            System.err.println("No se pudo encontrar el archivo: " + path);
            return null;
        }
    }
}

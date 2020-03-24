
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Reloj extends JPanel {

    // Tiempo de actualizacion
    private static final int ACT_TIMER = 1000;

    // objeto calendario
    private Calendar calendario = null;

    public Reloj() {
        setUI(new RelojUI());
        calendario = Calendar.getInstance();
        setPreferredSize(new Dimension(295, 270));

        Timer timer = new Timer(ACT_TIMER, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // agregar un segundo al objeto calendario y repintar
                calendario.add(Calendar.SECOND, 1);
                repaint();
            }
        });
        timer.start();
    }

    public Calendar getCalendar() {
        return this.calendario;
    }
}

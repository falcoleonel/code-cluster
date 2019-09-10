import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

public class DiagramaPastel extends JFrame {

    public int opcion1 = 0;
    public int opcion2 = 0;
    public int opcion3 = 0;
    private boolean bnd = false;

    public DiagramaPastel() {
        super("Diagrama de sabores de helado");
        initialize();

    }

    private void initialize() {

        setSize(370, 370);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel lblPartido = new JLabel("    ¿Cual sabor de helado prefieres?");
        getContentPane().add(lblPartido);

        String[] names = new String[] { "Vainilla", "Chocolate", "Fresa" };
        JComboBox comboBox = new JComboBox<String>(names);
        comboBox.setEditable(true);

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox comboBox = (JComboBox) event.getSource();
                Object selected = comboBox.getSelectedItem();

                if (selected == "Vainilla")
                    opcion1++;
                if (selected == "Chocolate")
                    opcion2++;
                if (selected == "Fresa")
                    opcion3++;
                bnd = true;
                repaint();

            }
        });
        getContentPane().add(comboBox);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DiagramaPastel().setVisible(true);
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (bnd == true) {
            int suma = opcion1 + opcion2 + opcion3;

            int grados1 = opcion1 * 360 / suma;
            int grados2 = opcion2 * 360 / suma;
            int grados3 = opcion3 * 360 / suma;

            g.setColor(new Color(255, 255, 192));
            g.fillArc(46, 100, 200, 200, 0, grados1);
            g.fillRect(270, 100, 20, 20);
            g.drawString("Vainilla", 300, 113);

            g.setColor(new Color(69, 50, 46));
            g.fillArc(46, 100, 200, 200, grados1, grados2);
            g.fillRect(270, 125, 20, 20);
            g.drawString("Chocolate", 300, 143);

            g.setColor(new Color(213, 48, 50));
            g.fillArc(46, 100, 200, 200, grados1 + grados2, grados3);
            g.fillRect(270, 155, 20, 20);
            g.drawString("Fresa", 300, 173);
        }
    }

}

package rellenos;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;

/**
 * choose_color
 */
public class choose_color extends JDialog implements ActionListener{
    JColorChooser jcolor = new JColorChooser();
    JButton btAceptar = new JButton("Aceptar__");

    Dibujos d;

    choose_color(Dibujos dib){
        d=dib;
        btAceptar.addActionListener(this);
        add(jcolor);
        add(btAceptar);

        setLayout(new FlowLayout());
        setSize(500, 430);
        setTitle("Escoge un color");
        setVisible(true);
        setLocation(220, 100);
        setResizable(false);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btAceptar) {
            d.color = jcolor.getColor();            
        }
        dispose();
    }


    
}
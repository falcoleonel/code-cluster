import java.awt.*;

import javax.swing.*;
//import javax.swing.JLabel;
//import javax.swing.SwingConstants;
//import com.sun.xml.internal.ws.org.objectweb.asm.Label;


public class index {
    public static void main (String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel textJLabel = new JLabel("Texto de Ventana",SwingConstants.CENTER);
        textJLabel.setPreferredSize(new Dimension(300,100));

        frame.getContentPane().add(textJLabel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }
}
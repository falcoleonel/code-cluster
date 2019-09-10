import javax.swing.*;  
import java.awt.*;

public class Ventana {  

	public static void main(String[] args) {  

        JFrame frame = new JFrame("Ejemplito" );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,250);
        
        JLabel textLabel = new JLabel("Texto en la ventana",SwingConstants.CENTER); textLabel.setPreferredSize(new Dimension(300, 100)); 
        frame.getContentPane().add(textLabel, BorderLayout.CENTER); 
        
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
        
	}  
}  
import javax.swing.*;
import java.awt.*;

public class VisorImagen extends JFrame {
	private JScrollPane panel;
	private Pantalla pantalla;

	public VisorImagen(String archivo) {
		super("Visor Imagen");
		Image img = Toolkit.getDefaultToolkit().getImage(archivo);
		pantalla = new Pantalla(img);
		panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		getContentPane().add(panel);
		panel.setViewportView(pantalla);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new VisorImagen("kylo.jpg");
	}
}

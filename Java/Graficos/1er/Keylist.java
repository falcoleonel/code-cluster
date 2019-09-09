import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Keylist extends JFrame implements KeyListener {

	public Keylist() {
		super("Visor Imagen");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);
		addKeyListener(this);
	}

	public static void main(String[] args) {
		new Keylist();
	}

	public void keyTyped(KeyEvent e) {

		System.out.println("Una tecla fue tecleada");
	}

	public void keyPressed(KeyEvent e) {

		System.out.println("Una tecla fue presionada");
	}

	public void keyReleased(KeyEvent e) {

		System.out.println("Una tecla fue liberada");
	}
}

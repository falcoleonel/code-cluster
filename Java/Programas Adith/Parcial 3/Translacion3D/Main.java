import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Main extends JFrame implements KeyListener {
	BufferedImage buffer;
	Pixel pixel;
	Translation translationV;
	
	public static void main(String[] args) {
        new Main();
    }
	
	public Main(){
        setTitle("Transalacion 3D");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable( false );
        setLocationRelativeTo( null );

		buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		pixel = new Pixel(this, buffer);
		translationV = new Translation(0, 0, 0);
		
		this.setVisible(true);
		addKeyListener(this);
	
    }

    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyPressed(KeyEvent evt) {
		
		if(evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			translationV = Translation.traslacion(translationV, new Translation(5, 0, 0));
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_LEFT) {
			translationV = Translation.traslacion(translationV, new Translation(-5, 0, 0));
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_UP) {
			translationV = Translation.traslacion(translationV, new Translation(0, -5, 0));
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_DOWN) {
			translationV = Translation.traslacion(translationV, new Translation(0, 5, 0));
		}
		
		if(evt.getKeyChar() == '-' ) {
			translationV = Translation.traslacion(translationV, new Translation(0, 0, 5));
		}
		
		if(evt.getKeyChar() == '+') {
			translationV = Translation.traslacion(translationV, new Translation(0, 0, -5));
		}
		if(evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R') {
			Translation.reset(translationV);
		}

		repaint();
    }

	
	
	public void paint(Graphics g){
		Pixel.clear();

		Cube.drawCube(translationV, 20);
		g.drawImage(buffer, 0, 0, this);
		repaint();
		
    }
}
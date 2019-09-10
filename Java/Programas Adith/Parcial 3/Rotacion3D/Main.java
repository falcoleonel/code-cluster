import java.awt.*;	
import javax.swing.*;
import java.util.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Main extends JFrame implements KeyListener {
	BufferedImage buffer;
	Pixel pixel;
	Rotation RotationV, growthV;
	ArrayList<Rotation> originalCube, drawingCube;

	public static int degrees;
	
	public static void main(String[] args) {
        new Main();
    }
	
	public Main(){
        setTitle("Rotacion 3D");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable( false );
        setLocationRelativeTo( null );

		buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		pixel = new Pixel(this, buffer);

		RotationV = new Rotation(0, 0, 0);
		growthV = new Rotation(100, 100, 100);

		originalCube = Cube.getPoints(RotationV, growthV);
		drawingCube = Cube.getPoints(RotationV, growthV);
		degrees = 0;

		this.setVisible(true);
		addKeyListener(this);
	
    }

    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyPressed(KeyEvent evt) {

		
		if(evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			degrees -=10;
			drawingCube = Rotation.doRotationY(originalCube, degrees);
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_LEFT) {
			degrees+=10;
			drawingCube = Rotation.doRotationY(originalCube, degrees);
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_UP) {
			degrees+=10;
			drawingCube = Rotation.doRotationX(originalCube, degrees);
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_DOWN) {
			degrees-=10;
			drawingCube = Rotation.doRotationX(originalCube, degrees);
		}
		
		if(evt.getKeyChar() == '-' ) {
			degrees-=10;
			drawingCube = Rotation.doRotationZ(originalCube, degrees);
		}
		
		if(evt.getKeyChar() == '+') {
			degrees+=10;
			drawingCube = Rotation.doRotationZ(originalCube, degrees);
		}
		if(evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R') {
			Rotation.reset(RotationV);
			drawingCube = Cube.getPoints(RotationV, growthV);
			degrees = 0;
		}

		repaint();
    }

	
	
	public void paint(Graphics g){
		Pixel.clear();

		Cube.drawCube(drawingCube);
		g.drawImage(buffer, 0, 0, this);
		repaint();
		
    }
}
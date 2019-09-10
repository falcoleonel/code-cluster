import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.image.BufferedImage;
import java.util.*;


public class Main extends JFrame implements KeyListener  {
	BufferedImage buffer;
	Pixel pixel;
	ArrayList<Surface> surface, surfaceOriginal;

	public static int degrees;
	int x = 260;
	int z = 250;
	
	public static void main(String[] args) {
        new Main();
    }
	
	public Main(){
        setTitle("Superficie");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable( false );
        setLocationRelativeTo( null );

		buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		pixel = new Pixel(this, buffer);

		// rotationV = new Surface(0, 0, 0);
		// degrees = new Surface(0.0, 0.0, 0.0);

		surfaceOriginal = Surface.getSurfacePoints(new Surface(0, 0, 0), new Surface(260, 0, 250), 10);
		surface = Surface.getSurfacePoints(new Surface(0, 0, 0), new Surface(260, 0, 250), 10);
		setVisible(true);
		addKeyListener(this);

    }

    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyPressed(KeyEvent evt) {
		// surface = Surface.do3DRotation(surfaceOriginal, degrees);

		// if(evt.getKeyCode() == KeyEvent.VK_RIGHT) degrees._z += 10;
		
		// if(evt.getKeyCode() == KeyEvent.VK_LEFT) degrees._x += 10;
		
		// if(evt.getKeyCode() == KeyEvent.VK_DOWN) degrees._y += 10;

		// if(evt.getKeyCode() == KeyEvent.VK_UP) degrees._y -= 10;
		// if(evt.getKeyCode() == KeyEvent.VK_RIGHT) x+=5;
		
		// if(evt.getKeyCode() == KeyEvent.VK_LEFT) x-=5;
		
		// if(evt.getKeyCode() == KeyEvent.VK_UP) z-=5;
		
		// if(evt.getKeyCode() == KeyEvent.VK_DOWN) z+=5;

		if(evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			degrees -=10;
			surface = Surface.doRotationY(surfaceOriginal, degrees);
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_LEFT) {
			degrees+=10;
			surface = Surface.doRotationY(surfaceOriginal, degrees);
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_UP) {
			degrees+=10;
			surface = Surface.doRotationX(surfaceOriginal, degrees);
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_DOWN) {
			degrees-=10;
			surface = Surface.doRotationX(surfaceOriginal, degrees);
		}
		
		if(evt.getKeyChar() == '-' ) {
			degrees-=10;
			surface = Surface.doRotationZ(surfaceOriginal, degrees);
		}
		
		if(evt.getKeyChar() == '+') {
			degrees+=10;
			surface = Surface.doRotationZ(surfaceOriginal, degrees);
		}
		// if(evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R') {
		// 	Surface.reset(RotationV);
		// 	surface = Cube.getPoints(RotationV, growthV);
		// 	degrees = 0;
		// }


		repaint();
    }
	
	@Override
	public void paint(Graphics g){
		Pixel.clear();
		Surface.drawSurface(surface);
		g.drawImage(buffer, 0, 0, this);
    }
}
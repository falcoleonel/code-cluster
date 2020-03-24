import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.image.BufferedImage;
import java.util.*;


public class PSuperficie extends JFrame implements KeyListener  {
	BufferedImage buffer;
	Pixel pixel;
	ArrayList<Surface> surface, surfaceOriginal;

	public static int degrees;
	int x = 260;
	int z = 250;
	
	public static void main(String[] args) {
        new PSuperficie();
    }
	
	public PSuperficie(){
        setTitle("Superficie");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable( false );
        setLocationRelativeTo( null );

		buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		pixel = new Pixel(this, buffer);

		surfaceOriginal = Surface.getSurfacePoints(new Surface(0, 0, 0), new Surface(260, 0, 250), 10);
		surface = Surface.getSurfacePoints(new Surface(0, 0, 0), new Surface(260, 0, 250), 10);
		setVisible(true);
		addKeyListener(this);

    }

    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyPressed(KeyEvent evt) {

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
		
		if(evt.getKeyChar() == 'z' ) {
			degrees-=10;
			surface = Surface.doRotationZ(surfaceOriginal, degrees);
		}
		
		if(evt.getKeyChar() == 'x') {
			degrees+=10;
			surface = Surface.doRotationZ(surfaceOriginal, degrees);
		}

		repaint();
    }
	
	@Override
	public void paint(Graphics g){
		Pixel.clear();
		Surface.drawSurface(surface);
		g.drawImage(buffer, 0, 0, this);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;


public class Main extends JFrame implements KeyListener {
	BufferedImage buffer;
	Pixel pixel;
	ArrayList<Curve> curve, originalCurve;

	public static int degrees = 0;
	
	public static void main(String[] args) {
        new Main();
    }
	
	public Main(){
        setTitle("Curva Explicita");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable( false );
        setLocationRelativeTo( null );

        curve = Curve.getPoints(new Curve(0.0, 0.0, 0.0));

		buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		pixel = new Pixel(this, buffer);

		setVisible(true);
    }

    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyPressed(KeyEvent evt) {

    	curve = Curve.getPoints(new Curve(0.0, 0.0, 0.0));

		if(evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			degrees -=10;
			curve = Curve.doRotationY(curve, degrees);
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_LEFT) {
			degrees+=10;
			curve = Curve.doRotationY(curve, degrees);
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_UP) {
			degrees+=10;
			curve = Curve.doRotationX(curve, degrees);
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_DOWN) {
			degrees-=10;
			curve = Curve.doRotationX(curve, degrees);
		}
		
		if(evt.getKeyChar() == '-' ) {
			degrees-=10;
			curve = Curve.doRotationZ(curve, degrees);
		}
		
		if(evt.getKeyChar() == '+') {
			degrees+=10;
			curve = Curve.doRotationZ(curve, degrees);
		}


		repaint();
    }
	
	public void paint(Graphics g){
		Pixel.clear();
		
		Curve.drawCurve(curve);
		g.drawImage(buffer, 0, 0, this);
    }
}
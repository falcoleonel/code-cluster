import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Main extends JFrame implements KeyListener {
	BufferedImage buffer;
	Pixel pixel;
	Scale scaleV, growthV;
	
	public static void main(String[] args) {
        new Main();
    }
	
	public Main(){
        setTitle("Escala 3D");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable( false );
        setLocationRelativeTo( null );

		buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		pixel = new Pixel(this, buffer);
		scaleV = new Scale(0, 0, 0);
		growthV = new Scale(30, 30, 20);

		this.setVisible(true);
		addKeyListener(this);
	
    }

    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyPressed(KeyEvent evt) {

		
		if(evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			growthV = Scale.doScale(growthV, new Scale(5, 0, 0));
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_LEFT) {
			growthV = Scale.doScale(growthV, new Scale(-5, 0, 0));
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_UP) {
			growthV = Scale.doScale(growthV, new Scale(0, -5, 0));
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_DOWN) {
			growthV = Scale.doScale(growthV, new Scale(0, 5, 0));
		}
		
		if(evt.getKeyChar() == '-' ) {
			growthV = Scale.doScale(growthV, new Scale(0, 0, 5));
		}
		
		if(evt.getKeyChar() == '+') {
			growthV = Scale.doScale(growthV, new Scale(0, 0, -5));
		}
		if(evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R') {
			Scale.reset(scaleV);
			Scale.resetDistPoint(growthV);
		}

		repaint();
    }

	
	
	public void paint(Graphics g){
		Pixel.clear();

		Cube.drawCube(scaleV, growthV);
		g.drawImage(buffer, 0, 0, this);
		repaint();
		
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;


public class Main extends JFrame implements KeyListener{
	BufferedImage buffer;
	Pixel pixel;
	ArrayList<ArrayList<Cylinder>> originalCylinder, cylinder;
	Cylinder rotationV, degrees;

	public static int opcT, opcL;
	
	public static void main(String[] args) {
        new Main();
    }
	
	public Main(){
		//boolean T, S, R;
		
        setTitle("Cilindro");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable( false );
        setLocationRelativeTo( null );

		buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		pixel = new Pixel(this, buffer);

		rotationV = new Cylinder(0, 0, 0);
		degrees = new Cylinder(0.0, 0.0, 0.0);

		originalCylinder = Cylinder.getCylinderPoints(30);
		cylinder = Cylinder.getCylinderPoints(30);
		
		this.setVisible(true);
		addKeyListener(this);
		
    }

    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyPressed(KeyEvent evt) {
    	cylinder = Cylinder.do3DRotation(originalCylinder, degrees);		
		
		if(evt.getKeyCode() == KeyEvent.VK_RIGHT) degrees._z += 10;
		
		if(evt.getKeyCode() == KeyEvent.VK_LEFT) degrees._x += 10;
		
		if(evt.getKeyCode() == KeyEvent.VK_DOWN) degrees._y += 10;

		if(evt.getKeyCode() == KeyEvent.VK_UP) degrees._y -= 10;

		repaint();
    }
	
	@Override
	public void paint(Graphics g){
		Pixel.clear();

		Cylinder.drawCylinder(cylinder);
		g.drawImage(buffer, 0, 0, this);
		repaint();
		
    }
}
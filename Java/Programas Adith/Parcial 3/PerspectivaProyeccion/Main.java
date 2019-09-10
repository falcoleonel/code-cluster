import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Main extends JFrame {
	BufferedImage buffer;
	Pixel pixel;
	
	public static void main(String[] args) {
        new Main();
    }
	
	public Main(){
        setTitle("Perspectiva Proyeccion");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable( false );
        setLocationRelativeTo( null );

		buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		pixel = new Pixel(this, buffer);
		
		setVisible(true);
	
    }
	
	
	public void paint(Graphics g){
		Pixel.clear();

		Cube.drawCube(30, 0, 30, 60);
		// Cube.drawCube(100, 200, 10, 150);
		g.drawImage(buffer, 0, 0, this);
		repaint();
		
    }
}
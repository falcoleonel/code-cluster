import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class PPerspectiva extends JFrame {
	BufferedImage buffer;
	Pixel pixel;
	
	public static void main(String[] args) {
        new PPerspectiva();
    }
	
	public PPerspectiva(){
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

		CubePerspectiva.drawCube(0, 0, 25, 60);
		g.drawImage(buffer, 0, 0, this);
		repaint();
		
    }
}

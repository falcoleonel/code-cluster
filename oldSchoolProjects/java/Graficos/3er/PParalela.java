import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class PParalela extends JFrame {
	BufferedImage buffer;
	Pixel pixel;
	
	public static void main(String[] args) {
        new PParalela();
    }
	
	public PParalela(){
        setTitle("Perspectiva Paralela");
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

		CubeParalela.drawCube(0, 0, 0, 230);
		g.drawImage(buffer, 0, 0, this);
		repaint();
		
    }
}

import java.awt.*;	
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class Main extends JFrame  {
	BufferedImage buffer;
	Pixel pixel;
	Rotation RotationV, growthV;
	ArrayList<Rotation> originalCube, drawingCube;

	public static int degrees;
	
	public static void main(String[] args) {
        new Main();
    }
	
	public Main(){
        setTitle("Rotacion Cubo");
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

		while(true)
		{
				
			for(int i = 0; i < 360 ; i++ )
			{
				degrees -=1;
				drawingCube = Rotation.doRotationY(originalCube, degrees);
				try {
					Thread.sleep (5);
				} catch (Exception e) {}
					
				repaint();
			}
				
			
			for(int i = 0; i < 360 ; i++ )
			{
				degrees +=1;
				drawingCube = Rotation.doRotationX(originalCube, degrees);
				try {
					Thread.sleep (5);
					} catch (Exception e) {}
					
				repaint();
			}

			for(int i = 0; i < 360 ; i++ )
			{
				degrees +=1;
				drawingCube = Rotation.doRotationY(originalCube, degrees);

				try {
					Thread.sleep (5);
					} catch (Exception e) {}
					
				repaint();
			}
				
			for(int i = 0; i < 360 ; i++ )
			{
				degrees -=1;
				drawingCube = Rotation.doRotationX(originalCube, degrees);
				try {
					Thread.sleep (5);
					} catch (Exception e) {}
					
				repaint();
			}
			
			for(int i = 0; i < 360 ; i++ )
			{
				degrees -=1;
				drawingCube = Rotation.doRotationZ(originalCube, degrees);
				try {
					Thread.sleep (5);
					} catch (Exception e) {}
					
				repaint();
			}
				
			for(int i = 0; i < 360 ; i++ )
			{
				degrees +=1;
				drawingCube = Rotation.doRotationZ(originalCube, degrees);
				try {
					Thread.sleep (5);
					} catch (Exception e) {}
					
				repaint();
			}
				
		}
	
    }

	
	public void paint(Graphics g){
		Pixel.clear();

		Cube.drawCube(drawingCube);
		g.drawImage(buffer, 0, 0, this);
		repaint();
		
    }
}
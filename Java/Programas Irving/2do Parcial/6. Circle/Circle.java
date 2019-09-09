import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Circle extends JFrame {
	 private BufferedImage buffer;
	 public JPanel myJPanel;
	public static void main(String[] args) {
		new Circle();
	}
	
	public Circle(){
		super("CIRCUNFERENCIA SIMETRIA 8 LADOS");
		setSize(900,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myJPanel =new JPanel();
	    add(myJPanel);
	    setVisible(true);
	    buffer = new BufferedImage (1,1,BufferedImage.TYPE_INT_RGB);
	}
	
	public void paint (Graphics g){
		try {
			drawCicle(100,500,150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void drawPixel(int x,int y, Color c){
		buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer,x,y, this);
	}
	
	public void drawCicle(int r, int xc, int yc) throws InterruptedException{
		 int x,y;
		  double yr;
		  x = 0;
		  y = r;
		  yr = r;
		  drawPixel(xc+x,yc+y,Color.BLACK);
		  while (x < yr){
		    x = x + 1;
		    yr = Math.sqrt(r*r-x*x);
		    y = (int)Math.round(yr);
		    drawPixel(xc+x,yc+y,Color.BLACK);
		    drawPixel(xc-x,yc+y,Color.BLACK);
		    drawPixel(xc+x,yc-y,Color.BLACK);
		    drawPixel(xc-x,yc-y,Color.BLACK);
		    drawPixel(xc+y,yc+x,Color.BLACK);
		    drawPixel(xc-y,yc+x,Color.BLACK);
		    drawPixel(xc+y,yc-x,Color.BLACK);
		    drawPixel(xc-y,yc-x,Color.BLACK);
		  }
	}
}

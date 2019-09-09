import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LineaSimple extends JFrame{
	 private BufferedImage buffer;
	 private Graphics graPixel;  
	 public JPanel JPanel;
	    
	    public LineaSimple() {
	        super("Linea");
	        setSize(300,300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        JPanel =new JPanel();
	        add(JPanel);
	        setVisible(true);
	        buffer = new BufferedImage (1,1,BufferedImage.TYPE_INT_RGB);
	    }
	   
	    public static void main(String[] args) {
	        new LineaSimple();
	    }
	    
	    public void paint (Graphics g){
	        /*
	         * Horizontal
	         * */;
	        drawLinePixel(50,200,50,100);

	    }
	    
	    public void paintPixel(int x, int y, Color c){
	        buffer.setRGB(0, 0, c.getRGB());
	        this.getGraphics().drawImage(buffer, x, y, this);
	    }
	    public void drawLinePixel(int x1, int y1,int x2, int y2){
	        if(x1==x2){
	        	if(y1 < y2 ){
		            for(int y=y1; y<y2; y++){
		                int x = x1;
		                paintPixel(x,y,Color.cyan);
		            	}
	            }else{
		        	for(int y=y1; y>y2; y--){
		                int x = x1;
		                paintPixel(x,y,Color.red);
		            } 
	            } 
	        }else if (y1==y2 || x1 == y2 || x1==y1) {
	            int dx=x2-x1, dy=y2-y1;
	            int m=dy/dx, b=y1-m*x1;
	            if(x1 < x2 || x1 < y2 || x1==y1){
		            for(int x=x1; x<x2; x++){
		                float y = m*x+b;
		                paintPixel(x,Math.round(y), Color.black);
		            }
		       }else {
		            for(int x=x1; x>x2; x--){
		                float y = m*x+b;
		                paintPixel(x,Math.round(y), Color.blue);
		            }
	           }
	        }
	    }
}
//Dibujar Monito Manito
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Monito extends JFrame
{
	public Monito()
	{
		super("Monitos en la cama saltan sin parar!");
		setSize(200,300);
		//show();
		addWindowListener(new WindowAdapter()
      	{
         	public void windowClosing(WindowEvent e)
         	{
           		dispose();
           		System.exit(0); //calling the method is a must
         	}
      	});

	}

	public void paint(Graphics g)
	{		
		//getContentPane().setBackground( new Color(255,255,255) );
		//drawString(String str, int x, int y)
		g.drawString("uno se cayo y se ouso a llorar", 10,50);
		//Cara
		//drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
		g.drawArc(50,60,50,50,0,360);
		g.drawArc(60,70,30,30,180,180);
		//fillOval(int x, int y, int width, int height)
		g.fillOval(65,75,5,5);
		g.fillOval(80,75,5,5);
		//Dibujar cuerpo
		g.drawLine(75,110,75,200);
		//drawLine(int x1, int y1, int x2, int y2)
			//Brazos
		g.drawLine(75,120,45,80);
		g.drawLine(75,120,105,160);
			//Piernas
		g.drawLine(75,200,45,240);
		g.drawLine(75,200,105,240);
	}
	public static void main(String[] args) {
		Monito m = new Monito();
		m.setVisible(true);
	}
}
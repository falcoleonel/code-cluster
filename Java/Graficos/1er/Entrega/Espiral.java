import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class Espiral extends JFrame implements Runnable {

	private Graphics2D graphics;
	private Thread thr = new Thread(this);

	public static void main(String[] args) {
		Espiral frame = new Espiral();
		frame.setVisible(true);
	}

	public Espiral() {
		super("Espirales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(425, 550);
	}

	public void run() {
		createFigure(1);
	}

	public void paint(Graphics g) {
		if (this.graphics == null) {
			graphics = (Graphics2D) g;
			thr.start();
			createFigure(-1);
		}
	}

	public void createFigure(int side) {

		graphics.setColor(new Color(235, 44, 250));
		double padding = 5, s = 5, y = 300, x = 200 - padding;

		if (side == 1)
			x = 200;

		try {
			while (y > 0) {
				graphics.draw(new Arc2D.Double(x, y, s, s, 0, side * 180, Arc2D.OPEN));

				s += padding * 2;
				y -= padding;

				if (side == 1)
					x -= padding * 2;

				side *= -1;
				Thread.sleep(Math.round(x));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

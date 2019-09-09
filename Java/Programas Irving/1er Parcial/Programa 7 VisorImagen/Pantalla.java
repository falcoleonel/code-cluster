import javax.swing.*;
import java.awt.*;
public class Pantalla extends JPanel{
	private Image imagen;
	Pantalla(Image img){
		imagen = img;
	}

	public void paint(Graphics g){
	super.paint(g);
	Dimension tam = new Dimension(imagen.getWidth(this), imagen.getHeight(this));
	setPreferredSize(tam);
	setMinimumSize(tam);
	setMaximumSize(tam);
	setSize(tam);
	update(g);
	}
	public void update(Graphics g){
	g.drawImage(imagen, 0, 0, this);
	}
}
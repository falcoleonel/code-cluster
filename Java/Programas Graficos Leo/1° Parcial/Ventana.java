//Abrir y cerrar frame
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ventana extends JFrame
{
    public Ventana()
    {
        super("Abre y cierra la ventana");
        setSize(200,300);
        addWindowListener(new WindowAdapter()
      	{
         	public void windowClosing(WindowEvent e)
         	{
           		dispose();
           		System.exit(0); //calling the method is a must
         	}
      	});
    }
    
	public static void main(String[] args) {
		Ventana v = new Ventana();
		v.setVisible(true);
	}
}

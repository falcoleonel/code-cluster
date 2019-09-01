package dibujarpixel;
import java.awt.*;
import javax.swing.*; 


public class Ventana extends JFrame
{
    private Imagen img; 
    public Ventana()
    {
        setSize(500,500);
        setTitle("Juanita");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Crear imagen
        img=new Imagen(500,500);
        img.dibujarPxl(250, 250, Color.RED);
    }

    public void paint(Graphics g)
    {
        g.drawImage(img,0,0,this);
    }
    public static void main(String[] args)
    {
        Ventana ventana=new Ventana();
    }
    
}

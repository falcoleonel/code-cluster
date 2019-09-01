import java.awt.*;
import javax.swing.*;
import java.lang;

public class Pantalla extends JPanel 
{
    private Image img;

    public void paint(Graphics g)
    {
        super.paint(g);//Desplegar para obtener dimensiones reales en pantalla
        Dimension tam= new Dimension(img.getWidth(this),img.getHeight(this));
        setPreferredSize(tam);
        setMinimumSize(tam);
        setMaximumSize(tam);
        setSize(tam);
        update(g);
    }
    public void update(Graphics g)
    {
        g.drawImage(img, 0, 0, this);
    }
}

public class VisorImg extends JFrame
{
    private JScrollPane panel;
    private Pantalla pantalla;

    public VisorImg(String archivo)
    {
        super("Visor_Imagen");
        
        Image img = Toolkit.getDefaultToolkit().getImage(archivo);
        pantalla = new Pantalla(img);
        panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(panel);
        panel.setViewportView(pantalla);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,300);
        show();
    }

    public static void main(String[] args)
    {
    VisorImg visor = new VisorImg(archivo);
    }    
}

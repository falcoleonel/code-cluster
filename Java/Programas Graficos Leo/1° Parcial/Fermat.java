import java.awt.*;
import java.lang.Runnable;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.*;


public class Fermat implements Runnable
{
    private Thread hilo;
    public Fermat()
    {
        hilo = new Thread(this);
        hilo.start();
    }
    public void run()
    {
                JFrame frame = new JFrame("Fermat");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
    }
    
    public static void main(String args[]) {
            Fermat espiral = new Fermat();
    }
}
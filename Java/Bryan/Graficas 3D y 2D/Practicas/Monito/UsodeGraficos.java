import java.awt.*;
import javax.swing.*;

public class UsodeGraficos extends JFrame {
    public static void main (String[] args) {
        new UsodeGraficos();
    }
    public UsodeGraficos(){
        super("Uso de graficos");
        setSize(200,300);
        show();
        setBackground(Color.WHITE);
        setOpacity(0.1f);
    }
    public void paint(Graphics g){
        g.drawString("Demo Graficos", 10, 50);

        //Cara
        g.drawArc(50, 60, 50, 50, 0, 360);
        g.drawArc(60, 70, 30, 30, 180, 180);

        g.fillOval(65, 75, 5, 5);
        g.fillOval(80, 75, 5, 5);

        //Cuerpo
        g.drawLine(75, 110, 75, 200);

        g.drawLine(75, 120, 45, 160);
        g.drawLine(75, 120, 105, 160);

        //piernas
        g.drawLine(75, 200, 45, 240);
        g.drawLine(75, 200, 105, 240);
        
    }

}
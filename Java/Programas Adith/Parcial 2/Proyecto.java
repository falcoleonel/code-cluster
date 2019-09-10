import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
public class Proyecto extends JFrame implements ActionListener{
    public static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    private static final int FRAME_X_ORIGIN = 150;
    private static final int FRAME_Y_ORIGIN  = 200;

    JButton stop, go;
    Graphics g;

    MovingBanner myBanner;
    Thread thrd;
    public static void main(String[] args){
        Proyecto frame = new Proyecto();
        frame.setVisible(true);

    }

    public Proyecto(){
        Container contentPane;
        contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setVisible(true);

        g = contentPane.getGraphics();
        JPanel panel1, panel2;

        panel1 = new JPanel(new FlowLayout());
        panel2 = new JPanel(new FlowLayout());

        contentPane.add(panel1);
        contentPane.add(panel2);

        stop = new JButton("STOP");
        go = new JButton("GO");

        stop.addActionListener(this);
        go.addActionListener(this);
        addKeyListener(this);

        panel1.add(go);
        panel1.add(stop);
        myBanner = new MovingBanner();

    }
    public void actionPerformed(ActionEvent event){

        if (event.getSource() instanceof JButton){
            JButton clickedButton = (JButton) event.getSource();
            if(clickedButton == go){
              System.out.println("Go");

                myBanner.startAnimation();
                thrd = new Thread (myBanner);
                thrd.start();           
            }
            if(clickedButton == stop){
                myBanner.stopAnimation();
                thrd = null;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        c = e.getKeyChar();
        repaint();
    }
}

class MovingBanner extends JPanel implements Runnable{
    private int x;

    public static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    private static final int FRAME_X_ORIGIN = 150;
    private static final int FRAME_Y_ORIGIN  = 200;

    int bodyX = 250;
    int bodyY1 = 160;
    int bodyY2 = 210;
    int armHeight = 190;
    int armLength = bodyX + 30;
    int armLength1 = bodyX - 30;
    int legY = 110;

    private Boolean animate;
    public MovingBanner(){
        x = 2;
        animate = true;
        // new Thread(this).start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawLine(bodyX + x, bodyY1, bodyX + x, bodyY2); //body
        g.drawOval(bodyX + x, bodyY2, 40, 40); //head
        g.drawLine(armLength + x,armHeight,  armLength1 + x, armHeight); //arms
        g.drawLine(bodyX + x, bodyY1, bodyX + 20 + x,legY); //leg
        g.drawLine(bodyX + x, bodyY1, bodyX - 20 + x, legY);    //leg
    }
    public void run(){
        while (animate){
            changeX();
            repaint();
            try {Thread.sleep(100); } catch(Exception e){};
        }
    }
    public void changeX() {

        if (x <= FRAME_WIDTH)
             x++;
        else x = 2;

    }
    public void stopAnimation() {
        animate = false;
    }

    public void startAnimation() {
        animate = true;
        new Thread(this).start();
    }

}
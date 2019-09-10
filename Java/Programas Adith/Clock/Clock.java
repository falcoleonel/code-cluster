import java.util.Calendar;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.sound.sampled.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Clock extends JFrame implements Runnable {

    private Thread thr;

    public Clock() {
        super("Clock");
        setResizable(false);
        setSize(900, 1000);
        setVisible(true);
        setLocationRelativeTo(null);
        setBackground(new Color(46, 134, 193  ));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thr = new Thread(this);
        thr.start();

        playSong();
    }

    public static void main(String args[]) {
        new Clock();
 
    }

    private Image background;
    private Image buffer;
    // private int handSec = 90;
    // private int lgHand = 45;
    // private int smHand = 30;
    private int min;
    private int hour;
    private int sec;

    private Point targetPoint;
    private BufferedImage handMin;
    private BufferedImage handHour;
    private BufferedImage eyes;
    // private BufferedImage base;

    @Override
    public void paint(Graphics g) {
        if (background == null) {

            background = createImage(getWidth(), getHeight());
            Graphics gBackground = background.getGraphics();
            gBackground.setClip(0, 0, getWidth(), getHeight());
            ImageIcon bg1 = new ImageIcon("src/body7.png");
            gBackground.drawImage(bg1.getImage(), 0, 0, this); //Important
            gBackground.setColor(Color.white);

        }
        update(g);
        // mouse();
        // updateEyes(g);
    }

    AffineTransform identity = new AffineTransform();
    Image arrow;

    @Override
    public void update(Graphics g) {
        // double x1, y1, angle1, x2, y2, angle2;
        g.setClip(0, 0, getWidth(), getHeight());
        Calendar cal = Calendar.getInstance();
        Graphics2D g2d = (Graphics2D) g.create();

        // Graphics2D g2 = (Graphics2D) g;
        

        if (cal.get(Calendar.MINUTE) != min) {

            //regenerar la imagen de background
            hour = cal.get(Calendar.HOUR);
            min = cal.get(Calendar.MINUTE);
            // hour = hour - 2;
			// min = min - 15;

            buffer = createImage(getWidth(), getHeight());
            Graphics gbuffer = buffer.getGraphics();
            gbuffer.setClip(0, 0, getWidth(), getHeight());
            gbuffer.drawImage(background, 0, 20, this);

            gbuffer.fillArc((getWidth() - 90)/2+5, (getHeight() - 90)/2+5, 80, 80, angle12(hour), 3);
            gbuffer.fillArc((getWidth() - 100)/2+5, (getHeight() - 100)/2+5, 90, 90, angle60(min), 3);

        }

        //pintar buffer 
        g.drawImage(buffer, 0, 0, this);
        

        int x4 = targetPoint.x;
        int y4 = (targetPoint.y > 500) ? 500 :  targetPoint.y ;
        if(y4 < 70)
            y4 = 70;

        
            
        g2d.drawImage(eyes, (x4 -450)/20, (y4 /5)-50, this); 
            
            //ad------------------------
        Graphics2D g2d2 = (Graphics2D) g.create();
        sec = cal.get(Calendar.SECOND);
        g2d2.setColor(Color.BLACK);
           
        g2d2.translate(getWidth() / 2, getHeight() / 2);
        g2d2.rotate(sec * Math.PI / 30);
        g2d2.drawLine(0, 0, 0, -170);
    
        
    }
    


    @Override
    public void run() {
        // public Clock() {
            addMouseMotionListener(new MouseAdapter() {

                @Override
                public void mouseMoved(MouseEvent e) {
                    targetPoint = e.getPoint();
                    // repaint();
                   
                }

            });
            try {
                // base = ImageIO.read(getClass().getResource("src/Base.png"));
                handMin = ImageIO.read(getClass().getResource("src/handMin.png"));
                handHour = ImageIO.read(getClass().getResource("src/handHour.png"));
                eyes = ImageIO.read(getClass().getResource("src/eyes2.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        while (true) {
            try {
                thr.sleep(1000);
            } catch (InterruptedException ex) {}
            repaint();
        }
    }

    private int angle12(int hour) {
        if (hour < 3) return (360 / 12) * Math.abs(hour - 3);
        if (hour > 3) return (360 / 12) * (3 - hour);
        return 0;

    }
    private int angle60(int min) {
        if (min < 15) return (360 / 60) * Math.abs(min - 15);
        if (min > 15) return (360 / 60) * (15 - min);
        return 0;
    }
    private double getx(double angle, int radio) {
        double x = (double) radio * (double)(Math.sin(Math.toRadians(angle)));
        return x;
    }
    private double gety(double angle, int radio) {
        double y = (double) radio * (double)(Math.cos(Math.toRadians(angle)));
        return y;
    }

    private void playSong(){
        try {
           Clip clip= AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("src/GGU.wav")));
            clip.start();
          } catch (Exception e) {
            System.err.println(e.getMessage());
          }
    }
}
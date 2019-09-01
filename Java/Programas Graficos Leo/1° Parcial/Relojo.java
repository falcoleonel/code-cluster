import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Graphics.*;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.TimerTask;
import java.util.Calendar.*;
import javax.swing.*;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.*;

public class Relojo extends JPanel
{
    private GregorianCalendar calendario;
    private java.util.TimeZone hora=  java.util.TimeZone.getDefault();
    private java.util.Timer timerReloj= new java.util.Timer();
    int[] x=new int[2];
    int[] y=new int[2];
    ImageIcon img;
    ImageIcon img2;
    ImageIcon img3;
    // URL sonido=getClass().getResource("khSound1.mp3");
    // Media media;//=new javafx.scene.media.Media(new File(sonido).toURI().toString());
    // MediaPlayer mediaPlayer;// = new javafx.scene.media.MediaPlayer(file);
    
    public Relojo()
    {
        img = new ImageIcon(getClass().getResource("sora1.png"));
        img2 = new ImageIcon(getClass().getResource("paopu1.png"));
        img3 = new ImageIcon(getClass().getResource("crown1.png"));
        // media = new Media(sonido.toString());
        // mediaPlayer=new MediaPlayer(media);
        this.setPreferredSize(new Dimension(400,400));
        this.setMinimumSize(new Dimension(400,400) );
        //Actualizar cada 1000ms=1seg
        timerReloj.schedule(new TickTimerTask(),0,1000);

    }
    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setSize(500,520);
        ventana.setTitle("Reloj Analogo");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Relojo reloj = new Relojo();
        ventana.add(reloj);
        ventana.setVisible(true);
    }
    //La mascara principal del reloj
    public void paint(Graphics g)
    {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(img.getImage(), 0,0, this.getWidth(), this.getHeight(),null);
        dibujaHoras((Graphics2D)g);
        dibujarManecillas((Graphics2D)g);
    }
    //Puntos de la manecilla
    void minutos(int radioInicial,int radioFinal,double pos)
    {
        pos-=Math.PI/2;
        x[0]=(int)(getWidth()/2+radioInicial*Math.cos(pos));
        y[0]=(int)(getHeight()/2+radioInicial*Math.sin(pos));
        x[1]=(int)(getWidth()/2+radioFinal*Math.cos(pos));
        y[1]=(int)(getHeight()/2+radioFinal*Math.sin(pos));
    }
    //Dibujar horas
    void dibujaHoras(Graphics2D g)
    {
        g.setStroke(new java.awt.BasicStroke(20));
        g.setColor(Color.MAGENTA);
        for(double pos=0;pos<Math.PI*2;pos+=Math.PI/6)
        {
            minutos(200, 200, pos);
            g.drawImage(img2.getImage(), x[0]-20,y[0]-20,40, 40, null,null);
            // g.drawPolyline(x,y,2);
        }
    }
    //Dibujar las manecillas del reloj
    void dibujarManecillas(Graphics2D g)
    {
        double h=2*Math.PI*(calendario.get(Calendar.HOUR));
        double m=2*Math.PI*(calendario.get(Calendar.MINUTE));
        double s=2*Math.PI*(calendario.get(Calendar.SECOND));
                
        g.setStroke(new java.awt.BasicStroke(9));

        minutos(0,100,h/12+m/(60*12));
        g.setColor(Color.blue);
        g.drawPolyline(x,y,2);
        //g.drawImage(img3.getImage(), x[0],y[1],50,50,null,null);


        if(calendario.get(Calendar.SECOND)==0)
        {
            Sonido();
        }
        
        g.setStroke(new java.awt.BasicStroke(6));

        minutos(0,140,m/60+s/(60*60));
        g.setColor(Color.red);
        g.drawPolyline(x,y,2);
        //g.drawImage(img3.getImage(), x[0],y[0],50,50,null,null);


        g.setStroke(new java.awt.BasicStroke(3));

        minutos(0,170,s/60);
        g.setColor(Color.MAGENTA);
        g.drawPolyline(x,y,2);
        //g.drawImage(img3.getImage(), x[0],y[0],60,60,null,null);
        
        g.drawImage(img3.getImage(),getWidth()/2-25,getHeight()/2-35,50,50,null,null);
        // g.fillOval(getWidth()/2-8,getHeight()/2-8,16,16);
    }
    public void Sonido()
    {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("khSound1.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //Metodo para actualizar el reloj cada seg.
    class TickTimerTask extends TimerTask
    {
        public void run()
        {
            calendario=(GregorianCalendar)GregorianCalendar.getInstance(hora);
            repaint();
        }
    }
}
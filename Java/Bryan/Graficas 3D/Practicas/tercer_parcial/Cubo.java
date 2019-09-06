package tercer_parcial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;

public class Cubo extends JFrame {
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Cubo().setVisible(true);
        });
    }
    
    Dimension dimVentana;
    BufferedImage fondo;
    Graphics2D g;
    Camara camara;
    
    Coordenada[] rotacionZ;
    Poliedro puntos3;
    List<Point> puntos2;

    public Cubo() {
        
        setTitle("Weas en R^3");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        dimVentana = new Dimension(800, 600);
        Dimension dimPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimVentana);
        setLocation(
            (dimPantalla.width - dimVentana.width) / 2,
            (dimPantalla.height - dimVentana.height) / 2
        );
        
        fondo = new BufferedImage(dimVentana.width, dimVentana.height, BufferedImage.TYPE_3BYTE_BGR);
        g = fondo.createGraphics();
        g.setBackground(Color.BLACK);
        g.setColor(Color.RED);
        
        camara = new Camara(Coordenada.CERO, Coordenada.IDENTIDAD, 1, dimVentana, true);
        
        rotacionZ = new Coordenada[] { new Coordenada(), new Coordenada(0, 0, 0.5) };
        
        puntos3 = new Poliedro(new ArrayList<Coordenada>() {
            {
                Coordenada rotor = new Coordenada(Math.PI / 4, 0, 0).exp();
                Coordenada p1 = new Coordenada(1, 1, 1);
                for(int i = 0; i < 4; i++) {
                    p1 = rotor.por(p1).por(rotor.conj());
                    add(p1);
                    add(new Coordenada(-p1.x, p1.y, p1.z));
                }
            }}, new Coordenada(5, 0, 0), Coordenada.IDENTIDAD
        );
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Thread(() -> {
                    
                    long tiempo = Calendar.getInstance().getTimeInMillis();
                    long tiempoDibujar = 0;
                    int fps = 60;
                    double pef = 1e3 / fps;
                    
                    while(true) {
                        
                        long dt = Calendar.getInstance().getTimeInMillis() - tiempo;
                        actualizar(dt / 1e3);
                        
                        tiempoDibujar += dt;
                        tiempo += dt;
                        if(tiempoDibujar >= pef) {
                            tiempoDibujar %= pef;
                            dibujarFondo();
                            repaint();
                        }
                    }
                }).start();
            }
        });
    }
    
    public static <T extends Vector<T>> void actualizar(T[] o, double dt) {
        for(int i = o.length - 1; i > 0; i--)
            o[i - 1] = o[i - 1].mas(o[i].por(dt));
    }

    public void actualizar(double dt) {
        
        actualizar(rotacionZ, dt);
        
        puntos3.rotacion = rotacionZ[0].exp();
        puntos2 = camara.proyectar(puntos3);
    }
    
    public void dibujarFondo() {
        
        g.clearRect(0, 0, dimVentana.width, dimVentana.height);
        for(int i = 0; i < 4; i++) {
            Dibujo2D.dibujarRecta(
                fondo,
                new Point(puntos2.get(2 * i)),
                new Point(puntos2.get(2 * i + 1)),
                Color.RED
            );
            Dibujo2D.dibujarRecta(
                fondo,
                new Point(puntos2.get(2 * i)),
                new Point(puntos2.get(2 * (i + 1) % 8)),
                Color.RED
            );
            Dibujo2D.dibujarRecta(
                fondo,
                new Point(puntos2.get(2 * i + 1)),
                new Point(puntos2.get((2 * (i + 1) + 1) % 8)),
                Color.RED
            );
        }
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(fondo, 0, 0, this);
    }
}

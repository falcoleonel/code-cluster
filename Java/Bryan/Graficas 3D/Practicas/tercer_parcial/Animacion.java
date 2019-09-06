package tercer_parcial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;

public class Animacion extends JFrame {
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Animacion().setVisible(true);
        });
    }
    
    Dimension dimVentana;
    BufferedImage fondo;
    Graphics2D g;
    Camara camara;
    
    Coordenada[] rotacionZ;
    Coordenada[] rotacionY;
    Coordenada[] traslacion;
    Real[] escalamientoX;
    Real[] escalamientoY;
    Poliedro puntos3;
    List<Point> puntos2;

    public Animacion() {
        
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
        
        rotacionZ = new Coordenada[] { new Coordenada(), new Coordenada() };
        rotacionY = new Coordenada[] { new Coordenada(), new Coordenada() };
        traslacion = new Coordenada[] { new Coordenada(10, 0, 0), new Coordenada() };
        escalamientoX = new Real[] { new Real(1), new Real(0) };
        escalamientoY = new Real[] { new Real(1), new Real(0) };
        
        puntos3 = new Poliedro(new ArrayList<Coordenada>() {
            {
                for(double t = 0; t <= Math.PI * 20; t += 0.01) {
                    add(new Coordenada(t * Math.cos(t) / 10, t * Math.sin(t) / 10, t / 10));
                }
            }}, Coordenada.CERO, Coordenada.IDENTIDAD
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
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        rotacionZ[1].z = -0.5;
                        break;
                    case KeyEvent.VK_RIGHT:
                        rotacionZ[1].z = 0.5;
                        break;
                    case KeyEvent.VK_UP:
                        rotacionY[1].y = -0.5;
                        break;
                    case KeyEvent.VK_DOWN:
                        rotacionY[1].y = 0.5;
                        break;
                    
                    case KeyEvent.VK_A:
                        traslacion[1].y = 5;
                        break;
                    case KeyEvent.VK_D:
                        traslacion[1].y = -5;
                        break;
                    case KeyEvent.VK_W:
                        traslacion[1].x = 5;
                        break;
                    case KeyEvent.VK_S:
                        traslacion[1].x = -5;
                        break;
                    case KeyEvent.VK_Q:
                        traslacion[1].z = -5;
                        break;
                    case KeyEvent.VK_E:
                        traslacion[1].z = 5;
                        break;
                        
                    case KeyEvent.VK_P:
                        if(camara.obtenerPerspectiva()) {
                            camara.ajustarPerspectiva(false);
                            camara.ajustarAngSolido(50);
                        }
                        else {
                            camara.ajustarPerspectiva(true);
                            camara.ajustarAngSolido(1);
                        }
                        break;
                    
                    case KeyEvent.VK_M:
                        escalamientoX[1].val = 0.5;
                        break;
                    case KeyEvent.VK_N:
                        escalamientoX[1].val = -0.5;
                        break;
                    case KeyEvent.VK_K:
                        escalamientoY[1].val = 0.5;
                        break;
                    case KeyEvent.VK_J:
                        escalamientoY[1].val = -0.5;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        rotacionZ[1].z = 0;
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        rotacionY[1].y = 0;
                        break;
                        
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_D:
                        traslacion[1].y = 0;
                        break;
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_S:
                        traslacion[1].x = 0;
                        break;
                    case KeyEvent.VK_Q:
                    case KeyEvent.VK_E:
                        traslacion[1].z = 0;
                        break;
                    
                    case KeyEvent.VK_M:
                    case KeyEvent.VK_N:
                        escalamientoX[1].val = 0;
                        break;
                    case KeyEvent.VK_K:
                    case KeyEvent.VK_J:
                        escalamientoY[1].val = 0;
                        break;
                }
            }
        });
    }
    
    public static <T extends Vector<T>> void actualizar(T[] o, double dt) {
        for(int i = o.length - 1; i > 0; i--)
            o[i - 1] = o[i - 1].mas(o[i].por(dt));
    }

    public void actualizar(double dt) {
        
        actualizar(rotacionZ, dt);
        actualizar(rotacionY, dt);
        actualizar(traslacion, dt);
        actualizar(escalamientoX, dt);
        actualizar(escalamientoY, dt);
        
        puntos3.rotacion = rotacionZ[0].exp().por(rotacionY[0].exp());
        puntos3.posicion = traslacion[0];
        camara.ajustarEscala(escalamientoX[0].val, escalamientoY[0].val);
        puntos2 = camara.proyectar(puntos3);
    }
    
    public void dibujarFondo() {
        
        g.clearRect(0, 0, dimVentana.width, dimVentana.height);
        
        Point p0 = puntos2.get(0);
        for(Point p : puntos2) {
            Dibujo2D.dibujarRecta(
                fondo,
                new Point(p0),
                new Point(p),
                Color.RED
            );
            p0 = p;
        }
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(fondo, 0, 0, this);
    }
}

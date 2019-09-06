
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

/**
 *
 * @author scoral
 */
public class Superficies extends JFrame {
    
    public static void main(String[] args) {
        Superficies sup= new Superficies();
        sup.setVisible(true);
                    
            long tiempo = Calendar.getInstance().getTimeInMillis();
            long t_ejecucion=0;

            while(t_ejecucion<12000) {
                
                long dt = Calendar.getInstance().getTimeInMillis() - tiempo;

                tiempo += dt;
                t_ejecucion+=dt;
                //System.out.println(t_ejecucion);//milis


                if (t_ejecucion<5000) {
                    sup.rotacionZ[1].z= -.1;
                    sup.traslacion[1].x= -1;
                    sup.traslacion[1].y= .1;
                    sup.rotacionY[1].y= .5;
                }
                else if(t_ejecucion < 10000) {
                    sup.rotacionZ[1].z= .1;
                    sup.traslacion[1].x= 1;
                    sup.traslacion[1].y= -.1;
                    sup.rotacionY[1].y= -.5;
                }
                else{
                    sup.stop();
                    sup.rotacionZ[1].z=-.5;
                }

            }
  
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
    Poliedro[] puntos3;
    List<List<Point>> puntos2;

    public Superficies() {
        /***** Declaracion de frame de ventana */
        setTitle("Jesus Bryan Reyes Nuñez Reg: 16310343");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        dimVentana = new Dimension(800, 800);
        Dimension dimPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimVentana);
        setLocation(
            (dimPantalla.width - dimVentana.width) / 2,
            (dimPantalla.height - dimVentana.height) / 2
            );
        /***** FIN Declaracion de frame de ventana */
        
        //Graficos en Frame
        fondo = new BufferedImage(dimVentana.width, dimVentana.height, BufferedImage.TYPE_3BYTE_BGR);
        g = fondo.createGraphics();
        g.setBackground(Color.BLACK);
        g.setColor(Color.YELLOW);
        //fin Graficos en Frame
        
        
        //Coordenadas de proyeccion
            //Posicion del observador
        camara = new Camara(Coordenada.CERO, Coordenada.IDENTIDAD, 1, dimVentana, true);
        
        rotacionZ = new Coordenada[] { new Coordenada(), new Coordenada() };
        rotacionY = new Coordenada[] { new Coordenada(), new Coordenada() };
        traslacion = new Coordenada[] { new Coordenada(10, 0, 0), new Coordenada() };
        escalamientoX = new Real[] { new Real(1), new Real(0) };
        escalamientoY = new Real[] { new Real(1), new Real(0) };
        //FIN Coordenadas de proyeccion
        
        /*****Calculos del cilindro */
        int c =42;
        puntos3 = new Poliedro[c];
        puntos2 = new ArrayList<>();

        for(int i = 0; i < (c/2); i++) {
            double y = 0.1 * (i - 10);//Separacion de circulos
            puntos3[i] = new Poliedro(new ArrayList<Coordenada>() {
                {
                    for(double x = -1; x <= 1; x += 0.1) {
                        add(new Coordenada(Math.cos(Math.PI * (x + 1)), Math.sin(Math.PI * (x + 1)), y));
                    }
                }}, Coordenada.CERO, Coordenada.IDENTIDAD
                );
                System.out.println("x= " + puntos3[i].posicion.x + "y= " +puntos3[i].posicion.y + "z= " + puntos3[i].posicion.z);
            }

        for(int i = 0; i < (c/2); i++) {
            double x = 0.1 * (i - 10);//Separacion de lineas verticales
            puntos3[i + (c/2)] = new Poliedro(new ArrayList<Coordenada>() {
                {
                    for(double y = -1; y <= 1; y += 0.1) {
                        add(new Coordenada(Math.cos(Math.PI * (x + 1)), Math.sin(Math.PI * (x + 1)), y));
                    }
                }}, Coordenada.CERO, Coordenada.IDENTIDAD
                );
            }
        /*****FIN Circulos del cilindro */
                
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
                    case KeyEvent.VK_DOWN:
                        rotacionY[1].y = -0.5;
                        break;
                    case KeyEvent.VK_UP:
                        rotacionY[1].y = 0.5;
                        break;
                    
                    case KeyEvent.VK_NUMPAD4:
                        traslacion[1].y = 5;
                        break;
                    case KeyEvent.VK_NUMPAD6:
                        traslacion[1].y = -5;
                        break;
                    case KeyEvent.VK_NUMPAD9:
                        traslacion[1].x = 5;
                        break;
                    case KeyEvent.VK_NUMPAD5:
                        traslacion[1].x = -5;
                        break;
                    case KeyEvent.VK_NUMPAD2:
                        traslacion[1].z = -5;
                        break;
                    case KeyEvent.VK_NUMPAD8:
                        traslacion[1].z = 5;
                        break;
                        
                    case KeyEvent.VK_SPACE:
                        if(camara.obtenerPerspectiva()) {
                            setTitle("Paralela");
                            camara.ajustarPerspectiva(false);
                            camara.ajustarAngSolido(50);
                        }
                        else {
                            setTitle("Perspectiva");
                            camara.ajustarPerspectiva(true);
                            camara.ajustarAngSolido(1);
                        }
                        break;
                    
                    case KeyEvent.VK_ADD:
                        escalamientoX[1].val = 0.5;
                        escalamientoY[1].val = 0.5;
                        break;
                    case KeyEvent.VK_SUBTRACT:
                        escalamientoX[1].val = -0.5;
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
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_UP:
                        rotacionY[1].y = 0;
                        break;
                        
                    case KeyEvent.VK_NUMPAD6:
                    case KeyEvent.VK_NUMPAD4:
                        traslacion[1].y = 0;
                        break;
                    case KeyEvent.VK_NUMPAD5:
                    case KeyEvent.VK_NUMPAD9:
                        traslacion[1].x = 0;
                        break;
                    case KeyEvent.VK_NUMPAD2:
                    case KeyEvent.VK_NUMPAD8:
                        traslacion[1].z = 0;
                        break;
                    
                    case KeyEvent.VK_ADD:
                    case KeyEvent.VK_SUBTRACT:
                        escalamientoX[1].val = 0;
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
        
        camara.ajustarEscala(escalamientoX[0].val, escalamientoY[0].val);
        puntos2.clear();
        for(int i = 0; i < puntos3.length; i++) {
            puntos3[i].rotacion = rotacionZ[0].exp().por(rotacionY[0].exp());
            puntos3[i].posicion = traslacion[0];
            puntos2.add(camara.proyectar(puntos3[i]));
        }
    }
    
    public void dibujarFondo() {
        
        g.clearRect(0, 0, dimVentana.width, dimVentana.height);
        
        for(List<Point> l : puntos2) {
            Point p0 = l.get(0);
            for(Point p : l) {
                Dibujo2D.dibujarRecta(
                    fondo,
                    new Point(p0),
                    new Point(p),
                    Color.YELLOW
                );
                p0 = p;
            }
        }
    }

    public void stop() {
        rotacionY[1].y = 0;
        rotacionZ[1].z = 0;

        traslacion[1].x= 0;
        traslacion[1].y= 0;
        traslacion[1].z= 0;

        escalamientoX[1].val=0;
        escalamientoY[1].val=0;
    }
    
    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(fondo, 0, 0, this);
    }
}

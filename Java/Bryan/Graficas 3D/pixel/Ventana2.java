package pixel;

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
import javax.swing.*;

import java.util.Hashtable;


public class Ventana2 extends Ventana{
    public Figures3d_Cube img3d;
    public Coordenada3d c_Cubo;
    public Hashtable<String,Integer> proyeccion; //Proyeccion x,y,z

    public Ventana2(int w, int h){
        super(w,h);

        c_Cubo=new Coordenada3d("c_Cubo");
        
        proyeccion=new Hashtable<String, Integer>();//Nuevo Diccionario

        proyeccion.put("x",0);
        proyeccion.put("y",0);
        proyeccion.put("z",10);

        this.img3d = new Figures3d_Cube(this.getWidth(),this.getHeight());
        //this.img3d.move(0, 0, 0);
        //this.img3d.resize(4);
        

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    //Keycode: https://keycode.info/
                    case KeyEvent.VK_ADD:
                        resize_(img3d.get_tam()+1);
                        break;
                        
                    case KeyEvent.VK_SUBTRACT:
                        resize_(img3d.get_tam()-1);
                        break;

                    case KeyEvent.VK_NUMPAD5:
                        move(0, 0,+1);
                        break;
                    case KeyEvent.VK_NUMPAD9:
                        move(0, 0,-1);
                        break;
                    case KeyEvent.VK_NUMPAD8:
                        move(0,+1,0);
                        break;
                    case KeyEvent.VK_NUMPAD2:
                        move(0,-1,0);
                        break;
                    case KeyEvent.VK_NUMPAD6:
                        move(+1,0,0);
                        break;
                    case KeyEvent.VK_NUMPAD4:
                        move(-1,0,0);
                        break;

                        case KeyEvent.VK_RIGHT:
                        rotar(Orientacion.getX()+1,Orientacion.getY(),Orientacion.getZ());
                         //rotacionZ[1].z = 0.5;
                         break;
                         case KeyEvent.VK_UP:
                         //rotacionY[1].y = -0.5;
                         break;
                         case KeyEvent.VK_DOWN:
                         //rotacionY[1].y = 0.5;
                         break;
                         
                         case KeyEvent.VK_A:
                         //traslacion[1].y = 5;
                         break;
                         case KeyEvent.VK_D:
                         //traslacion[1].y = -5;
                         break;
                         case KeyEvent.VK_W:
                         //traslacion[1].x = 5;
                         break;
                         case KeyEvent.VK_S:
                         //traslacion[1].x = -5;
                         break;
                         case KeyEvent.VK_Q:
                         //traslacion[1].z = -5;
                         break;
                         case KeyEvent.VK_E:
                         //traslacion[1].z = 5;
                         break;
                         
                         case KeyEvent.VK_P:
                         /*
                         if(camara.obtenerPerspectiva()) {
                             camara.ajustarPerspectiva(false);
                             camara.ajustarAngSolido(50);
                            }
                            else {
                                camara.ajustarPerspectiva(true);
                                camara.ajustarAngSolido(1);
                            }*/
                        break;
                        
                        case KeyEvent.VK_M:
                        //escalamientoX[1].val = 0.5;
                        break;
                        case KeyEvent.VK_N:
                        //escalamientoX[1].val = -0.5;
                        break;
                        case KeyEvent.VK_K:
                        //escalamientoY[1].val = 0.5;
                        break;
                        case KeyEvent.VK_J:
                        //escalamientoY[1].val = -0.5;
                        break;
                        
                    }
            }
        });
    }
    public void paint(Graphics g){
        super.paint(g);
        //g.drawImage((Image)img, 0, 0, Color.BLACK,this);
        g.drawImage(img3d, 0, 0, Color.BLACK,this);
    }
    public void clear() {
        super.clear();
        this.img3d = new Figures3d_Cube(this.getWidth(),this.getHeight());
    }
    public static void main(String[] args) {
        System.out.println("abriendo ventana....");
        Ventana2 ventana = new Ventana2(1200,650);
        
        //ventana.img3d.drawLine(500, 300, 15, 500, 300, 30,Color.GREEN);
        //ventana.img3d.Cubo3D(0, 0, 0, 2, Color.WHITE);
        //ventana.img3d.Cubo3D(0, 0, 0, 4, Color.WHITE);
        //ventana.img3d.drawEjes3D();
        ventana.img3d.drawEjes3D();
        //ventana.img3d.Cubo3D(0, 0, 0, 6, Color.WHITE);
        ventana.resize_(6);

        ventana.img3d.move(5, 0, 0);
        //ventana.img3d.rotar_cubo_X(0);

        //for (int i = 0; i < 10; i++) {
        //ventana.img3d.DrawCubo3D_rotado(1,0,0,Color.WHITE);
        ventana.draw(Color.WHITE);
        //ventana.animacion();
        /**
        for (int i = 0; i < 360; i++) {
            ventana.clear();
            ventana.repaint();
            ventana.img3d.DrawCubo3D_rotado(i,0,0,Color.WHITE);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                //TODO: handle exception
            }
             * 
             
            }
            */

        //ventana.img3d.proyeccion_paralela();
        //ventana.clear();
        //ventana.img3d.proyeccion_paralela();
        //ventana.img3d.dibujarPxl(600, 300, Color.WHITE);
    }
    public void animacion() {
        //Tamaño
        this.img3d.resize(6);
        for (int i = 2; i < 360; i=i++) {
            clear();
            repaint();
            //this.img3d.resize(2);
            //this.img3d.DrawCubo3D_rotado(i,0,0,Color.WHITE);
            this.img3d.DrawCubo3D_rotado(0,0,0,Color.WHITE);

            //this.img3d.DrawCubo3D(img3d.cubo3D,Color.MAGENTA);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
        
        /*****
         * 
         for (int i = 10; i > 2; i=i-2) {
             clear();
             repaint();
             this.img3d.move(i, 0, 0);
             try {
                 Thread.sleep(1000);
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
            ///Posicion
            for (int i = 2; i < 100; i=i+5) {
                clear();
                repaint();
                this.img3d.move(0, i, 0);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
            for (int i = 100; i > -100; i=i-5) {
                clear();
                repaint();
                this.img3d.Cubo3D(0, i, 0, 5, Color.WHITE);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
            */
        }
        public void draw(Color c) {
            img3d.DrawCubo3D(c);
        }
        public void resize_(int tam) {
            clear();
            img3d.resize(tam);
            img3d.DrawCubo3D(Color.white);
            repaint();
        }

        public void move(int x, int y,int z) {
            clear();
            System.out.println("x_" + img3d.get_x_p());
            System.out.println("y_" + img3d.get_y_p());
            System.out.println("z_" + img3d.get_z_p());
            this.img3d.move(x,y,z);


            img3d.DrawCubo3D(Color.white);
            repaint();
        }
        public void rotar(int x, int y,int z) {
            clear();
            this.img3d.DrawCubo3D_rotado(x,y,z,Color.WHITE);
            repaint();            
        }
    }
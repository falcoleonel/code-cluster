package pixel;

import java.awt.*;
import java.awt.font.NumericShaper.Range;

import javax.swing.*;

import java.util.Hashtable;


public class Ventana2 extends Ventana{
    public Figures3d_Cube img3d;
    public Hashtable<String,Integer> proyeccion; //Proyeccion x,y,z

    public Ventana2(int w, int h){
        super(w,h);

        proyeccion=new Hashtable<String, Integer>();//Nuevo Diccionario

        proyeccion.put("x",0);
        proyeccion.put("y",0);
        proyeccion.put("z",10);

        this.img3d = new Figures3d_Cube(this.getWidth(),this.getHeight());
    }
    public void paint(Graphics g){
        super.paint(g);
        //g.drawImage((Image)img, 0, 0, Color.BLACK,this);
        g.drawImage((Image)img3d, 0, 0, Color.BLACK,this);
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
        
        ventana.img3d.resize(6);
        ventana.img3d.move(0, 0, 0);
        //ventana.img3d.rotar_cubo_X(0);

        //ventana.img3d.DrawCubo3D(Color.WHITE);
        //for (int i = 0; i < 10; i++) {
        ventana.img3d.DrawCubo3D_rotado(1,0,0,Color.WHITE);

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
        for (int i = 2; i < 360; i=i+2) {
            clear();
            repaint();
            //this.img3d.resize(2);
            //this.img3d.DrawCubo3D_rotado(i,0,0,Color.WHITE);

            this.img3d.DrawCubo3D(Color.MAGENTA);
            try {
                Thread.sleep(100);
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
    }
package animacion;

import java.awt.*;
import javax.swing.*;
import java.lang.Thread;

//import java.util.concurrent.TimeUnit;

public class Ventana extends JFrame{
    
    public Imagen img;

    public Ventana(String[] args){
        //Crear ventana
        setSize(500,500);
        setTitle("Animacion");
        setVisible(true);
        //setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Crear imagen
        img = new Imagen(500,500);
        
        figures(args, img);
    }
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
    public static void main(String[] args) {
        System.out.println("abriendo ventana....");
        Ventana ventana = new Ventana(args);
    }
    public void figures(String[] args, Imagen img) {
        //lines(img);
        switch (args[0]) {
            case "1":
                circles();                
                break;
            
            case "2":
                linea_caida();            
                break;

            case "3":
                botar();            
                break;
            default:
                break;
        }
              //squares(img);
        //elipses(img);
    }
    public void lines(Imagen img) {
        //Al derecho
        img.drawLine(50, 50, 150, 150, Color.BLUE);
        img.drawLine(200, 100, 300, 100, Color.BLUE);
        //Al Reves
        img.drawLine(450, 50, 350, 150, Color.RED);
        img.drawLine(600, 100, 500, 100, Color.RED);
    }
    public void circles() {

        caida_circulo_vertical(100, 250, 10, Color.BLUE);
        rebote_circulo_vertical(250, 250, 10, 60, Color.BLUE);
        rebote_circulo_vertical(150, 250, 10, 30, Color.BLUE);
        rebote_circulo_vertical(150, 250, 10, 15, Color.BLUE);
        rebote_circulo_vertical(150, 250, 10, 7, Color.BLUE);

    }
    public void linea_caida() {
        linea_caid(100, 100, 400, 90, Color.BLUE);
    }
    public void botar() {
        int i = 0;
        while (i==0) {
            rebote_circulo_vertical(250, 250, 10, 90, Color.BLUE);
        }
    }
    public void squares(Imagen img) {
        img.drawRect(250, 250, 450, 350, Color.WHITE);
        img.drawRect(425, 325, 275, 275, Color.WHITE);
    }
    public void elipses(Imagen img) {
        img.drawElipse(600, 300, 30, 10, Color.GREEN);
        img.drawElipse(600, 300, 50, 20, Color.GREEN);
        img.drawElipse(600, 300, 70, 30, Color.GREEN);
        img.drawElipse(600, 300, 100, 50, Color.GREEN);
    }

    public void reload(int _time) {

        try {
            Thread.sleep(_time);
        } catch (Exception e) {
        }
        img.clean();
        repaint();
        
    }
    /***funciones de animacion */
    public int caida_circulo_vertical(int h, int posicion_x, int radio, Color c) {
        
        int altura = h;
        int suelo=490-radio;
        double tiempo = 0;
        double posicion_y = 0;
        
        do{
            reload(10);
            
            posicion_y = altura + (.5*9.8*(Math.pow(tiempo,2)));
            
            img.drawCirc(posicion_x, (int)posicion_y, radio, c);
            img.flood(posicion_x, (int)posicion_y, c.getRGB());

            System.out.println((int)posicion_y);
            
            tiempo = tiempo +.01;
        }
        while(posicion_y < suelo);

        System.out.println("Velocidad:" + (300/tiempo));

        return (int)(9.8*(double)tiempo); //velocidad
    }
    public void rebote_circulo_vertical(int h, int posicion_x, int radio, int velocidad_inicial,Color c) {
        
        int altura = h;
        int suelo=490-radio;
        
        double tiempo = 0;
        int posicion_y = 0;

        double gravedad = 9.8;
        System.out.println("a:" +velocidad_inicial);

        double velocidad = 0;
        //double posicion_y = velocidad del suelo - 1/2 (g*t^2)
        
        do{
            reload(10);
            tiempo = tiempo +.01;
            posicion_y = suelo - (int)(Math.ceil((velocidad_inicial * tiempo) - (.5*gravedad* Math.pow(tiempo, 2) )));
            img.drawCirc(posicion_x, posicion_y, radio, c);
            img.flood(posicion_x, posicion_y, c.getRGB());

            velocidad = gravedad*tiempo;
            System.out.println("velocidad:"+velocidad);
            
        }
        while(posicion_y < suelo);
    }

    public void linea_caid(int h, int posicion_x, int posicion_x2, int velocidad_inicial,Color c) {
        
        double altura = 500-h;
        int suelo=490;
        double tiempo = 0;
        int posicion_y = 0;
        double gravedad = 9.8;


        double velocidad = 0;
        //double posicion_y = velocidad del suelo - 1/2 (g*t^2)
        //y=x2+x+1
        do{
            
            reload(10);
            tiempo = tiempo +.01;
            posicion_y = 500-(int)(altura - (.5*9.8*(Math.pow(tiempo,2))));
            
            System.out.println("x:"+posicion_x + "y:"+posicion_y);
            
            img.drawLine(posicion_x, posicion_y, posicion_x2, (posicion_y+20>suelo)?suelo:posicion_y+10,c,5);

            velocidad = gravedad*tiempo;
            System.out.println("velocidad:"+velocidad);
            //posicion_x=(posicion_x+1);
        }
        while(posicion_y < suelo);
    }
    
}
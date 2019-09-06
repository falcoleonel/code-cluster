package rellenos;

import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Image;
import java.awt.event.*;
import java.awt.event.WindowEvent;

import java.awt.Graphics;

/**
 * Interfaz
 */
public class Interfaz extends JFrame {
    int w = 650,h = 600;
    //Dibujos objDibujos = new Dibujos(w,h);
    Imagen image =new Imagen(w, h);
    

    public Interfaz() {
        super("Mini Paint");
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(w,h);

        //objDibujos= new Dibujos(650,600);


        this.addWindowListener(
            new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
            );
            System.out.println("Imprimiento Ventana ....");
            initComponents();
        }
        
        public void initComponents() {
            set_combo();
            getContentPane().setLayout(null);
            
            //Declarar Posiciones
            
            //Configurar ComboBox
            //figure.addItem();    
            //figure.addItem("Dibujar");
                //figure.addItem("Colorear");
            
            //Tipo de Letra
            color.setFont(f1);
            grosorTrazo.setFont(f1);
            
            //Grosor Slider
            grosor.setMaximum(10);
            grosor.setMinimum(1);
            
            //Acciones
            actions();
            
            //Insertar Elementos a panel y Configurar Posicion
            set_Bounds();

            


            //White board
            //dibujo.setBorder(new EtchedBorder(EtchedBorder.RAISED));
            //dibujo.setBackground(Color.WHITE);

    }
        public void set_Bounds() {
            color.setBounds(10,80,150,25);          //boton
            grosorTrazo.setBounds(265, 40, 100, 25);//Texto
            grosor.setBounds(230, 80, 150, 25);     //Slider
            clean.setBounds(450, 80, 150, 25);      //boton
            figure.setBounds(10, 20, 150, 25);    //ComboBox
            //dibujo.setBounds(10, 130, 600, 420);    //Dibujo

            getContentPane().add(color);
            getContentPane().add(grosorTrazo);
            getContentPane().add(grosor);
            getContentPane().add(clean);
            getContentPane().add(figure);
            //getContentPane().add(dibujo);
            
        }

        public void actions() {
            clean.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    //dibujo.repaint();
                    //paint(image);
                }});
            dibujo.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent e){
                    image.leerCoordenadas(e);
                    image.dibujarPxl(e, grosor.getValue());
                    //objDibujos.leerCoordenadas(e);
                    //objDibujos.pintar(e,grosor.getValue());
                }});
                /*
            color.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    new choose_color(objDibujos);
                }});
            }}
                */
                dibujo.addMouseMotionListener(new MouseAdapter(){
                    public void mouseDragged(MouseEvent e) {
                        image.dibujarPxl(e, grosor.getValue());
                        //objDibujos.pintar(e,grosor.getValue());
                }});
        }
        public void add_elements() {
                //Agregar a la consola

        }
        public void set_combo() {
            //String[] a = {"Circulo","Cuadrado"};
            //figure = new JComboBox(a);

            //figure.setSelectedIndex(4);
            //figureList.addActionListener(this);
        }
        public void paint(Graphics g){
            g.drawImage(image, 10, 130, this);
        }
        //Controles
        Font f1 = new Font("Segoe UI", Font.PLAIN, 12);
        
        JButton color = new JButton("Elegir Color");
        JButton clean = new JButton("Limpiar");
        JLabel grosorTrazo = new JLabel("Grosor del trazo");
        JSlider grosor = new JSlider();
        JPanel dibujo = new JPanel();

        //private JComboBox figure =new JComboBox<>();
        //String[] figureStrings = { "Circulo", "Cuadrado", "Linea", "Linea Punteada", "" };
        String[] figureStrings = { "Dibujar", "Rellenar" };

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox figure = new JComboBox<>(figureStrings);
        //JComboBox figure;

        //
    }
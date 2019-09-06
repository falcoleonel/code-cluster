package rellenos;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
//import java.awt.event.WindowEvent;

/**
 * Interfaz
 */
public class Interfaz extends JFrame {
    Dibujos objDibujos = new Dibujos();

    public Interfaz() {
        super("Mini Paint");
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(650,600);

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
        getContentPane().setLayout(null);
        
        //Configurar botones
            color.setBounds(10,80,150,25);
            color.setFont(f1);
            color.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new choose_color(objDibujos);
                }
            });

            grosorTrazo.setBounds(265, 40, 100, 25);
            grosorTrazo.setFont(f1);

            grosor.setMaximum(10);
            grosor.setBounds(230, 80, 150, 25);

            clean.setBounds(450, 80, 150, 25);

            clean.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    dibujo.repaint();
                }
            }
            );

            dibujo.setBounds(10, 130, 600, 420);
            dibujo.setBorder(new EtchedBorder(EtchedBorder.RAISED));
            dibujo.setBackground(Color.WHITE);

            dibujo.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent e){
                    if (!e.isMetaDown())                     
                    objDibujos.leerCoordenadas(e);
                    else
                    objDibujos.rellenar(e, grosor.getValue());

                }
            });
            dibujo.addMouseMotionListener(new MouseAdapter(){
                public void mouseDragged(MouseEvent e) {
                    if (!e.isMetaDown()) 
                        objDibujos.pintar(e,grosor.getValue());
                }
            }
            );

        //Agregar a la consola
        getContentPane().add(color);
        getContentPane().add(grosorTrazo);
        getContentPane().add(grosor);
        getContentPane().add(clean);
        getContentPane().add(dibujo);
        

    }
        //Controles
    Font f1 = new Font("Segoe UI", Font.PLAIN, 12);

    JButton color = new JButton("Elegir Color");
    JButton clean = new JButton("Limpiar");
    JLabel grosorTrazo = new JLabel("Grosor del trazo");
    JSlider grosor = new JSlider();
    JPanel dibujo = new JPanel();
        //
}
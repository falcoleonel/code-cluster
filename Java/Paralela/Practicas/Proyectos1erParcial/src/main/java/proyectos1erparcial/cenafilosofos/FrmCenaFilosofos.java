/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectos1erparcial.cenafilosofos;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 *
 * @author samuel.coral
 */
public class FrmCenaFilosofos extends JFrame {
    
    public static void main(String[] args) {
        new FrmCenaFilosofos().setVisible(true);
    }

    public FrmCenaFilosofos() {
        inicializar();
    }
    
    public LinkedList<Boolean> filosofos;
    public LinkedList<Boolean> tenedores;
    
    private void inicializar() {
        
        setTitle("Filósofos >:'v");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        Dimension dimVentana = new Dimension(800, 600);
        Dimension dimPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize(dimVentana);
        setLocation(
            (dimPantalla.width - dimVentana.width) / 2,
            (dimPantalla.height - dimVentana.height) / 2
        );
        
        filosofos = new LinkedList<>();
        filosofos.add(true);
        filosofos.add(true);
        filosofos.add(false);
        filosofos.add(true);
        filosofos.add(false);
        
        ponerMesa();
    }
    
    public void ponerMesa() {
        
        Container content = getContentPane();
        content.removeAll();
        double r = Math.min(getWidth(), getHeight()) * 4.0 / 10.0;
        double t = r * 1.0 / 2.0;
        int n = filosofos.size();
        Point c = new Point(getWidth() / 2, getHeight() / 2);
        
        for(int i = 0; i < n; i++) {
            
            JCheckBox filosofo = new JCheckBox();
            filosofo.setSelected(filosofos.get(i));
            filosofo.setVisible(true);
            //filosofo.setEnabled(false);
            filosofo.setLocation(
                c.x + (int)(r * Math.cos(2 * Math.PI * i / n)),
                c.y + (int)(r * Math.sin(2 * Math.PI * i / n))
            );
            filosofo.setBounds(new Rectangle(
                filosofo.getLocation(),
                filosofo.getPreferredSize()
            ));
            
            JCheckBox tenedor = new JCheckBox();
            tenedor.setSelected(true);
            tenedor.setVisible(true);
            //tenedor.setEnabled(false);
            tenedor.setLocation(
                c.x + (int)(t * Math.cos((2 * i + 1) * Math.PI / n)),
                c.y + (int)(t * Math.sin((2 * i + 1) * Math.PI / n))
            );
            tenedor.setBounds(new Rectangle(
                tenedor.getLocation(),
                tenedor.getPreferredSize()
            ));
            
            content.add(filosofo);
            content.add(tenedor);
        }
        
        repaint();
    }
}

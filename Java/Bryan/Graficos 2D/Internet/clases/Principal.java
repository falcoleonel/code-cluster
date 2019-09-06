
package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Principal extends JApplet {
 
 JSlider ancho;
 PanelDibujo pd;
 JSpinner grosor;
 JComboBox combo;
 Color colores[];
 
 public void init(){
  pd=new PanelDibujo(this);
  add(pd);
  JPanel pcontroles=new JPanel();
  ancho=new JSlider(JSlider.HORIZONTAL,1,50,1);
  ancho.addChangeListener(new ChangeListener() {
   
   @Override
   public void stateChanged(ChangeEvent e) {
    pd.setAncho(ancho.getValue());
    pd.repaint();
    repaint();
   }
  });
  grosor=new JSpinner();
  grosor.setValue(1);
  grosor.addChangeListener(new ChangeListener() {
   
   @Override
   public void stateChanged(ChangeEvent e) {
    pd.setGrosor(Integer.parseInt(grosor.getValue().toString()));
    pd.repaint();
    repaint();
   }
  });
  colores=new Color[6];
  colores[0]=Color.BLACK;
  colores[1]=Color.BLUE;
  colores[2]=Color.YELLOW;
  colores[3]=Color.GRAY;
  colores[4]=Color.GREEN;
  colores[5]=Color.CYAN;
  String nombres[]=new String[6];
  nombres[0]="Negro";
  nombres[1]="Azul";
  nombres[2]="Amarillo";
  nombres[3]="Gris";
  nombres[4]="Verde";
  nombres[5]="Cyan";
  combo=new JComboBox(nombres);
  combo.addItemListener(new ItemListener() {
   
   @Override
   public void itemStateChanged(ItemEvent e) {
    int i= combo.getSelectedIndex();
    pd.setColor(colores[i]);
    pd.repaint();
    repaint();
   }
  });
  pcontroles.add(new JLabel("Ancho"));
  pcontroles.add(ancho);
  pcontroles.add(new JLabel("Grosor"));
  pcontroles.add(grosor);
  pcontroles.add(new JLabel("Color"));
  pcontroles.add(combo);
  add(pcontroles,BorderLayout.SOUTH);
 }
}

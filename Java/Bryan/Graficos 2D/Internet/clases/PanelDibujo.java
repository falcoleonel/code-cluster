
package clases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class PanelDibujo extends JPanel implements MouseListener,MouseMotionListener{
  
 int x1,x2,y1,y2;
 int ancho=10;
 int grosor=1;
 Color color=Color.BLACK;
 Principal p;
 
 public PanelDibujo(Principal pr){
  p=pr;
  addMouseListener(this);
  addMouseMotionListener(this);
 }
 
 public void paintComponent(Graphics g){
  super.paintComponents(g);
  Graphics2D g2d=(Graphics2D)g;
  g2d.setColor(color);
  float dash[] = {ancho};
        g2d.setStroke(new BasicStroke(grosor,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f));
        g2d.drawLine(x1, y1, x2, y2);
        
 }

 @Override
 public void mouseClicked(MouseEvent e) {
  
 }

 @Override
 public void mouseEntered(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void mouseExited(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void mousePressed(MouseEvent e) {
  x1=e.getX();
  y1=e.getY();
 }

 @Override
 public void mouseReleased(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void mouseDragged(MouseEvent e) {
  x2=e.getX();
  y2=e.getY();
  repaint();
  p.repaint();
 }

 @Override
 public void mouseMoved(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }

 public void setAncho(int ancho) {
  this.ancho = ancho;
 }

 public void setGrosor(int grosor) {
  this.grosor = grosor;
 }

 public void setColor(Color color) {
  this.color = color;
 }
}
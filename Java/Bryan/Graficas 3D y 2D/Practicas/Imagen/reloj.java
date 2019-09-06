import java.awt.Graphics;

import javax.swing.*;

public class reloj extends JFrame{
    public static void main(String[] args){
        new reloj();
    }
    public reloj(){
        super("Relojito");

        setResizable(false);
        setSize(200,200);
        show();
    }
    public void paint (Graphics g) {
        if(fondo==null){
            fondo=createImage(getWidth(),getHeigth());
            //Pintar circulo del reloj
            Graphics gfondo =fondo.getGraphics();
            gfondo.setClip(0,0,getWidth()-100/2,getHeigth());
            gfondo.drawOval((getWidth()-100)/2, (getHeight()-100)/2, 100, 100);
        }
        
    }
}

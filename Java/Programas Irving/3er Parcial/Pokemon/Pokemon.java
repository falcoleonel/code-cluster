import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Pokemon extends JFrame implements KeyListener,Runnable {
    
    private BufferedImage buffer,imagen;
    private ArrayList<PuntosRotacion> puntosArray;
    private PuntosRotacion points3D;
    private int gX,gY,gZ,vX,vY,vZ;
    private String pokemon;
    private double avanceM;
    private Thread thread;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new Pokemon();
    }
    
    private Pokemon(){
        super("Poliwarth");
        gX=250;gY=15;gZ=10;
        vX=0;vY=0;vZ-=67;
        avanceM=2.18;
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        new PonerPixel(buffer);
        this.setVisible(true);
        this.addKeyListener(this);
        thread=new Thread(this);        
        thread.start();
        musicaPlay("/Users/Irving/Desktop/Pokemon/PokemonAudio.wav");
    }

    public void leerArchivo(String pokemon,double unidad)
    {
        String[] array;
        puntosArray = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;   
        try 
        {
            archivo = new File (pokemon);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            // Abrimos el archivo para leerlo
            String linea;
            while((linea=br.readLine())!=null)
            {
                //Identificamos los vectores del archivo
                if(linea.charAt(0)=='v'&&linea.charAt(1)!='t'){
                    array=linea.split("\\s+");                    
                    points3D=new PuntosRotacion( (Double.parseDouble(array[1])+vX)*avanceM,(Double.parseDouble(array[2])+vY)*avanceM,(Double.parseDouble(array[3])+vZ)*avanceM);
                    puntosArray.add(new PuntosRotacion((Double.parseDouble(array[1])+vX)*avanceM,(Double.parseDouble(array[2])+vY)*avanceM,(Double.parseDouble(array[3])+vZ)*avanceM));
                }
            }
            puntosArray.add(points3D);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
        try{  
            //Se cierra el archivo
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
    }
  
    //Funcion para identificar archivo .wav y darle play
    public void musicaPlay(String nombreSonido){
        try {
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
         Clip clip = AudioSystem.getClip();
         clip.open(audioInputStream);
         clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
          System.out.println("Error al reproducir.");
        }
     }

    public void run()
    {
        //ruta de los vectores del pokemon
        ///////////////////----------------------------------------------////////////////////////////////
        pokemon="/Users/Irving/Desktop/Pokemon/BR_Poliwrath-Shiny01.obj";
        try {
                Thread.sleep (100);
            } catch (Exception e) {}
        //Muestra y rota el pokemon con un incremento
        while(true)
        {
            for(int y=0;y<250;y++)
            {
                if(gY<345)
                	gY+=4;
            	vZ+=.01;
            	avanceM+=.1; 
                try {
                        Thread.sleep (20);
                    } catch (Exception e) {}
                if(vZ==0 && avanceM >= 15.58)
                {                                                
                    try {
                        Thread.sleep (9000);
                        for(int x=0; x<=155; x++){
                            try {
                                Thread.sleep (20);
                                    vZ-=1;
                                    avanceM-=.1;
                                    repaint();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Pokemon.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }     
                       
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Pokemon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                repaint();
            }
            
        }
    }

    //Funcion para agregar la imagen de ponerFondoImagen
    public void agregarImagen(String imagen)
    {
    	try{
         this.imagen = ImageIO.read(new File(imagen));
    	}
    	catch(Exception e){
          e.printStackTrace();
        }
    }
    
    //Funcion para pintar pixel y cargar la imagen de ponerFondoImagen
    public void paint(Graphics g){
        if (imagen==null)
        {
          //Ruta absoluta de la imagen de ponerFondoImagen  
          ///////////////////----------------------------------------------////////////////////////////////  
          agregarImagen("/Users/Irving/Desktop/Pokemon/a.jpg");
        }
        PonerPixel.ponerFondoImagen(imagen,buffer);
        //g.drawImage(imagen,0,0,tama.width,tama.height,null);
        pintarPokemon(g);
    }
    
    //Funcion para dibujar el pokemon
    public void pintarPokemon(Graphics g)
    {
        leerArchivo(pokemon,avanceM);
        puntosArray=PuntosRotacion.rotacionCompleta(puntosArray,gX,gY,gZ);
        FormaDibujar.calculoVector(puntosArray,pokemon,null);        
        g.drawImage(buffer,0,0,this);
    }

    public void keyPressed(KeyEvent e) {
        // System.out.println(e.getKeyCode());
        if(e.getKeyCode()==37)//izq
            vY-=1;
        else if(e.getKeyCode()==38)//arriba
        {
            vZ+=1;
            avanceM+=.1;         
        }
        else if(e.getKeyCode()==39)//der
            vY+=1;
        else if(e.getKeyCode()==40)//abajo
        {
            vZ-=1;
            avanceM-=.1;         
        }
        else if(e.getKeyCode()==83)//s
            vX+=1;
        else if(e.getKeyCode()==90)//z
            vX-=1;           
        repaint();
    }
    public void keyReleased(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }
}
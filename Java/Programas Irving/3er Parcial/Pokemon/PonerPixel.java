
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

public class PonerPixel {
    private final BufferedImage buffer;
    public static PonerPixel instancia;
    public static int ancho, alto;

    //Constructor para agregar tamaños y asignar la instancia
    public PonerPixel(BufferedImage bufferImagen) {
        buffer = bufferImagen;
        ancho = this.buffer.getWidth();
        alto = this.buffer.getHeight();
        instancia = this;
    }
    //Funcion para pintar pixel cuando esten listos los calculos
    public void pintarPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }
    
    public static void ponerFondoImagen(BufferedImage imagen,BufferedImage imagen2) 
    {
        imagen2.getGraphics().drawImage(imagen,0,0,instancia.buffer.getWidth(),instancia.buffer.getHeight(),null);
    }   
    
    //Funcion para pintar fondos
    public static void pintarFondo(Color c) {
        for (int i = 0; i < instancia.buffer.getWidth(); i++)
            for (int j = 0; j < instancia.buffer.getHeight(); j++)
                instancia.pintarPixel(i, j, c);
    }
}
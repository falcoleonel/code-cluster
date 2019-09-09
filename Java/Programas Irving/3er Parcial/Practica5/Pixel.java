
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

//Pintar pixel
public class Pixel {
    private final BufferedImage buffer;
    public static Pixel instance;
    public static int width, height;

    public Pixel(BufferedImage b) { 
        buffer = b;
        width = this.buffer.getWidth();
        height = this.buffer.getHeight();
        instance = this;
    }

    public void drawPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }

    public static void fondo(Color c) {
        for (int i = 0; i < instance.buffer.getWidth(); i++)
            for (int j = 0; j < instance.buffer.getHeight(); j++)
                instance.drawPixel(i, j, c);
    }
}

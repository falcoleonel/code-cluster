package dibujarpixel;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Imagen extends BufferedImage
{
    public Imagen(int w, int h)
    {
        super(w,h,BufferedImage.TYPE_INT_RGB);
    }
    public void dibujarPxl(int x, int y,Color c)
    {
        setRGB(x,y,c.getRGB());
    }
}

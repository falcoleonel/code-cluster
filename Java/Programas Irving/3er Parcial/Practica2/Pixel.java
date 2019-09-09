
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Pixel {
    private final BufferedImage buffer;
    public static Pixel instance;
    public static int width, height;
    private LinkedList<Point> list = new LinkedList<>();

    public Pixel(BufferedImage bi) {
        buffer = bi;
        list.add(new Point(1, 0));
        list.add(new Point(0, 1));
        list.add(new Point(-1, 0));
        list.add(new Point(0, -1));
        width = this.buffer.getWidth();
        height = this.buffer.getHeight();
        instance = this;
    }

    public void drawPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }

    public static void paintBackground(Color c) {
        for (int i = 0; i < instance.buffer.getWidth(); i++)
            for (int j = 0; j < instance.buffer.getHeight(); j++)
                instance.drawPixel(i, j, c);
    }
}

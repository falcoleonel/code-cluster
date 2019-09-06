import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class BasicModel {
    Pixel pixel = new Pixel(); //Instance of a Pixel

    public BasicModel(){
        pixel.setTitle("L\u00ednea con modelo b\u00e1sico");
    }
    // ------------------Methot to draw a line------------------------------------
    // x0,y0 ---> start of the line x0,y0 --> end of the line
    public void drawLine(int x0, int y0, int x1, int y1) {
        float y = 0;

        float m = cal_M(x0, y0, x1, y1); // slope
        float b = cal_B(x0, y0, x1, y1); // Independent Term
        
        for (int x = 0; x <= x1; x++) {
            y = (m * x) + b; // calculate y
            pixel.putPixel(x, Math.round(y), Color.BLACK);
        }

    }
    public float cal_M(int x0, int y0, int x1, int y1) {
        return (y1 - y0) / ((x1 - x0) == 0 ? 1 : (x1 - x0));
    }

    public float cal_B(int x0, int y0, int x1, int y1) {
        return y0 - (cal_M(x0, y0, x1, y1) * x0);
    }    

    public static void main(String[] args) {
        BasicModel line = new BasicModel();
        line.drawLine(50, 100, 200, 50);

    }

}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Proyecto extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        
        new Proyecto().setVisible(true);
    }

    // Cosas para dibujar

    public static void dibujarPixel(BufferedImage img, int x, int y, Color color) {
        if(x >= 0 && y >= 0 && x < img.getWidth() && y < img.getHeight())
            img.setRGB(x, y, color.getRGB());
    }

    public static void rellenarRectangulo(BufferedImage img, int x1, int y1, int x2, int y2, Color color) {
        
        for(int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++)
            for(int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++)
                dibujarPixel(img, x, y, color);
    }

    public static void rellenarCirculo(BufferedImage img, int x, int y, int r, Color color) {

        int X = 0;
        int Y = r;
        int delta = 2 * (1 - r);
        int error;
        
        while(Y >= 0) {
            
            for(int h = x - X; h <= x + X; h++) {
                dibujarPixel(img, h, y + Y, color);
                dibujarPixel(img, h, y - Y, color);
            }
            
            error = 2 * (delta + Y) - 1;
            if(delta < 0 && error <= 0) {
                delta += 2 * ++X + 1;
                continue;
            }
            
            error = 2 * (delta - X) - 1;
            if(delta > 0 && error > 0) {
                delta += 1 - 2 * --Y;
                continue;
            }
            
            delta += 2 * (++X - Y--);
        }
    }
	
	public static void dibujarCirculo(BufferedImage img, int x, int y, int r, int grosor, Color color) {

        int X = 0;
        int Y = r;
        int delta = 2 * (1 - r);
        int error;
        
        while(Y >= X) {
            
            for(int w = -(int)Math.floor(grosor / 2f); w < (int)Math.ceil(grosor / 2f); w++)
                for(int h = -(int)Math.floor(grosor / 2f); h < (int)Math.ceil(grosor / 2f); h++) {
					dibujarPixel(img, x + w + X, y + h + Y, color);
					dibujarPixel(img, x + w - X, y + h + Y, color);
					dibujarPixel(img, x + w + X, y + h - Y, color);
					dibujarPixel(img, x + w - X, y + h - Y, color);
					dibujarPixel(img, x + w + Y, y + h + X, color);
					dibujarPixel(img, x + w - Y, y + h + X, color);
					dibujarPixel(img, x + w + Y, y + h - X, color);
					dibujarPixel(img, x + w - Y, y + h - X, color);
				}
            
            error = 2 * (delta + Y) - 1;
            if(delta < 0 && error <= 0) {
                delta += 2 * ++X + 1;
                continue;
            }
            
            error = 2 * (delta - X) - 1;
            if(delta > 0 && error > 0) {
                delta += 1 - 2 * --Y;
                continue;
            }
            
            delta += 2 * (++X - Y--);
        }
    }

    public static void dibujarRecta(BufferedImage img, int x1, int y1, int x2, int y2, int grosor, Color color) {

        int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1, sy = y1 < y2 ? 1 : -1;
        int err = (dx > dy ? dx : -dy) / 2, e2;
        
        while(true) {
            
            for(int x = -(int)Math.floor(grosor / 2f); x < (int)Math.ceil(grosor / 2f); x++)
                for(int y = -(int)Math.floor(grosor / 2f); y < (int)Math.ceil(grosor / 2f); y++)
                    dibujarPixel(img, x1 + x, y1 + y, color);
            if(x1 == x2 && y1 == y2) break;
            e2 = err;
            
            if(e2 > -dx) { err -= dy; x1 += sx; }
            if(e2 < dy)  { err += dx; y1 += sy; }
        }
    }

    // El form

    BufferedImage fondo;
    Graphics2D gf;
    Dimension dimVentana;
    Timer timer;
    double t;

    public Proyecto() {

        t = 0;
        fondo = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
        gf = fondo.createGraphics();
        gf.setBackground(Color.CYAN);

        inicializar();
    }

    private void inicializar() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Proyecto Thomas");
        setResizable(false);

        Dimension dimPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        dimVentana = new Dimension(800, 600);
        setSize(dimVentana);
        setLocation(
            (dimPantalla.width - dimVentana.width) / 2,
            (dimPantalla.height - dimVentana.height) / 2
        );

        timer = new Timer(1000 / 30, e -> {
            t += 1.0 / 30;
            gf.clearRect(0, 0, dimVentana.width, dimVentana.height);
            dibujar(fondo, t);
            repaint();
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, this);
    }

    public static int encerrar(int a, int b, int v) {
        return (v - b) % (b - a) + a + b;
    }

    // dibujar lo del proyecto
    public void dibujar(BufferedImage img, double t) {

        for(int i = 0; i < 5; i++) {
			rellenarCirculo(img, 200 + 100 * i, 450, 45, Color.BLACK);
			dibujarCirculo(img, 200 + 100 * i, 450, 50, 10, Color.DARK_GRAY);
		}
//Linea
        double ct = Math.cos(Math.PI * t);
        double st = Math.sin(Math.PI * t);
        int vn = (int)(30 * t);
		
		dibujarRecta(img, 
            200 + (int)(50 * ct), 
            450 + (int)(50 * st), 
            600 + (int)(50 * ct), 
            450 + (int)(50 * st), 
            10, 
            Color.GRAY
        );

        rellenarRectangulo(img, 0, 500, 800, 600, Color.GREEN);

        rellenarCirculo(img, encerrar(-150, 950, 100 - vn), 110, 50, Color.WHITE);
        rellenarCirculo(img, encerrar(-150, 950, 150 - vn), 110, 50, Color.WHITE);
        rellenarCirculo(img, encerrar(-150, 950, 70 - vn), 130, 50, Color.WHITE);
        rellenarCirculo(img, encerrar(-150, 950, 110 - vn), 90, 50, Color.WHITE);

        rellenarCirculo(img, encerrar(-150, 950, 410 - vn), 180, 50, Color.WHITE);
        rellenarCirculo(img, encerrar(-150, 950, 470 - vn), 180, 50, Color.WHITE);
        rellenarCirculo(img, encerrar(-150, 950, 350 - vn), 150, 50, Color.WHITE);
        rellenarCirculo(img, encerrar(-150, 950, 420 - vn), 160, 50, Color.WHITE);

        rellenarCirculo(img, encerrar(-150, 950, 700 - vn), 130, 50, Color.WHITE);
        rellenarCirculo(img, encerrar(-150, 950, 770 - vn), 110, 50, Color.WHITE);
        rellenarCirculo(img, encerrar(-150, 950, 650 - vn), 80, 50, Color.WHITE);
        rellenarCirculo(img, encerrar(-150, 950, 720 - vn), 90, 50, Color.WHITE);
		
        rellenarRectangulo(img, 80, 230, 680, 400, Color.BLUE);//Cuerpo Azul
        rellenarRectangulo(img, 80, 120, 280, 400, Color.BLUE);//Cabina
        rellenarRectangulo(img, 100, 150, 260, 240, Color.gray);//Ventanas
        rellenarRectangulo(img, 175, 150, 185, 240, Color.BLACK);//Marco
        rellenarRectangulo(img, 680, 230, 700, 400, Color.GRAY);//Cara
        rellenarRectangulo(img, 600, 230, 650, 150, Color.BLACK);//Chimenea
        rellenarRectangulo(img, 0, 510, 800, 500, Color.gray);//Vias
		
		rellenarCirculo(img, encerrar(-100, 700, 600 - (int)(300 * t)), 150 + (int)(50 * st), 30, Color.DARK_GRAY);
		rellenarCirculo(img, encerrar(-100, 700, 400 - (int)(300 * t)), 170 + (int)(50 * ct), 30, Color.DARK_GRAY);
		rellenarCirculo(img, encerrar(-100, 700, 200 - (int)(300 * t)), 150 + (int)(50 * ct), 30, Color.DARK_GRAY);
    }
}


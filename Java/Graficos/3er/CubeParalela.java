import java.awt.*;
import java.awt.Color;
import java.util.*;

public class CubeParalela {

	public static void drawCube(int x1, int y1, int z1, int large) {

		ArrayList<Integer> pointsX1 = new ArrayList<Integer>();
		ArrayList<Integer> pointsY1 = new ArrayList<Integer>();
		ArrayList<Integer> pointsX2 = new ArrayList<Integer>();
		ArrayList<Integer> pointsY2 = new ArrayList<Integer>();

		int[] arrX = {0, 1, 1, 0, 0};
		int[] arrY = {0, 0, 1, 1, 0};

		double x, y, z = 400.0;

		// dirección de proyección está definida por el vector (xp, yp, zp)
		double xp = -60.0, yp = -60.0, zp = -86.0;

		//Cara Trasera 
		for(int index = 0; index < arrX.length; index++) {
			double tempX, tempY;
			
			tempX = x1 + (arrX[index] * large);
			tempY = y1 + (arrY[index] * large);

			// Ecuaciones paramétricas
			// x = x1 + xpu
			// y = y1 + ypu
			// z = z1 + zpu
			
			x = tempX + (xp * ((z - z1) / zp));

			// Pixel.height - ecuacion -->Derecha
			y = Pixel.height - (tempY + (yp * ((z - z1) / zp)));
			
			pointsX1.add((int)x); pointsY1.add((int)y);
			// System.out.println(x + " - " + y);
		}

		// Cara frontal 
		for(int index = 0; index < arrX.length; index++) {
			double tempX, tempY, tempZ; 
			
			tempZ = z1 + large; //Proyeccion 
			tempX = x1 + (arrX[index] * large);
			tempY = y1 + (arrY[index] * large);
			
			x = tempX + (xp * ((z - tempZ) / zp));
			y = Pixel.height - (tempY + (yp * ((z - tempZ) / zp)));
			
			pointsX2.add((int)x); pointsY2.add((int)y);
		}

		for(int index = 1; index < pointsX1.size(); index++) {

			Line.drawLine(pointsX1.get(index - 1), pointsY1.get(index - 1), pointsX1.get(index), pointsY1.get(index), new Color(255,31,85)); //1er cara
			Line.drawLine(pointsX2.get(index - 1), pointsY2.get(index - 1), pointsX2.get(index), pointsY2.get(index), new Color(255,31,85)); //2do cara
			Line.drawLine(pointsX1.get(index - 1), pointsY1.get(index - 1), pointsX2.get(index - 1), pointsY2.get(index - 1), new Color(152, 37, 164)); //Aristas
		}
	}
	
}

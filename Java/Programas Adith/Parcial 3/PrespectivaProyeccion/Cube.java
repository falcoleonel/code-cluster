import java.awt.*;
import java.awt.Color;
import java.util.*;

public class Cube {

	public static void drawCube(int x1, int y1, int z1, int large) {

		ArrayList<Integer> valuesX1 = new ArrayList<Integer>();
		ArrayList<Integer> valuesY1 = new ArrayList<Integer>();
		ArrayList<Integer> valuesX2 = new ArrayList<Integer>();
		ArrayList<Integer> valuesY2 = new ArrayList<Integer>();

		int[] arrX = {0, 1, 1, 0, 0};
		int[] arrY = {0, 0, 1, 1, 0};

		double x, y, z = 350.0;
		
		// dirección de proyección está definida por el vector (xp, yp, zp)
		double xc = -60.0; double yc = -40.0; double zc = -80.0;

		//Cara Trasera 
		for(int index = 0; index < arrX.length; index++) {
			double tempX, tempY;
			
			tempX = x1 + (arrX[index] * large);
			tempY = y1 + (arrY[index] * large);

			x = xc + (tempX - xc)*((z - zc) / (z1 - zc));
			y = (yc + (tempY - yc)*((z - zc) / (z1 - zc)));
			
			valuesX1.add((int)x); valuesY1.add((int)y);
			// System.out.println(x + " - " + y);
		}

		// Cara frontal 
		for(int index = 0; index < arrX.length; index++) {
			double tempX, tempY, tempZ; 
			
			tempZ = z1 + large; //Proyeccion 
			tempX = x1 + (arrX[index] * large);
			tempY = y1 + (arrY[index] * large);
			
			x = xc + (tempX - xc)*((z - zc) / (tempZ - zc));
			y = (yc + (tempY - yc)*((z - zc) / (tempZ - zc)));
			
			valuesX2.add((int)x); valuesY2.add((int)y);
		}

		for(int index = 1; index < valuesX1.size(); index++) {

			Linea.drawLine(valuesX1.get(index - 1), valuesY1.get(index - 1), valuesX1.get(index), valuesY1.get(index), new Color(46, 134, 193)); //1er cara
			Linea.drawLine(valuesX2.get(index - 1), valuesY2.get(index - 1), valuesX2.get(index), valuesY2.get(index), new Color(46, 134, 193)); //2do cara
			Linea.drawLine(valuesX1.get(index - 1), valuesY1.get(index - 1), valuesX2.get(index - 1), valuesY2.get(index - 1), new Color(234, 100, 76)); //Aristas
		}

	}
	
}
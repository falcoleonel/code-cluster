import java.awt.*;
import java.awt.Color;
import java.util.*;

public class CubeT {

	public static void drawCube(Translation vector, int large) {

		ArrayList<Integer> pointsX1 = new ArrayList<Integer>();
		ArrayList<Integer> pointsY1 = new ArrayList<Integer>();
		ArrayList<Integer> pointsX2 = new ArrayList<Integer>();
		ArrayList<Integer> pointsY2 = new ArrayList<Integer>();

		int[] arrX = {0, 1, 1, 0, 0};
		int[] arrY = {0, 0, 1, 1, 0};

		double x, y, z = 350.0;
		
		// direccion de proyeccion esta definida por el vector (xp, yp, zp)
		double xc = -60.0; double yc = -60.0; double zc = -80.0;

		//Cara Trasera 
		for(int index = 0; index < arrX.length; index++) {
			double tempX, tempY;
			
			tempX = vector.x + (arrX[index] * large);
			tempY = vector.y + (arrY[index] * large);

			x = xc + (tempX - xc)*((z - zc) / (vector.z - zc));
			y = (yc + (tempY - yc)*((z - zc) / (vector.z - zc)));
			
			pointsX1.add((int)x); pointsY1.add((int)y);
		}

		// Cara frontal 
		for(int index = 0; index < arrX.length; index++) {
			double tempX, tempY, tempZ; 
			
			//Proyeccion 
			tempZ = vector.z + large; 
			tempX = vector.x + (arrX[index] * large);
			tempY = vector.y + (arrY[index] * large);
			
			x = xc + (tempX - xc)*((z - zc) / (tempZ - zc));
			y = (yc + (tempY - yc)*((z - zc) / (tempZ - zc)));
			
			pointsX2.add((int)x); pointsY2.add((int)y);
		}

		for(int index = 1; index < pointsX1.size(); index++) {

			// primera cara
			Line.drawLine(pointsX1.get(index - 1), pointsY1.get(index - 1), pointsX1.get(index), pointsY1.get(index), new Color(52, 37, 164)); 
			// segunda cara
			Line.drawLine(pointsX2.get(index - 1), pointsY2.get(index - 1), pointsX2.get(index), pointsY2.get(index), new Color(52, 37, 164));
			// Aristas 
			Line.drawLine(pointsX1.get(index - 1), pointsY1.get(index - 1), pointsX2.get(index - 1), pointsY2.get(index - 1), new Color(152, 37, 164)); 
		}

	}
	
}

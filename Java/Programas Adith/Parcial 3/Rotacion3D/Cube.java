import java.awt.*;
import java.awt.Color;
import java.util.*;

public class Cube {

	public static void drawCube(ArrayList<Rotation> points) {

		double x, y, z = 430.0;
		double xp = -60.0; double yp = -60.0; double zp = -86.0;
		ArrayList<Rotation> tempPoints = new ArrayList<Rotation>();
		
		for(int index = 0; index < points.size(); index++) {

			x = points.get(index).x + (xp * ((z - points.get(index).z) / zp));
			y = Pixel.height - (points.get(index).y + (yp * ((z - points.get(index).z) / zp)));
			tempPoints.add(new Rotation((int)x, (int)y, 0));
		}
		
		for(int index = 0; index < (tempPoints.size() / 2) - 1; index++) {

			Line.drawLine(tempPoints.get(index).x, tempPoints.get(index).y, tempPoints.get(index + 1).x, tempPoints.get(index + 1).y, new Color(46, 134, 193)); 
			Line.drawLine(tempPoints.get(index + 4).x, tempPoints.get(index + 4).y, tempPoints.get(index + 5).x, tempPoints.get(index + 5).y, new Color(46, 134, 193)); 
			Line.drawLine(tempPoints.get(index).x, tempPoints.get(index).y, tempPoints.get(index + 4).x, tempPoints.get(index + 4).y, new Color(46, 134, 193)); 
		}
		Line.drawLine(tempPoints.get(3).x, tempPoints.get(3).y, tempPoints.get(0).x, tempPoints.get(0).y, new Color(46, 134, 193)); 
		Line.drawLine(tempPoints.get(7).x, tempPoints.get(7).y, tempPoints.get(4).x, tempPoints.get(4).y, new Color(46, 134, 193)); 
		Line.drawLine(tempPoints.get(3).x, tempPoints.get(3).y, tempPoints.get(7).x, tempPoints.get(7).y, new Color(46, 134, 193)); 
	

	}

	public static ArrayList<Rotation> getPoints(Rotation vector, Rotation growth) {

		ArrayList<Rotation> points = new ArrayList<Rotation>();
		int[] arrX = {0, 1, 1, 0};
		int[] arrY = {0, 0, 1, 1};
		int x, y; int z = 0; //Rotación sobre su propio eje
		
		for(int index = 0; index < arrX.length; index++) {
			
			x = vector.x + (arrX[index] * growth.x);
			y = vector.y + (arrY[index] * growth.y);
			points.add(new Rotation(x, y, z));
		}
		z = z + growth.z;
		for(int index = 0; index < arrX.length; index++) {
			x = vector.x + (arrX[index] * growth.x);
			y = vector.y + (arrY[index] * growth.y);
			points.add(new Rotation(x, y, z));
		}
	
		return points;
	}
	
}
import java.awt.*;
import java.awt.Color;
import java.util.*;

public class Surface {
    public int x, y, z;
    public double _x, _y, _z;

    public Surface(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Surface(double x, double y, double z){
        this._x = x;
        this._y = y;
        this._z = z;
    }


	public static ArrayList<Surface> getSurfacePoints(Surface origin, Surface destiny, int div) {
		ArrayList<Surface> points = new ArrayList<Surface>();
		int x = 0; int y = 0; int z = 0;
		
		ArrayList<Integer> pointsX = new ArrayList<Integer>();
		ArrayList<Integer> pointsY = new ArrayList<Integer>();
		ArrayList<Integer> pointsZ = new ArrayList<Integer>();
		
		double incX = (((double)destiny.x - (double)origin.x) / (double)div);
		double incY = (((double)destiny.y - (double)origin.y) / (double)div);
		double incZ = (((double)destiny.z - (double)origin.z) / (double)div);
		
		if(destiny.x == 0) {
			for(double index = origin.y; index <= destiny.y; index+=incY) {
				pointsY.add((int)index);
			}
			for(double index2 = origin.z; index2 <= destiny.z; index2+=incZ) {
				pointsZ.add((int)index2);
			}
			for(int varZ = 1; varZ < pointsZ.size(); varZ++) {
				for(int varY = 1; varY < pointsY.size(); varY++) {
				
					points.add(new Surface(x, pointsY.get(varY - 1), pointsZ.get(varZ - 1)));
					points.add(new Surface(x, pointsY.get(varY), pointsZ.get(varZ - 1)));
					points.add(new Surface(x, pointsY.get(varY), pointsZ.get(varZ)));
					points.add(new Surface(x, pointsY.get(varY - 1), pointsZ.get(varZ)));
				}
			}
		}
		if(destiny.y == 0) {
			for(double index = origin.x; index <= destiny.x; index+=incX) {
				pointsX.add((int)index);
			}
			for(double index2 = origin.z; index2 <= destiny.z; index2+=incZ) {
				pointsZ.add((int)index2);
			}
			for(int varZ = 1; varZ < pointsZ.size(); varZ++) {
				for(int varX = 1; varX < pointsX.size(); varX++) {
					
					points.add(new Surface(pointsX.get(varX - 1), y, pointsZ.get(varZ - 1)));
					points.add(new Surface(pointsX.get(varX), y, pointsZ.get(varZ - 1)));
					points.add(new Surface(pointsX.get(varX), y, pointsZ.get(varZ)));
					points.add(new Surface(pointsX.get(varX - 1), y, pointsZ.get(varZ)));
				}
			}
		}
		
		if(destiny.z == 0) {
			for(double index = origin.x; index <= destiny.x; index+=incX) {
				pointsX.add((int)index);
			}
			for(double index2 = origin.y; index2 <= destiny.y; index2+=incY) {
				pointsY.add((int)index2);
			}
			for(int varY = 1; varY < pointsY.size(); varY++) {
				for(int varX = 1; varX < pointsX.size(); varX++) {
					
					points.add(new Surface(pointsX.get(varX - 1), pointsY.get(varY - 1), z));
					points.add(new Surface(pointsX.get(varX), pointsY.get(varY - 1), z));
					points.add(new Surface(pointsX.get(varX), pointsY.get(varY), z));
					points.add(new Surface(pointsX.get(varX - 1), pointsY.get(varY), z));
				}
			}
		}
		
		return points;
	}
	
	public static void drawSurface(ArrayList<Surface> points) {
		int limit = 1;
		double x, y, z = 400.0;
		double xp = -60.0; double yp = -60.0; double zp = -80.0;
		ArrayList<Surface> tempBase = new ArrayList<Surface>();
		ArrayList<Surface> tempDeep = new ArrayList<Surface>();

		
		for(int index = 0; index < points.size(); index++) {
			
			x = points.get(index).x + (xp * ((z - points.get(index).z) / zp));
			y = Pixel.height - (points.get(index).y + (yp * ((z - points.get(index).z) / zp)));
		
			tempBase.add(new Surface((int)x, (int)y, 0));
			
			double tempY = (Math.sin(Math.toRadians(90 + ((points.get(index).x + points.get(index).z) / 2 * 1.125))) * 80);
		
			x = points.get(index).x + (xp * ((z - points.get(index).z) / zp));
			y = Pixel.height - (tempY + (yp * ((z - points.get(index).z) / zp)));
		
			tempDeep.add(new Surface((int)x, (int)y, 0));
		}
		
		for(int index = 1; index <= tempDeep.size(); index++) {
			if(limit % 4 == 0) {
				Line.drawLine(tempDeep.get(index - 1).x, tempDeep.get(index - 1).y, tempDeep.get(index - 4).x, tempDeep.get(index - 4).y, new Color(52, 37, 164));
				limit = 1;
			}
			else {
				Line.drawLine(tempDeep.get(index - 1).x, tempDeep.get(index - 1).y, tempDeep.get(index).x, tempDeep.get(index).y, new Color(52, 37, 164));
				limit++;
			}
		}
	}

	public static ArrayList<Surface> doRotationX(ArrayList<Surface> points, double degree) {
		
		int x, y, z;
		ArrayList<Surface> temp = new ArrayList<Surface>();
		
		for(int index = 0; index < points.size(); index++) {
			
			x = points.get(index).x;
		// y' = y cos(A) + z sin(A)
			y = (int)(((double)points.get(index).y * Math.cos(Math.toRadians(degree))) + ((double)points.get(index).z * Math.sin(Math.toRadians(degree))));
		// z' = zsin(A) - y sin(A)
			z = (int)(((double)points.get(index).z * Math.cos(Math.toRadians(degree))) - ((double)points.get(index).y * Math.sin(Math.toRadians(degree)))) ;
									
			temp.add(new Surface(x, y, z));
		}
		
		return temp;
	}
	public static ArrayList<Surface> doRotationY(ArrayList<Surface> points, double degree) {

		int x, y, z;
		ArrayList<Surface> temp = new ArrayList<Surface>();
		for(int index = 0; index < points.size(); index++) {

			y = points.get(index).y;
		// x' = x cos(A) - z sin(A)
			x = (int)(((double)points.get(index).x * Math.cos(Math.toRadians(degree))) - ((double)points.get(index).z * Math.sin(Math.toRadians(degree))));
		// z' =  x sin(A) + z sin(A)
			z = (int)(((double)points.get(index).x * Math.sin(Math.toRadians(degree))) + ((double)points.get(index).z * Math.cos(Math.toRadians(degree))));
			
			temp.add(new Surface(x, y, z));
		}
		
		return temp;
	}
	public static ArrayList<Surface> doRotationZ(ArrayList<Surface> points, double degree) {
	
		int x, y, z;
		ArrayList<Surface> temp = new ArrayList<Surface>();
		for(int index = 0; index < points.size(); index++) {
			
			z = points.get(index).z;

		// x' = x cos(A) + y sin(A)
			x = (int)(((double)points.get(index).x * Math.cos(Math.toRadians(degree))) + ((double)points.get(index).y * Math.sin(Math.toRadians(degree))));
		// y' = y cos(A) - x sin(A)
			y = (int)( ((double)points.get(index).y * Math.cos(Math.toRadians(degree))) - ((double)points.get(index).x * Math.sin(Math.toRadians(degree))));
			temp.add(new Surface(x, y, z));
		}
		
		return temp;
	}
}

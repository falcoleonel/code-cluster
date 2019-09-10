

import java.awt.*;
import java.awt.Color;
import java.util.*;

public class Cylinder {
    public int x, y, z;
	public double _x, _y, _z;

	public Cylinder(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
	public Cylinder(double x, double y, double z){
        this._x = x;
        this._y = y;
        this._z = z;
    }

    public static ArrayList<ArrayList<Cylinder>> getCylinderPoints( double size) {
		ArrayList<ArrayList<Cylinder>> points = new ArrayList<ArrayList<Cylinder>>();
		double x, y, z, inc, scl;
		double t, phi;
		inc = Math.PI / 8; 
		scl = size;

		//x=(2 + cos(t)) cos(A)
		//z = (2 + cos(t)) sen(A)
		//y = t

		for(t = 0; t<=(2 * Math.PI); t+=inc) {
			ArrayList<Cylinder> temp = new ArrayList<Cylinder>();

			for(phi = 0; phi<=(2 * Math.PI); phi+=inc) {
				x = ((2 + Math.cos(t)) * Math.cos(phi)) * scl;
				z = ((2 + Math.cos(t)) * Math.sin(phi)) * scl;
				y = t * scl;
				temp.add(new Cylinder((int)x, (int)y, (int)z));
			}
			points.add(temp);
		}
		return points;
	}

    public static void drawCylinder(ArrayList<ArrayList<Cylinder>> points) {
		double x, y, z = 430.0;
		double xp = -60.0; double yp = -60.0; double zp = -86.0;

		ArrayList<ArrayList<Cylinder>> temPoints = new ArrayList<ArrayList<Cylinder>>();
		
		for(int index = 0; index < points.size(); index++) {

			ArrayList<Cylinder> temPointsAL = new ArrayList<Cylinder>();
			for(int index2 = 0; index2 < points.get(index).size(); index2++) {

				x = points.get(index).get(index2).x + (xp * ((z - points.get(index).get(index2).z) / zp));
				y = Pixel.height - (points.get(index).get(index2).y + (yp * ((z - points.get(index).get(index2).z) / zp)));
				temPointsAL.add(new Cylinder((int)x, (int)y, 0));
			}
			temPoints.add(temPointsAL);
		}
		
		for(int index = 0; index < temPoints.size(); index++) {
			for(int index2 = 1; index2 < temPoints.get(index).size(); index2++) {
				//Circulo
				Line.drawLine(temPoints.get(index).get(index2).x ,temPoints.get(index).get(index2).y ,temPoints.get(index).get(index2 - 1).x, temPoints.get(index).get(index2 - 1).y, new Color(46, 134, 193));
				if(index > 0) {
					Line.drawLine(temPoints.get(index).get(index2).x ,temPoints.get(index).get(index2).y ,temPoints.get(index - 1).get(index2).x,temPoints.get(index - 1).get(index2).y, new Color(46, 134, 193));
				}
			}
			Line.drawLine(temPoints.get(index).get(temPoints.get(index).size() - 1).x,temPoints.get(index).get(temPoints.get(index).size() - 1).y,temPoints.get(index).get(0).x,temPoints.get(index).get(0).y, new Color(46, 134, 193));
		}	
	}

	public static ArrayList<ArrayList<Cylinder>> do3DRotation(ArrayList<ArrayList<Cylinder>> points, Cylinder degrees) {
		int x, y, z;
		ArrayList<ArrayList<Cylinder>> tempX = new ArrayList<ArrayList<Cylinder>>();
		ArrayList<ArrayList<Cylinder>> tempY = new ArrayList<ArrayList<Cylinder>>();
		ArrayList<ArrayList<Cylinder>> tempZ = new ArrayList<ArrayList<Cylinder>>();
		
		tempX = doRotation3DX(points, degrees._x);
		tempY = doRotation3DY(tempX, degrees._y);
		tempZ = doRotation3DZ(tempY, degrees._z);
		return tempZ;
	}

	public static ArrayList<ArrayList<Cylinder>> doRotation3DX(ArrayList<ArrayList<Cylinder>> points, double degree) {
		int x, y, z;
		ArrayList<ArrayList<Cylinder>> temp = new ArrayList<ArrayList<Cylinder>>();
		for(int index = 0; index < points.size(); index++) {

			ArrayList<Cylinder> tempAL = new ArrayList<Cylinder>();

			for(int index2 = 0; index2 < points.get(index).size(); index2++) {
				x = points.get(index).get(index2).x;
				y = (int)(((double)points.get(index).get(index2).y * Math.cos(Math.toRadians(degree)))  + ((double)points.get(index).get(index2).z * Math.sin(Math.toRadians(degree))));
				z = (int)(((double)points.get(index).get(index2).y * -Math.sin(Math.toRadians(degree)))  + ((double)points.get(index).get(index2).z * Math.cos(Math.toRadians(degree))));
				tempAL.add(new Cylinder((int)x, (int)y, (int)z));
			}
			temp.add(tempAL);
		}
		return temp;
	}
	public static ArrayList<ArrayList<Cylinder>> doRotation3DY(ArrayList<ArrayList<Cylinder>> points, double degree) {
		int x, y, z;
		ArrayList<ArrayList<Cylinder>> temp = new ArrayList<ArrayList<Cylinder>>();
		for(int index = 0; index < points.size(); index++) {
			ArrayList<Cylinder> tempAL = new ArrayList<Cylinder>();
			for(int index2 = 0; index2 < points.get(index).size(); index2++) {
				x = (int)(((double)points.get(index).get(index2).x * Math.cos(Math.toRadians(degree))) + (-(double)points.get(index).get(index2).z * Math.sin(Math.toRadians(degree))));
				y = points.get(index).get(index2).y;
				z = (int)(((double)points.get(index).get(index2).x * Math.sin(Math.toRadians(degree)))  + ((double)points.get(index).get(index2).z * Math.cos(Math.toRadians(degree))));
				tempAL.add(new Cylinder((int)x, (int)y, (int)z));
			}
			temp.add(tempAL);
		}
		return temp;
	}
	public static ArrayList<ArrayList<Cylinder>> doRotation3DZ(ArrayList<ArrayList<Cylinder>> points, double degree) {
		int x, y, z;
		ArrayList<ArrayList<Cylinder>> temp = new ArrayList<ArrayList<Cylinder>>();
		for(int index = 0; index < points.size(); index++) {
			ArrayList<Cylinder> tempAL = new ArrayList<Cylinder>();
			for(int index2 = 0; index2 < points.get(index).size(); index2++) {
				x = (int)(((double)points.get(index).get(index2).x * Math.cos(Math.toRadians(degree)))  + ((double)points.get(index).get(index2).y * Math.sin(Math.toRadians(degree))));
				y = (int)(((double)points.get(index).get(index2).x * -Math.sin(Math.toRadians(degree)))  + ((double)points.get(index).get(index2).y * Math.cos(Math.toRadians(degree))));
				z = points.get(index).get(index2).z;
				tempAL.add(new Cylinder((int)x, (int)y, (int)z));
			}
			temp.add(tempAL);
		}
		return temp;
	}
}
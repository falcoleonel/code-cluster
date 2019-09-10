

import java.awt.*;
import java.awt.Color;
import java.util.*;

public class Curve {
    public int x, y, z;
	public static Curve instance;
    public Curve(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Curve(double x, double y, double z){
        this._x = x;
        this._y = y;
        this._z = z;
    }

	public static void reset3DPoint(Curve point) {
		point.x = 0;
		point.y = 0;
		point.z = 0;
	}
	public static void reset3DPointA(Curve point) {
		point.x = 40;
		point.y = 40;
		point.z = 40;
	}
	public static Curve traslacion(Curve point, Curve movement) {
		point.x += movement.x;
		point.y += movement.y;
		point.z += movement.z;
		return point;
	}

	public static ArrayList<Curve> getPoints(Curve origin) {
		
		double t;
		int s = 100;
		
		ArrayList<Curve> p = new ArrayList<Curve>();
		
		double inicio = -Math.PI;
		double fin = Math.PI;
		
		for(t = inicio; t <= fin; t+=0.05) 
		{
			origin._x = s * Math.cos(4 * t);
			origin._y = s *2 * Math.pow(Math.cos(t), 2);
			origin._z = s * Math.sin(2 * t);
						
			p.add(new Curve((int)origin.x, (int)origin.y, (int)origin.z));
		}

		return p;
	}
	
	public static void drawCurve(ArrayList<Curve> points) {
		double x, y; double z = 430.0;
		double xp = -60.0; double yp = -60.0; double zp = -86.0;
		ArrayList<Curve> tempDrawPoints = new ArrayList<Curve>();
		
		for(int cont = 0; cont < points.size(); cont++) {
			x = points.get(cont).x + (xp * ((z - points.get(cont).z) / zp));
			y = Pixel.height - (points.get(cont).y + (yp * ((z - points.get(cont).z) / zp)));
			tempDrawPoints.add(new Curve((int)x, (int)y, 0));
		}
		
		for(int cont = 1; cont < tempDrawPoints.size(); cont++) {
			Line.drawLine(tempDrawPoints.get(cont).x, tempDrawPoints.get(cont).y, tempDrawPoints.get(cont - 1).x, tempDrawPoints.get(cont - 1).y, new Color(46, 134, 193));
		}
		Line.drawLine(tempDrawPoints.get(tempDrawPoints.size() - 1).x, tempDrawPoints.get(tempDrawPoints.size() - 1).y, tempDrawPoints.get(0).x, tempDrawPoints.get(0).y, new Color(46, 134, 193));
	}

	public static ArrayList<Curve> doRotationX(ArrayList<Curve> points, double degree) {
		
		int x, y, z;
		ArrayList<Curve> temp = new ArrayList<Curve>();
		
		for(int index = 0; index < points.size(); index++) {
			
			x = points.get(index).x;
		// y' = y cos(A) + z sin(A)
			y = (int)(((double)points.get(index).y * Math.cos(Math.toRadians(degree))) + ((double)points.get(index).z * Math.sin(Math.toRadians(degree))));
		// z' = zsin(A) - y sin(A)
			z = (int)(((double)points.get(index).z * Math.cos(Math.toRadians(degree))) - ((double)points.get(index).y * Math.sin(Math.toRadians(degree)))) ;
									
			temp.add(new Curve(x, y, z));
		}
		
		return temp;
	}
	public static ArrayList<Curve> doRotationY(ArrayList<Curve> points, double degree) {

		int x, y, z;
		ArrayList<Curve> temp = new ArrayList<Curve>();
		for(int index = 0; index < points.size(); index++) {

			y = points.get(index).y;
		// x' = x cos(A) - z sin(A)
			x = (int)(((double)points.get(index).x * Math.cos(Math.toRadians(degree))) - ((double)points.get(index).z * Math.sin(Math.toRadians(degree))));
		// z' =  x sin(A) + z sin(A)
			z = (int)(((double)points.get(index).x * Math.sin(Math.toRadians(degree))) + ((double)points.get(index).z * Math.cos(Math.toRadians(degree))));
			
			temp.add(new Curve(x, y, z));
		}
		
		return temp;
	}
	public static ArrayList<Curve> doRotationZ(ArrayList<Curve> points, double degree) {
	
		int x, y, z;
		ArrayList<Curve> temp = new ArrayList<Curve>();
		for(int index = 0; index < points.size(); index++) {
			
			z = points.get(index).z;

		// x' = x cos(A) + y sin(A)
			x = (int)(((double)points.get(index).x * Math.cos(Math.toRadians(degree))) + ((double)points.get(index).y * Math.sin(Math.toRadians(degree))));
		// y' = y cos(A) - x sin(A)
			y = (int)( ((double)points.get(index).y * Math.cos(Math.toRadians(degree))) - ((double)points.get(index).x * Math.sin(Math.toRadians(degree))));
			temp.add(new Curve(x, y, z));
		}
		
		return temp;
	}
}
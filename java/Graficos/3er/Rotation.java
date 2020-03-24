import java.awt.*;
import java.awt.Color;
import java.util.*;

public class Rotation {
    public int x, y, z;
	
    public Rotation(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
	public static void reset(Rotation point) {
		point.x = 0;
		point.y = 0;
		point.z = 0;
	}
	public static void resetDistPoint(Rotation point) {
		point.x = 40;
		point.y = 40;
		point.z = 40;
	}

	public static ArrayList<Rotation> doRotationX(ArrayList<Rotation> points, double degree) {
		
		int x, y, z;
		ArrayList<Rotation> temp = new ArrayList<Rotation>();

		for(int index = 0; index < points.size(); index++) {
			
			x = points.get(index).x;
		// y' = y cos(A) + z sin(A)
			y = (int)(((double)points.get(index).y * Math.cos(Math.toRadians(degree))) + ((double)points.get(index).z * Math.sin(Math.toRadians(degree))));
		// z' = zsin(A) - y sin(A)
			z = (int)(((double)points.get(index).z * Math.cos(Math.toRadians(degree))) - ((double)points.get(index).y * Math.sin(Math.toRadians(degree)))) ;
									
			temp.add(new Rotation(x, y, z));
		}
		
		return temp;
	}
	public static ArrayList<Rotation> doRotationY(ArrayList<Rotation> points, double degree) {

		int x, y, z;
		ArrayList<Rotation> temp = new ArrayList<Rotation>();
		for(int index = 0; index < points.size(); index++) {

			y = points.get(index).y;
		// x' = x cos(A) - z sin(A)
			x = (int)(((double)points.get(index).x * Math.cos(Math.toRadians(degree))) - ((double)points.get(index).z * Math.sin(Math.toRadians(degree))));
		// z' =  x sin(A) + z sin(A)
			z = (int)(((double)points.get(index).x * Math.sin(Math.toRadians(degree))) + ((double)points.get(index).z * Math.cos(Math.toRadians(degree))));
			
			temp.add(new Rotation(x, y, z));
		}
		
		return temp;
	}
	public static ArrayList<Rotation> doRotationZ(ArrayList<Rotation> points, double degree) {
	
		int x, y, z;
		ArrayList<Rotation> temp = new ArrayList<Rotation>();
		for(int index = 0; index < points.size(); index++) {
			
			z = points.get(index).z;

		// x' = x cos(A) + y sin(A)
			x = (int)(((double)points.get(index).x * Math.cos(Math.toRadians(degree))) + ((double)points.get(index).y * Math.sin(Math.toRadians(degree))));
		// y' = y cos(A) - x sin(A)
			y = (int)( ((double)points.get(index).y * Math.cos(Math.toRadians(degree))) - ((double)points.get(index).x * Math.sin(Math.toRadians(degree))));
			temp.add(new Rotation(x, y, z));
		}
		
		return temp;
	}
}

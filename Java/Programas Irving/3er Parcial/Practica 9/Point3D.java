

import java.util.ArrayList;


public class Point3D {
    public int x, y, z;
    public static Point3D instance;

    public Point3D(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void resetPoint(Point3D point) {
        point.x = 0;
        point.y = 0;
        point.z = 0;
    }

    public static ArrayList<Point3D> rotacionX(ArrayList<Point3D> points, double degree) {
        int x, y, z;
        ArrayList<Point3D> temp = new ArrayList<>();
        for (Point3D point : points) {
            x = point.x;
            y = (int) (((double) point.y * Math.cos(Math.toRadians(degree))) + ((double) point.z * Math.sin(Math.toRadians(degree))));
            z = (int) (((double) point.y * -Math.sin(Math.toRadians(degree))) + ((double) point.z * Math.cos(Math.toRadians(degree))));
            temp.add(new Point3D(x, y, z));
        }
        return temp;
    }

    public static ArrayList<Point3D> rotacionY(ArrayList<Point3D> points, double degree) {
        int x, y, z;
        ArrayList<Point3D> temp = new ArrayList<>();
        for (Point3D point : points) {
            x = (int) (((double) point.x * Math.cos(Math.toRadians(degree))) + (-(double) point.z * Math.sin(Math.toRadians(degree))));
            y = point.y;
            z = (int) (((double) point.x * Math.sin(Math.toRadians(degree))) + ((double) point.z * Math.cos(Math.toRadians(degree))));
            temp.add(new Point3D(x, y, z));
        }
        return temp;
    }

    public static ArrayList<Point3D> rotacionZ(ArrayList<Point3D> points, double degree) {
        int x, y, z;
        ArrayList<Point3D> temp = new ArrayList<>();
        for (Point3D point : points) {
            x = (int) (((double) point.x * Math.cos(Math.toRadians(degree))) + ((double) point.y * Math.sin(Math.toRadians(degree))));
            y = (int) (((double) point.x * -Math.sin(Math.toRadians(degree))) + ((double) point.y * Math.cos(Math.toRadians(degree))));
            z = point.z;
            temp.add(new Point3D(x, y, z));
        }
        return temp;
    }
}
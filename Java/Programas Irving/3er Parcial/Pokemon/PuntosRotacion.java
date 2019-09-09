
import java.util.*;

public class PuntosRotacion {
    public int x, y, z;
    public double gX,gY,gZ;
    public static PuntosRotacion instancia;

    //Constructor para asignar los vectores iniciales
    public PuntosRotacion(int xInicial, int yInicial, int zInicial){
        this.x = xInicial;
        this.y = yInicial;
        this.z = zInicial;
        this.gX = xInicial;
        this.gY = yInicial;
        this.gZ = zInicial;
    }
    
    //Funcion para rotar los puntos con los vectores iniciales
    public PuntosRotacion(double gX, double gY, double gZ){
        this.gX = gX;
        this.gY = gY;
        this.gZ = gZ;
    }
    
    //Funcion de rotacion de los vectores, usando las formulas generales de rotacion en 3D
    public static ArrayList<PuntosRotacion> rotacionCompleta(ArrayList<PuntosRotacion> points, double rotacionX,double rotacionY,double rotacionZ) {
        double x, y, z, tx,ty,tz;
        ArrayList<PuntosRotacion> punto = new ArrayList<>();
        for (PuntosRotacion point : points) {
            x=point.gX;
            y=point.gY;
            z=point.gZ;
            if(rotacionX!=0){
            ty=y;
            tz=z;
            y = (((double) ty * Math.cos(Math.toRadians(rotacionX))) + ((double) tz * Math.sin(Math.toRadians(rotacionX))));
            z = (((double) ty * -Math.sin(Math.toRadians(rotacionX))) + ((double) tz * Math.cos(Math.toRadians(rotacionX))));
}

            if(rotacionY!=0){
            tx=x;
            tz=z;
            x = (((double) tx * Math.cos(Math.toRadians(rotacionY))) + (-(double) tz * Math.sin(Math.toRadians(rotacionY))));
            z = (((double) tx * Math.sin(Math.toRadians(rotacionY))) + ((double) tz * Math.cos(Math.toRadians(rotacionY))));
}
            if(rotacionZ!=0){
            tx=x;
            ty=y;
            x = (((double) tx * Math.cos(Math.toRadians(rotacionZ))) + ((double) ty * Math.sin(Math.toRadians(rotacionZ))));
            y = (((double) tx * -Math.sin(Math.toRadians(rotacionZ))) + ((double) ty * Math.cos(Math.toRadians(rotacionZ))));            
            }
            punto.add(new PuntosRotacion(x, y, z));
        }
        return punto;
    }
}
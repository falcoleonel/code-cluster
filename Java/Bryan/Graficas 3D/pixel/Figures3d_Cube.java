package pixel;

import java.awt.*;
import java.lang.Math;

/**
 * Figures3d_Cube
 */
public class Figures3d_Cube extends Figures3d{

    public Cube cubo3D ;
    public Figures3d_Cube (int w, int h){
        super(w,h);
        
        cubo3D=new Cube(0, 0, 0, U);
    }
    public void DrawCubo3D(Color c) {

        drawLine(cubo3D.Vertice[0].x ,cubo3D.Vertice[0].y,cubo3D.Vertice[0].z, cubo3D.Vertice[1].x ,cubo3D.Vertice[1].y,cubo3D.Vertice[1].z,c);
        drawLine(cubo3D.Vertice[0].x ,cubo3D.Vertice[0].y,cubo3D.Vertice[0].z, cubo3D.Vertice[2].x ,cubo3D.Vertice[2].y,cubo3D.Vertice[2].z,c);
        drawLine(cubo3D.Vertice[0].x ,cubo3D.Vertice[0].y,cubo3D.Vertice[0].z, cubo3D.Vertice[4].x ,cubo3D.Vertice[4].y,cubo3D.Vertice[4].z,c);
        drawLine(cubo3D.Vertice[4].x ,cubo3D.Vertice[4].y,cubo3D.Vertice[4].z, cubo3D.Vertice[5].x ,cubo3D.Vertice[5].y,cubo3D.Vertice[5].z,c);
        drawLine(cubo3D.Vertice[4].x ,cubo3D.Vertice[4].y,cubo3D.Vertice[4].z, cubo3D.Vertice[6].x ,cubo3D.Vertice[6].y,cubo3D.Vertice[6].z,c);

        drawLine(cubo3D.Vertice[3].x ,cubo3D.Vertice[3].y,cubo3D.Vertice[3].z, cubo3D.Vertice[1].x ,cubo3D.Vertice[1].y,cubo3D.Vertice[1].z,c);
        drawLine(cubo3D.Vertice[3].x ,cubo3D.Vertice[3].y,cubo3D.Vertice[3].z, cubo3D.Vertice[2].x ,cubo3D.Vertice[2].y,cubo3D.Vertice[2].z,c);
        drawLine(cubo3D.Vertice[3].x ,cubo3D.Vertice[3].y,cubo3D.Vertice[3].z, cubo3D.Vertice[7].x ,cubo3D.Vertice[7].y,cubo3D.Vertice[7].z,c);
        drawLine(cubo3D.Vertice[7].x ,cubo3D.Vertice[7].y,cubo3D.Vertice[7].z, cubo3D.Vertice[5].x ,cubo3D.Vertice[5].y,cubo3D.Vertice[5].z,c);
        drawLine(cubo3D.Vertice[7].x ,cubo3D.Vertice[7].y,cubo3D.Vertice[7].z, cubo3D.Vertice[6].x ,cubo3D.Vertice[6].y,cubo3D.Vertice[6].z,c);

        drawLine(cubo3D.Vertice[1].x ,cubo3D.Vertice[1].y,cubo3D.Vertice[1].z, cubo3D.Vertice[5].x ,cubo3D.Vertice[5].y,cubo3D.Vertice[5].z,c);
        drawLine(cubo3D.Vertice[2].x ,cubo3D.Vertice[2].y,cubo3D.Vertice[2].z, cubo3D.Vertice[6].x ,cubo3D.Vertice[6].y,cubo3D.Vertice[6].z,c);
    }
    public void DrawCubo3D_rotado(int anguloX, int anguloY, int anguloZ, Color c) {
        //Cube cubo_aux = new Cube(cubo3D.Centro.x, cubo3D.Centro.y, cubo3D.Centro.z, cubo3D.tamaño);
        //Cube cubo_aux = cubo3D;
        //cubo_aux.rotacionX(anguloX);
        cubo3D.rotacionX(anguloX);    
        DrawCubo3D(c);

        /**
             * 
             System.out.println("Vertice_cubo_aux"+cubo_aux.Vertice[0].name);
             System.out.println("Vertice_cubo_aux"+cubo_aux.Vertice[0].x);
             System.out.println("Vertice_cubo_aux"+cubo_aux.Vertice[0].y);
             System.out.println("Vertice_cubo_aux"+cubo_aux.Vertice[0].z);
             */
            //cubo3D=cubo_aux;
    }
    public void move(int x, int y, int z) {
        move_x(x);
        move_y(y);
        move_z(z);
        System.out.println("x" + cubo3D.Centro.x);
        System.out.println("y" + cubo3D.Centro.y);
        System.out.println("z" + cubo3D.Centro.z);
        cubo3D.calcular_vertices();
    }
    public void move_x(int posicion) {
        int aux = cubo3D.Centro.x;
        cubo3D.Centro.x= (aux + posicion);
    }
    public void move_y(int posicion) {
        cubo3D.Centro.y= (get_y_p() + posicion);
    }
    public void move_z(int posicion) {
        cubo3D.Centro.z= (get_z_p() + posicion);
    }
    public void resize(int tam) {
        cubo3D.tamaño=U*tam;
        //System.out.println("Tamaño"+cubo3D.tamaño);
        cubo3D.calcular_vertices();
    }
    public int get_tam() {
        return (int)(cubo3D.tamaño/U);
    }
    public int get_x_p() {
        return cubo3D.Centro.x;
    }
    public int get_y_p() {
        return cubo3D.Centro.y;
    }
    public int get_z_p() {
        return cubo3D.Centro.z;
    }
}
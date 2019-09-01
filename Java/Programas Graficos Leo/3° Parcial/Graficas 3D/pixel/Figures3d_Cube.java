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
        
        cubo3D=new Cube(0, 0, 0, U, Color.PINK);
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
        
        Coordenada3d[] Vertice_aux = cubo3D.Vertice;

        for (int i = 0; i < cubo3D.Vertice.length ; i++) {
        //for (int i = 0; i < 2 ; i++) {
            
            //System.out.println("x: "+Vertice_aux[i].x);
            //System.out.println("y: "+Vertice_aux[i].y);
            //System.out.println("z: "+Vertice_aux[i].z);

            Vertice_aux[i]=calcula_rotacion_x(cubo3D.Vertice[i], anguloX);

            //System.out.println("x: "+Vertice_aux[i].x);
            //System.out.println("y: "+Vertice_aux[i].y);
            //System.out.println("z: "+Vertice_aux[i].z);
        }

        drawLine(Vertice_aux[0].x ,Vertice_aux[0].y,Vertice_aux[0].z, Vertice_aux[2].x ,Vertice_aux[2].y,Vertice_aux[2].z,c);
        drawLine(Vertice_aux[0].x ,Vertice_aux[0].y,Vertice_aux[0].z, Vertice_aux[4].x ,Vertice_aux[4].y,Vertice_aux[4].z,c);
        drawLine(Vertice_aux[0].x ,Vertice_aux[0].y,Vertice_aux[0].z, Vertice_aux[1].x ,Vertice_aux[1].y,Vertice_aux[1].z,c);
        drawLine(Vertice_aux[4].x ,Vertice_aux[4].y,Vertice_aux[4].z, Vertice_aux[5].x ,Vertice_aux[5].y,Vertice_aux[5].z,c);
        drawLine(Vertice_aux[4].x ,Vertice_aux[4].y,Vertice_aux[4].z, Vertice_aux[6].x ,Vertice_aux[6].y,Vertice_aux[6].z,c);

        drawLine(Vertice_aux[3].x ,Vertice_aux[3].y,Vertice_aux[3].z, Vertice_aux[1].x ,Vertice_aux[1].y,Vertice_aux[1].z,c);
        drawLine(Vertice_aux[3].x ,Vertice_aux[3].y,Vertice_aux[3].z, Vertice_aux[2].x ,Vertice_aux[2].y,Vertice_aux[2].z,c);
        drawLine(Vertice_aux[3].x ,Vertice_aux[3].y,Vertice_aux[3].z, Vertice_aux[7].x ,Vertice_aux[7].y,Vertice_aux[7].z,c);
        drawLine(Vertice_aux[7].x ,Vertice_aux[7].y,Vertice_aux[7].z, Vertice_aux[5].x ,Vertice_aux[5].y,Vertice_aux[5].z,c);
        drawLine(Vertice_aux[7].x ,Vertice_aux[7].y,Vertice_aux[7].z, Vertice_aux[6].x ,Vertice_aux[6].y,Vertice_aux[6].z,c);

        drawLine(Vertice_aux[1].x ,Vertice_aux[1].y,Vertice_aux[1].z, Vertice_aux[5].x ,Vertice_aux[5].y,Vertice_aux[5].z,c);
        drawLine(Vertice_aux[2].x ,Vertice_aux[2].y,Vertice_aux[2].z, Vertice_aux[6].x ,Vertice_aux[6].y,Vertice_aux[6].z,c);
    }
    public void move(int x, int y, int z) {
        Coordenada3d a = new Coordenada3d("Centro");
        a.x=x*U;a.y=y*U;a.z=z*U;
        cubo3D.re_center(a);
        cubo3D.calcular_vertices(0,0,0);
    }
    public void resize(int tam) {
        cubo3D.tamaño=U*tam;
        cubo3D.calcular_vertices(0,0,0);
    }
    public void rotar_cubo_X(int grados) {
        cubo3D.rotacionX(grados);
    }
    public Coordenada3d calcula_rotacion_x(Coordenada3d coordenada, int grados_x){
        System.out.println("x: "+coordenada.x);
        System.out.println("y: "+coordenada.y);
        System.out.println("z: "+coordenada.z);

        coordenada.x = (int)((double)coordenada.x*Math.cos((double)grados_x)) - (int)((double)coordenada.y * Math.sin((double)grados_x));
        coordenada.y = (int)((double)coordenada.x*Math.cos((double)grados_x)) + (int)((double)coordenada.y * Math.sin((double)grados_x));
        
        System.out.println("x: "+coordenada.x);
        System.out.println("y: "+coordenada.y);
        System.out.println("z: "+coordenada.z);

        return coordenada;
    }


}
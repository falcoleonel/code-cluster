package pixel;

import java.awt.*;
import java.util.Hashtable;

/**
 * Figures3d
 */
public class Figures3d extends Figures2d{

    public Hashtable<String,Integer> coordenada2d; //coordenada actual x,y
    public Coordenada3d proyeccion; //Proyeccion x,y,z

    public Cube cubo3D;

    public int U; //Pixeles por unidad

    public Figures3d (int w, int h){
        super(w,h);
        
        coordenada2d=new Hashtable<String, Integer>();
        coordenada2d.put("x", 0);
        coordenada2d.put("y", 0);

        proyeccion= new Coordenada3d("Proyeccion") ;//Nueva Coordenada
        proyeccion.x=1;
        proyeccion.y=1;
        proyeccion.z=5;

        if (w<h)
            U=w/50; //Pixeles por unidad
        else
            U=h/50; //Pixeles por unidad
    }
    public void proyeccion_paralela(int x, int y, int z) {
        this.coordenada2d.put("x", x - proyeccion.x*(z/proyeccion.z));
        this.coordenada2d.put("y", y - proyeccion.y*(z/proyeccion.z));
    }
    public void proyeccion_perspectiva(int x, int y, int z) {
        this.coordenada2d.put("x", proyeccion.x - (proyeccion.z*(x-proyeccion.x)/(z-proyeccion.z)));
        this.coordenada2d.put("y", proyeccion.y - (proyeccion.z*(y-proyeccion.y)/(z-proyeccion.z)));
        //this.coordenada2d.put("y", y - proyeccion.y*(z/proyeccion.get("z")));
    }

    public void Cubo3D(int x_centro,int y_centro,int z_centro,int U_tamaño, Color c) {
            //proyeccion_paralela
        cubo3D=new Cube(0, 0, 0, U_tamaño*U, c);

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
        
        /******************************* 
         * 
         U_tamaño=U_tamaño*U;
         drawLine(x_centro-U_tamaño,y_centro-U_tamaño,z_centro-U_tamaño, x_centro+U_tamaño,y_centro-U_tamaño,z_centro-U_tamaño, c);
         drawLine(x_centro-U_tamaño,y_centro-U_tamaño,z_centro-U_tamaño, x_centro-U_tamaño,y_centro+U_tamaño,z_centro-U_tamaño, c);
         drawLine(x_centro-U_tamaño,y_centro-U_tamaño,z_centro-U_tamaño, x_centro-U_tamaño,y_centro-U_tamaño,z_centro+U_tamaño, c);
         
         drawLine(x_centro+U_tamaño,y_centro+U_tamaño,z_centro+U_tamaño, x_centro+U_tamaño,y_centro+U_tamaño,z_centro-U_tamaño, c);
         drawLine(x_centro+U_tamaño,y_centro+U_tamaño,z_centro+U_tamaño, x_centro+U_tamaño,y_centro-U_tamaño,z_centro+U_tamaño, c);
         drawLine(x_centro+U_tamaño,y_centro+U_tamaño,z_centro+U_tamaño, x_centro-U_tamaño,y_centro+U_tamaño,z_centro+U_tamaño, c);
         
         drawLine(x_centro-U_tamaño,y_centro-U_tamaño,z_centro+U_tamaño, x_centro+U_tamaño,y_centro-U_tamaño,z_centro+U_tamaño, c);
         drawLine(x_centro-U_tamaño,y_centro+U_tamaño,z_centro+U_tamaño, x_centro-U_tamaño,y_centro-U_tamaño,z_centro+U_tamaño, c);
         drawLine(x_centro+U_tamaño,y_centro-U_tamaño,z_centro-U_tamaño, x_centro+U_tamaño,y_centro+U_tamaño,z_centro-U_tamaño, c);
        
         drawLine(x_centro+U_tamaño,y_centro-U_tamaño,z_centro-U_tamaño, x_centro+U_tamaño,y_centro-U_tamaño,z_centro+U_tamaño, c);
         drawLine(x_centro+U_tamaño,y_centro+U_tamaño,z_centro-U_tamaño, x_centro-U_tamaño,y_centro+U_tamaño,z_centro-U_tamaño, c);
         drawLine(x_centro-U_tamaño,y_centro+U_tamaño,z_centro+U_tamaño,x_centro-U_tamaño,y_centro+U_tamaño,z_centro-U_tamaño, c);
        */

    }
    public void dibujarPxl(int x, int y, int z, Color c) {
        proyeccion_paralela(x, y, z);
        super.dibujarPxl(coordenada2d.get("x"),coordenada2d.get("y"),c);
    }

    public void drawLine(int x1,int y1,int z1,int x2,int y2,int z2,Color c) {

        proyeccion_paralela(x1, y1, z1);
        //proyeccion_perspectiva(x1, y1, z1);

        x1=x1+(getHeight()/2);
        y1=y1+(getWidth()/2);

        x1=coordenada2d.get("x")+((getWidth()/2)-1);
        y1=(getHeight()/2)-coordenada2d.get("y");

        proyeccion_paralela(x2, y2, z2);
        //proyeccion_perspectiva(x2, y2, z2);

        x2=coordenada2d.get("x")+((getWidth()/2)-1);
        y2=(getHeight()/2)-coordenada2d.get("y");

        //System.out.println("x1: "+x1+" y1: "+y1);
        //System.out.println("x2: "+x2+" y2: "+y2);
        super.drawLine(x1, y1, x2, y2, c);
    }

    public void drawEjes3D() {
        //Ejes Positivos 
        this.drawLine(0, 0, 0, 0, getHeight()/2, 0, Color.GREEN);
        this.drawLine(0, 0, 0, (getWidth()/2), 0, 0, Color.GREEN);
        this.drawLine(0, 0, 0, 0, 0, 1000, Color.GREEN);
        
        //Ejes Negativos
        this.drawLine(0, 0, 0, 0, -getHeight()/2+1, 0, Color.BLUE);
        this.drawLine(0, 0, 0, -(getWidth()/2)+1, 0, 0, Color.BLUE);
        this.drawLine(0, 0, 0, 0, 0, -1000, Color.BLUE);
    }
    public void rotacion() {
        
    }
    /*
    
    public void drawCuadrado(int x, int y, int tam, Color c) {
        //Usar posicion x,y como centro 
        //Calcular xi y yi
        //Calcular xf y yf
        //Dibujar un rectangulo con los calculos del rectangulo
        int xi,yi,xf,yf;

        int distancia_del_centro = tam*U;

        drawRect(x-distancia_del_centro, int y0, int x1, int y1, Color c);

        public void drawRect(int x0, int y0, int x1, int y1, Color c) {
            drawLine(x0, y0, x0, y1, c);
            drawLine(x0, y0, x1, y0, c);
    
            drawLine(x1, y0, x1, y1, c);
            drawLine(x0, y1, x1, y1, c);
         }
    }*/
}
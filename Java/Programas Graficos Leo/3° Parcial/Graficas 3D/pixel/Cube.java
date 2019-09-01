package pixel;

import java.awt.*;
import java.lang.Math;

/**
 * Figures3d
 */
public class Cube{
    public Coordenada3d[] Vertice = new Coordenada3d[8];
    public Coordenada3d Centro = new Coordenada3d("Centro");
    public int tamaño;
    
    public Cube (int x_centro,int y_centro,int z_centro,int pix_tamaño, Color c){
        
        tamaño=pix_tamaño;

        Centro.x=x_centro;
        Centro.y=y_centro;
        Centro.z=z_centro;

        Vertice[0]=new Coordenada3d("A");
        Vertice[1]=new Coordenada3d("B");
        Vertice[2]=new Coordenada3d("C");
        Vertice[3]=new Coordenada3d("D");
        Vertice[4]=new Coordenada3d("E");
        Vertice[5]=new Coordenada3d("F");
        Vertice[6]=new Coordenada3d("G");
        Vertice[7]=new Coordenada3d("H");

        calcular_vertices(0,0,0);
    }
    public void calcular_vertices(int angulo_x, int angulo_y, int angulo_z) {
        
        Vertice[0].x= Centro.x -tamaño;
        Vertice[0].y= Centro.y +tamaño;
        Vertice[0].z= Centro.z -tamaño;
        
        Vertice[1].x= Centro.x +tamaño;
        Vertice[1].y= Centro.y +tamaño;
        Vertice[1].z= Centro.z -tamaño;
        
        Vertice[2].x= Centro.x -tamaño;
        Vertice[2].y= Centro.y +tamaño;
        Vertice[2].z= Centro.z +tamaño;

        Vertice[3].x= Centro.x +tamaño;
        Vertice[3].y= Centro.y +tamaño;
        Vertice[3].z= Centro.z +tamaño;

        Vertice[4].x= Centro.x -tamaño;
        Vertice[4].y= Centro.y -tamaño;
        Vertice[4].z= Centro.z -tamaño;

        Vertice[5].x= Centro.x +tamaño;
        Vertice[5].y= Centro.y -tamaño;
        Vertice[5].z= Centro.z -tamaño;
        
        Vertice[6].x= Centro.x -tamaño;
        Vertice[6].y= Centro.y -tamaño;
        Vertice[6].z= Centro.z +tamaño;

        Vertice[7].x= Centro.x +tamaño;
        Vertice[7].y= Centro.y -tamaño;
        Vertice[7].z= Centro.z +tamaño;
    }
    public static void main(String[] args) {
        Cube cubo = new Cube(0,0,0,2,Color.RED);
        //cubo.printVertice();
    }
    public void printVertice() {
        for (Coordenada3d k : this.Vertice) {
            System.out.println("Arista: "+ k.name  );
            System.out.print("X: "+ k.x +" ");
            System.out.print("Y: "+ k.y +" ");
            System.out.println("Z: "+ k.z +" ");
        }
    }
    public void re_center(Coordenada3d p) {
        Centro=p;
    }

    public void rotacionX(int grados) {

            double hip=0;
        for (int i = 0; i < 1; i++) {  
            hip= Math.sqrt(Math.pow(Vertice[i].y, 2)+Math.pow(Vertice[i].x, 2));
            System.out.println(hip);
            System.out.println(Vertice[i].y);
            System.out.println(Vertice[i].x);
            Vertice[i].x = (int)(Math.cos(grados)* hip);
            Vertice[i].y = (int)(Math.sin(grados)* hip);
            System.out.println(Vertice[i].y);
        }
        /**
         * 
         hip= Math.sqrt(Math.pow(Vertice[1].y, 2)+Math.pow(Vertice[1].z, 2));
         System.out.println(hip);
         System.out.println(Vertice[1].y);
         System.out.println(Vertice[1].z);
         Vertice[1].z = (int)(Math.cos(grados)* hip);
         Vertice[1].y = (int)(Math.sin(grados)* hip);
         System.out.println(Vertice[1].y);
        */
    }
    /*
    public void proyeccion_paralela(int x, int y, int z) {
        this.coordenada2d.put("x", x - proyeccion.get("x")*(z/proyeccion.get("z")));
        this.coordenada2d.put("y", y - proyeccion.get("y")*(z/proyeccion.get("z")));
    }
    public void proyeccion_perspectiva(int x, int y, int z) {
        this.coordenada2d.put("x", proyeccion.get("x") - (proyeccion.get("z")*(x-proyeccion.get("x"))/(z-proyeccion.get("z"))));
        this.coordenada2d.put("y", proyeccion.get("y") - (proyeccion.get("z")*(y-proyeccion.get("y"))/(z-proyeccion.get("z"))));
        //this.coordenada2d.put("y", y - proyeccion.get("y")*(z/proyeccion.get("z")));
    }

    public void Cubo3D(int x_centro,int y_centro,int z_centro,int tamaño, Color c) {
                //proyeccion_paralela
        int x,y;

        tamaño=tamaño*U;

        drawLine(x_centro-tamaño,y_centro-tamaño,z_centro-tamaño, x_centro+tamaño,y_centro-tamaño,z_centro-tamaño, c);
        drawLine(x_centro-tamaño,y_centro-tamaño,z_centro-tamaño, x_centro-tamaño,y_centro+tamaño,z_centro-tamaño, c);
        drawLine(x_centro-tamaño,y_centro-tamaño,z_centro-tamaño, x_centro-tamaño,y_centro-tamaño,z_centro+tamaño, c);//Problema con esta linea
        
        drawLine(x_centro+tamaño,y_centro+tamaño,z_centro+tamaño, x_centro+tamaño,y_centro+tamaño,z_centro-tamaño, c);//Problemas con esta linea
        drawLine(x_centro+tamaño,y_centro+tamaño,z_centro+tamaño, x_centro+tamaño,y_centro-tamaño,z_centro+tamaño, c);
        drawLine(x_centro+tamaño,y_centro+tamaño,z_centro+tamaño, x_centro-tamaño,y_centro+tamaño,z_centro+tamaño, c);

        drawLine(x_centro-tamaño,y_centro-tamaño,z_centro+tamaño, x_centro+tamaño,y_centro-tamaño,z_centro+tamaño, c);
        drawLine(x_centro-tamaño,y_centro+tamaño,z_centro+tamaño, x_centro-tamaño,y_centro-tamaño,z_centro+tamaño, c);
        drawLine(x_centro+tamaño,y_centro-tamaño,z_centro-tamaño, x_centro+tamaño,y_centro+tamaño,z_centro-tamaño, c);
        
        drawLine(x_centro+tamaño,y_centro-tamaño,z_centro-tamaño, x_centro+tamaño,y_centro-tamaño,z_centro+tamaño, c);
        drawLine(x_centro+tamaño,y_centro+tamaño,z_centro-tamaño, x_centro-tamaño,y_centro+tamaño,z_centro-tamaño, c);
        drawLine(x_centro-tamaño,y_centro+tamaño,z_centro+tamaño,x_centro-tamaño,y_centro+tamaño,z_centro-tamaño, c);
        /**
         * 
        */
        

        /*****
         *        a--------b
         *         \        \
         *       e| c--------d
         *         \|     f  |
         *         g--------- h
         *
         x+
         y-
         z-

         x+
         y+
         z-


         *  
         print
         */
        /*

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

        System.out.println("x1: "+x1+" y1: "+y1);
        System.out.println("x2: "+x2+" y2: "+y2);
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

import java.awt.Color;
import java.util.*;
import java.io.*;

public class FormaDibujar{
    //Matriz para almacenar todos los vectores del pokemon
    public static int[][] matrices = new int[3000][3000];
    public static int zActual,angulo1=90,angulo2=90;
    public static PuntosRotacion centro= new PuntosRotacion(-100,-40,-100);
    static int cont=0;
    public FormaDibujar()
    {
        for(int l1=0;l1<1200;l1++)
            for(int l2=0;l2<700;l2++)
                matrices[l1][l2]=-1000;
    }
    
    //Funcion usando el metodo de bresenham para crear lineas en 2D
    private static void LineaBresenham(int x0, int y0, int x1, int y1, Color c) {
        int x, y;
        int diferenciaX, diferenciaY, A, B, pk, stepx, stepy;
        diferenciaX = (x1 - x0);
        diferenciaY = (y1 - y0);
        if(diferenciaY < 0) {
            diferenciaY = -1 * diferenciaY;
            stepy = -1;
        } else
            stepy = 1;
        if(diferenciaX < 0) {
            diferenciaX = -1 * diferenciaX;
            stepx = -1;
        } else
            stepx = 1;
        x = x0;
        y = y0;
        if( zActual > matrices[x][y])
        {
            PonerPixel.instancia.pintarPixel(x, y,c);
            matrices[x][y]=zActual;
        }
        if(diferenciaX > diferenciaY) {
            pk = (2 * diferenciaY) - diferenciaX;
            A = 2 * diferenciaY;
            B = (2 * diferenciaY) - (2 * diferenciaX);
            while(x != x1) {
                x = x + stepx;
                if(pk < 0)
                    pk = pk + A;
                else {
                    y = y + stepy;
                    pk = pk + B;
                }
                if( zActual > matrices[x][y])
                {
                    PonerPixel.instancia.pintarPixel(x, y,c);
                    matrices[x][y]=zActual;
                }
            }
        } else {
            pk = (2 * diferenciaX) - diferenciaY;
            A = 2 * diferenciaX;
            B = (2 * diferenciaX) - (2 * diferenciaY);
            while(y != y1) {
                y = y + stepy;
                if(pk < 0)
                    pk = pk + A;
                else {
                    x = x + stepx;
                    pk = pk + B;
                }
                if( zActual > matrices[x][y])
                {
                    PonerPixel.instancia.pintarPixel(x, y,c);
                    matrices[x][y]=zActual;
                }
            }
        }
    } 

    //Funcion de rellono de triangular combinando el algoritmo de bresenham
    private static void rellenar(int xor,int yor,int x0, int y0, int x1, int y1, Color c) {
        int x, y;
        int diferenciaX, diferenciaY, A, B, pk, stepx, stepy;
        diferenciaX = (x1 - x0);
        diferenciaY = (y1 - y0);
        if(diferenciaY < 0) {
            diferenciaY = -1 * diferenciaY;
            stepy = -1;
        } else
            stepy = 1;
        if(diferenciaX < 0) {
            diferenciaX = -1 * diferenciaX;
            stepx = -1;
        } else
            stepx = 1;
        x = x0;
        y = y0;
        LineaBresenham(xor,yor,x, y, c);
        if(diferenciaX > diferenciaY) {
            pk = (2 * diferenciaY) - diferenciaX;
            A = 2 * diferenciaY;
            B = (2 * diferenciaY) - (2 * diferenciaX);
            while(x != x1) {
                x = x + stepx;
                if(pk < 0)
                    pk = pk + A;
                else {
                    y = y + stepy;
                    pk = pk + B;
                }
            LineaBresenham(xor,yor,x, y, c);
            }
        } else {
            pk = (2 * diferenciaX) - diferenciaY;
            A = 2 * diferenciaX;
            B = (2 * diferenciaX) - (2 * diferenciaY);
            while(y != y1) {
                y = y + stepy;
                if(pk < 0)
                    pk = pk + A;
                else {
                    x = x + stepx;
                    pk = pk + B;
                }
            LineaBresenham(xor,yor,x, y, c);
            }
        }
    }
    
    //Funcion para obtener los puntos del array list y retornando
    public static ArrayList<PuntosRotacion> getPoints(PuntosRotacion origen, PuntosRotacion abertura) {
        ArrayList<PuntosRotacion> vector = new ArrayList<>();
        //Arreglos para identificar donde dibujar
        int[] arrX = {0, 1, 1, 0};
        int[] arrY = {0, 0, 1, 1};
        int x, y;
        for(int cont = 0; cont < arrX.length; cont++) {
            x = origen.x + (arrX[cont] * abertura.x);
            y = origen.y + (arrY[cont] * abertura.y);
            vector.add(new PuntosRotacion(x, y, -abertura.z/2));
        }
        for(int cont = 0; cont < arrX.length; cont++) {
            x = origen.x + (arrX[cont] * abertura.x);
            y = origen.y + (arrY[cont] * abertura.y);
            vector.add(new PuntosRotacion(x, y, abertura.z/2));
        }
        return vector;
    }

    //Funcion para la manipulacion de los vectores del Pokemon
    public static void calculoVector(ArrayList<PuntosRotacion> puntos,String nombre,Color c) {
        for(int l1=0;l1<3000;l1++){        
            for(int l2=0;l2<700;l2++){
                matrices[l1][l2]=-1000;
            }    
        }    
        //Posicionamiento general del pokemon
        double x, y, z = 410.0;
        double xp = -100.0, yp = -40.0, zp = -100.0;
        ArrayList<PuntosRotacion> puntosIndividuales = new ArrayList<PuntosRotacion>();
        String[] aux,aux2,aux3,aux4;
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        archivo = new File (nombre);
        String linea;
        
        for (PuntosRotacion point : puntos) {
            x = point.gX + (xp * ((z - point.gZ) / zp));
            y = PonerPixel.alto - (point.gY + (yp * ((z - point.gZ) / zp)));
            puntosIndividuales.add(new PuntosRotacion((int) x, (int) y,(int) Math.round(point.gZ)));
        }
        try 
        {
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            //Abertura del archivo para procesar los vectores del pokemon .obj
            while((linea=br.readLine())!=null)
            {                
                if(linea.charAt(0)=='f'){
                    //Se ingnorar caracteres especiales y solo leer los numeros
                    aux=linea.split("\\s+");
                    aux2=aux[1].split("/");
                    aux3=aux[2].split("/");
                    aux4=aux[3].split("/");
                    //Algoritmo de lectura y procesamiento de los vectores del archivo .obj
                    if(puntosIndividuales.get(Integer.parseInt(aux2[0])-1).x>0 && puntosIndividuales.get(Integer.parseInt(aux2[0])-1).y>0 && puntosIndividuales.get(Integer.parseInt(aux3[0])-1).x>0 && puntosIndividuales.get(Integer.parseInt(aux3[0])-1).y>0 && puntosIndividuales.get(Integer.parseInt(aux4[0])-1).x>0 && puntosIndividuales.get(Integer.parseInt(aux4[0])-1).y>0){
                        matrices[puntosIndividuales.get(Integer.parseInt(aux2[0])-1).x][puntosIndividuales.get(Integer.parseInt(aux2[0])-1).y]=puntosIndividuales.get(Integer.parseInt(aux2[0])-1).z;
                        if(puntosIndividuales.get(Integer.parseInt(aux2[0])-1).z<=puntosIndividuales.get(Integer.parseInt(aux3[0])-1).z && puntosIndividuales.get(Integer.parseInt(aux2[0])-1).z<=puntosIndividuales.get(Integer.parseInt(aux4[0])-1).z)                            
                            zActual=puntosIndividuales.get(Integer.parseInt(aux2[0])-1).z;
                        
                        else if(puntosIndividuales.get(Integer.parseInt(aux3[0])-1).z<=puntosIndividuales.get(Integer.parseInt(aux2[0])-1).z && puntosIndividuales.get(Integer.parseInt(aux3[0])-1).z<=puntosIndividuales.get(Integer.parseInt(aux4[0])-1).z)
                            zActual=puntosIndividuales.get(Integer.parseInt(aux3[0])-1).z;
                        
                        else
                            zActual=puntosIndividuales.get(Integer.parseInt(aux4[0])-1).z;
                        
                        //Se mandar los puntos procesados al algoritmo de Bresenham para dibujarlos 
                        LineaBresenham(puntosIndividuales.get(Integer.parseInt(aux2[0])-1).x, puntosIndividuales.get(Integer.parseInt(aux2[0])-1).y, puntosIndividuales.get(Integer.parseInt(aux3[0])-1).x, puntosIndividuales.get(Integer.parseInt(aux3[0])-1).y,Color.WHITE);
                        LineaBresenham(puntosIndividuales.get(Integer.parseInt(aux2[0])-1).x, puntosIndividuales.get(Integer.parseInt(aux2[0])-1).y, puntosIndividuales.get(Integer.parseInt(aux4[0])-1).x, puntosIndividuales.get(Integer.parseInt(aux4[0])-1).y,Color.WHITE);
                        LineaBresenham(puntosIndividuales.get(Integer.parseInt(aux3[0])-1).x, puntosIndividuales.get(Integer.parseInt(aux3[0])-1).y, puntosIndividuales.get(Integer.parseInt(aux4[0])-1).x, puntosIndividuales.get(Integer.parseInt(aux4[0])-1).y,Color.WHITE);
                        if(cont<=200){
                        cont+=1;    
                        c = new Color(46,52,cont);
                        }else{
                        cont=120;
                        }
                        //Se llama a la funcion rellenar ingresando los vectores del archivo
                        rellenar(puntosIndividuales.get(Integer.parseInt(aux2[0])-1).x, puntosIndividuales.get(Integer.parseInt(aux2[0])-1).y, puntosIndividuales.get(Integer.parseInt(aux3[0])-1).x, puntosIndividuales.get(Integer.parseInt(aux3[0])-1).y, puntosIndividuales.get(Integer.parseInt(aux4[0])-1).x, puntosIndividuales.get(Integer.parseInt(aux4[0])-1).y,c);        
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
        try{   
            //Cerrar el archivo
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
    }
}
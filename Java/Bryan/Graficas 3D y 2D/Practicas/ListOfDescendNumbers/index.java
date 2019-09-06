//import javax.swing.JOptionPane;
import java.util.Random;
import java.util.Vector;

public class index{
	public static void main(String[] args){
        showVector(ordenamiento(generateRandoms(10,1,100)));
    }

	public static int getRandom(int A,int B){
        Random r = new Random();
        return r.nextInt((B - A) + 1) + A;
     }

    public static int[] generateRandoms(int R,int A, int B){
        int numbers [] = new int[R];
        for (int i = 0; i < R; i++) {
            numbers[i] = getRandom(A, B);
        }
        return numbers;
     }

    public static void showVector (int numbers[]){
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Array =" + numbers[i]);
        }
     }

    public static int [] ordenamiento(int arreglo[]){
        for(int i = 0; i < arreglo.length - 1; i++)
        {
            for(int j = 0; j < arreglo.length - 1; j++)
            {
                if (arreglo[j] < arreglo[j + 1])
                {
                    int tmp = arreglo[j+1];
                    arreglo[j+1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
        //showVector(arreglo);
        return arreglo;
     }

 }
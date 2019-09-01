import java.util.Arrays;
import java.util.Collections;
public class NumerosDes
{
	
    public static void main(String args[])
    {
    	Integer[] numeros = new Integer[args.length];
    	for(int i = 0; i < args.length;i++){
    		numeros[i] = Integer.parseInt(args[i]);
    		System.out.println(numeros[i]);
    	}
    System.out.println("ordenando..... \n");
    Arrays.sort(numeros, Collections.reverseOrder());
    System.out.println("Argumentos en orden descendente: \n");
    for (int i = 0; i < numeros.length; i++)
       System.out.println(numeros[i]);
    }
    
}

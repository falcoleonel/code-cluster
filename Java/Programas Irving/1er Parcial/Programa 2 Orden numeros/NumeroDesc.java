import java.util.Arrays;
import java.util.Collections;

public class NumeroDesc {


    public static void main(String[] args) {
        Integer[] numeros = new Integer[args.length];
    	if (args.length == 0)
    		System.out.println("Por favor ingresa al menos 2 numeros");
    	if (args.length == 1)
    		System.out.println("Ingresa mas de 1 numero ");
    	else{
        System.out.println("Tus numeros son: ");
        
        for(int i = 0; i < args.length; i++){
            numeros[i] = Integer.parseInt(args[i]);
            }
        }
        Arrays.sort(numeros, Collections.reverseOrder());
        for(int i = 0; i < numeros.length; i++){
           System.out.println(numeros[i]); 
        }
    }
}
     
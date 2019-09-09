import java.util.Random;

public class NumerosAlea {

    public static void main(String[] args) {
    	int numeros= (int)(Math.random()*150000)+1;
        int numeros1= (int)(Math.random()*150000)+1;
        System.out.println("Numero 1");
        System.out.println(numeros);
        System.out.println("Numero 2: ");
        System.out.println(numeros1);
        System.out.println("Tu numero mayor es: ");
        	if (numeros>numeros1)
        		System.out.println(numeros);
        	else
        		System.out.println(numeros1);
        }
    }

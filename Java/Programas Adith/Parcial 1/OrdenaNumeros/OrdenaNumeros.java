import java.util.ArrayList;
import java.util.Collections;

public class OrdenaNumeros {

	public static void main(String args[]) {

		if (args.length == 0) {
			System.out.println("Error");
			return;
		}

		ArrayList<Integer> numeros = new ArrayList<Integer>();
		
		for (String arg : args) { 
			int numero = Integer.parseInt(arg); 
			numeros.add(numero); 
		}
		Collections.sort(numeros, Collections.reverseOrder());

		for(Integer numero:numeros){
			 System.out.println(numero); 
		}
	}

}
import java.lang.Math;

public class NumerosRandom {
	public static void main(String[] args) {

		double numero1 = Math.random();
		double numero2 = Math.random();

		System.out.println("Primer Numero: " + numero1);
		System.out.println("Segundo Numero: " + numero2);

		if( numero1 > numero2 ){

			System.out.println("Mayor: " + numero1);
		}
		else {

			System.out.println("Mayor: " + numero2);
		}
        
	}
}
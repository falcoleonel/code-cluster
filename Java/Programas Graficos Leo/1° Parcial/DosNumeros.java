import java.lang.Math.*;

public class DosNumeros
{
	private static double num1;
	private static double num2;
	public void DosNumeros()
	{
	}
	public static void main(String[] args) {
		DosNumeros dosNumeros= new DosNumeros();
		Double imprimir;
		dosNumeros.num1=Math.random();
		dosNumeros.num2=Math.random();
		imprimir=Math.max(dosNumeros.num1,dosNumeros.num2);

		System.out.println("1: "+dosNumeros.num1);
		System.out.println("2: "+dosNumeros.num2);
		if(imprimir==dosNumeros.num1)
		{
			System.out.println("Numero 1 es mayor: "+dosNumeros.num1);	
		}
		else
		{		
			System.out.println("Numero 2 es mayor: "+dosNumeros.num2);	
		}
	}
}
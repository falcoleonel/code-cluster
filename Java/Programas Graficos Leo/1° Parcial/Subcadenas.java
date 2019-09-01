//Imprimir todas las subcadenas
import java.util.Arrays;
import java.util.Collections;

public class Subcadenas
{
	public static void main(String[] args) {
		String cadena = args[0];
		char[] arregloChar = cadena.toCharArray();
		System.out.println("\n'"+cadena+"'");
		Integer tam=cadena.length();
		for(int i=0;i< tam;i++)
		{
			Sub(arregloChar,i,tam);
			System.out.println();
		}
		for (int i=tam;i>=0;i--) 
		{
			Sub(arregloChar,0,i);
			System.out.println();
		}
		
		for (char letra : arregloChar) 
		{
			System.out.println(letra);
		}
	}
	static void Sub(char[] arreglo,int inicio,int fin)
	{
		for(int i=inicio;i<fin;i++)
		{
			System.out.print(arreglo[i]);
		}
	}
}
//HexToIP
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class HexToIP
{
	public static void main(String[] args) 
	{
		String cadena=args[0];
		if(cadena.matches("^(\\d{1,3})+\\.{1}+(\\d{1,3})+\\.{1}+(\\d{1,3})+\\.{1}+(\\d{1,3})$"))
		{
			System.out.println("Es Ip");
			ToHex(cadena);
		}
		else if (cadena.matches("^[0-9a-fA-F]{2}+\\-+[0-9a-fA-F]{2}+\\-+[0-9a-fA-F]{2}+\\-+[0-9a-fA-F]{2}$")) 
		{
			System.out.println("Es Hexadecimal");
			ToIP(cadena);	
		}
		else
		{
			System.out.println("Error al Ingresar");
		}	
	}
	public static void ToHex(String cadena)
	{
		String[] arreglo= cadena.split("\\.");
		for (int i=0 ; i<arreglo.length; i++)
		{
			if(i!=(arreglo.length-1))
				System.out.printf("%02X-",Integer.parseInt(arreglo[i]));
			else
				System.out.printf("%02X",Integer.parseInt(arreglo[i]));
		}
		//Stream.of(cadena.split("\\.")).forEach(
		//	c -> System.out.printf("%02X", Integer.parseInt(c))
		//);
	}
	public static void ToIP(String cadena) 
	{
		String[] arreglo= cadena.split("\\-");
		for (int i=0 ; i<arreglo.length; i++) 
		{
			if(i!=(arreglo.length-1))
				System.out.printf("%d.",Integer.parseInt(arreglo[i], 16));
			else
				System.out.printf("%d",Integer.parseInt(arreglo[i], 16));
		}
		//Stream.of(cadena.split("\\-")).forEach(
		//	c -> System.out.printf("%d.", Integer.parseInt(c, 16))
		//);
	}
}
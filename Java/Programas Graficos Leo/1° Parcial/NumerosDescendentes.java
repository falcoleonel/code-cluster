import java.util.Arrays;
import java.util.Collections;

public class NumerosDescendentes
{
	public static void main(String args[])

	{
	 Integer[] arreglo = new Integer[args.length];
		for(int i=0; i<args.length;i++)
		{
			arreglo[i]= Integer.parseInt(args[i]);
		}
		Arrays.sort(arreglo,Collections.reverseOrder());
		for(int i=0;i<args.length;i++)
		{
			System.out.println(arreglo[i]);
		}
	}
}
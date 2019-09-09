
import java.util.ArrayList;
import java.util.List;

public class Cubo
{
	public List puntitos;
	public int[] xses= new int[8];
	public int[] yses= new int[8];
	public Cubo(int p1x,int p1y,int p1z,
		int p2x,int p2y,int p2z,
		int p3x,int p3y,int p3z,
		int p4x,int p4y,int p4z,
		int p5x,int p5y,int p5z,
		int p6x,int p6y,int p6z,
		int p7x,int p7y,int p7z,
		int p8x,int p8y,int p8z)
	{
		puntitos = new ArrayList<Punto>();
		Punto temp = new Punto();
		temp.x = p1x;
		temp.y = p1y;
		temp.z = p1z;
		puntitos.add(temp);
		
		temp = new Punto();
		temp.x = p2x;
		temp.y = p2y;
		temp.z = p2z;
		puntitos.add(temp);
		
		temp = new Punto();
		temp.x = p3x;
		temp.y = p3y;
		temp.z = p3z;
		puntitos.add(temp);
		
		temp = new Punto();
		temp.x = p4x;
		temp.y = p4y;
		temp.z = p4z;
		puntitos.add(temp);
		
		temp = new Punto();
		temp.x = p5x;
		temp.y = p5y;
		temp.z = p5z;
		puntitos.add(temp);
		
		temp = new Punto();
		temp.x = p6x;
		temp.y = p6y;
		temp.z = p6z;
		puntitos.add(temp);
		
		temp = new Punto();
		temp.x = p7x;
		temp.y = p7y;
		temp.z = p7z;
		puntitos.add(temp);
		
		temp = new Punto();
		temp.x = p8x;
		temp.y = p8y;
		temp.z = p8z;
		puntitos.add(temp);
	}
}
import javax.swing.JOptionPane;
import java.util.Random;

public class index{
	public static void main(String[] args){
		//System.out.println("arg0"+getRandom(1,100));
		JOptionPane.showMessageDialog(null, "Numero Aleatorio 1: "+getRandom(1,100));
		JOptionPane.showMessageDialog(null, "Numero Aleatorio 2: "+getRandom(1,100));
	 }

	 public static int getRandom(int A, int B){
        Random r = new Random();
        return r.nextInt((B - A) + 1) + A;
    }      
 }
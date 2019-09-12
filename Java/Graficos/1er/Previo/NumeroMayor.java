import java.lang.Math;
import java.lang.Double;

public class NumeroMayor {
    public static void main(String args[]) {
        Double xx = new Double((Math.random() * 10000) + 1);
        Double yy = new Double((Math.random() * 10000) + 1);

        int x = xx.intValue();
        int y = yy.intValue();

        System.out.println("Tus numeros son: " + x + " y " + y);
        System.out.println("El mayor es: " + Math.max(x, y));
    }

}


public class Subcadenas {

    public static void main(String args[]) {
        for (int i = 0; i < args.length; i++) {

            String cadena = args[i];
            int len = cadena.length();

            for (int k = len; k > 0; k--) {
                System.out.println(cadena.substring(0, k));
            }
            for (int j = 1; j < len; j++) {
                System.out.println(cadena.substring(j, len));
            }
            len = 0;
        }
    }
}

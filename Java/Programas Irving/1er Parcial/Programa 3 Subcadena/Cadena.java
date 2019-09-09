public class Cadena {

    public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Ningún argumento");
      return;
    }

    String arg = args[0];
    
    for (int i = arg.length(); i >= 0; i--) {
      System.out.println(arg.substring(0, i));
    }
    for (int i = 0; i <= arg.length(); i++) {
      System.out.println(arg.substring(i));
    }
        
    }
}

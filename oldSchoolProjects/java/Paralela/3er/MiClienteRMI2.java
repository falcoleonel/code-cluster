public class MiClienteRMI2 {
    public static void main(String[] args) {
        try {
            MiInterfazRemota mir = (MiInterfazRemota) java.rmi.Naming.lookup("//" + args[0] + "/PruebaRMI");
            for (int i = 1; i <= mir.miMetodo2(); i++)
                mir.miMetodo1();
        } catch (Exception e) {
            System.out.println("Error, no encuentro: " + e.getMessage());
        }
    }
}

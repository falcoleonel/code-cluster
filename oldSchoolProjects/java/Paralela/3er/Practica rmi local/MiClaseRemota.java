public class MiClaseRemota extends java.rmi.server.UnicastRemoteObject implements MiInterfazRemota {
    public MiClaseRemota() throws java.rmi.RemoteException {
        // Codigo del constructor
    }

    public void miMetodo1() throws java.rmi.RemoteException {
        // Aqui ponemos el codigo que queramos
        System.out.println("Estoy en miMetodo1()");
    }

    public int miMetodo2() throws java.rmi.RemoteException {
        // Aqui ponemos el codigo que queramos
        return 5;
    }

    public void otroMetodo() {
        //
        //
    }

    public static void main(String[] args) {
        try {
            MiInterfazRemota mir = new MiClaseRemota();
            java.rmi.Naming.rebind(
                    "//" + java.net.InetAddress.getLocalHost().getHostAddress() + ":" + args[0] + "/PruebaRMI", mir);
        } catch (Exception e) {
        }
    }
}

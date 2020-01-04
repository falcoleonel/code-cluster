package servidor;

import combinaciones.Combinaciones;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Servidor extends UnicastRemoteObject implements ServidorInterfaz {

    public Servidor() throws RemoteException { }
    
    @Override
    public ArrayList<ArrayList<Integer>> combinaciones(int[] lista, int suma, int tope)
    throws RemoteException {
        
        System.out.println("Un cliente se ha conectado...");
        long t1 = System.nanoTime();
        ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();
        for(int i = 1; i <= tope; i++)
            Combinaciones.hacerCombinaciones(lista, new ArrayList<>(), resultado, i, 0, suma);
        System.out.println("Listo, tardó " + (System.nanoTime() - t1) / 1e+6 + "s.");
        return resultado;
    }
    
    public static void main(String[] args) {
        try {

            int puerto = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "Puerto del servidor:",
                "1234"
            ));
            Servidor server = new Servidor();
            String dirServer = "//"
                + InetAddress.getLocalHost().getHostAddress()
                + ":" + puerto + "/Combinaciones";
            LocateRegistry.createRegistry(puerto);
            Naming.rebind(dirServer, server);
            System.out.println("Server iniciado en: " + dirServer);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServidorInterfaz extends Remote {
    
    public ArrayList<ArrayList<Integer>> combinaciones(int[] lista, int suma, int tope)
        throws RemoteException;
}

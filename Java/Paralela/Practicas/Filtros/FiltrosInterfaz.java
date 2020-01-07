import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FiltrosInterfaz extends Remote {

    public void registrarCliente(FiltrosCliente cliente) throws RemoteException;

    public void procesarDilatacion(int[][] img, int[][] resultado, boolean[][] kernel)
        throws RemoteException;

    public void procesarErosion(int[][] img, int[][] resultado, boolean[][] kernel)
        throws RemoteException;
}

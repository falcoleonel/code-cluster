import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FiltrosCliente extends Remote {

    public void recibirDilatada(int[][] img) throws RemoteException;
    public void recibitErosionada(int[][] img) throws RemoteException;
}


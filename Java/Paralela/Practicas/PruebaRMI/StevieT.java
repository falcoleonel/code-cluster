import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StevieT extends Remote {

    public void sweepPick() throws RemoteException;
    public int getSweeps() throws RemoteException;
}

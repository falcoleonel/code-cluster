import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatCliente extends Remote {
//interfaz cliente
    public void verificaConexion() throws RemoteException;
    public void enviarMensaje(String nombre, String mensaje) throws RemoteException;
}

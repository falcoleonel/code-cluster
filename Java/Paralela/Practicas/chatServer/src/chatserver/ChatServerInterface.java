package chatserver;
//package chatclient;

import chatclient.ChatClient;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
// Registrar usuario en el servidor
public interface ChatServerInterface extends Remote{
    
    // Remover usuario del servidor
     public boolean login (ChatClient client) throws RemoteException;
    
    // Mandar mensaje a todos los clientes
     public boolean logout (ChatClient client) throws RemoteException;
    
    // Obtener lista de clientes conectados
    public ArrayList<ChatClient> getConnected() throws RemoteException;
    public void publish (String message, ChatClient client) throws RemoteException;
}

package chatserver;

import chatclient.ChatClient;
import chatclient.ChatClientClass;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/*client.getName()  ANTES ESTABA COMO client.getName()*/
public class ChatServer extends UnicastRemoteObject implements ChatServerInterface {
    private ArrayList<ChatClient> clients;
    public ChatServer() throws RemoteException {
       clients = new ArrayList<>();
    }
    
 @Override
 public boolean login(ChatClient client) throws RemoteException {
    System.out.println(client.getName() + " se conectó....");
    client.receive("Te conectaste con exito.\n" + getOnlineUsers());
    publish(client.getName() + " se ha conectado.", client);
    clients.add(client);
    return true;
 }
// Método que regresa una cadena
// con los nombres de los clientes conectados.
 public String getOnlineUsers() throws RemoteException {
    StringBuilder sb = new StringBuilder();
    sb.append("Usuarios en linea: \n");
    for (ChatClient client : clients) {
        sb.append(client.getName()).append("\n");
    }
    if (clients.isEmpty()) {
        sb.append("nadie.\n");
    }
    return sb.toString();
 }
 @Override
 public boolean logout(ChatClient client) throws RemoteException {
    System.out.println(client.getName() + "Se desconectó....");
    client.receive("Te desconectaste con exito.");
    publish(client.getName() + " se ha desconectado.", client);
    clients.remove(client);
    return true;
 }
 @Override
 public void publish(String s, ChatClient c) throws RemoteException {
    System.out.println(s);
    for (ChatClient client : clients) {
        try {
            client.receive(s);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
 }
 @Override
 public ArrayList<ChatClient> getConnected() throws RemoteException {
    return clients;
 }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

/**
 *
 * @author ichij
 */
import chatserver.ChatServer;
import chatserver.ChatServerInterface;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
//La interfaz extiende a remote
public interface ChatClient extends Remote{
    

   
    //metodo para recibir mensajes
    public void receive (String message) throws RemoteException;
    //metodo para obtener el nombre del cliente
    public String getName() throws RemoteException;
    public void setGUI(ChatClientUI ui) throws RemoteException; //lo agregue yo
}


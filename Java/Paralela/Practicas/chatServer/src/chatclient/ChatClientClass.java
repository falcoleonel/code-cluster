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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class ChatClientClass extends UnicastRemoteObject implements ChatClient{
    
    private String name;
    private ChatClientUI ui;
    
    public ChatClientClass (String name) throws RemoteException{
        this.name = name;
    }
    
    @Override
    public void receive (String message) throws RemoteException{
        System.out.println(message);
        //Escribe el mensaje recibido en la GUI
        ui.writeMessage(message);
    }
    
    @Override
    public String getName() throws RemoteException{
        return name;
    }
    
    // Método para asignar la GUI
    public void setGUI(ChatClientUI ui) throws RemoteException{
        this.ui = ui;
    }
}
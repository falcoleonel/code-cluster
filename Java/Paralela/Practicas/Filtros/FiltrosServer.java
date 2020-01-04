import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

public class FiltrosServer extends UnicastRemoteObject implements FiltrosInterfaz {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public FiltrosCliente cliente;

    public FiltrosServer() throws RemoteException { }

    @Override
    public void procesarDilatacion(int[][] img, int[][] resultado, boolean[][] kernel) throws RemoteException {
        Filtros.dilatacion(img, resultado, kernel);
        cliente.recibirDilatada(resultado);
    }

    @Override
    public void procesarErosion(int[][] img, int[][] resultado, boolean[][] kernel) throws RemoteException {
        Filtros.erosion(img, resultado, kernel);
        cliente.recibitErosionada(resultado);
    }

    @Override
    public void registrarCliente(FiltrosCliente cliente) throws RemoteException {
        this.cliente = cliente;
    }

    public static void main(String[] args) {
        try {

            String puerto = JOptionPane.showInputDialog(null, "Ingrese el puerto:", "1234");
            FiltrosServer server = new FiltrosServer();
            String dirServer = "//"
                + InetAddress.getLocalHost().getHostAddress()
                + ":" + puerto + "/filtros";
            Naming.rebind(dirServer, server);
            System.out.println("Server iniciado en: " + dirServer);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

import java.rmi.RemoteException;

public class StevieServer extends java.rmi.server.UnicastRemoteObject implements StevieT {
    public StevieServer() throws RemoteException {}

    public static void main(String[] args) {
        try {
            StevieT stevieT = new StevieServer();

            java.rmi.Naming.rebind("//" + args[0] + ":1234/StevieServer", stevieT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sweepPick() throws RemoteException {
        System.out.println("OMG LMAO");
    }

    @Override
    public int getSweeps() throws RemoteException {
        return 10;
    }
}
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SudokuServer extends UnicastRemoteObject implements ISudoku {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SudokuServer() throws RemoteException { }

    @Override
    public ArrayList<int[][]> solutionsSeq(int[][] sudoku, int lowLim, int upLim) throws RemoteException {
        return ConcurrentSudokuSolver.solutions(sudoku, lowLim, upLim);
    }

    @Override
    public ArrayList<int[][]> solutionsConc(int[][] sudoku, int lowLim, int upLim) throws RemoteException {
        return SudokuSolver.solutions(sudoku, lowLim, upLim);
    }

    public static void main(String[] args) {
        
        try {

            SudokuServer srv = new SudokuServer();
            String adr = InetAddress.getLocalHost().getHostAddress();
            int port = Integer.parseInt(args[0]);
            String resource = "//" + adr + ":" + port + "/Sudoku";
            LocateRegistry.createRegistry(port);
            Naming.rebind(resource, srv);
            System.out.println("Server iniciado en: " + resource);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}


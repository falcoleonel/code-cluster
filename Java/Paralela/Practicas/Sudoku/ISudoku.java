import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ISudoku extends Remote {

    public ArrayList<int[][]> solutionsSeq(int[][] sudoku, int lowLim, int upLim) throws RemoteException;
    public ArrayList<int[][]> solutionsConc(int[][] sudoku, int lowLim, int upLim) throws RemoteException;
}


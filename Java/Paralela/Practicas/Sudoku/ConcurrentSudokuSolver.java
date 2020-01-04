import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ConcurrentSudokuSolver extends RecursiveAction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static boolean validSudoku(int[][] sudoku) {
        
        ArrayList<HashSet<Integer>> sets = new ArrayList<>();
        for(int i = 0; i < 3; i++) sets.add(new HashSet<>());
        for(int i = 0; i < 9; i++) {
            for(int k = 0; k < 3; k++) sets.get(k).clear();
            for(int j = 0; j < 9; j++) {
                int m = j / 3 + 3 * (i / 3);
                int n = j % 3 + 3 * (i % 3);
                if(!sets.get(0).add(sudoku[i][j]) ||
                    !sets.get(1).add(sudoku[j][i]) ||
                    !sets.get(2).add(sudoku[m][n]))
                    return false;
                for(int k = 0; k < 3; k++) sets.get(k).remove(0);
            }
        }
        return true;
    }

    public static int[][] readSudoku(File archivo) throws FileNotFoundException {

        FileInputStream lectura = new FileInputStream(archivo);
        int[][] sudoku = new int[9][9];
        int i = 0, j = 0;
        try {
            while(j != 9) {
                while(i != 9) {
                    int r = 0;
                    while(r < '0' || r > '9') r = lectura.read();
                    sudoku[j][i++] = r - '0';
                }
                i = 0;
                j++;
            }
        } catch(IOException ex) { }
        
        return sudoku;
    }

    public static ArrayList<int[][]> solutions(int[][] sudoku, int lowLim, int upLim) {

        ArrayList<int[][]> sols = new ArrayList<>();
        new ForkJoinPool().invoke(new ConcurrentSudokuSolver(sudoku, 0, 0, sols, lowLim, upLim, true));
        return sols;
    }

    public static String sudokuToString(int[][] sudoku) {

        String res = "";
        for(int i = 0; i < sudoku.length; i++) {
            if(i % 3 == 0) res += '\n';
            for(int j = 0; j < sudoku[i].length; j++) {
                if(j % 3 == 0) res += ' ';
                res += sudoku[i][j];
            }
            res += '\n';
        }
        return res;
    }

    ArrayList<int[][]> sols;
    int[][] matrix;
    int x;
    int y;
    int lowLim;
    int upLim;
    boolean useLim;

    public ConcurrentSudokuSolver(
        int[][] matrix,
        int x,
        int y,
        ArrayList<int[][]> sols,
        int lowLim,
        int upLim,
        boolean useLim
    ) {
        this.matrix = matrix;
        this.x = x;
        this.y = y;
        this.sols = sols;
        this.lowLim = lowLim;
        this.upLim = upLim;
        this.useLim = useLim;
    }

    @Override
    protected void compute() {

        if(x == 9) { x = 0; y++; }
        if(y == 9) {
            if(validSudoku(matrix))
                synchronized(sols) {
                    sols.add(matrix);
                }
            return;
        }

        if(matrix[y][x] != 0) invokeAll(
            new ConcurrentSudokuSolver(matrix, x + 1, y, sols, lowLim, upLim, useLim)
        );
        else {

            ArrayList<ConcurrentSudokuSolver> tasks = new ArrayList<>();
            for(int i = useLim ? lowLim : 1; i < (useLim ? upLim : 10); i++) {

                int[][] m = new int[9][9];
                for(int j = 0; j < 9; j++) for(int k = 0; k < 9; k++)
                    m[j][k] = matrix[j][k];
                m[y][x] = i;
                if(validSudoku(m)) tasks.add(
                    new ConcurrentSudokuSolver(m, x + 1, y, sols, lowLim, upLim, false)
                );
            }
            invokeAll(tasks);
        }
    }
}


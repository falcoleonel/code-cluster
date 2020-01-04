import java.util.ArrayList;

public class SudokuSolver {

    public static void solutionsAux(
        int[][] matrix,
        int x,
        int y,
        ArrayList<int[][]> sols,
        int lowLim,
        int upLim,
        boolean useLim
    ) {
        if(x == 9) { x = 0; y++; }
        if(y == 9) {
            if(ConcurrentSudokuSolver.validSudoku(matrix)) sols.add(matrix);
            return;
        }

        if(matrix[y][x] != 0) solutionsAux(matrix, x + 1, y, sols, lowLim, upLim, useLim);
        else {

            for(int i = useLim ? lowLim : 1; i < (useLim ? upLim : 10); i++) {

                int[][] m = new int[9][9];
                for(int j = 0; j < 9; j++) for(int k = 0; k < 9; k++)
                    m[j][k] = matrix[j][k];
                m[y][x] = i;
                if(ConcurrentSudokuSolver.validSudoku(m))
                    solutionsAux(m, x + 1, y, sols, lowLim, upLim, false);
            }
        }
    }

    public static ArrayList<int[][]> solutions(int[][] sudoku, int lowLim, int upLim) {

        ArrayList<int[][]> sols = new ArrayList<>();
        solutionsAux(sudoku, 0, 0, sols, lowLim, upLim, true);
        return sols;
    }
}


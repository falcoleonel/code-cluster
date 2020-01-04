import java.util.ArrayList;
import java.util.function.Function;

public class Solucionador extends Thread {

    public Function<int[][], ArrayList<int[][]>> metodo;
    public int[][] sudoku;
    public ArrayList<int[][]> resultado;

    public Solucionador(Function<int[][], ArrayList<int[][]>> metodo, int[][] sudoku) {
        this.metodo = metodo;
        this.sudoku = sudoku;
    }

    @Override
    public synchronized void start() {
        resultado = metodo.apply(sudoku);
    }
}


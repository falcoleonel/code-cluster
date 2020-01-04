package ordenamientos;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSortConcurrente extends RecursiveAction {

    public static void ordenar(int[] list) {
        new ForkJoinPool().invoke(
            new MergeSortConcurrente(list, 0, list.length / 2, (list.length + 1) / 2)
        );
    }
    
    int[] list;
    int offset;
    int left;
    int right;

    public MergeSortConcurrente(int[] list, int offset, int left, int right) {
        this.list = list;
        this.offset = offset;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {

        if(left == 0) return;
        invokeAll(
            new MergeSortConcurrente(list, offset, left / 2, (left + 1) / 2),
            new MergeSortConcurrente(list, offset + left, right / 2, (right + 1) / 2)
        );
        MergeSort.merge(list, offset, left, right);
    }
}

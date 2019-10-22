import java.util.Random;

public class PruebaMergeSort {

    public static void main(String[] args) {

        for (int i = 100; i < 100000000; i *= 10) {

            int[] arreglo1 = new int[i];
            int[] arreglo2 = new int[i];

            Random r = new Random();

            for (int j = 0; j < i; j++)
                arreglo1[j] = arreglo2[j] = r.nextInt();

            System.out.print(i + " Elementos, \n Secuencial: ");

            long tiempo1 = System.nanoTime();
            MergeSort.mergeSort(arreglo1);
            long tiempo2 = System.nanoTime();

            System.out.print((tiempo2 - tiempo1) / 1e+6 + "ms, Paralelo: ");
            MergeSortForkJoin m = new MergeSortForkJoin();
            tiempo1 = System.nanoTime();
            m.sort(arreglo2);
            tiempo2 = System.nanoTime();

            System.out.println((tiempo2 - tiempo1) / 1e+6 + "ms \n");
        }
    }
}

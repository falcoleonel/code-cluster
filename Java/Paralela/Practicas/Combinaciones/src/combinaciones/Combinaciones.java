package combinaciones;

import java.util.ArrayList;
import ordenamientos.MergeSort;
import ordenamientos.MergeSortConcurrente;
import servidor.Resultado;
import servidor.ServidorInterfaz;

public class Combinaciones {
    
    public static ArrayList<ArrayList<Integer>> combinaciones(int[] lista, int suma) {

        ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();
        MergeSort.ordenar(lista);
        for(int i = 1; i <= lista.length; i++)
            hacerCombinaciones(lista, new ArrayList<>(), resultado, i, 0, suma);
        
        return resultado;
    }
    
    public static ArrayList<ArrayList<Integer>> combinacionesConcurrente(int[] lista, int suma) {
        
        ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();
        MergeSortConcurrente.ordenar(lista);
        Thread hilo1 = new Thread(() -> {
            for(int i = 1; i <= lista.length / 4; i++)
                hacerCombinaciones(lista, new ArrayList<>(), resultado, i, 0, suma);
        });
        Thread hilo2 = new Thread(() -> {
            for(int i = lista.length / 4 + 1; i <= lista.length; i++)
                hacerCombinaciones(lista, new ArrayList<>(), resultado, i, 0, suma);
        });
        hilo1.start();
        hilo2.start();
        try {
            hilo1.join();
            hilo2.join();
        } catch(InterruptedException ex) {}
        
        return resultado;
    }
    
    public static ArrayList<ArrayList<Integer>> combinacionesParalelo(int[] lista, int suma, ServidorInterfaz servidor) {
        
        ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();
        MergeSortConcurrente.ordenar(lista);
        Resultado hilo1 = new Resultado(() -> {
            try {
                return servidor.combinaciones(lista, suma, lista.length / 2);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            return new ArrayList<>();
        });
        Thread hilo2 = new Thread(() -> {
            for(int i = lista.length / 2 + 1; i <= lista.length; i++)
                hacerCombinaciones(lista, new ArrayList<>(), resultado, i, 0, suma);
        });
        hilo1.start();
        hilo2.start();
        try {
            hilo1.join();
            hilo2.join();
        } catch(InterruptedException ex) {}
        resultado.addAll(hilo1.resultado);
        
        return resultado;
    }

    public static void hacerCombinaciones(
        int[] lista,
        ArrayList<Integer> actual,
        ArrayList<ArrayList<Integer>> totales,
        int restantes,
        int indice,
        int suma
    ) {

        int sumaActual = actual.stream().mapToInt(x -> x).sum();
        if(sumaActual > suma) return;
        if(restantes-- == 0) {
            if(sumaActual == suma) synchronized(totales) {
                totales.add(actual);
            }
        }

        else for(int i = indice; i < lista.length - restantes;) {
            ArrayList<Integer> siguiente = new ArrayList<>();
            actual.forEach(e -> siguiente.add(e));
            siguiente.add(lista[i]);
            hacerCombinaciones(lista, siguiente, totales, restantes, ++i, suma);
        }
    }
}

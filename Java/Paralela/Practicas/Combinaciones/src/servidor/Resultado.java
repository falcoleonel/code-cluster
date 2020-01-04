package servidor;

import java.util.ArrayList;
import java.util.function.Supplier;

public class Resultado extends Thread {

    public ArrayList<ArrayList<Integer>> resultado;
    public Supplier<ArrayList<ArrayList<Integer>>> calcular;
    
    public Resultado(Supplier<ArrayList<ArrayList<Integer>>> calcular) {
        this.calcular = calcular;
    }

    @Override
    public synchronized void start() {
        resultado = calcular.get();
    }
}

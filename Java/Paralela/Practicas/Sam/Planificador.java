

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Planificador implements Runnable {

    public final TreeMap<Integer, Proceso> procesos;
    private final ReentrantLock lock;
    private final Condition vacia;
    
    public Planificador() {
        procesos = new TreeMap<>();
        lock = new ReentrantLock();
        vacia = lock.newCondition();
    }
    
    @Override
    public void run() {
        try {
            
            int ciclos = 0;
            while(true) {
                
                try {
                    //matar procesos inactivos
                    lock.lock();
                    procesos.keySet().removeAll(
                        procesos.entrySet().stream().filter(
                            proceso -> !proceso.getValue().isAlive()
                        ).map(p -> p.getKey()).collect(Collectors.toList())
                    );
                    //ejecutar todos los procesos y esperar el quantum
                    for(Map.Entry<Integer, Proceso> proceso : procesos.entrySet()) {

                        proceso.getValue().reanudar();
                        Thread.sleep(proceso.getValue().quantum);
                        if(proceso.getValue().isAlive())
                            proceso.getValue().suspender();
                    }
                    
                    if(procesos.isEmpty()) vacia.await();
                    
                } finally {
                    lock.unlock();
                }
            }
        } catch(InterruptedException ex) {
            
            try {
                for(Thread proceso : procesos.values()) {
                    proceso.interrupt();
                    proceso.join();
                }
            } catch(InterruptedException exi) {
                System.err.println(">:v");
            }
            
            procesos.clear();
        }
    }
    
    public void agregarProceso(Proceso proceso) {
        
        try {
            
            lock.lock();
            if(procesos.containsKey(proceso.pid)) return;
            procesos.put(proceso.pid, proceso);
            proceso.start();
            
        } finally {
            vacia.signal();
            lock.unlock();
        }
    }
    
    public void interrumpirProceso(int proceso) {
        if(procesos.containsKey(proceso)) {
            try {
                lock.lock();
                procesos.get(proceso).interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}

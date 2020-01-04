/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectos1erparcial;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author samue
 * @param <T>
 */
public class Proceso<T> extends Thread {
    
    public T info;
    private boolean pausado;
    public int pid;
    public int quantum;
    
    private final ReentrantLock lockPausado;
    private final Condition condicionPausado;
    
    public Proceso(T info, int pid, int quantum) {
        
        this.info = info;
        this.quantum = quantum;
        this.pid = pid;
        
        lockPausado = new ReentrantLock();
        condicionPausado = lockPausado.newCondition();
    }
    
    public void suspender() {
        pausado = true;
    }
    
    public void reanudar() {
        
        try {
            
            lockPausado.lock();
            pausado = false;
            condicionPausado.signal();
            
        } finally {
            lockPausado.unlock();
        }
    }
    
    protected void comprobarPausa() throws InterruptedException {
        try {
            lockPausado.lock();
            if(pausado) condicionPausado.await();
        } finally {
            lockPausado.unlock();
        }
    }
}

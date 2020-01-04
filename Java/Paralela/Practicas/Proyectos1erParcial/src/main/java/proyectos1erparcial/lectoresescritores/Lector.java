/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectos1erparcial.lectoresescritores;

import proyectos1erparcial.Proceso;

/**
 *
 * @author samue
 */
public class Lector extends Proceso<FrmLectoresEscritores> {
    
    int tiempo;
    
    public Lector(FrmLectoresEscritores info, int pid, int quantum, int tiempo) {
        super(info, pid, quantum);
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        
        try {
            
            try {
                info.lock.lock();
            } finally {
                info.lock.unlock();
            }
            
            System.out.println(info.datos);
            Thread.sleep(tiempo);
            
        } catch(InterruptedException ex) {
            System.err.println("Lector interrumpido");
        }
        
        info.actualizarTablas();
    }
}

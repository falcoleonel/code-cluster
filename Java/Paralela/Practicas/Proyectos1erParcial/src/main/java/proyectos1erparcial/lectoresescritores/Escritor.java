/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectos1erparcial.lectoresescritores;

import java.util.logging.Level;
import java.util.logging.Logger;
import proyectos1erparcial.Proceso;

/**
 *
 * @author samue
 */
public class Escritor extends Proceso<FrmLectoresEscritores> {
    
    int tiempo;
    
    public Escritor(FrmLectoresEscritores info, int pid, int quantum, int tiempo) {
        super(info, pid, quantum);
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        
        info.planificador.procesos.values().stream()
            .filter(p -> p.pid < pid)
            .forEach(p -> { p.interrupt(); try {
                    p.join();
                } catch (InterruptedException ex) { }
            });
            
        try {
            
            info.lock.lock();
            info.datos = info.txtDatos.getText();
            Thread.sleep(tiempo);
            
        } catch(InterruptedException ex) {
            System.err.println("Escritor interrumpido");
        } finally {
            info.lock.unlock();
        }
        
        info.actualizarTablas();
    }
}

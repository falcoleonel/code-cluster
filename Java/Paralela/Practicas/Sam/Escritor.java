
import java.util.logging.Level;
import java.util.logging.Logger;


public class Escritor extends Proceso<FrmLectoresEscritores> {
    
    int tiempo;
    
    public Escritor(FrmLectoresEscritores info, int pid, int quantum, int tiempo) {
        super(info, pid, quantum);
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        //interrumpir a lectores y espera a que se salgan
        info.planificador.procesos.values().stream()
            .filter(p -> p.pid < pid)
            .forEach(p -> { p.interrupt(); try {
                    p.join();
                } catch (InterruptedException ex) { }
            });
            
        try {
            //escribo datos
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

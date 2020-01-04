
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

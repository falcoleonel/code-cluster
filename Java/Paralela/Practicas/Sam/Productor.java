

public class Productor extends Proceso<String> {
    
    FrmProductorConsumidor almacen;
    public int tiempoDentro;
    public int tiempoFuera;
    
    public Productor(FrmProductorConsumidor almacen, int id, int quantum, int tiempoDentro, int tiempoFuera) {
        
        super("Entrando", id, quantum);
        this.almacen = almacen;
        this.tiempoDentro = tiempoDentro;
        this.tiempoFuera = tiempoFuera;
    }

    @Override
    public void run() {
        
        try {
            while(true) {
                
                info = "Descanzando";
                almacen.ActualizarTabla(true);
                comprobarPausa();
                // Trabajar
                info = "Trabajando";
                almacen.ActualizarTabla(true);
                Thread.sleep(tiempoFuera);
                try {
                    
                    almacen.lock.lock();
                    if(almacen.ocupados.size() >= almacen.capacidad) {
                        
                        info = "Esperando";
                        almacen.ActualizarTabla(true);
                        almacen.almacenLleno.await();
                        continue;
                    }
                    
                    info = "Almacenando";
                    almacen.ActualizarTabla(true);
                    almacen.ocupados.add("Pack");
                    almacen.ActualizarProductos();
                    Thread.sleep(tiempoDentro);
                    almacen.almacenVacio.signal();
                    
                } finally {
                    almacen.lock.unlock();
                }
            }
        } catch(InterruptedException ex) {
            System.out.println("Se ha expulsado a un productor");
        }
    }
}

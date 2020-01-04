/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectos1erparcial.productorconsumidor;

import proyectos1erparcial.Proceso;

/**
 *
 * @author samue
 */
public class Consumidor extends Proceso<String> {
    
    FrmProductorConsumidor almacen;
    public int tiempoDentro;
    public int tiempoFuera;
    
    public Consumidor(FrmProductorConsumidor almacen, int id, int quantum, int tiempoDentro, int tiempoFuera) {
        
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
                almacen.ActualizarTabla(false);
                comprobarPausa();
                // Trabajar
                info = "Trabajando";
                almacen.ActualizarTabla(false);
                Thread.sleep(tiempoFuera);
                try {
                    
                    almacen.lock.lock();
                    if(almacen.ocupados.isEmpty()) {
                        
                        info = "Esperando";
                        almacen.ActualizarTabla(false);
                        almacen.almacenVacio.await();
                        continue;
                    }
                    
                    info = "Retirando";
                    almacen.ActualizarTabla(false);
                    almacen.ocupados.pop();
                    almacen.ActualizarProductos();
                    Thread.sleep(tiempoDentro);
                    almacen.almacenLleno.signal();
                    
                } finally {
                    almacen.lock.unlock();
                }
            }
        } catch(InterruptedException ex) {
            System.out.println("Se ha expulsado a un consumidor");
        }
    }
}

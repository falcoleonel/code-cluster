/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matematicas;

import java.util.List;

/**
 *
 * @author scoral
 */
public class Poliedro {
    
    public final List<Cuaternion> puntos;
    public Cuaternion posicion;
    public Cuaternion rotacion;
    
    public Poliedro(List<Cuaternion> puntos, Cuaternion posicion, Cuaternion rotacion) {
        this.puntos = puntos;
        this.posicion = posicion;
        this.rotacion = rotacion;
    }
}

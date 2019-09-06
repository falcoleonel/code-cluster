/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercer_parcial;

import java.util.List;

/**
 *
 * @author scoral
 */
public class Poliedro {
    
    public final List<Coordenada> puntos;
    public Coordenada posicion;
    public Coordenada rotacion;
    
    public Poliedro(List<Coordenada> puntos, Coordenada posicion, Coordenada rotacion) {
        this.puntos = puntos;
        this.posicion = posicion;
        this.rotacion = rotacion;
    }
}

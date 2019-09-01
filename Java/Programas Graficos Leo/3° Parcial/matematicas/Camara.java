/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matematicas;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author scoral
 */
public class Camara {
    
    private Cuaternion posicion;
    private Cuaternion rotacion;
    private double angSolido;
    private Dimension dimVentana;
    private Point2D.Double factor;
    private Dimension dimVentana2;
    private boolean perspectiva;
    private Point2D.Double escala;
    
    public Camara(
        Cuaternion posicion,
        Cuaternion rotacion,
        double angSolido,
        Dimension dimVentana,
        boolean perspectiva
    ) {
        this.posicion = posicion;
        this.rotacion = rotacion;
        this.angSolido = angSolido;
        this.dimVentana = dimVentana;
        this.perspectiva = perspectiva;
        dimVentana2 = new Dimension(dimVentana.width / 2, dimVentana.height / 2);
        escala = new Point2D.Double(1, 1);
        
        reajustarFactor();
    }
    
    public Point2D.Double obtenerEscala() {
        return escala;
    }
    
    public void ajustarEscala(double x, double y) {
        escala = new Point2D.Double(x, y);
    }
    
    public boolean obtenerPerspectiva() {
        return perspectiva;
    }
    
    public void ajustarPerspectiva(boolean perspectiva) {
        this.perspectiva = perspectiva;
    }
    
    public Cuaternion obtenerPosicion() {
        return posicion;
    }
    
    public void ajustarPosicion(Cuaternion posicion) {
        this.posicion = posicion;
    }
    
    public Cuaternion obtenerRotacion() {
        return rotacion;
    }
    
    public void ajustarRotacion(Cuaternion rotacion) {
        this.rotacion = rotacion;
    }
    
    public double obtenerAngSolido() {
        return angSolido;
    }
    
    public void ajustarAngSolido(double angSolido) {
        this.angSolido = angSolido;
        reajustarFactor();
    }
    
    public Dimension obtenerDimVentana() {
        return dimVentana;
    }
    
    public void ajustarDimVentana(Dimension dimVentana) {
        this.dimVentana = dimVentana;
        dimVentana2 = new Dimension(dimVentana.width / 2, dimVentana.height / 2);
        reajustarFactor();
    }
    
    private void reajustarFactor() {
        factor = new Point2D.Double(
            Math.sqrt(angSolido * dimVentana.width / dimVentana.height),
            Math.sqrt(angSolido * dimVentana.height / dimVentana.width)
        );
    }
    
    public List<Point> proyectar(Poliedro poliedro) {
        
        return poliedro.puntos.stream().map((Cuaternion p) -> 
            rotacion
                .por(poliedro.rotacion
                    .por(p)
                    .por(poliedro.rotacion.conj())
                .menos(poliedro.posicion).menos(posicion))
                .por(rotacion.conj())
        ).map((Cuaternion pm) ->
            new Point(
                (int)(-pm.y * escala.x * dimVentana.width / factor.x /
                    (perspectiva ? pm.x : -1)) + dimVentana2.width,
                (int)(-pm.z * escala.y * dimVentana.height / factor.y /
                    (perspectiva ? pm.x : -1)) + dimVentana2.height
            )
        ).collect(Collectors.toList());
    }
}

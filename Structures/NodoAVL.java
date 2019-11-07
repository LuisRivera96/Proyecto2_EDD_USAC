/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

/**
 *
 * @author user
 */
public class NodoAVL {
    NodoAVL derecha;
    NodoAVL izquierda;
    //DATOS
    String nombre;
    String contenido;
    int FE;
    int altura;
    String timestamp;
    String propietario;
    
    public NodoAVL(String nombre,String contenido,String timestamp,String propietario){
        this.nombre = nombre;
        this.contenido = contenido;
        FE = 0;
        altura = 1;
        this.timestamp = timestamp;
        this.propietario = propietario;
        derecha = null;
        izquierda = null;
    }

    public NodoAVL getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoAVL derecha) {
        this.derecha = derecha;
    }

    public NodoAVL getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoAVL izquierda) {
        this.izquierda = izquierda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getFE() {
        return FE;
    }

    public void setFE(int FE) {
        this.FE = FE;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    
    
}

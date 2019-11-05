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
public class NodoFila {
    
    NodoFila siguiente;
    NodoFila anterior;
    NodoContenido siguienteC;
    String carpeta;
    int  fila;
    String XF;
    
    public NodoFila(int fila,String carpeta){
        this.carpeta = carpeta;
        this.fila = fila;
        XF = "X"+carpeta;
        siguiente = null;
        anterior = null;
        siguienteC = null;
        
    }

    public NodoFila getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoFila siguiente) {
        this.siguiente = siguiente;
    }

    public NodoFila getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoFila anterior) {
        this.anterior = anterior;
    }

    public NodoContenido getSiguienteC() {
        return siguienteC;
    }

    public void setSiguienteC(NodoContenido siguienteC) {
        this.siguienteC = siguienteC;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    
    
    
}

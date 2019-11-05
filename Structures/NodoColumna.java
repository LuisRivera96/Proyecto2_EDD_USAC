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
public class NodoColumna {
    
    NodoColumna siguiente;
    NodoColumna anterior;
    NodoContenido abajoC;
    String carpeta;
    int columna;
    String YC;
    
    public NodoColumna(int columna,String carpeta){
        this.columna = columna;
        this.carpeta = carpeta;
        YC = "Y"+carpeta;
        siguiente = null;
        anterior = null;
        abajoC = null;
    }

    public NodoColumna getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoColumna siguiente) {
        this.siguiente = siguiente;
    }

    public NodoColumna getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoColumna anterior) {
        this.anterior = anterior;
    }

    public NodoContenido getAbajoC() {
        return abajoC;
    }

    public void setAbajoC(NodoContenido abajoC) {
        this.abajoC = abajoC;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    
    
    
}

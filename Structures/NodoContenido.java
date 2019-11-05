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
public class NodoContenido {
    
    NodoContenido siguiente;
    NodoContenido anterior;
    NodoContenido abajo;
    NodoContenido arriba;
    String directorio;
    int x;
    int y;
    String XY;
    
    public NodoContenido(int x,int y,String directorio){
        this.directorio = directorio;
        this.x = x;
        this.y = y;
        XY = "XY"+directorio;
        siguiente = null;
        anterior = null;
        abajo = null;
        arriba = null;
    }

    public NodoContenido getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoContenido siguiente) {
        this.siguiente = siguiente;
    }

    public NodoContenido getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoContenido anterior) {
        this.anterior = anterior;
    }

    public NodoContenido getAbajo() {
        return abajo;
    }

    public void setAbajo(NodoContenido abajo) {
        this.abajo = abajo;
    }

    public NodoContenido getArriba() {
        return arriba;
    }

    public void setArriba(NodoContenido arriba) {
        this.arriba = arriba;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}

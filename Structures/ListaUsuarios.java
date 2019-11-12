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
public class ListaUsuarios {
    NodoHash inicio;
    NodoHash fin;
    
    public ListaUsuarios(){
        inicio = null;
    }
    
    public void add(String usuario,String password,String timestamp){
        NodoHash nuevo = new NodoHash(usuario,password,timestamp);
        if(inicio == null){
            inicio = nuevo;
            fin = inicio;
        }else{
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }
    
    public NodoHash buscar(String usuario){
        NodoHash temporal = inicio;
        while(temporal != null){
            if(temporal.usuario.equals(usuario)){
                return temporal;
            }
            temporal = temporal.siguiente;
        }
        return null;
    }
    
    
}

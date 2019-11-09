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
public class Hash {
    
    ListaUsuarios  hash[];
    int tamano;
    
    public Hash(){
        hash = new ListaUsuarios[7];
        tamano = 0;
    }
    
    public void insertarUsuario(String usuario,String password){
        
        int indice = getIndice(usuario);
        hash[indice].add(usuario, password);
        tamano++;
        if((double)tamano/hash.length >0.75){
            hash = reHash(hash);
        }
        
    }
    
    public void validarUsuario(String usuario,String password){
        
    }
    
    int getIndice(String usuario){
        int var = 0;
        int posicion;
        for(int i=0;i<usuario.length();i++){
            var += usuario.codePointAt(i);
        }
        
        posicion = var % hash.length;
        return posicion;
    }
    
    ListaUsuarios[] reHash(ListaUsuarios tabla[]){
        ListaUsuarios temporal[] = new ListaUsuarios[numeroPrimoSiguiente(tabla.length)];
        for (int i = 0; i < tabla.length; i++) {
            temporal[i] = tabla[i];
        }
        return temporal;
    }
    
    public int numeroPrimoSiguiente(int actual){
       //un numero primo es divisible solo entre el mismo y 1
        int siguiente = actual;
        int contador = 2;//evitamos el 1
        boolean primo = false;
        while (primo != true) {//si es primo sale del ciclo
            siguiente++;
            while (siguiente > contador) {//evitamos dividirlo entre el mismo                
                primo = true;
                if (siguiente % contador == 0) {//si es divisible no es primo
                    primo = false;
                    break;
                }
                contador++;
            }
            contador = 2;

        }
        return siguiente; 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import java.io.PrintWriter;

/**
 *
 * @author user
 */
public class AVL {
    
    NodoAVL raiz;
    
    public AVL(){
        raiz = null;
    }
    
    ////ADDD////
    int altura(NodoAVL N){
        if(N == null){
            return 0;
        }
        return N.altura;
    }
    
    int mayor(int a,int b){
        return(a>b)?a:b;
    }
    ///////////////ROTACIONES///////////////////////////
    //LEFT ROTATE
    NodoAVL leftRotate(NodoAVL x){
        NodoAVL y = x.derecha;
        NodoAVL T2 = y.izquierda;
        
        y.izquierda = x;
        x.derecha = T2;
        
        x.altura = mayor(altura(x.izquierda),altura(x.derecha))+1;
        y.altura = mayor(altura(y.izquierda),altura(y.derecha))+1;
        
        return y;
    }
    //RIGHT ROTATE
    NodoAVL rightRotate(NodoAVL y){
        NodoAVL x = y.izquierda;
        NodoAVL T2 = x.derecha;
        
        x.derecha = y;
        y.izquierda = T2;
        
        y.altura = mayor(altura(y.izquierda),altura(y.derecha)) + 1;
        x.altura = mayor(altura(x.izquierda),altura(x.derecha)) + 1;
        
        return x;
    }
    
    //GET BALANCE
    int getBalance(NodoAVL N){
        if(N == null){
            return 0;
        }
        return altura(N.izquierda) - altura(N.derecha);
    }
    
    void insertar(String nombre,String contenido,String timestamp,String propietario){
        if(raiz != null){
            raiz = insertarR(raiz,nombre,contenido,timestamp,propietario);
        }else{
            NodoAVL nuevo = new NodoAVL(nombre,contenido,timestamp,propietario);
            raiz = nuevo;
        }
        
    }
    
    NodoAVL insertarR(NodoAVL nodo,String nombre,String contenido,String timestamp,String propietario){
        if(nodo == null){
            return (new NodoAVL(nombre,contenido,timestamp,propietario));
        }
        
        if(nombre.compareTo(nodo.nombre) < 0){
           nodo.izquierda = insertarR(nodo.izquierda,nombre,contenido,timestamp,propietario);
       }else if(nombre.compareTo(nodo.nombre) > 0){
           nodo.derecha = insertarR(nodo.derecha,nombre,contenido,timestamp,propietario);
       }else{
            System.out.println("Archivo ya Ingresado");
            return nodo;
       }
        nodo.altura = 1 + mayor(altura(nodo.izquierda),altura(nodo.derecha));
        nodo.FE = getBalance(nodo);
        int balance = getBalance(nodo);
        
        //LEFT LEFT CASE
        if(balance > 1 && nombre.compareTo(nodo.izquierda.nombre) < 0){
            return rightRotate(nodo);
        }
        //RIGHT RIGHT
        if(balance < -1 && nombre.compareTo(nodo.derecha.nombre) > 0){
            return leftRotate(nodo);
        }
        //LEFT RIGHT
        if(balance > 1 && nombre.compareTo(nodo.izquierda.nombre) > 0){
            nodo.izquierda = leftRotate(nodo.izquierda);
            return rightRotate(nodo);
        }
        //RIGHT LEFT
        if(balance < -1 && nombre.compareTo(nodo.derecha.nombre) < 0){
            nodo.derecha = rightRotate(nodo.derecha);
            return leftRotate(nodo);
        }
        
        return nodo;
    }
    
    //DELETE////
    
    
    ///GRAPHIZ///
    String graficar(NodoAVL raiz){
        String Dot="";
        
        if(raiz != null){
            if(raiz.derecha != null || raiz.izquierda != null){
                Dot += raiz.nombre + ":f" + raiz.nombre + "[id=" + raiz.nombre + ", color=\"blue\"];\n";
                Dot += raiz.nombre + "[label=\"<N "+ raiz.nombre + " I> | <f" + raiz.nombre + "> "+"Nombre Archivo: "+raiz.nombre+"\\n Contenido: "+raiz.contenido+"\\n Factor de Equilibrio: "+raiz.FE+"\\n Altura: "+raiz.altura+"\\n Timestamp: "+raiz.timestamp+"\\n Propietario: "+raiz.propietario+" | <f"+raiz.nombre+"D> \" shape=\"record\"];\n";
            }else{
                Dot += raiz.nombre + ":f" + raiz.nombre + "[label=\""+"Nombre Archivo: "+raiz.nombre+"\\n Contenido: "+raiz.contenido+"\\n Factor de Equilibrio: "+raiz.FE+"\\n Altura: "+raiz.altura+"\\n Timestamp: "+raiz.timestamp+"\\n Propietario: "+raiz.propietario+ ", color=\"blue\" shape=\"rectangle\"];\n";
            }
            String a = graficar(raiz.izquierda);
            if(a != ""){
                Dot += "\""+raiz.nombre+"\" : N"+raiz.nombre + "I -> ";
                Dot += " "+ a;
            }
            String b = graficar(raiz.derecha);
            if(b != ""){
               Dot += "\""+raiz.nombre+"\" : f"+raiz.nombre+"D -> ";
               Dot += " "+b;
            }
        }
        return Dot;
    }
    
    void getGrafica(){
        String dot = "";
        dot += "digraph AVL{\n";
        dot += "compound=true;\n";
        dot += "node[shape=\"Mrecord\"];\n";
        dot += graficar(raiz);
        dot += "}";
        try{
           PrintWriter file = new PrintWriter("","UTF-8");
           file.print(dot);
           file.close();
           String cmd = "dot -Tjpg AVL.dot -o AVL.jpg";
           Runtime.getRuntime().exec(cmd);
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    
    
}

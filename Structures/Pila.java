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
public class Pila {
    
    NodoPila cima;
    
    public Pila(){
        cima = null;
    }
    
    void push(String evento,String timestamp,String usuario){
        NodoPila nuevo = new NodoPila(evento,timestamp,usuario);
        if(cima == null){
            nuevo.siguiente = null;
            cima = nuevo;
        }else{
            nuevo.siguiente = cima;
            cima = nuevo;
        }
    }
    
    void pop(){
        if(cima == null){
            System.out.println("Pila sin eventos");
        }else{
            cima = cima.siguiente;
        }
    }
    
    void getGraphica(){
        String line1 = "";
        int count = 0;
        NodoPila temporal = cima;
        line1 += "digraph Eventos{\n node [shape=record];\n rankdir=LR;\n nodo0[label=\"|";
        while(temporal.siguiente != null){
            line1 += "<"+count+">"+"Fecha y Hora: "+temporal.timestamp+"\\n Operacion: "+temporal.evento+"\\n Usuario: "+temporal.usuario+"\\n";
            count++;
            if(temporal.siguiente.siguiente != null){
                line1 += "|"; 
            }
            temporal = temporal.siguiente;
            
        }
        line1 += "\"];";
        try{
           PrintWriter file = new PrintWriter("","UTF-8");
           file.print(line1);
           file.close();
           String cmd = "dot -Tjpg Eventos.dot -o Eventos.jpg";
           Runtime.getRuntime().exec(cmd);
        }catch(Exception e){
            System.out.println("Error");
        } 
        
    }
    
    
    
}

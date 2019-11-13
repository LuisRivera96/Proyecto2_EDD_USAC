/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public class Pila {
    
    NodoPila cima;
    
    public Pila(){
        cima = null;
    }
    
    public void push(String evento,String timestamp,String usuario){
        NodoPila nuevo = new NodoPila(evento,timestamp,usuario);
        if(cima == null){
            nuevo.siguiente = null;
            cima = nuevo;
        }else{
            nuevo.siguiente = cima;
            cima = nuevo;
        }
    }
    
    public void pop(){
        if(cima == null){
            System.out.println("Pila sin eventos");
        }else{
            cima = cima.siguiente;
        }
    }
    
    public String getTime(){
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String time = hourdateFormat.format(date);
        return time;
    }
    
   public void getGraphica(){
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
            FileWriter txtDot = new FileWriter("Eventos.dot");
            PrintWriter pw = new PrintWriter(txtDot);
            pw.println("digraph EVENTOS{\n");
           //pw.println("compound=true;\n");
            //pw.println("node[shape=\"Mrecord\"];\n");
            pw.println(line1);
            pw.println("}");
            txtDot.close();
           //compilar dot y generar imagen
            File miDir = new File(".");
            String ruta = miDir.getCanonicalPath() + "\\";//ruta actual
            String salida = "dot -Tjpg Eventos.dot -o Eventos.jpg";
            Runtime rt = Runtime.getRuntime();
            rt.exec(salida);
        }catch(Exception e){
            System.out.println("Error");
        }
        
        
        
    }
    
    
    
}

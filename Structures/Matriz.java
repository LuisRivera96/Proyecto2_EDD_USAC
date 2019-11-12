/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author user
 */
public class Matriz {
    
    NodoFila  raizFila;
    NodoColumna raizColumna;
    
    public Matriz(){
        raizFila = null;
        raizColumna = null;
    }
    
    ///////////////////////////////////////////////METODO ADD//////////////////////////////////////////////////////////
    
    public void insertarFila(int fila,String carpeta){
        
        NodoFila nuevo = new NodoFila(fila,carpeta);
        NodoFila anterior = null;
        
        if(raizFila == null){
            raizFila = new NodoFila(fila,carpeta);
        }else{
            NodoFila cola = raizFila;
            
            while(cola != null && cola.fila < fila){
                anterior = cola;
                cola = cola.siguiente;
            }
            
            if(anterior == null){
                nuevo.siguiente = raizFila;
                raizFila.anterior = nuevo;
                raizFila = nuevo;
            }else{
                anterior.siguiente = nuevo;
                nuevo.anterior = anterior;
                
                if(cola != null){
                    nuevo.siguiente = cola;
                    cola.anterior = nuevo;
                }
            }
        }
        
        
    }
    
    public void insertarColumna(int columna,String carpeta){
        NodoColumna nuevo = new NodoColumna(columna,carpeta);
        NodoColumna anterior = null;
        
        if(raizColumna == null){
            raizColumna = new NodoColumna(columna,carpeta);
        }else{
            NodoColumna cola = raizColumna;
            
            while(cola != null && cola.columna < columna){
                anterior = cola;
                cola = cola.siguiente;
            }
            
            if(anterior == null){
                nuevo.siguiente = raizColumna;
                raizColumna.anterior = nuevo;
                raizColumna = nuevo;
            }else{
                anterior.siguiente = nuevo;
                nuevo.anterior = anterior;
                
                if(cola != null){
                    cola.anterior = nuevo;
                    nuevo.siguiente = cola;
                }
            }
            
        }
        
    }
    
    
    NodoContenido insertarContenidoFila(int x,int y,String directorio,NodoFila fila){
        NodoContenido nuevo  = new NodoContenido(x,y,directorio);
        
        if(fila.siguienteC == null){
            fila.siguienteC = nuevo;
            return fila.siguienteC;
        }else{
            NodoContenido cola = fila.siguienteC;
            NodoContenido temporal = null;
            
            while(cola != null && cola.x < x){
                temporal = cola;
                cola = cola.siguiente;
            }
            
            if(temporal == null){
                nuevo.siguiente = fila.siguienteC;
                fila.siguienteC.anterior = nuevo;
                fila.siguienteC = nuevo;
            }else{
                temporal.siguiente = nuevo;
                nuevo.anterior = temporal;
                
                if(cola != null){
                    nuevo.siguiente = cola;
                    cola.anterior = temporal;
                }
            }
        }
     return nuevo;   
    }
    
    
    void insertarContenidoColumna(NodoColumna columna,NodoContenido nuevo){
        
        if(columna.abajoC == null){
            columna.abajoC = nuevo;
        }else{
            NodoContenido cFinal = columna.abajoC;
            NodoContenido cAnterior = null;
            
            while(cFinal != null &&  cFinal.y < nuevo.y){
                cAnterior = cFinal;
                cFinal = cFinal.abajo;
            }
            
            if(cAnterior == null){
                nuevo.abajo = columna.abajoC;
                columna.abajoC.arriba = nuevo;
                columna.abajoC = nuevo;
            }else{
                cAnterior.abajo = nuevo;
                nuevo.arriba = cAnterior;
                
                if(cFinal != null){
                    nuevo.abajo = cFinal;
                    cFinal.arriba = nuevo;
                }
            }
        }
        
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    NodoFila obtenerFila(String carpeta){
        NodoFila temporal = raizFila;
        while(temporal != null){
            if(temporal.carpeta.equals(carpeta)){
                return temporal;
            }
            temporal = temporal.siguiente;
        }
        return null;
    }
    
    NodoColumna obtenerColumna(String carpeta){
        NodoColumna temporal = raizColumna;
        while(temporal != null){
            if(temporal.carpeta.equals(carpeta)){
                return temporal;
            }
            temporal = temporal.siguiente;
        }
        return null;
    }
    
    ///////////////////////////////////ADD FILAS Y COLUMNAS//////////////////////////
    void add(int x,int y,String carpeta){
        if(obtenerFila(carpeta) == null){
            insertarFila(y,carpeta);
        }
        if(obtenerColumna(carpeta) == null){
            insertarColumna(x,carpeta);
        }
    }
    
    ///////////////////////////////////ADD CONTENIDO/////////////////////////////////
    void addContenido(int x,int y,String directorio){
        String [] separador = directorio.split("/");
        String Carpeta_Padre = separador[0];
        String Carpeta_Hijo = separador[1];
        NodoColumna c_seleccionada = obtenerColumna(Carpeta_Padre);
        NodoFila f_seleccionada = obtenerFila(Carpeta_Hijo);
        NodoContenido nuevo = null;
        nuevo = insertarContenidoFila(x,y,directorio,f_seleccionada);
        insertarContenidoColumna(c_seleccionada,nuevo);
    }
    
    
    ////////////////////////////////////GRAPHIZ//////////////////////////////////////////
    
    String graficar(NodoFila fila,NodoColumna columna){
        String line1 = "";
        NodoFila auxiliar = fila;
        NodoContenido contenido = fila.siguienteC;
        
        while(auxiliar != null){
            line1 += auxiliar.XF + "[label=\""+ auxiliar.carpeta +"\" style = filled, fillcolor = bisque1, pos=\"0,"+ (0-auxiliar.fila) +"!\"];\n";
            auxiliar = auxiliar.siguiente;
        }
        
        NodoColumna auxiliarC = columna;
        while(auxiliarC != null){
            line1 +=  auxiliarC.YC + "[label=\""+ auxiliarC.carpeta +"\" style = filled, fillcolor = lightskyblue, pos=\""+ (auxiliarC.columna) +",0!\"];\n";
            auxiliarC = auxiliarC.siguiente;
        }
        line1 += "header ->"+raizFila.XF+";\n";
        line1 += "header ->"+raizColumna.YC+";\n";
        
        auxiliar = fila;
        while(auxiliar != null){
            if(auxiliar.siguiente != null){
                line1 += auxiliar.XF + " -> " + auxiliar.siguiente.XF + " -> " + auxiliar.XF + ";\n";
            }
            auxiliar = auxiliar.siguiente;
        }
        
        auxiliarC = columna;
        while(auxiliarC != null){
            if(auxiliarC.siguiente != null){
                line1 += auxiliarC.YC + " -> " + auxiliarC.siguiente.YC + " -> " + auxiliarC.YC + ";\n";
            }
            auxiliar = auxiliar.siguiente;
        }
        
        /////////////////////////CONTENIDO/////////////////////////////////////////
        auxiliar = fila;
        while(auxiliar != null){
            contenido = auxiliar.siguienteC;
            while(contenido != null){
                line1 += contenido.XY + "[label=\""+contenido.directorio+"\" pos=\""+contenido.x+","+(0-contenido.y)+"!\"];\n";
                contenido = contenido.siguiente;
            }
            auxiliar = auxiliar.siguiente;
        }
        
        auxiliar = fila;
        while(auxiliar != null){
            if(auxiliar.siguienteC != null){
                line1 += auxiliar.XF + " -> " + auxiliar.siguienteC.XY+";\n";   
            }
            auxiliarC = columna;
            
            while(columna != null){
                if(columna.abajoC != null){
                    line1 += columna.YC + " -> " + columna.abajoC.XY + ";\n";
                }
                columna = columna.siguiente;
            }
            
            contenido = auxiliar.siguienteC;
            while(contenido != null){
                if(contenido.siguiente != null){
                    line1 += contenido.XY + " -> " + contenido.siguiente.XY + " -> " + contenido.XY+ ";\n";
                }
                if(contenido.abajo != null){
                    line1 += contenido.XY + " -> " + contenido.abajo.XY + " -> " + contenido.XY + ";\n";
                }
                contenido = contenido.siguiente;
            }
            auxiliar = auxiliar.siguiente;
        }
        return line1;
        
    }
    
    void getGrafica(){
        try{
            FileWriter txtDot = new FileWriter("DISPERSA.dot");
            PrintWriter pw = new PrintWriter(txtDot);
            pw.println("digraph DISPERSA{\n");
            pw.println("node[shape=box];\n");
            pw.println("graph[nodesep = 0.5];\n");
            pw.println("header[label=\"MATRIX\" style=filled, color=firebrick1, pos=\"0,0!\"];\n");
            pw.println(graficar(raizFila,raizColumna));
            pw.println("}");
            txtDot.close();
           //compilar dot y generar imagen
            File miDir = new File(".");
            String ruta = miDir.getCanonicalPath() + "\\";//ruta actual
            String salida = "Neato -Tjpg Dispersa.dot -o Dispersa.jpg";
            Runtime rt = Runtime.getRuntime();
            rt.exec(salida);
        }catch(Exception e){
            System.out.println("Error");
        }
        
          
    }
    
    
}

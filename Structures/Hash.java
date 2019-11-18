/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static edd_drive.Interfaz.modeloA;
import static edd_drive.Interfaz.modeloE;
import static edd_drive.Interfaz.conteoA;
import static edd_drive.Interfaz.conteoE;
import static edd_drive.Interfaz.hash;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        boolean verificacion = validarUsuario(usuario,password);
        
        if(verificacion == true){
        tamano++;    
        int indice = getIndice(usuario);
        //sha256
        String Npass = convertirSHA256(password);
        String timestamp = "";
        if(hash[indice] ==  null){
            hash[indice] = new ListaUsuarios();
        }
        String time = getTime();
        hash[indice].add(usuario,Npass,time);
        
        if((double)tamano/hash.length >0.75){
            hash = reHash(hash);
        } 
      }
        
        
    }
    
    public boolean validarUsuario(String usuario,String password){
        boolean validez1,validez2,validezT = false;
        NodoHash userN = null;
        if(password.length() > 7){
            validez1 = true;
        }else{
            validez1 = false;
            conteoE++;
            modeloE.addRow(new Object[]{conteoE,usuario,"Contrasena menor a 8 Caracteres"});
            //agregar a userNoInsertado
            return validezT;
        }
        //BUSCAR USUARIO
        userN = buscar(usuario);
        if(userN != null){
            validez2 = false;
            conteoE++;
            modeloE.addRow(new Object[]{conteoE,usuario,"Usuario ya Registrado"});
            return validezT;
        }else{
            validez2 = true;
            conteoA++;
            modeloA.addRow(new Object[]{conteoA,usuario,password});
        }
        if(validez1 == true && validez2 == true){
            validezT = true;
            return validezT;
        }else{
            validezT = false;
            return validezT;
        }
        
    }
    
    
    public NodoHash buscar(String usuario){
        NodoHash userN =  null;
        for (int i = 0; i < hash.length; i++) {
            if(hash[i] != null){
               userN = hash[i].buscar(usuario);
               if(userN != null){
                   return userN;
               }
            }
            
        }
        return userN;
        
    }
    int getIndice(String usuario){
        int var = 0;
        int posicion;
        for(int i=0;i<usuario.length();i++){
            char caracter = usuario.charAt(i);
            int ascci = (int) caracter;
            var += ascci;
        }
        
        posicion = var % hash.length;
        //System.out.println("Posicion "+posicion);
        return posicion;
    }
    
    public String getTime(){
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String time = hourdateFormat.format(date);
        return time;
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
        while (primo != true){//si es primo sale del ciclo
            siguiente++;
            while(siguiente > contador){//evitamos dividirlo entre el mismo                
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
    
    //FUNCION SHA256
    public String convertirSHA256(String password) {
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} 
	catch (NoSuchAlgorithmException e) {		
		e.printStackTrace();
		return null;
	}
	    
	byte[] hash = md.digest(password.getBytes());
	StringBuffer sb = new StringBuffer();
	    
	for(byte b : hash) {        
		sb.append(String.format("%02x", b));
	}
	    
	return sb.toString();
    }
    /////
    //GARICAR HASH
    public String graficar(){
        String dot = "";
        String contenido = "";
        //CREAR POSICIONES
        dot += "\t\t//Agregar Hash\n";
        dot += "LIST[label=\"";
        for (int i = 0; i < hash.length; i++) {
            contenido += i+")\\n\\n\\n\\n";
            if (i < hash.length - 1) {
                dot += "<N" + i + ">" + contenido + "|";
            } else {
                dot += "<N" + i + ">" + contenido;    
            }
            contenido = "";
        }
        dot += "\"];\n";
        dot += "\t\t//Agregar Usuarios\n";
        for (int i = 0; i < hash.length; i++) {
            if(hash[i] != null){
                dot += "N" + i + "[label=\"{";
                //recorrer lista
                NodoHash temporal;
                temporal = hash[i].getInicio();
                
                while(temporal != null){
                    if(temporal.siguiente != null){
                        dot += "Nombre: "+temporal.usuario+ " Password: "+temporal.password+" TimesTamp: "+temporal.timestamp+"|";  
                    }else{
                        dot += "Nombre: "+temporal.usuario+ " Password: "+temporal.password+" TimesTamp: "+temporal.timestamp;
                    }
                    temporal = temporal.siguiente;
                }
                
                dot += "}\"];\n";
            }
            
        }
        
        dot += "\t\t//Agregar Conexiones\n";
        
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != null) {
                dot+="LIST:N"+i+"->N"+i+";\n";
            }
        }
        return dot;
    }
    ////
    public void graficarHash(){
        try {
            //escribir dot
            FileWriter txtDot = new FileWriter("HASH.dot");
            PrintWriter pw = new PrintWriter(txtDot);
            pw.println("digraph HASH{\n");
            pw.println("node[shape=record];\n");
            pw.println("rankdir=LR;\n");
            pw.println(graficar());
            pw.println("}");
            txtDot.close();

            //compilar dot y generar imagen
            File miDir = new File(".");
            String ruta = miDir.getCanonicalPath() + "\\";//ruta actual
            String salida = "dot -Tpng HASH.dot -o HASH.png";
            Runtime rt = Runtime.getRuntime();
            rt.exec(salida);


        } catch (Exception e) {
            System.out.println("ERRORGRAFICARHASH");
            System.out.println(e);
        }
    }
    
    
}

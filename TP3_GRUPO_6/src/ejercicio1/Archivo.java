package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public class Archivo {
	
	private static final String Ruta = "Archivos/Personas.txt";
	
	 public static boolean archivoExiste(String rutaArchivo) {
	        File archivo = new File(rutaArchivo);
	        if(archivo.exists()) {
	        	return true;
	        }
	        return false;
	    }
	 
	 public static TreeSet<Persona> Guardar() {
		 TreeSet<Persona> ts = new TreeSet<Persona>();
		 FileReader entrada;
		 try {
			 
			 entrada = new FileReader(Ruta);
			 BufferedReader miBuffer = new BufferedReader(entrada);
			 String linea = "";
			 
			 while((linea = miBuffer.readLine()) != null) {
				 String[] bloques = linea.split("-");
				 
				 if(bloques.length == 3) {
					 String nombre = bloques[0];
					 String apellido = bloques[1];
					 String dni = bloques[2];
					 
					 try {
						 Persona.VerificarDniInvalido(dni);
						 Persona persona = new Persona();
						 
						 persona.setNombre(nombre);
						 persona.setApellido(apellido);
						 persona.setDni(dni);
						 
						 ts.add(persona);
						 
					 }catch(DniInvalido e) {
						 System.out.println(e.getMessage());
					 }
				 }
				 
				 
			 } 
			 miBuffer.close();
		 }catch(IOException e) {
			 System.out.println("Error al leer el Archivo");
			 e.printStackTrace();
		 }
		 
		 return ts;
	 }
	 
	 public static boolean creaArchivo(String ruta) {
		 FileWriter escritura;
		 try {
			 escritura = new FileWriter(ruta, false);
			 escritura.write("");
			 escritura.close();
			 return true;
			 }
		 catch (IOException e) {
			 e.printStackTrace();
		 }
		 return false;
	 }
	 
	 public static void escribirArchivo(String ruta, TreeSet<Persona> ts) {
		 try {
			 FileWriter entrada = new FileWriter(ruta, true);
			 BufferedWriter miBuffer = new BufferedWriter(entrada);
			 Iterator<Persona> it = ts.iterator();
			 while (it.hasNext()) {
				 Persona persona = it.next();
				 miBuffer.write(persona.getNombre() + '-' + persona.getApellido() + '-' + persona.getDni()+'\n');
			 }
			 miBuffer.close();
			 entrada.close();
			 }
		 catch (IOException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public static void leerArchivo(String ruta) {
		 FileReader entrada;
		 try {
			 entrada = new FileReader(ruta);
			 BufferedReader miBuffer = new BufferedReader(entrada);
			 String linea;
			 while((linea = miBuffer.readLine()) != null) {
				 System.out.println(linea);
			 }
			 miBuffer.close();
			 entrada.close();
		 }
		 catch (IOException e) {
			 e.printStackTrace();
		 }
	 }
}


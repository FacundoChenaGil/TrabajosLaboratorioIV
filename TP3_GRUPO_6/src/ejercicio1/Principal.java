package ejercicio1;

import java.util.Iterator;
import java.util.TreeSet;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 if (Archivo.archivoExiste("archivos/Personas.txt")) {
	            System.out.println(" El archivo existe.");
	        } else {
	            System.out.println(" El archivo no existe.");
	        }
		 TreeSet<Persona> ts = Archivo.Guardar();
		 
		 Iterator<Persona> it = ts.iterator();
		 while (it.hasNext()) {
			 Persona persona = it.next();
			 System.out.println(persona.toString());
		 }
	    }
	}



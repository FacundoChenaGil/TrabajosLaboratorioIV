package ejercicio1;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 if (Archivo.archivoExiste("archivos/Personas.txt")) {
	            System.out.println(" El archivo existe.");
	        } else {
	            System.out.println(" El archivo no existe.");
	        }
	    }

	}



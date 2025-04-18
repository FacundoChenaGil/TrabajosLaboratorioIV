package ejercicio1;

import java.io.File;

public class Archivo {
	
	
	 public static boolean archivoExiste(String rutaArchivo) {
	        File archivo = new File(rutaArchivo);
	        if(archivo.exists()) {
	        	return true;
	        }
	        return false;
	    }
	 
	
	public static void verificarDniInvalido (String dni) throws DniInvalido{
		Boolean hayCaracteres = false;
		for(int i=0; i<dni.length(); i++) {
			if(!Character.isDigit(dni.charAt(i))) {
				hayCaracteres = true;
			}
		}
		
		if(hayCaracteres) {
			throw new DniInvalido();
		}
	}
	
}

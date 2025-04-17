package ejercicio1;

public class Archivo {
	
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

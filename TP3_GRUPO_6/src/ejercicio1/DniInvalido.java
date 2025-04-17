package ejercicio1;

import java.io.IOException;
@SuppressWarnings("serial")

public class DniInvalido extends IOException{
	public DniInvalido() {
		
	}

	@Override
	public String getMessage() {
		return "El DNI ingresado no contiene solo caracteres númericos.";
	}
}


package ejercicio1;

import java.io.IOException;
@SuppressWarnings("serial")

// Constructor con mensaje personalizado
public class DniInvalido extends IOException {
    public DniInvalido(String mensaje) {
        super(mensaje);
    }
 // Constructor por defecto
    public DniInvalido() {
        super("DNI inválido.");
    }

}


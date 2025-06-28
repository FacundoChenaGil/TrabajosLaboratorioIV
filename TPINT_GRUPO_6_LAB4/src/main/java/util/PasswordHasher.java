package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    // Número de rondas de hashing (10-12 es un buen equilibrio entre seguridad y rendimiento)
    private static final int ROUNDS = 10;

    /**
     * Genera un hash seguro de una contraseña
     * @param password La contraseña en texto plano
     * @return El hash de la contraseña
     */
    public static String hashPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        return BCrypt.hashpw(password, BCrypt.gensalt(ROUNDS));
    }

    /**
     * Verifica si una contraseña coincide con un hash
     * @param password La contraseña en texto plano a verificar
     * @param hashedPassword El hash almacenado
     * @return true si la contraseña coincide, false en caso contrario
     */
    public static boolean checkPassword(String password, String hashedPassword) {
        if (password == null || hashedPassword == null || 
            password.trim().isEmpty() || hashedPassword.trim().isEmpty()) {
            return false;
        }
        return BCrypt.checkpw(password, hashedPassword);
    }
}

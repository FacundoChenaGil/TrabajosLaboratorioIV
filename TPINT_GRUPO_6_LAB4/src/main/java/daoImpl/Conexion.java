package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Convertimos la clase a 'final' y con constructor privado para que no se puedan crear instancias.
// Actuará como una clase de utilidad con métodos estáticos.
public final class Conexion {

    private static final String HOST = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "bancoutn?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    // Constructor privado para evitar que se creen objetos de esta clase.
    private Conexion() {}

    /**
     * Método estático que crea y devuelve una NUEVA conexión a la base de datos.
     * Quien llama a este método es responsable de cerrar la conexión.
     * @return Una nueva conexión a la base de datos.
     * @throws SQLException si ocurre un error al conectar.
     */
    public static Connection getConexion() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST + DB_NAME, USER, PASS);
            connection.setAutoCommit(false); // Bueno para manejar transacciones manualmente
        } catch (ClassNotFoundException e) {
            // Lanzamos una SQLException para que el código que llama sepa que algo salió mal.
            throw new SQLException("Error al cargar el driver de la base de datos", e);
        }
        return connection;
    }

    /**
     * Método de utilidad para cerrar una conexión de forma segura.
     * @param conn La conexión a cerrar.
     */
    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // Imprimimos el error, pero no detenemos el programa por un fallo al cerrar.
                e.printStackTrace();
            }
        }
    }
}

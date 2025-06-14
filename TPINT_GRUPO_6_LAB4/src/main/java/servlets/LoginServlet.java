package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet de Login para manejar la autenticación de usuarios.
 */
@WebServlet("/LoginServlet") // Esta anotación mapea el Servlet a la URL /LoginServlet
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Maneja las solicitudes POST para procesar el formulario de login.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Obtener los parámetros del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userRole = null;
        String errorMessage = null;

        // 2. Lógica de autenticación (HARDCODEADA por ahora)
        if ("admin".equals(username) && "admin".equals(password)) {
            userRole = "admin";
        } else if ("cliente".equals(username) && "cliente".equals(password)) {
            userRole = "cliente";
        } else {
            errorMessage = "Usuario o contraseña incorrectos.";
        }

        // 3. Manejo del resultado de la autenticación
        if (userRole != null) {
            // Autenticación exitosa
            HttpSession session = request.getSession(); // Obtener la sesión (o crear una nueva)
            session.setAttribute("userRole", userRole); // Guardar el rol del usuario en la sesión
            session.setAttribute("username", username); // Guardar el nombre de usuario

            // Redirigir al dashboard apropiado
            if ("admin".equals(userRole)) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
            } else if ("cliente".equals(userRole)) {
                response.sendRedirect(request.getContextPath() + "/clientes/dashboard.jsp");
            }
        } else {
            // Autenticación fallida
            request.setAttribute("loginError", errorMessage); // Guardar el mensaje de error en el Request
            // Reenviar al formulario de login para mostrar el error
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    /**
     * Maneja las solicitudes GET (si alguien intenta acceder al Servlet directamente por GET).
     * Podrías redirigirlos al formulario de login.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
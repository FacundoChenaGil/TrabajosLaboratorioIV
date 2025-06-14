package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet para cerrar la sesión de un usuario.
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la sesión actual (no crear una nueva si no existe)
        HttpSession session = request.getSession(false); 
        if (session != null) {
            session.invalidate(); // Invalidar la sesión
        }
        // Redirigir al usuario a la página de inicio o login
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    // Si alguien intenta hacer un POST al logout, también lo maneja igual
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
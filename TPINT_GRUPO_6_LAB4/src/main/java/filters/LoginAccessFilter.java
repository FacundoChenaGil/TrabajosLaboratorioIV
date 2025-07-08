package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Filtro que se ejecuta cuando un usuario accede a la página de login.
 * Si el usuario ya tiene una sesión activa, la invalida para forzar un nuevo inicio de sesión.
 */
@WebFilter("/login.jsp")
public class LoginAccessFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("****************************************");
        System.out.println("LoginAccessFilter: Filtro ejecutado para la URL: " + ((HttpServletRequest) request).getRequestURI());
        System.out.println("****************************************");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false); // No crear una nueva sesión si no existe

        // Si hay una sesión y el usuario está logueado (tiene un rol)
        if (session != null && session.getAttribute("userRole") != null) {
            // Invalidar la sesión existente
            session.invalidate();
            // Preparar un mensaje para notificar al usuario en la página de login
            request.setAttribute("sessionClosedMessage", "Tu sesión anterior ha sido cerrada.");
        }

        // Continuar con la cadena de filtros para que se muestre la página login.jsp
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        // Método de inicialización (opcional)
    }

    public void destroy() {
        // Método de destrucción (opcional)
    }
}

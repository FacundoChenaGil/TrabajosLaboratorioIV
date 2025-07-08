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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminAuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isAdmin = false;
        if (session != null && "administrador".equals(session.getAttribute("userRole"))) {
            isAdmin = true;
        }

        if (isAdmin) {
            // Si es admin, permite que la petición continúe hacia el recurso solicitado (JSP, Servlet, etc.)
            chain.doFilter(request, response);
        } else {
            // Si no es admin, redirige a la página de login con un mensaje de error.
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp?error=notAuthorizedAdmin");
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {}

    public void destroy() {}
}

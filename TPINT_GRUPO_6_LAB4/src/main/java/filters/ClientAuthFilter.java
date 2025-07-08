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

@WebFilter("/clientes/*")
public class ClientAuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isClient = false;
        if (session != null && "cliente".equals(session.getAttribute("userRole"))) {
            isClient = true;
        }

        if (isClient) {
            // Si es cliente, permite que la petición continúe.
            chain.doFilter(request, response);
        } else {
            // Si no es cliente, redirige a la página de login con un mensaje de error.
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp?error=notAuthorizedClient");
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {}

    public void destroy() {}
}

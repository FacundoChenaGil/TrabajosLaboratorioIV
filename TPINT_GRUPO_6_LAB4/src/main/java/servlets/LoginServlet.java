package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocioImpl.ClienteNegocioImpl;
import entidad.Cliente;
import entidad.TipoUsuario;
import util.PasswordHasher;

// Importar las clases de javax.servlet.* para resolver los errores de HttpSession
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet de Login para manejar la autenticación de usuarios.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
    private ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl(); // NUEVO

    public LoginServlet() {
        super();
    }

    /**
     * Maneja las solicitudes POST para procesar el formulario de login.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMessage = null;

        // 1. Validar campos vacíos
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            errorMessage = "Por favor ingrese usuario y contraseña.";
            request.setAttribute("loginError", errorMessage);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        try {
            // 2. Buscar usuario en la base de datos
            Usuario usuario = usuarioDao.getUsuarioPorNombre(username);
            
            // 3. Validar credenciales
            if (usuario == null || !PasswordHasher.checkPassword(password, usuario.getClave())) {
                errorMessage = "Usuario o contraseña incorrectos.";
                request.setAttribute("loginError", errorMessage);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            if (!usuario.isActivo()) {
                errorMessage = "Su cuenta esta inhabilitada.";
                request.setAttribute("loginError", errorMessage);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            
            // 4. Obtener el rol del usuario
            String userRole = usuario.getTipoUsuario().getDescripcion().toLowerCase();

            // 5. Configurar la sesión
            HttpSession session = request.getSession(); // Crea una nueva sesión limpia
            session.setAttribute("usuario", usuario);
            session.setAttribute("username", usuario.getUsuario());
            session.setAttribute("userRole", userRole);
            
            if (userRole.equals("cliente")) {
                Cliente cliente = clienteNegocio.obtenerClientePorUsuario(username);
                session.setAttribute("dniCliente", cliente.getDni());
            }
            
            // 6. Redirigir según el rol
            String redirectPath = userRole.equals("administrador") 
                                ? "/admin/menuAdministrador.jsp" 
                                : "/clientes/miCuenta.jsp";
            
            response.sendRedirect(request.getContextPath() + redirectPath);
            
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = "Error en el sistema. Por favor intente más tarde.";
            request.setAttribute("loginError", errorMessage);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
    
    
    
    

    /**
     * Maneja las solicitudes GET redirigiendo al formulario de login.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Si ya está autenticado, redirigir al dashboard correspondiente
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            String userRole = (String) session.getAttribute("userRole");
            String redirectPath = "administrador".equals(userRole) ? 
                                "/admin/dashboard.jsp" : 
                                "/clientes/dashboard.jsp";
            response.sendRedirect(request.getContextPath() + redirectPath);
        } else {
            // Si no está autenticado, mostrar el formulario de login
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
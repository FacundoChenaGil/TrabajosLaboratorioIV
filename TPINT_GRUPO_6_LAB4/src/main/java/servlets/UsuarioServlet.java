package servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import negocio.IUsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;
import negocio.IClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IUsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
	IClienteNegocio clienteNegocio = new ClienteNegocioImpl();
  
    public UsuarioServlet(IUsuarioNegocio usuarioNegocio) {
		this.usuarioNegocio = usuarioNegocio;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombreUsuario = request.getParameter("nombreUsuario");
		
		
		if(nombreUsuario != null) {
	    	Usuario usuario = new Usuario();
	    	usuario = usuarioNegocio.getUsuarioPorNombre(nombreUsuario);
	    	
	    	request.setAttribute("usuario", usuario);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/cambiarContraseña.jsp");
	        dispatcher.forward(request, response);
	    }
	    else {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetro incorrecto o no recibido.");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario = request.getParameter("nombreUsuario");
		
		
		if(nombreUsuario != null) {
	    	Usuario usuario = new Usuario();
	    	usuario = usuarioNegocio.getUsuarioPorNombre(nombreUsuario);
	    	
	    	String clave = request.getParameter("new-password");
	    	String claveAValidar = request.getParameter("confirm-password");
	    	
	    	if(!clave.equals(claveAValidar)) {
	    		request.setAttribute("mensajeError", "Las claves no coinciden.");
	    		request.setAttribute("usuario", usuario);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/cambiarContraseña.jsp");
				dispatcher.forward(request, response);
				return;
	    		
	    	}
	    	
	    	usuario.setClave(clave);
	    	
	    	boolean actualizada = usuarioNegocio.modificarClave(usuario);
	    	
	    	if(actualizada) {
	    		request.setAttribute("mensajeExito", "La clave se actualizó correctamente.");
	    		request.setAttribute("usuario", usuario);
	    		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/cambiarContraseña.jsp");
	    		dispatcher.forward(request, response);
	    	} else {
				request.setAttribute("mensajeError", "No se pudo actualizar la clave.");
				request.setAttribute("usuario", usuario);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/cambiarContraseña.jsp");
				dispatcher.forward(request, response);
			}
	    }
	    else {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetro incorrecto o no recibido.");
	    }
	}

}

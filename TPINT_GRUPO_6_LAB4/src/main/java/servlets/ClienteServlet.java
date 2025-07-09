package servlets;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.CuentaPrestamoddlDTO;
import entidad.TipoUsuario;
import entidad.TiposDeCuentas;
import entidad.Usuario;
import negocio.IClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import util.PasswordHasher;
import negocio.IUsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;


@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IClienteNegocio clienteNegocio = new ClienteNegocioImpl();
	IUsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();

	public ClienteServlet(IClienteNegocio clienteNegocio, IUsuarioNegocio usuarioNegocio) {
		this.clienteNegocio = clienteNegocio;
		this.usuarioNegocio = usuarioNegocio;
	}

	public ClienteServlet() {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");

		if ("alta".equals(accion)) {
			try {
				HttpSession sessionClientes = request.getSession();

		        // Crear Cliente
		        Cliente cliente = new Cliente();
		        cliente.setDni(request.getParameter("dni"));
		        cliente.setCuil(request.getParameter("cuil"));
		        cliente.setNombre(request.getParameter("nombre"));
		        cliente.setApellido(request.getParameter("apellido"));
		        cliente.setSexo(request.getParameter("sexo"));
		        cliente.setNacionalidad(request.getParameter("nacionalidad"));
		        cliente.setFechaNacimiento(LocalDate.parse(request.getParameter("fecha")));
		        cliente.setDireccion(request.getParameter("direccion"));
		        cliente.setLocalidad(request.getParameter("localidad"));
		        cliente.setProvincia(request.getParameter("provincia"));
		        cliente.setCorreoElectronico(request.getParameter("email"));
		        cliente.setTelefono(request.getParameter("telefono"));

		        // Crear Usuario
				Usuario usuario = new Usuario();
				
				usuario.setUsuario(request.getParameter("username"));
				
				usuario.setClave(request.getParameter("password"));
				
			
				
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setIdTipoUsuario(2); // 2 = Cliente
				usuario.setTipoUsuario(tipoUsuario);
				
				cliente.setUsuario(usuario);

		        // Validaciones
		        if (clienteNegocio.existeClienteActivo(cliente.getDni())) {
		            sessionClientes.setAttribute("mensajeError", "Ya existe un cliente activo con ese DNI.");
		            sessionClientes.setAttribute("clienteForm", cliente);
		            response.sendRedirect("admin/altaCliente.jsp");
		            return;
		        }

		        if (!cliente.getCuil().contains(cliente.getDni())) {
		            sessionClientes.setAttribute("mensajeError", "El CUIL no contiene el DNI.");
		            sessionClientes.setAttribute("clienteForm", cliente);
		            response.sendRedirect("admin/altaCliente.jsp");
		            return;
		        }

		        if (clienteNegocio.existeCUIL(cliente.getCuil())) {
		            sessionClientes.setAttribute("mensajeError", "El CUIL ya está en uso.");
		            sessionClientes.setAttribute("clienteForm", cliente);
		            response.sendRedirect("admin/altaCliente.jsp");
		            return;
		        }

		        if (usuarioNegocio.existeUsuario(cliente.getUsuario().getUsuario())) {
		            sessionClientes.setAttribute("mensajeError", "El nombre de usuario ya está en uso.");
		            sessionClientes.setAttribute("clienteForm", cliente);
		            response.sendRedirect("admin/altaCliente.jsp");
		            return;
		        }

		        if (clienteNegocio.existeCorreoElectronico(cliente.getCorreoElectronico())) {
		            sessionClientes.setAttribute("mensajeError", "El correo electrónico ya está registrado.");
		            sessionClientes.setAttribute("clienteForm", cliente);
		            response.sendRedirect("admin/altaCliente.jsp");
		            return;
		        }

		        // Registro
		        String claveHasheada = PasswordHasher.hashPassword(request.getParameter("password"));
		        usuario.setClave(claveHasheada);
		        cliente.setUsuario(usuario);
		        boolean exito = clienteNegocio.registrarCliente(cliente);
		        if (exito) {
		            sessionClientes.setAttribute("mensajeExito", "Cliente registrado correctamente.");
		        } else {
		            sessionClientes.setAttribute("mensajeError", "No se pudo registrar el cliente.");
		            sessionClientes.setAttribute("clienteForm", cliente);
		        }

		        response.sendRedirect("admin/altaCliente.jsp");

		    } catch (Exception e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("mensajeError", "Ocurrió un error inesperado.");
		        response.sendRedirect("admin/altaCliente.jsp");
		    }
		} 
		else if("modificar".equals(accion)) {
			
			// Obtener parámetros del formulario
			String dni = request.getParameter("dni");
			String cuil = request.getParameter("cuil");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String genero = request.getParameter("genero");
			String telefono = request.getParameter("telefono");
			String fechaNacimientoStr = request.getParameter("fecha");
			String nacionalidad = request.getParameter("nacionalidad");
			String provincia = request.getParameter("provincia");
			String localidad = request.getParameter("localidad");
			String direccion = request.getParameter("direccion");
			String email = request.getParameter("email");
			String estadoStr = request.getParameter("estado");
			
			String emailOriginal = request.getParameter("emailOriginal");
			
			// Convertir y validar datos
			LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
			boolean activa = "1".equals(estadoStr);
			
			// Crear objeto Cliente
			Cliente cliente = new Cliente();
			cliente.setDni(dni);
			cliente.setCuil(cuil);
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setSexo(genero);
			cliente.setTelefono(telefono);
			cliente.setFechaNacimiento(fechaNacimiento);
			cliente.setNacionalidad(nacionalidad);
			cliente.setProvincia(provincia);
			cliente.setLocalidad(localidad);
			cliente.setDireccion(direccion);
			cliente.setCorreoElectronico(email);
			cliente.setActivo(activa);
			
			if(!email.equals(emailOriginal)) {
				if(clienteNegocio.existeCorreoElectronico(email)) {
					request.setAttribute("mensajeError", "El email ingresado no existe.");
					request.setAttribute("cliente", cliente);
					request.setAttribute("listaClientes", clienteNegocio.obtenerClientes());
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/modificarCliente.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
			
			int actualizado = clienteNegocio.modificarCliente(cliente);
			
			if (actualizado == 1) {
				request.setAttribute("mensajeExito", "El cliente fue modificado correctamente.");
				response.sendRedirect("ClienteServlet?Param=mostrarClientes");
			} else {
				request.setAttribute("mensajeError", "No se pudo actualizar el cliente.");
				request.setAttribute("cliente", cliente);
				request.setAttribute("listaClientes", clienteNegocio.obtenerClientes());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/modificarCliente.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if ("eliminar".equals(accion)) {
		    String dni = request.getParameter("dni");
		    boolean eliminado = clienteNegocio.eliminarCliente(dni);

		    response.sendRedirect(request.getContextPath() + "/ClienteServlet?Param=mostrarClientes");
		}
		else {
			request.setAttribute("error", "Acción no válida.");
			request.getRequestDispatcher("admin/gestionDeClientes.jsp").forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String param = request.getParameter("Param");
	    String dni = request.getParameter("dni");
	    
	    if("mostrarClientes".equals(param)) {
	        List<Cliente> listaClientes = clienteNegocio.obtenerClientes();

	        request.setAttribute("listaClientes", listaClientes);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/gestionDeClientes.jsp");
	        dispatcher.forward(request, response);
	    }
	    else if(dni != null) {
	    	Cliente cliente = clienteNegocio.obtenerClientePorDni(dni);
	    	
	    	request.setAttribute("cliente", cliente);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/modificarCliente.jsp");
	        dispatcher.forward(request, response);
	    	
	    }
	    else {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetro incorrecto o no recibido.");
	    }
	}
	
}
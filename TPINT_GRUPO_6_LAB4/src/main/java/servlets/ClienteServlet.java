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
				
				String claveHasheada = PasswordHasher.hashPassword(request.getParameter("password"));
				
				usuario.setClave(claveHasheada);
				
				
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setIdTipoUsuario(2); // 2 = Cliente
				usuario.setTipoUsuario(tipoUsuario);
				
				
				//boolean exitoUsuario = usuarioNegocio.altaUsuario(usuario);
				cliente.setUsuario(usuario);

				// Negocio
				

				boolean exito = clienteNegocio.registrarCliente(cliente);

				if (exito) {
					response.sendRedirect("admin/gestionDeClientes.jsp");
				} else {
					request.setAttribute("error", "El DNI, correo o usuario ya existen.");
					request.getRequestDispatcher("admin/altaCliente.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();

				request.setAttribute("error", "Ocurrió un error inesperado al procesar el alta.");
				request.getRequestDispatcher("admin/altaCliente.jsp").forward(request, response);
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
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		else {
			request.setAttribute("error", "Acción no válida.");
			request.getRequestDispatcher("admin/altaCliente.jsp").forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("➡️ Entró a doGet");
	    String param = request.getParameter("Param");

	    if("mostrarClientes".equals(param)) {
	        List<Cliente> listaClientes = clienteNegocio.obtenerClientes();

	        request.setAttribute("listaClientes", listaClientes);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/gestionDeClientes.jsp");
	        dispatcher.forward(request, response);
	    }
	    else {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetro incorrecto o no recibido.");
	    }
	}
	
}
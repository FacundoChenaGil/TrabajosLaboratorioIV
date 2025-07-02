package servlets;


import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.TipoUsuario;
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

		System.out.println("📩 [Servlet] Entró al ClienteServlet por POST");
		String accion = request.getParameter("accion");
		System.out.println("🧭 Acción recibida: " + accion);

		if ("alta".equals(accion)) {
			try {
				System.out.println("➡️ [Alta Cliente] Comenzando carga de datos...");

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

				System.out.println("📦 [Alta Cliente] Datos del cliente listos.");

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

				System.out.println("🔐 [Alta Cliente] Datos del usuario listos.");
				System.out.println("🔍 Contraseña ingresada: " + request.getParameter("password"));

				// Negocio
				
				System.out.println("📡 [Alta Cliente] Llamando a clienteNegocio.registrarCliente...");

				boolean exito = clienteNegocio.registrarCliente(cliente);

				if (exito) {
					System.out.println("✅ [Alta Cliente] Cliente registrado correctamente.");
					response.sendRedirect("admin/gestionDeClientes.jsp");
				} else {
					System.out.println("⚠️ [Alta Cliente] Falló el alta del cliente (cliente ya existe o error interno).");
					request.setAttribute("error", "El DNI, correo o usuario ya existen.");
					request.getRequestDispatcher("admin/altaCliente.jsp").forward(request, response);
				}
			} catch (Exception e) {
				System.out.println("❌ [Alta Cliente] Excepción al intentar registrar cliente: " + e.getMessage());
				e.printStackTrace();

				request.setAttribute("error", "Ocurrió un error inesperado al procesar el alta.");
				request.getRequestDispatcher("admin/altaCliente.jsp").forward(request, response);
			}
		} else {
			System.out.println("🚫 [ClienteServlet] Acción no reconocida: " + accion);
			request.setAttribute("error", "Acción no válida.");
			request.getRequestDispatcher("admin/altaCliente.jsp").forward(request, response);
		}
	}
}
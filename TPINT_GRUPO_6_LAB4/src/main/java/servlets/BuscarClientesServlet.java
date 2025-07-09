package servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import negocio.IClienteNegocio;
import negocioImpl.ClienteNegocioImpl;


@WebServlet("/BuscarClientesServlet")
public class BuscarClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BuscarClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private IClienteNegocio clienteNegocio = new ClienteNegocioImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String filtro = request.getParameter("filtro");

			List<Cliente> clientes = null;

			if (filtro != null && !filtro.trim().isEmpty()) {
				clientes = clienteNegocio.buscarClientesPorNombreApellidoUsuario(filtro.trim());
			}

			request.setAttribute("filtro", filtro);
			request.setAttribute("clientes", clientes);
			
			
			// Paginación
			int pagina = 1;
			int registrosPorPagina = 5;

			if (request.getParameter("page") != null) {
				try {
					pagina = Integer.parseInt(request.getParameter("page"));
				} catch (NumberFormatException e) {
					pagina = 1;
				}
			}

			try {
				IClienteNegocio clienteNegocio = new ClienteNegocioImpl();
				List<Cliente> todos = clienteNegocio.obtenerTodosLosClientes();

				int totalRegistros = todos.size();
				int totalPaginas = (int) Math.ceil(totalRegistros / (double) registrosPorPagina);

				int inicio = (pagina - 1) * registrosPorPagina;
				int fin = Math.min(inicio + registrosPorPagina, totalRegistros);

				List<Cliente> paginaActual = todos.subList(inicio, fin);

				request.setAttribute("clientes", paginaActual);
				request.setAttribute("paginaActual", pagina);
				request.setAttribute("totalPaginas", totalPaginas);
				request.setAttribute("cantidadClientes", totalRegistros);

			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "No se pudo cargar el listado de clientes.");
			}


							
			
			
			request.getRequestDispatcher("/admin/listadoClientes.jsp").forward(request, response);
		}
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

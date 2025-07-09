package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import negocio.IClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ReporteClientesNuevos")
public class ReporteClientesNuevosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReporteClientesNuevosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaFinStr = request.getParameter("fechaFin");

        int pagina = 1;
        int registrosPorPagina = 5;

        try {
            // Validar fechas y existencia
            if (fechaInicioStr != null && fechaFinStr != null &&
                !fechaInicioStr.isEmpty() && !fechaFinStr.isEmpty()) {

                if (request.getParameter("page") != null) {
                    pagina = Integer.parseInt(request.getParameter("page"));
                }

                // Parsear fechas
                LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
                LocalDate fechaFin = LocalDate.parse(fechaFinStr);

                // Obtener lista total de clientes nuevos en ese rango
                IClienteNegocio clienteNegocio = new ClienteNegocioImpl();
                List<Cliente> todos = clienteNegocio.obtenerClientesNuevosEntreFechas(fechaInicio, fechaFin);

                // Paginación
                int totalRegistros = todos.size();
                int totalPaginas = (int) Math.ceil(totalRegistros / (double) registrosPorPagina);

                int inicio = (pagina - 1) * registrosPorPagina;
                int fin = Math.min(inicio + registrosPorPagina, totalRegistros);

                List<Cliente> paginaActual = todos.subList(inicio, fin);

                // Enviar datos al JSP
                request.setAttribute("clientesNuevos", paginaActual);
                request.setAttribute("cantidadClientes", totalRegistros);
                request.setAttribute("paginaActual", pagina);
                request.setAttribute("totalPaginas", totalPaginas);
            }

            // Siempre reenviamos las fechas (para el formulario y los botones de paginación)
            request.setAttribute("fechaInicio", fechaInicioStr);
            request.setAttribute("fechaFin", fechaFinStr);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Hubo un problema al generar el reporte.");
        }

        // Redireccionar al JSP
        request.getRequestDispatcher("admin/reporteClientesNuevos.jsp").forward(request, response);
    }
		

		
		

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

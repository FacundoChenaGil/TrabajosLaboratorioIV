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
	    
	    	   
	    try {
	        if (fechaInicioStr != null && fechaFinStr != null && 
	            !fechaInicioStr.isEmpty() && !fechaFinStr.isEmpty()) {
	            
	            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
	            LocalDate fechaFin = LocalDate.parse(fechaFinStr);

	            IClienteNegocio clienteNegocio = new ClienteNegocioImpl();
	            List<Cliente> clientesNuevos = clienteNegocio.obtenerClientesNuevosEntreFechas(fechaInicio, fechaFin);

	            request.setAttribute("clientesNuevos", clientesNuevos);
	            request.setAttribute("cantidadClientes", clientesNuevos.size());
	            request.setAttribute("fechaInicio", fechaInicioStr);
	            request.setAttribute("fechaFin", fechaFinStr);
	           
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        request.setAttribute("error", "Hubo un problema al generar el reporte.");
	    }

	    request.getRequestDispatcher("admin/reporteClientesNuevos.jsp").forward(request, response);
	}
		
		

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

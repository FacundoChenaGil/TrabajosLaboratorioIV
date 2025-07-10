package servlets;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import negocio.IClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ClientesSaldoElevadoServlet")
public class ClientesSaldoElevadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ClientesSaldoElevadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    String filtroNombre = request.getParameter("busqueda");
		    String saldoMinStr = request.getParameter("saldoMinimo");

		    BigDecimal saldoMinimo = BigDecimal.ZERO;
		    if (saldoMinStr != null && !saldoMinStr.trim().isEmpty()) {
		        try {
		            saldoMinimo = new BigDecimal(saldoMinStr);
		        } catch (NumberFormatException e) {
		            saldoMinimo = BigDecimal.ZERO;
		        }
		    }

		    String pageParam = request.getParameter("page");
		    int page = 1;
		    int registrosPorPagina = 5;

		    if (pageParam != null && !pageParam.isEmpty()) {
		        try {
		            page = Integer.parseInt(pageParam);
		        } catch (NumberFormatException e) {
		            page = 1;
		        }
		    }

		    int offset = (page - 1) * registrosPorPagina;

		    IClienteNegocio clienteNegocio = new ClienteNegocioImpl();

		    // Lista paginada
		    List<Cliente> clientes = clienteNegocio.obtenerClientesConSaldoMinimo(saldoMinimo, filtroNombre, offset, registrosPorPagina);

		    //  Total de resultados para saber si hay más páginas
		    int totalRegistros = clienteNegocio.contarClientesConSaldoMinimo(saldoMinimo, filtroNombre);
		    int totalPaginas = (int) Math.ceil((double) totalRegistros / registrosPorPagina);

		    // Enviamos datos al JSP
		    request.setAttribute("clientes", clientes);
		    request.setAttribute("page", page);
		    request.setAttribute("totalPaginas", totalPaginas);
		    request.setAttribute("busqueda", filtroNombre);
		    request.setAttribute("saldoMinimo", saldoMinStr);

		    request.getRequestDispatcher("/admin/reporteClientesSaldosElevados.jsp").forward(request, response);
		}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import negocio.ICuentaNegocio;
import negocioImpl.CuentaNegocioImpl;


@WebServlet("/ReporteSaldoNegativo")
public class ReporteSaldosNegativosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
    public ReporteSaldosNegativosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ICuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
        List<Cliente> todosLosClientes = cuentaNegocio.obtenerClientesConSaldoNegativo();

       
        int registrosPorPagina = 5;

        // Obtener el número de página desde la request
        int paginaActual = 1;
        String paginaParam = request.getParameter("page");
        if (paginaParam != null) {
            try {
                paginaActual = Integer.parseInt(paginaParam);
            } catch (NumberFormatException e) {
                paginaActual = 1;
            }
        }

        // Cálculo de límites para sublista
        int totalClientes = todosLosClientes.size();
        int inicio = (paginaActual - 1) * registrosPorPagina;
        int fin = Math.min(inicio + registrosPorPagina, totalClientes);

        // Obtener sublista paginada
        List<Cliente> clientesPaginados = todosLosClientes.subList(inicio, fin);

        // Calcular total de páginas
        int totalPaginas = (int) Math.ceil((double) totalClientes / registrosPorPagina);

        // Enviar datos al JSP
        request.setAttribute("clientesNegativos", clientesPaginados);
        request.setAttribute("paginaActual", paginaActual);
        request.setAttribute("totalPaginas", totalPaginas);

        request.getRequestDispatcher("/admin/reporteSaldosNegativos.jsp").forward(request, response);
    }


		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

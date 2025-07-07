package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidad.Cuota;
import entidad.Prestamo;
import negocio.ICuotaNegocio;
import negocioImpl.CuotaNegocioImpl;
import negocio.IPrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

@WebServlet("/PrestamoServlet")
public class PrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrestamoServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPrestamoS = request.getParameter("idPrestamo");
		String pageParam = request.getParameter("page");

		String dni = (String) request.getSession().getAttribute("dniCliente");
		IPrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
		List<Prestamo> prestamos = prestamoNegocio.obtenerPrestamosPorDni(dni);
		request.setAttribute("prestamos", prestamos); // para mantener el combo cargado

		// Validar que se haya seleccionado un préstamo válido
		if (idPrestamoS == null || idPrestamoS.isEmpty() || idPrestamoS.equals("-1")) {
			request.setAttribute("error", "Debe seleccionar un préstamo.");
			request.getRequestDispatcher("clientes/historialPagodePrestamos.jsp").forward(request, response);
			return;
		}

		int idPrestamo;
		try {
		    idPrestamo = Integer.parseInt(idPrestamoS);
		} catch (NumberFormatException e) {
		    request.setAttribute("error", "El ID de préstamo no es válido.");
		    request.getRequestDispatcher("clientes/historialPagodePrestamos.jsp").forward(request, response);
		    return;
		}
		
		
		ICuotaNegocio cuotaNegocio = new CuotaNegocioImpl();
		List<Cuota> cuotasPagadas = cuotaNegocio.listarHistorialPagos(idPrestamo);

		int pageSize = 5;
		int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
		int fromIndex = (page - 1) * pageSize;
		int toIndex = Math.min(fromIndex + pageSize, cuotasPagadas.size());

		List<Cuota> cuotasPaginadas = cuotasPagadas.subList(fromIndex, toIndex);

		request.setAttribute("cuotas", cuotasPaginadas);
		request.setAttribute("totalCuotas", cuotasPagadas.size());
		request.setAttribute("paginaActual", page);
		request.setAttribute("tamanoPagina", pageSize);
		request.setAttribute("idPrestamoSeleccionado", idPrestamo);

		System.out.println("Mostrando cuotas de la página: " + page); // Borrar prueba

		request.getRequestDispatcher("clientes/historialPagodePrestamos.jsp").forward(request, response);
	}

}

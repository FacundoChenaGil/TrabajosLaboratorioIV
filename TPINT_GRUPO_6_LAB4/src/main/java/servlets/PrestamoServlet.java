package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import negocio.ICuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocio.IClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocio.ITipoEstadoPrestamoNegocio;
import negocioImpl.TipoEstadoPrestamoNegocioImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoEstadoPrestamo;

@WebServlet("/PrestamoServlet")
public class PrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IPrestamoNegocio negocioPrestamo = new PrestamoNegocioImpl();
	private ICuentaNegocio negocioCuenta = new CuentaNegocioImpl();
	private IClienteNegocio negocioCliente = new ClienteNegocioImpl();
	private ITipoEstadoPrestamoNegocio negocioTs = new TipoEstadoPrestamoNegocioImpl();
	

	public PrestamoServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if("mostrar".equals(accion)) {
			String cbu = request.getParameter("cuentaSeleccionada");
	        String montoStr = request.getParameter("Monto_Solicitar");
	        String cuotasStr = request.getParameter("Cantidad_Cuotas");

	        if (cbu == null || montoStr == null || cuotasStr == null) {
	            response.sendRedirect("clientes/solicitarPrestamo.jsp");
	            return;
	        }

	        BigDecimal montoSolicitado = new BigDecimal(montoStr);
	        BigDecimal cantCuotas = new BigDecimal(cuotasStr);
	        
	        LocalDateTime fechaSolicitud = LocalDateTime.now();
	        BigDecimal importeAPagar = negocioPrestamo.calcularImporteAPagar(montoSolicitado);
	        BigDecimal importeCuota = negocioPrestamo.calcularImporteCuota(cantCuotas, importeAPagar);
	        LocalDate fechaFin = fechaSolicitud.toLocalDate().plusMonths(cantCuotas.intValue());
	        
	        request.setAttribute("cbu", cbu);
	        request.setAttribute("montoSolicitado", montoSolicitado);
	        request.setAttribute("importeAPagar", importeAPagar);
	        request.setAttribute("importeCuota", importeCuota);
	        request.setAttribute("cantCuotas", cantCuotas.intValue());
	        request.setAttribute("fechaSolicitud", fechaSolicitud.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
	        request.setAttribute("fechaFin", fechaFin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	        
	        Cliente cliente = negocioCliente.obtenerClientePorUsuario((String) request.getSession().getAttribute("username"));
	        Cuenta cuenta = negocioCuenta.read(cbu);
	        TipoEstadoPrestamo ts = negocioTs.read(1);
	        
	        Prestamo prestamo = new Prestamo();
	        
	        prestamo.setCliente(cliente);
	        prestamo.setCuentaAcreditada(cuenta);
	        prestamo.setTipoEstadoPrestamo(ts);
	        prestamo.setCantidadCuotas(cantCuotas.intValue());
	        prestamo.setFechaSolicitud(fechaSolicitud);
	        prestamo.setImportePedido(montoSolicitado);
	        prestamo.setImporteAPagar(importeAPagar);
	        prestamo.setImporteCuota(importeCuota);
	        
	        request.getSession().setAttribute("prestamoPendiente", prestamo);
	        request.getSession().setAttribute("fechaFinPrestamo", fechaFin);

	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/confirmarPrestamo.jsp");
	        dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idPrestamoS = request.getParameter("idPrestamo");
		String pageParam = request.getParameter("page");
		String accion = request.getParameter("accion");
		
		if("alta".equals(accion)) {
			Prestamo prestamoPendiente = (Prestamo) request.getSession().getAttribute("prestamoPendiente");
			
			if (prestamoPendiente != null) {
				
				if (prestamoPendiente.getImportePedido() == null || 
			            prestamoPendiente.getImportePedido().compareTo(BigDecimal.ZERO) <= 0) {
			            request.setAttribute("mensajeError", "El importe solicitado no puede ser 0 o negativo.");
			            request.getRequestDispatcher("clientes/confirmarPrestamo.jsp").forward(request, response);
			            return;
			    }
				
	            boolean exito = negocioPrestamo.agregarPrestamo(prestamoPendiente);

	            if (exito) {
	                request.getSession().removeAttribute("prestamoPendiente");
	                request.setAttribute("mensajeExito", "Préstamo solicitado correctamente.");
	                request.getSession().setAttribute("prestamoPendiente", prestamoPendiente);
	            } else {
	                request.setAttribute("mensajeError", "Ocurrió un error al procesar el préstamo. Intente nuevamente.");
	            }

	            // Siempre volver a la misma página de confirmación, con el resultado
	            request.getRequestDispatcher("clientes/confirmarPrestamo.jsp").forward(request, response);
	            return; // IMPORTANTE: detenemos el flujo para no ejecutar el resto
	        } else {
	            // Si no hay un préstamo en sesión, redirigimos a la solicitud inicial
	            response.sendRedirect("clientes/solicitarPrestamo.jsp");
	            return;
	        }
		}

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

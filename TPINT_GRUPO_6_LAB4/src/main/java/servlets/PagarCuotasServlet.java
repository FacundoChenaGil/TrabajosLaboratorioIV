package servlets;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Cuota;
import excepciones.SaldoInsuficienteExcepcion;
import negocio.ICuotaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.CuotaNegocioImpl;
import negocio.ICuentaNegocio;
import entidad.Prestamo;
import negocio.IPrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;


@WebServlet("/PagarCuotasServlet")
public class PagarCuotasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PagarCuotasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String cuotaIdParam = request.getParameter("cuotaId");
	    String numeroCuenta = request.getParameter("cuentaId");

	    if (cuotaIdParam != null && numeroCuenta != null && !cuotaIdParam.isEmpty() && !numeroCuenta.isEmpty()) {
	        try {
	            int cuotaId = Integer.parseInt(cuotaIdParam);
	            ICuotaNegocio cuotaNegocio = new CuotaNegocioImpl();

	            boolean pagoExitoso = cuotaNegocio.pagarCuota(cuotaId, numeroCuenta);

	            if (pagoExitoso) {
	                request.setAttribute("mensaje", "✅ ¡Pago realizado con éxito!");
	            } else {
	                request.setAttribute("mensaje", "❌ Error: No se pudo procesar el pago. Verifique que la cuota no esté ya pagada.");
	            }
	        } catch (SaldoInsuficienteExcepcion e) {
	        	request.setAttribute("mensaje", "❌ Error: " + e.getMessage());
	        } catch (NumberFormatException e) {
	            request.setAttribute("mensaje", "Error: El ID de la cuota no es válido.");
	        }
	    } else {
	        request.setAttribute("mensaje", "Error: Faltan datos para procesar el pago.");
	    }

	    // Recargar la información necesaria para la JSP, independientemente del resultado
	    String dni = (String) request.getSession().getAttribute("dniCliente");
	    if (dni != null) {
	        IPrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
	        ICuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	        
	        List<Prestamo> prestamos = prestamoNegocio.obtenerPrestamosConCuotasPendientesPorDni(dni);
	        List<Cuenta> cuentas = cuentaNegocio.obtenerCuentasPorDni(dni);
	        
	        request.setAttribute("prestamos", prestamos);
	        request.setAttribute("cuentas", cuentas);
	    }

	    request.getRequestDispatcher("clientes/pagarPrestamo.jsp").forward(request, response);
	}
	

}

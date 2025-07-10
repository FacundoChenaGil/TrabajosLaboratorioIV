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

	    if (cuotaIdParam == null || numeroCuenta == null || cuotaIdParam.isEmpty() || numeroCuenta.isEmpty()) {
	        request.setAttribute("mensaje", "Datos incompletos para procesar el pago.");
	        request.getRequestDispatcher("clientes/pagarPrestamo.jsp").forward(request, response);
	        return;
	    }

	    int cuotaId = Integer.parseInt(cuotaIdParam);

	    ICuotaNegocio cuotaNegocio = new CuotaNegocioImpl();
	    ICuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	    Cuenta cuenta = cuentaNegocio.obtenerPorNumero(numeroCuenta);
	    Cuota cuota = cuotaNegocio.obtenerPorId(cuotaId);

	    if (cuota == null || cuenta == null) {
	        request.setAttribute("mensaje", "No se encontró la cuota o cuenta seleccionada.");
	        request.getRequestDispatcher("clientes/pagarPrestamo.jsp").forward(request, response);
	        return;
	    }

	    BigDecimal saldoActual = cuenta.getSaldo();
	    BigDecimal montoCuota = cuota.getImporte();

	    if (saldoActual.compareTo(montoCuota) < 0) {
	        request.setAttribute("mensaje", "Saldo insuficiente para realizar el pago.");
	        request.getRequestDispatcher("clientes/pagarPrestamo.jsp").forward(request, response);
	        return;
	    }

	    // Descontar el saldo
	    BigDecimal nuevoSaldo = saldoActual.subtract(montoCuota);
	    boolean saldoActualizado = cuentaNegocio.actualizarSaldoPorNumeroCuenta(numeroCuenta, nuevoSaldo);

	    if (!saldoActualizado) {
	        request.setAttribute("mensaje", "Error al actualizar el saldo.");
	        request.getRequestDispatcher("clientes/pagarPrestamo.jsp").forward(request, response);
	        return;
	    }

	    // Marcar cuota como pagada
	    boolean cuotaPagada = cuotaNegocio.marcarComoPagada(cuotaId, numeroCuenta);
	    System.out.println("cuotaId = " + cuotaId); // Borrar prueba
	    System.out.println("numeroCuenta = " + numeroCuenta);//Borrar prueba
	    System.out.println("cuota = " + cuota); //Borrar prueba
	    System.out.println("cuenta = " + cuenta);//Borrar prueba

	    if (!cuotaPagada) {
	        request.setAttribute("mensaje", "Error al registrar el pago de la cuota.");
	        request.getRequestDispatcher("clientes/pagarPrestamo.jsp").forward(request, response);
	        return;
	    }

	    request.setAttribute("mensaje", "✅ Cuota pagada exitosamente.");

	    // Recargar préstamos y cuentas
	    IPrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
	    String dni = (String) request.getSession().getAttribute("dniCliente");
	    List<Prestamo> prestamos = prestamoNegocio.obtenerPrestamosConCuotasPendientesPorDni(dni);
	    List<Cuenta> cuentas = cuentaNegocio.obtenerCuentasPorDni(dni);

	    request.setAttribute("prestamos", prestamos);
	    request.setAttribute("cuentas", cuentas);

	    request.getRequestDispatcher("clientes/pagarPrestamo.jsp").forward(request, response);
	}
	

}

package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.TiposDeCuentas;
import negocio.ICuentaNegocio;
import negocio.ITipoDeCuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TipoDeCuentaNegocioImpl;
import negocio.IClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

/**
 * Servlet implementation class CuentaServlet
 */
@WebServlet("/CuentaServlet")
public class CuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ITipoDeCuentaNegocio tipoCuentaNegocio = new TipoDeCuentaNegocioImpl();;
	private ICuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	private IClienteNegocio clienteNegocio = new ClienteNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CuentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String param = request.getParameter("Param");
	    String cbu = request.getParameter("cbu");
	    
	    if ("mostrarTodo".equals(param)) {
	        List<Cuenta> listaCuentas = cuentaNegocio.readAll();	        
	        request.setAttribute("listaCuentas", listaCuentas);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/gestionDeCuentas.jsp");
	        dispatcher.forward(request, response);
	    }
	    else if (cbu != null) {
	        Cuenta cuenta = cuentaNegocio.read(cbu);
	        List<TiposDeCuentas> listaTiposCuenta = tipoCuentaNegocio.listarTiposDeCuentas();
	        
	        request.setAttribute("tiposCuenta", listaTiposCuenta);
	        request.setAttribute("cuenta", cuenta);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/modificarCuenta.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        System.out.println("Parametro no reconocido o no recibido");
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetro incorrecto o no recibido.");
	    }
		
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

	    if ("modificar".equals(action)) {
	        // Obtener parámetros del formulario
	        String numeroCuenta = request.getParameter("numeroCuentaModificar");
	        String cbu = request.getParameter("cbuModificar");
	        String saldoStr = request.getParameter("saldoModificar");
	        String fechaCreacionStr = request.getParameter("fechaCreacionModificar");
	        String dni = request.getParameter("dniModificar");
	        String tipoCuentaStr = request.getParameter("tipoCuentaModificar");
	        String estadoStr = request.getParameter("estado");

	        // Convertir y validar datos
	        BigDecimal saldo = new BigDecimal(saldoStr);
	        LocalDate fechaCreacion = LocalDate.parse(fechaCreacionStr);
	        int idTipoCuenta = Integer.parseInt(tipoCuentaStr);
	        boolean activa = "1".equals(estadoStr);

	        // Crear objeto Cuenta
	        Cuenta cuenta = new Cuenta();
	        cuenta.setNumeroCuenta(numeroCuenta);
	        cuenta.setCBU(cbu);
	        cuenta.setSaldo(saldo);
	        cuenta.setFechaCreacion(fechaCreacion);
	        cuenta.setActiva(activa);

	        // Obtener objetos dependientes (Cliente y TipoCuenta)
	        Cliente cliente = clienteNegocio.obtenerClientePorDni(dni);
	        TiposDeCuentas tipoCuenta = tipoCuentaNegocio.getTipoCuentaPorID(idTipoCuenta);

	        cuenta.setCliente(cliente);
	        cuenta.setTipoCuenta(tipoCuenta);

	        // Llamar al método de actualización
	        boolean actualizada = cuentaNegocio.actualizarCuenta(cuenta);

	        if (actualizada) {
	            response.sendRedirect(request.getContextPath() + "/CuentaServlet?Param=mostrarTodo");
	        } else {
	            request.setAttribute("mensajeError", "No se pudo actualizar la cuenta.");
	            request.setAttribute("cuenta", cuenta);
	            request.setAttribute("tiposCuenta", tipoCuentaNegocio.listarTiposDeCuentas());
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/modificarCuenta.jsp");
	            dispatcher.forward(request, response);
	        }
	    }
	    
	    if ("eliminar".equals(action)) {
			String cbu = request.getParameter("cbu");

			boolean eliminado = cuentaNegocio.eliminarCuenta(cbu);

			if (eliminado) {
				response.sendRedirect(request.getContextPath() + "/CuentaServlet?Param=mostrarTodo");
			} else {
				//Por si despues queremos enviar mensajes al jsp
				// request.setAttribute("mensajeError", "No se pudo inactivar la cuenta. Por favor, intente de nuevo.");
				response.sendRedirect(request.getContextPath() + "/CuentaServlet?Param=mostrarTodo");
			}
		}
		
	}

}

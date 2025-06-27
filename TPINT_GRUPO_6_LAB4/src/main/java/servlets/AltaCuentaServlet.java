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

import negocio.IClienteNegocio;
import negocio.ICuentaNegocio;
import negocio.ITipoDeCuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TipoDeCuentaNegocioImpl;
import negocioImpl.ClienteNegocioImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.TiposDeCuentas;

@WebServlet("/AltaCuentaServlet")
public class AltaCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ITipoDeCuentaNegocio tipoCuentaNegocio;
	private ICuentaNegocio cuentaNegocio;
	private IClienteNegocio clienteNegocio;
	

	public AltaCuentaServlet() {
		super();
		this.tipoCuentaNegocio = new TipoDeCuentaNegocioImpl();
		this.cuentaNegocio = new CuentaNegocioImpl();
		this.clienteNegocio = new ClienteNegocioImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<TiposDeCuentas> tiposDeCuenta = tipoCuentaNegocio.listarTiposDeCuentas();
		request.setAttribute("tiposCuenta", tiposDeCuenta);

		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/altaCuenta.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

        if (action != null && action.equals("Guardar")) {
            String errorMessage = null; 
            int idTipoCuenta = 0;
            String dniCliente = request.getParameter("dniCliente");
            String numeroCuenta = request.getParameter("numeroCuenta");
            String cbu = request.getParameter("cbu");
            String idTipoCuentaStr = request.getParameter("idTipoCuenta"); 

            try {
                if (idTipoCuentaStr == null || idTipoCuentaStr.isEmpty()) {
                    errorMessage = "Debe seleccionar un tipo de cuenta."; 
                } else {
                    idTipoCuenta = Integer.parseInt(idTipoCuentaStr);
                }
            } catch (NumberFormatException e) {
                errorMessage = "Error: El tipo de cuenta seleccionado no es válido.";
            }
            
            if (errorMessage != null) {
                request.setAttribute("error", errorMessage);
                mantenerDatosFormulario(request, dniCliente, numeroCuenta, cbu, idTipoCuentaStr); 
                doGet(request, response);
                return;
            }
            
            if (!clienteNegocio.existeClienteActivo(dniCliente)) {
                errorMessage = "El DNI del cliente no existe o no está activo en el sistema.";
                request.setAttribute("error", errorMessage);
                mantenerDatosFormulario(request, dniCliente, numeroCuenta, cbu, idTipoCuentaStr); 
                doGet(request, response);
                return;
            }
            
            if (cuentaNegocio.contarCuentasPorDni(dniCliente) >= 3) {
                errorMessage = "El cliente ya posee el máximo de 3 cuentas activas.";
                request.setAttribute("error", errorMessage);
                mantenerDatosFormulario(request, dniCliente, numeroCuenta, cbu, idTipoCuentaStr); 
                doGet(request, response);
                return;
            }
            
            if (cuentaNegocio.existeNumeroCuenta(numeroCuenta)) {
                errorMessage = "El número de cuenta ingresado ya existe. Utilice otro.";
                request.setAttribute("error", errorMessage);
                mantenerDatosFormulario(request, dniCliente, numeroCuenta, cbu, idTipoCuentaStr); 
                doGet(request, response);
                return;
            }
            
            if (cuentaNegocio.existeCBU(cbu)) {
                errorMessage = "El CBU ingresado ya existe. Utilice otro.";
                request.setAttribute("error", errorMessage);
                mantenerDatosFormulario(request, dniCliente, numeroCuenta, cbu, idTipoCuentaStr); 
                doGet(request, response);
                return;
            }
            
            if (!cbu.contains(numeroCuenta)) {
                errorMessage = "El número de cuenta NO se encuentra dentro del CBU proporcionado.";
                request.setAttribute("error", errorMessage);
                mantenerDatosFormulario(request, dniCliente, numeroCuenta, cbu, idTipoCuentaStr); 
                doGet(request, response);
                return;
            }

            try {
                Cliente cliente = new Cliente();
                cliente.setDni(dniCliente); 

                TiposDeCuentas tipoCuenta = new TiposDeCuentas();
                tipoCuenta.setID(idTipoCuenta); 

                Cuenta cuenta = new Cuenta();
                cuenta.setCliente(cliente);
                cuenta.setTipoCuenta(tipoCuenta);
                cuenta.setNumeroCuenta(numeroCuenta);
                cuenta.setCBU(cbu);
                cuenta.setFechaCreacion(LocalDate.now());
                cuenta.setSaldo(new BigDecimal("10000.00")); 
                cuenta.setActiva(true);

                int resultadoDB = cuentaNegocio.insertarCuenta(cuenta);

                if (resultadoDB == 1) {
                    request.setAttribute("mensaje", "Cuenta fue creada exitosamente. Número de cuenta: " + numeroCuenta);
                    // Limpiar datos del formulario
                    request.setAttribute("dniCliente", "");
                    request.setAttribute("numeroCuenta", "");
                    request.setAttribute("cbu", "");
                    request.setAttribute("idTipoCuenta", ""); 
                } else {
                    errorMessage = "No se pudo agregar la cuenta debido a un problema interno en la base de datos. Intente nuevamente.";
                    request.setAttribute("error", errorMessage);
                    mantenerDatosFormulario(request, dniCliente, numeroCuenta, cbu, idTipoCuentaStr);
                }

            } catch (Exception e) {
                errorMessage = "Ocurrió un error inesperado al intentar crear la cuenta. Contacte a soporte.";
                request.setAttribute("error", errorMessage);
                e.printStackTrace();
                mantenerDatosFormulario(request, dniCliente, numeroCuenta, cbu, idTipoCuentaStr);
            }
            
            doGet(request, response);

        } else {
            doGet(request, response);
        }
	}

	private void mantenerDatosFormulario(HttpServletRequest request, String dniCliente, String numeroCuenta, String cbu,
			String idTipoCuenta) {
		request.setAttribute("dniCliente", dniCliente != null ? dniCliente : "");
		request.setAttribute("numeroCuenta", numeroCuenta != null ? numeroCuenta : "");
		request.setAttribute("cbu", cbu != null ? cbu : "");
		request.setAttribute("idTipoCuenta", idTipoCuenta != null ? idTipoCuenta : "");
	}
}
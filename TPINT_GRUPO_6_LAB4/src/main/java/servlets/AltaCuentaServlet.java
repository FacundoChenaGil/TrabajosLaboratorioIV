package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import negocio.IClienteNegocio;
import negocio.ICuentaNegocio;
import negocio.ITipoDeCuentaNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TipoDeCuentaNegocioImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.TiposDeCuentas;

@WebServlet("/AltaCuentaServlet")
public class AltaCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ITipoDeCuentaNegocio tipoCuentaNegocio = new TipoDeCuentaNegocioImpl();
	ICuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	IClienteNegocio clienteNegocio = new ClienteNegocioImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("mensaje", request.getSession().getAttribute("mensaje"));
		request.setAttribute("error", request.getSession().getAttribute("error"));
		request.setAttribute("dniCliente", request.getSession().getAttribute("dniCliente"));
		request.setAttribute("numeroCuenta", request.getSession().getAttribute("numeroCuenta"));
		request.setAttribute("cbu", request.getSession().getAttribute("cbu"));
		request.setAttribute("idTipoCuenta", request.getSession().getAttribute("idTipoCuenta"));

		request.getSession().removeAttribute("mensaje");
		request.getSession().removeAttribute("error");
		request.getSession().removeAttribute("dniCliente");
		request.getSession().removeAttribute("numeroCuenta");
		request.getSession().removeAttribute("cbu");
		request.getSession().removeAttribute("idTipoCuenta");

		List<TiposDeCuentas> tiposDeCuenta = tipoCuentaNegocio.listarTiposDeCuentas();
		request.setAttribute("tiposCuenta", tiposDeCuenta);

		request.getRequestDispatcher("admin/altaCuenta.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dni = request.getParameter("dniCliente");
		String numeroCuenta = request.getParameter("numeroCuenta");
		String cbu = request.getParameter("cbu");
		String idTipoCuenta = request.getParameter("idTipoCuenta");

		request.getSession().setAttribute("dniCliente", dni);
		request.getSession().setAttribute("numeroCuenta", numeroCuenta);
		request.getSession().setAttribute("cbu", cbu);
		request.getSession().setAttribute("idTipoCuenta", idTipoCuenta);

		if (!clienteNegocio.existeClienteActivo(dni)) {
			request.getSession().setAttribute("error", "El DNI ingresado no existe o no está activo.");
			response.sendRedirect("AltaCuentaServlet");
			return;
		}

		if (cuentaNegocio.contarCuentasPorDni(dni) >= 3) {
			request.getSession().setAttribute("error", "El cliente ya tiene el máximo de 3 cuentas activas.");
			response.sendRedirect("AltaCuentaServlet");
			return;
		}

		if (cuentaNegocio.existeNumeroCuenta(numeroCuenta)) {
			request.getSession().setAttribute("error", "El número de cuenta ingresado ya existe.");
			response.sendRedirect("AltaCuentaServlet");
			return;
		}

		if (cuentaNegocio.existeCBU(cbu)) {
			request.getSession().setAttribute("error", "El CBU ingresado ya existe.");
			response.sendRedirect("AltaCuentaServlet");
			return;
		}

		if (!cbu.contains(numeroCuenta)) {
			request.getSession().setAttribute("error", "El número de cuenta no está dentro del CBU.");
			response.sendRedirect("AltaCuentaServlet");
			return;
		}

		try {
			Cliente cliente = new Cliente();
			cliente.setDni(dni);

			TiposDeCuentas tipoCuenta = new TiposDeCuentas();
			tipoCuenta.setID(Integer.parseInt(idTipoCuenta));

			Cuenta cuenta = new Cuenta();
			cuenta.setCliente(cliente);
			cuenta.setTipoCuenta(tipoCuenta);
			cuenta.setNumeroCuenta(numeroCuenta);
			cuenta.setCbu(cbu);
			cuenta.setFechaCreacion(LocalDate.now());
			cuenta.setSaldo(new BigDecimal("10000.00"));
			cuenta.setActiva(true);

			if (cuentaNegocio.insertarCuenta(cuenta) == 1) {
				request.getSession().setAttribute("mensaje",
						"Cuenta creada correctamente. Número de cuenta: " + numeroCuenta);
				request.getSession().removeAttribute("dniCliente");
				request.getSession().removeAttribute("numeroCuenta");
				request.getSession().removeAttribute("cbu");
				request.getSession().removeAttribute("idTipoCuenta");
			} else {
				request.getSession().setAttribute("error", "No se pudo crear la cuenta. Intente nuevamente.");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("error", "Ocurrió un error inesperado al crear la cuenta.");
		}

		response.sendRedirect("AltaCuentaServlet");
	}
}

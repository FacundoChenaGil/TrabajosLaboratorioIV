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

		HttpSession session = request.getSession();

		request.setAttribute("mensaje", session.getAttribute("mensaje"));
		request.setAttribute("error", session.getAttribute("error"));
		request.setAttribute("dniCliente", session.getAttribute("dniCliente"));
		request.setAttribute("numeroCuenta", session.getAttribute("numeroCuenta"));
		request.setAttribute("cbu", session.getAttribute("cbu"));
		request.setAttribute("idTipoCuenta", session.getAttribute("idTipoCuenta"));

		session.removeAttribute("mensaje");
		session.removeAttribute("error");
		session.removeAttribute("dniCliente");
		session.removeAttribute("numeroCuenta");
		session.removeAttribute("cbu");
		session.removeAttribute("idTipoCuenta");

		List<TiposDeCuentas> tiposDeCuenta = tipoCuentaNegocio.listarTiposDeCuentas();
		request.setAttribute("tiposCuenta", tiposDeCuenta);

		request.getRequestDispatcher("admin/altaCuenta.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String dni = request.getParameter("dniCliente");
		String numeroCuenta = request.getParameter("numeroCuenta");
		String cbu = request.getParameter("cbu");
		String idTipoCuenta = request.getParameter("idTipoCuenta");

		session.setAttribute("dniCliente", dni);
		session.setAttribute("numeroCuenta", numeroCuenta);
		session.setAttribute("cbu", cbu);
		session.setAttribute("idTipoCuenta", idTipoCuenta);

		if (!clienteNegocio.existeClienteActivo(dni)) {
			session.setAttribute("error", "El DNI ingresado no existe o no está activo.");
			response.sendRedirect("AltaCuentaServlet");
			return;
		}

		if (cuentaNegocio.contarCuentasPorDni(dni) >= 3) {
			session.setAttribute("error", "El cliente ya tiene el máximo de 3 cuentas activas.");
			response.sendRedirect("AltaCuentaServlet");
			return;
		}

		if (cuentaNegocio.existeNumeroCuenta(numeroCuenta)) {
			session.setAttribute("error", "El número de cuenta ingresado ya existe.");
			response.sendRedirect("AltaCuentaServlet");
			return;
		}

		if (cuentaNegocio.existeCBU(cbu)) {
			session.setAttribute("error", "El CBU ingresado ya existe.");
			response.sendRedirect("AltaCuentaServlet");
			return;
		}

		if (!cbu.contains(numeroCuenta)) {
			session.setAttribute("error", "El número de cuenta no está dentro del CBU.");
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
			cuenta.setCBU(cbu);
			cuenta.setFechaCreacion(LocalDate.now());
			cuenta.setSaldo(new BigDecimal("10000.00"));
			cuenta.setActiva(true);

			if (cuentaNegocio.insertarCuenta(cuenta) == 1) {
				session.setAttribute("mensaje", "Cuenta creada correctamente. Número de cuenta: " + numeroCuenta);
				session.removeAttribute("dniCliente");
				session.removeAttribute("numeroCuenta");
				session.removeAttribute("cbu");
				session.removeAttribute("idTipoCuenta");
			} else {
				session.setAttribute("error", "No se pudo crear la cuenta. Intente nuevamente.");
			}
		} catch (Exception e) {
			session.setAttribute("error", "Ocurrió un error inesperado al crear la cuenta.");
		}

		response.sendRedirect("AltaCuentaServlet");
	}
}

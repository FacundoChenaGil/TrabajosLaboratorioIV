package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import negocio.ICuentaNegocio;
import negocioImpl.CuentaNegocioImpl;


@WebServlet("/MiCuentaServlet")
public class MiCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MiCuentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        String dni = (String) session.getAttribute("dniCliente");

        if (dni != null) {
            ICuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
            Cuenta cuenta = cuentaNegocio.obtenerCuentaPorDni(dni);

            request.setAttribute("cuenta", cuenta);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/miCuenta.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // por si no está logueado
	
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

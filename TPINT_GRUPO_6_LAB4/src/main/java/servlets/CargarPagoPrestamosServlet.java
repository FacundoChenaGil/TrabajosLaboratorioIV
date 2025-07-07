package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.Prestamo;
import negocio.IPrestamoNegocio;
import negocio.ICuentaNegocio;
import negocioImpl.PrestamoNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
 

@WebServlet("/CargarPagoPrestamosServlet")
public class CargarPagoPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CargarPagoPrestamosServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String dni = (String) session.getAttribute("dniCliente");
       
        
        // Simulación de sesión para pruebas
        if (dni == null) {
            dni = "35111222";
            session.setAttribute("dniCliente", dni);
        }

        IPrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
        ICuentaNegocio cuentaNegocio = new CuentaNegocioImpl();

        
        List<Prestamo> prestamos = prestamoNegocio.obtenerPrestamosConCuotasPendientesPorDni(dni);
        List<Cuenta> cuentas = cuentaNegocio.obtenerCuentasPorDni(dni);

       
       
	        request.setAttribute("prestamos", prestamos);
	        request.setAttribute("cuentas", cuentas);
	
	        request.getRequestDispatcher("clientes/pagarPrestamo.jsp").forward(request, response);
	    }

		
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

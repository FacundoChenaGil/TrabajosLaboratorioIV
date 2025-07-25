package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.IPrestamoNegocio;
import entidad.Prestamo;
import negocioImpl.PrestamoNegocioImpl;



@WebServlet("/SeleccionarPrestamoServlet")
public class SeleccionarPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SeleccionarPrestamoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String dni = (String) session.getAttribute("dniCliente");

        IPrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
        List<Prestamo> prestamos = prestamoNegocio.obtenerPrestamosPorDni(dni);

        request.setAttribute("prestamos", prestamos);
        request.getRequestDispatcher("clientes/historialPagodePrestamos.jsp").forward(request, response);
    }
}

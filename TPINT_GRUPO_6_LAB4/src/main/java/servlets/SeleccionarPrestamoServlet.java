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
        System.out.println("Entré al servlet");
       
        
        // Simulamos sesión
        String dni = "35111222";
        session.setAttribute("dniCliente", dni);

        System.out.println("DNI: " + dni); 

        IPrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
        List<Prestamo> prestamos = prestamoNegocio.obtenerPrestamosPorDni(dni);

        System.out.println("Préstamos encontrados: " + prestamos.size()); 

        for (Prestamo p : prestamos) {
            System.out.println("ID: " + p.getIdPrestamo() + " - Monto: " + p.getMonto_pedido());
        }

        request.setAttribute("prestamos", prestamos);
        request.getRequestDispatcher("clientes/historialPagodePrestamos.jsp").forward(request, response);
    }
}

package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.IPrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;
import entidad.PrestamoBackup;



@WebServlet("/GestionDePrestamosServlet")
public class GestionDePrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IPrestamoNegocio negPrestamo = new PrestamoNegocioImpl();
       
    public GestionDePrestamosServlet() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensajeExito = (String) request.getSession().getAttribute("mensajeExito");
        String mensajeError = (String) request.getSession().getAttribute("mensajeError");

        if (mensajeExito != null) {
            request.setAttribute("mensajeExito", mensajeExito);
            request.getSession().removeAttribute("mensajeExito");
        }
        if (mensajeError != null) {
            request.setAttribute("mensajeError", mensajeError);
            request.getSession().removeAttribute("mensajeError");
        }
        
        
		List<PrestamoBackup> prestamosPendientes = negPrestamo.leerPrestamosPendientes();
		request.setAttribute("prestamosPendientes", prestamosPendientes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/gestionDePrestamos.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAprobado = request.getParameter("idPrestamoAprobado");
	    String idRechazado = request.getParameter("idPrestamoRechazado");

	    if (idAprobado != null) {
	    	int id = Integer.parseInt(idAprobado);
	        boolean exito = negPrestamo.aprobarPrestamo(id);

	        if (exito) {
                request.getSession().setAttribute("mensajeExito", "Préstamo aprobado correctamente.");
            } else {
                request.getSession().setAttribute("mensajeError", "Error al aprobar el préstamo.");
            }
	    } else if (idRechazado != null) {
	    	int id = Integer.parseInt(idRechazado);
	        boolean exito = negPrestamo.rechazarPrestamo(id);

	        if (exito) {
                request.getSession().setAttribute("mensajeExito", "Préstamo rechazado correctamente.");
            } else {
                request.getSession().setAttribute("mensajeError", "Error al rechazar el préstamo.");
            }
	    }
		
	    response.sendRedirect(request.getContextPath() + "/GestionDePrestamosServlet");
	}

}
package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Prestamo;
import negocio.IPrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

@WebServlet("/HistorialPrestamosServlet")
public class HistorialPrestamosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = (String) request.getSession().getAttribute("dniCliente");
        if (dni != null) {
            IPrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
            List<Prestamo> prestamosFinalizados = prestamoNegocio.obtenerPrestamosFinalizadosPorDni(dni);
            request.setAttribute("prestamosFinalizados", prestamosFinalizados);
        }
        request.getRequestDispatcher("/clientes/historialPagodePrestamos.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

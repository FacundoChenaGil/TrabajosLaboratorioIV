package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Prestamo;
import negocio.IPrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

@WebServlet("/PrestamosActivosServlet")
public class PrestamosActivosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int ESTADO_ACTIVO = 2; 

    public PrestamosActivosServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); 

        String dni = (String) session.getAttribute("dniCliente");

        IPrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
        List<Prestamo> prestamosActivos = prestamoNegocio.obtenerPrestamosPorDniYEstado(dni, ESTADO_ACTIVO);

        request.setAttribute("listaPrestamosActivos", prestamosActivos);
        request.getRequestDispatcher("clientes/prestamosActivos.jsp").forward(request, response);
    }
}

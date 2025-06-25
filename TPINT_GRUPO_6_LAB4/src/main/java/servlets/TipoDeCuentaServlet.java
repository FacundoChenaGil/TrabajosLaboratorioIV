package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import entidad.TiposDeCuentas;
import negocio.ITipoDeCuentaNegocio;
import negocioImpl.TipoDeCuentaNegocioImpl;

/**
 * Servlet implementation class TipoDeCuentaServlet
 */
@WebServlet("/TipoDeCuentaServlet")
public class TipoDeCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Instancia tu capa de negocio. Hazlo una vez para el servlet.
    private ITipoDeCuentaNegocio tipoCuentaNegocio = new TipoDeCuentaNegocioImpl();

    public TipoDeCuentaServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        List<TiposDeCuentas> listaTiposDeCuenta = tipoCuentaNegocio.listarTiposDeCuentas();

       
        request.setAttribute("tiposCuenta", listaTiposDeCuenta);

      
        RequestDispatcher dispatcher = request.getRequestDispatcher("altaCuenta.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

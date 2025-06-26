package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.TiposDeCuentas;
import negocio.ICuentaNegocio;
import negocio.ITipoDeCuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TipoDeCuentaNegocioImpl;

/**
 * Servlet implementation class CuentaServlet
 */
@WebServlet("/CuentaServlet")
public class CuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ITipoDeCuentaNegocio tipoCuentaNegocio = new TipoDeCuentaNegocioImpl();;
	private ICuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CuentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String param = request.getParameter("Param");
	    String cbu = request.getParameter("cbu");
	    
	    if ("mostrarTodo".equals(param)) {
	        List<Cuenta> listaCuentas = cuentaNegocio.readAll();	        
	        request.setAttribute("listaCuentas", listaCuentas);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/gestionDeCuentas.jsp");
	        dispatcher.forward(request, response);
	    }
	    else if (cbu != null) {
	        Cuenta cuenta = cuentaNegocio.read(cbu);
	        List<TiposDeCuentas> listaTiposCuenta = tipoCuentaNegocio.listarTiposDeCuentas();
	        
	        request.setAttribute("tiposCuenta", listaTiposCuenta);
	        request.setAttribute("cuenta", cuenta);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/modificarCuenta.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        System.out.println("Parametro no reconocido o no recibido");
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetro incorrecto o no recibido.");
	    }
		
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

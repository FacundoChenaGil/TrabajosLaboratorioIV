package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.PromedioPorTipoCuenta;
import negocio.IPromedioPorTipoCuentaNegocio;
import negocioImpl.PromedioPorTipoCuentaNegocioImpl;


@WebServlet("/PromedioTipoCuentaServlet")
public class PromedioTipoCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PromedioTipoCuentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IPromedioPorTipoCuentaNegocio negocio = new PromedioPorTipoCuentaNegocioImpl();
		List<PromedioPorTipoCuenta> promedios = negocio.obtenerPromedios();
		
		request.setAttribute("promedios", promedios);
		
		request.getRequestDispatcher("/admin/reporteSaldosPromedioPorTipoCta.jsp").forward(request, response);
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

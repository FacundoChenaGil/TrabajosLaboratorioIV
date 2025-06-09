package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Seguro;
import dominio.SeguroDao;
import dominio.TipoSeguro;
import dominio.TipoSeguroDao;

/**
 * Servlet implementation class servletSeguro
 */
@WebServlet("/servletSeguro")
public class servletSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SeguroDao sdao = new SeguroDao();
	private TipoSeguroDao tsdao = new TipoSeguroDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servletSeguro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("btnFiltrar") != null) {
			ArrayList<Seguro> lista = sdao.obtenerSeguros(request.getParameter("tipoSeguro"));

			request.setAttribute("listaSeguros", lista);

			RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguro.jsp");
			rd.forward(request, response);
		}

		if ("agregar".equals(request.getParameter("Param"))) {
			int nuevoId = sdao.obtenerProximoId();
			ArrayList<TipoSeguro> listaTiposSeguro = tsdao.obtenerTiposSeguro();

			request.setAttribute("nuevoId", nuevoId);
			request.setAttribute("listaTiposSeguro", listaTiposSeguro);

			RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descripcion = request.getParameter("descripcion");
	    String tipoSeguro = request.getParameter("tipoSeguro");
	    String contratacion = request.getParameter("contratacion");
	    String costoMaximo = request.getParameter("costoMaximo");

	    if (request.getParameter("btnAceptar") != null) {
	        if (descripcion != null && !descripcion.isEmpty()
	                && tipoSeguro != null && !tipoSeguro.isEmpty()
	                && contratacion != null && !contratacion.isEmpty()
	                && costoMaximo != null && !costoMaximo.isEmpty()) {

	            try {
	                int idTipo = Integer.parseInt(tipoSeguro);
	                float costoContratacion = Float.parseFloat(contratacion);
	                float costoAsegurado = Float.parseFloat(costoMaximo);

	                if (costoContratacion <= 0 || costoAsegurado <= 0) {
	                    request.setAttribute("noCompleto", "Los costos deben ser mayores a 0.");
	                } else {
	                    SeguroDao dao = new SeguroDao();

	                    if (dao.existeSeguro(descripcion, idTipo)) {
	                        request.setAttribute("noCompleto", "Ya existe un seguro con esa descripción y tipo.");
	                    } else {
	                        Seguro seguro = new Seguro();
	                        TipoSeguro ts = new TipoSeguro();
	                        ts.setIdTipo(idTipo);

	                        seguro.setDescripcion(descripcion);
	                        seguro.setTipoSeguro(ts);
	                        seguro.setCostoContratacion(costoContratacion);
	                        seguro.setCostoAsegurado(costoAsegurado);

	                        int filas = dao.agregarSeguro(seguro);
	                        request.setAttribute("cantFilas", filas);
	                    }
	                }
	            } catch (NumberFormatException e) {
	                request.setAttribute("noCompleto", "Los campos de costo deben ser números válidos (usar punto para decimales).");
	            }
	        } else {
	            request.setAttribute("noCompleto", "Complete todos los campos obligatorios.");
	        }
	    }

	    ArrayList<TipoSeguro> listaTiposSeguro = tsdao.obtenerTiposSeguro();
	    request.setAttribute("listaTiposSeguro", listaTiposSeguro);
	    int nuevoId = sdao.obtenerProximoId();
	    request.setAttribute("nuevoId", nuevoId);

	    RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
	    rd.forward(request, response);
	}

}

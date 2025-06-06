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

/**
 * Servlet implementation class servletSeguro
 */
@WebServlet("/servletSeguro")
public class servletSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SeguroDao sdao = new SeguroDao();

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
			ArrayList<String> listaTiposSeguro = sdao.obtenerTiposSeguro();

			System.out.println("Tamaño de la lista: " + listaTiposSeguro.size());

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
		if (request.getParameter("btnAceptar") != null) {
			if (request.getParameter("descripcion") != null && request.getParameter("tipoSeguro") != null
					&& request.getParameter("contratacion") != null && request.getParameter("costoMaximo") != null) {

				String descripcion = request.getParameter("descripcion");
				int idTipo = Integer.parseInt(request.getParameter("tipoSeguro"));
				double costoContratacion = Double.parseDouble(request.getParameter("contratacion"));
				double costoAsegurado = Double.parseDouble(request.getParameter("costoMaximo"));
				
				SeguroDao dao = new SeguroDao();

				if (dao.existeSeguro(descripcion, idTipo)) {
				    request.setAttribute("noCompleto", "Ya existe un seguro con esa descripción y tipo.");
				    RequestDispatcher rd = request.getRequestDispatcher("AgregarSeguro.jsp");
				    rd.forward(request, response);
				    return;
				}

				Seguro seguro = new Seguro();
				seguro.setDescripcion(descripcion);
				seguro.setIdTipo(idTipo);
				seguro.setCostoContratacion(costoContratacion);
				seguro.setCostoAsegurado(costoAsegurado);

				
				int filas = dao.agregarSeguro(seguro);

				request.setAttribute("cantFilas", filas);

			} else {
				String error = "Complete y seleccione los campos.";
				request.setAttribute("noCompleto", error);
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
		rd.forward(request, response);

	}

}

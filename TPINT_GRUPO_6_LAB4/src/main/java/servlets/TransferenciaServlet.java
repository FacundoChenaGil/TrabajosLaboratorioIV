package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/TransferenciaServlet") 

public class TransferenciaServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {

			    String cbuDestino = request.getParameter("cbu");
			    String monto = request.getParameter("monto");

			    // Simulación de lógica
			    if (cbuDestino != null && monto != null) {
			        // Redireccionar a dashboard si todo está ok
			        response.sendRedirect("clientes/dashboard.jsp");
			    } else {
			        // Volver al formulario con mensaje de error
			        request.setAttribute("error", "Faltan datos para la transferencia.");
			        RequestDispatcher rd = request.getRequestDispatcher("clientes/nuevaTransferencia.jsp");
			        rd.forward(request, response);
			    }
			}
	}



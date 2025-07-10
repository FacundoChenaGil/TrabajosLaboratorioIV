package servlets;



import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoMovimiento;
import negocio.IMovimientoNegocio;
import negocioImpl.MovimientoNegocioImpl;
import negocio.ICuentaNegocio;
import negocioImpl.CuentaNegocioImpl;

import negocio.ITipoMovimientoNegocio;
import negocioImpl.TipoMovimientoNegocioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/MovimientoServlet")
public class MovimientoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IMovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
    private ICuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
    private ITipoMovimientoNegocio tipoMovimientoNegocio = new TipoMovimientoNegocioImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false); 
    	if (session == null || session.getAttribute("dniCliente") == null) {
    	    response.sendRedirect(request.getContextPath() + "/login.jsp"); // Redirige al login
    	    return;
    	}

    	String dniCliente = (String) session.getAttribute("dniCliente");

        List<Cuenta> listaCuentas = cuentaNegocio.obtenerCuentasConTipoPorDni(dniCliente);

        if (listaCuentas == null || listaCuentas.isEmpty()) {
            request.setAttribute("mensajeError", "No se encontraron cuentas activas para este cliente.");
        } else {
            request.setAttribute("listaCuentas", listaCuentas);
        }
        
        List<TipoMovimiento> tipos = tipoMovimientoNegocio.listarTodos();
        request.setAttribute("tiposMovimientos", tipos);


        request.getRequestDispatcher("/clientes/movimientos.jsp").forward(request, response);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String dniCliente = (String) session.getAttribute("dniCliente");

        if (dniCliente == null || dniCliente.isEmpty()) {
            request.setAttribute("error", "Sesión inválida.");
            request.getRequestDispatcher("/clientes/movimientos.jsp").forward(request, response);
            return;
        }

        String cbuSeleccionado = request.getParameter("cuenta");
        String desdeStr = request.getParameter("desde");
        String hastaStr = request.getParameter("hasta");
        String tipoStr = request.getParameter("tipo");

        List<Cuenta> cuentas = cuentaNegocio.obtenerCuentasConTipoPorDni(dniCliente);
        List<TipoMovimiento> tipos = tipoMovimientoNegocio.listarTodos();

        request.setAttribute("listaCuentas", cuentas); 
        request.setAttribute("tiposMovimientos", tipos);
        request.setAttribute("cuentaSeleccionada", cbuSeleccionado);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date desde = sdf.parse(desdeStr);
            Date hasta = sdf.parse(hastaStr);

            // Ajustamos la fecha hasta al inicio del día siguiente
            Calendar cal = Calendar.getInstance();
            cal.setTime(hasta);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date hastaParam = cal.getTime();

            int idTipo = Integer.parseInt(tipoStr);

  
            List<Movimiento> movimientos = movimientoNegocio.filtrarMovimientos(cbuSeleccionado, desde, hastaParam, idTipo);
            request.setAttribute("movimientos", movimientos);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar el filtro.");
        }

        request.getRequestDispatcher("/clientes/movimientos.jsp").forward(request, response);
    }
}
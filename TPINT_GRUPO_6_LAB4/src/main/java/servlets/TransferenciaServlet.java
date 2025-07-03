package servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import negocio.ICuentaNegocio;
import negocio.IMovimientoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

@WebServlet("/TransferenciaServlet")
public class TransferenciaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 // --- MENSAJE DE DEPURACIÓN CRÍTICO ---
        System.out.println(">>> TransferenciaServlet - doPost() ha sido invocado. <<<");
        // --- FIN DEL MENSAJE DE DEPURACIÓN ---

        HttpSession session = request.getSession();
        String accion = request.getParameter("accion");
        String cbuOrigen = (String) session.getAttribute("cbuSeleccionado");
        String cbuDestino = request.getParameter("cbuDestino");
        String montoStr = request.getParameter("monto");

        ICuentaNegocio cuentaNeg = new CuentaNegocioImpl();
        IMovimientoNegocio movNeg = new MovimientoNegocioImpl();

        if ("buscar".equals(accion)) {
            if (cbuDestino == null || cbuDestino.isEmpty()) {
                request.setAttribute("error", "Debe ingresar un CBU destino.");
            } else if (!cuentaNeg.existeCBU(cbuDestino)) {
                request.setAttribute("error", "El CBU destino no existe.");
            } else {
                request.setAttribute("cbuDestino", cbuDestino);
                request.setAttribute("nombreDestinatario", cuentaNeg.obtenerNombreTitular(cbuDestino));
                request.setAttribute("dniDestinatario", cuentaNeg.obtenerDniTitular(cbuDestino));
            }
            request.getRequestDispatcher("/clientes/nuevaTransferencia.jsp").forward(request, response);
            return;
        }
        System.out.println("Accion: " + accion);
        System.out.println("cbuOrigen: " + cbuOrigen);
        System.out.println("cbuDestino: " + cbuDestino);
        System.out.println("montoStr: " + montoStr);

        if ("confirmar".equals(accion)) {
            if (cbuOrigen == null || cbuDestino == null || montoStr == null || montoStr.isEmpty()) {
                request.setAttribute("error", "Faltan datos para realizar la transferencia.");
                request.getRequestDispatcher("/clientes/nuevaTransferencia.jsp").forward(request, response);
                return;
            }

            // 🚫 Validación: no permitir transferirse a sí mismo
            if (cbuOrigen.equals(cbuDestino)) {
                request.setAttribute("error", "No puedes transferirte a tu propia cuenta.");
                request.getRequestDispatcher("/clientes/nuevaTransferencia.jsp").forward(request, response);
                return;
            }

            BigDecimal monto;
            try {
                monto = new BigDecimal(montoStr);
                if (monto.compareTo(BigDecimal.ZERO) <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                request.setAttribute("error", "El monto ingresado no es válido.");
                request.getRequestDispatcher("/clientes/nuevaTransferencia.jsp").forward(request, response);
                return;
            }

            BigDecimal saldoOrigen = cuentaNeg.obtenerSaldo(cbuOrigen);
            if (saldoOrigen.compareTo(monto) < 0) {
                request.setAttribute("error", "Saldo insuficiente.");
                request.getRequestDispatcher("/clientes/nuevaTransferencia.jsp").forward(request, response);
                return;
            }

            boolean movOrigen = movNeg.insertarMovimiento(cbuOrigen, monto.negate(), "Transferencia a " + cbuDestino, 4);
            boolean movDestino = movNeg.insertarMovimiento(cbuDestino, monto, "Transferencia de " + cbuOrigen, 3);

            boolean actualizadoOrigen = cuentaNeg.actualizarSaldo(cbuOrigen, saldoOrigen.subtract(monto));
            BigDecimal saldoDestino = cuentaNeg.obtenerSaldo(cbuDestino);
            boolean actualizadoDestino = cuentaNeg.actualizarSaldo(cbuDestino, saldoDestino.add(monto));

            if (movOrigen && movDestino && actualizadoOrigen && actualizadoDestino) {
                request.setAttribute("exito", "Transferencia realizada con éxito.");
            } else {
                request.setAttribute("error", "Ocurrió un error al procesar la transferencia.");
            }

            request.getRequestDispatcher("/clientes/nuevaTransferencia.jsp").forward(request, response);
        }
    }
}         
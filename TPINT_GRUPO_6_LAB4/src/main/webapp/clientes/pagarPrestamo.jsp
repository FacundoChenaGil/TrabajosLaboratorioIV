<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Pago de Préstamos</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <script>
    function mostrarCuotas(prestamoId) {
      document.querySelectorAll('.tabla-cuotas').forEach(div => div.classList.add('hidden'));
      const activa = document.getElementById('prestamo-' + prestamoId);
      if (activa) activa.classList.remove('hidden');
    }
  </script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

  <main class="flex-grow py-10 px-4 text-gray-800">
    <div class="max-w-4xl mx-auto bg-white p-8 rounded-2xl shadow-lg">
      <h2 class="text-3xl font-bold text-red-700 text-center mb-6">Pago de Préstamos</h2>
      <c:if test="${not empty mensaje}">
        <div class="mb-4 p-4 rounded-lg shadow-md 
                    ${mensaje.contains('✅') ? 'bg-green-100 text-green-800 border-l-4 border-green-500' 
                                           : 'bg-red-100 text-red-800 border-l-4 border-red-500'}">
          ${mensaje}
        </div>
      </c:if>

      <!-- Selector de préstamo -->
      <div class="mb-6">
        <label for="prestamo" class="block text-sm font-medium mb-2">Seleccionar préstamo:</label>
        <select id="prestamo" onchange="mostrarCuotas(this.value)" class="w-full p-3 border border-gray-300 rounded-lg">
          <option value="">-- Elegir préstamo --</option>
          <c:forEach var="prestamo" items="${prestamos}">
            <option value="${prestamo.IDPrestamo}">Préstamo ${prestamo.IDPrestamo}</option>
          </c:forEach>
        </select>
      </div>

      <!-- Cuotas por préstamo -->
      <c:forEach var="prestamo" items="${prestamos}">
        <div id="prestamo-${prestamo.IDPrestamo}" class="tabla-cuotas hidden">
          <h3 class="text-lg font-semibold text-red-600 mb-3">Cuotas pendientes - Préstamo ${prestamo.IDPrestamo}</h3>

          <!-- Resumen -->
          <div class="mb-4 bg-red-50 border-l-4 border-red-600 p-4 rounded-md shadow-sm">
            <p class="font-semibold text-red-700">Resumen del préstamo:</p>
            <ul class="text-sm mt-1 text-gray-700 list-disc pl-5">
              <li>Cuotas pendientes: <strong>${prestamo.cuotasPendientes.size()}</strong></li>
              <li>Monto total adeudado: <strong>$${prestamo.montoTotalAdeudado}</strong></li>
            </ul>
          </div>

          <!-- Lista de cuotas -->
          <div class="space-y-4">
            <c:forEach var="cuota" items="${prestamo.cuotasPendientes}">
              <li class="flex justify-between items-center border p-4 rounded-lg 
                         ${cuota.idCuota == prestamo.primeraCuotaId ? 'bg-gray-50' : 'bg-gray-100 opacity-60'}">
                <div>
                  <p class="font-medium">Cuota ${cuota.numeroCuota} - $${cuota.importe}</p>
                  <p class="text-sm text-gray-500">Vence: ${cuota.fechaVencimiento}</p>
                </div>

                <div class="flex items-center gap-2">
                  <c:choose>
                    <c:when test="${cuota.idCuota == prestamo.primeraCuotaId}">
                      <form action="PagarCuotasServlet" method="post" class="flex items-center gap-2">
                        <input type="hidden" name="cuotaId" value="${cuota.idCuota}" />
                        <select name="cuentaId" required class="border border-gray-300 rounded px-2 py-1">
                          <option value="">Cuenta</option>
                          <c:forEach var="cuenta" items="${cuentas}">
                            <option value="${cuenta.numeroCuenta}">
                              ${cuenta.tipoCuenta.descripcion} - Nº ${cuenta.numeroCuenta}
                            </option>
                          </c:forEach>
                        </select>
                        <button type="submit" class="bg-red-600 hover:bg-red-700 text-white px-3 py-1 rounded-lg shadow">Pagar</button>
                      </form>
                    </c:when>
                    <c:otherwise>
                      <button disabled class="bg-gray-300 text-gray-600 px-4 py-2 rounded-lg cursor-not-allowed">
                        No disponible
                      </button>
                    </c:otherwise>
                  </c:choose>
                </div>
              </li>
            </c:forEach>
          </div>
        </div>
      </c:forEach>

      <!-- Volver -->
      <div class="mt-6 text-center">
        <a href="<%= request.getContextPath() %>/clientes/menuMovimientos.jsp" class="text-red-600 hover:underline">← Volver al menú</a>
      </div>
    </div>
  </main>

  <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
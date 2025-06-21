<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagar Prestamo</title>
<script src="https://cdn.tailwindcss.com"></script>

<script>
    function mostrarCuotas(prestamoId) {
      document.querySelectorAll('.tabla-cuotas').forEach(div => div.classList.add('hidden'));
      const activa = document.getElementById('prestamo-' + prestamoId);
      if (activa) activa.classList.remove('hidden');
    }
  </script>
</head>


<body class="bg-gray-100 min-h-screen py-10 px-4 text-gray-800">
  <div class="max-w-4xl mx-auto bg-white p-8 rounded-2xl shadow-lg">
    <h2 class="text-3xl font-bold text-red-700 text-center mb-6">Pago de Préstamos</h2>

    <!-- Selector de préstamo -->
    <div class="mb-6">
      <label for="prestamo" class="block text-sm font-medium mb-2">Seleccionar préstamo:</label>
      <select id="prestamo" onchange="mostrarCuotas(this.value)" class="w-full p-3 border border-gray-300 rounded-lg">
        <option value="">-- Elegir préstamo --</option>
        <c:forEach var="prestamo" items="${prestamos}">
          <option value="${prestamo.id}">Préstamo ${prestamo.id}</option>
        </c:forEach>
      </select>
    </div>

    <!-- Cuotas por préstamo -->
    <c:forEach var="prestamo" items="${prestamos}">
      <div id="prestamo-${prestamo.id}" class="tabla-cuotas hidden">
        <h3 class="text-lg font-semibold text-red-600 mb-3">Cuotas pendientes - Préstamo ${prestamo.id}</h3>

        <!-- Resumen -->
        <div class="mb-4 bg-red-50 border-l-4 border-red-600 p-4 rounded-md shadow-sm">
          <p class="font-semibold text-red-700">Resumen del préstamo:</p>
          <ul class="text-sm mt-1 text-gray-700 list-disc pl-5">
            <li>Cuotas pendientes: <strong>${prestamo.cantidadCuotas}</strong></li>
            <li>Monto total adeudado: <strong>$${prestamo.montoTotalAdeudado}</strong></li>
          </ul>
        </div>

				<!-- Lista de cuotas -->
				<div class="max-h-96 overflow-y-auto pr-2">
					<ul class="space-y-4">
						<c:forEach var="cuota" items="${prestamo.cuotasPendientes}">
							<li
								class="flex justify-between items-center border p-4 rounded-lg 
                         ${cuota.id == prestamo.primeraCuotaId ? 'bg-gray-50' : 'bg-gray-100 opacity-60'}">
								<div>
									<p class="font-medium">Cuota ${cuota.numero} -
										$${cuota.monto}</p>
									<p class="text-sm text-gray-500">Vence:
										${cuota.fechaVencimiento}</p>
								</div> <c:choose>
									<c:when test="${cuota.id == prestamo.primeraCuotaId}">
										<form action="PagarCuotaServlet" method="post"
											class="flex flex-col gap-2">
											<input type="hidden" name="cuotaId" value="${cuota.id}">

											<!-- Selector de cuentas -->
											<label for="cuenta-${cuota.id}"
												class="text-sm font-medium text-gray-700">Seleccionar
												cuenta para pago:</label> <select name="cuentaId"
												id="cuenta-${cuota.id}" required
												class="border border-gray-300 rounded px-2 py-1">
												<option value="">-- Elegir cuenta --</option>
												<c:forEach var="cuenta" items="${cuentas}">
													<option value="${cuenta.id}">${cuenta.nombre}-
														${cuenta.numero}</option>
												</c:forEach>
											</select>

											<button type="submit"
												class="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded-lg shadow">
												Pagar</button>
										</form>
									</c:when>
									</form>
									
									<c:otherwise>
										<button disabled
											class="bg-gray-300 text-gray-600 px-4 py-2 rounded-lg cursor-not-allowed">
											No disponible</button>
									</c:otherwise>
								</c:choose>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
    </c:forEach>
  </div>
</body>
<div class="mt-6 text-center">
      <a href="menuPagoPrestamos.jsp" class="text-red-600 hover:underline">← Volver al menú</a>
    </div>

</html>

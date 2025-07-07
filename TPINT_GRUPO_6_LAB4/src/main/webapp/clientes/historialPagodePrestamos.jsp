<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Historial de Pagos</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen py-10 px-4 text-gray-800">
	<div class="max-w-4xl mx-auto bg-white p-8 rounded-2xl shadow-lg">

		<!-- Formulario de selección de préstamo -->


		<form action="<%=request.getContextPath()%>/PrestamoServlet"
			method="post" class="mb-6">
			<label class="text-gray-700 font-semibold block mb-2">
				Seleccione un préstamo: </label> <select name="idPrestamo"
				class="w-full rounded-lg p-2 border border-gray-300">
				<option value="-1" disabled
					<c:if test="${empty idPrestamoSeleccionado}">selected</c:if>>
					-- Seleccionar --</option>
				<c:forEach var="prestamo" items="${prestamos}">
					<!-- <option value="${prestamo.idPrestamo}">Préstamo-->
					<option value="${prestamo.idPrestamo}"
						<c:if test="${prestamo.idPrestamo == idPrestamoSeleccionado}">selected</c:if>>

						#${prestamo.idPrestamo} - Monto: $${prestamo.montoPedido}</option>
				</c:forEach>
			</select>

			<button type="submit"
				class="mt-4 block bg-white text-[#D14444] font-semibold text-center py-3 rounded-lg shadow hover:bg-gray-50 transition w-full"
				name="btnmostrar">Ver Historial</button>
		</form>


		<!-- Título -->
		<h2 class="text-3xl font-bold text-red-700 text-center mb-6">Historial
			de Pagos</h2>

		<!-- Tabla de pagos -->
		<table
			class="w-full border border-gray-300 rounded-lg overflow-hidden text-left">
			<thead class="bg-red-600 text-white">
				<tr>
					<th class="px-4 py-2">Cuota</th>
					<th class="px-4 py-2">Monto</th>
					<th class="px-4 py-2">Fecha de Pago</th>
					<th class="px-4 py-2">Nº de Cuenta</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cuota" items="${cuotas}">
					<tr class="border-t border-gray-300">
						<td class="px-4 py-2">${cuota.numeroCuota}</td>
						<td class="px-4 py-2">$${cuota.importe}</td>
						<td class="px-4 py-2">${cuota.fechaPago}</td>
						<td class="px-4 py-2"><c:choose>
								<c:when test="${cuota.numeroCuenta != null}">
          					Cuenta #${cuota.numeroCuenta}
     					  </c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<c:if test="${not empty cuotas}">
			<div class="mt-6 flex justify-center items-center">
				<nav
					class="relative z-0 inline-flex rounded-md shadow-sm -space-x-px"
					aria-label="Pagination">
					<c:forEach begin="1"
						end="${(totalCuotas / tamanoPagina) + (totalCuotas % tamanoPagina == 0 ? 0 : 1)}"
						var="i">
						<form action="PrestamoServlet" method="post" class="inline">
							<input type="hidden" name="idPrestamo"
								value="${idPrestamoSeleccionado}" /> <input type="hidden"
								name="page" value="${i}" />
							<button type="submit"
								class="px-3 py-1 mx-1 rounded-lg border 
                  ${paginaActual == i ? 'bg-red-600 text-white' : 'bg-white text-red-600 border-red-600'}">
								${i}</button>
						</form>
					</c:forEach>
				</nav>
			</div>
		</c:if>
		<!-- Volver al menú -->
		<div class="mt-6 text-center">
			<a
				href="<%=request.getContextPath()%>/clientes/menuPagoPrestamos.jsp"
				class="text-red-600 hover:underline">← Volver al menú</a>
		</div>
	</div>
</body>
</html>





<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<form action="historialPagoPrestamos.jsp" method="get" class="mb-6">
			<label class="text-gray-700 font-semibold block mb-2">Seleccione
				un préstamo:</label> <select name="idPrestamo"
				class="w-full rounded-lg p-2 border border-gray-300">
				<c:forEach var="prestamo" items="${prestamos}">
					<option value="${prestamo.id}">Préstamo #${prestamo.id} -
						Monto: $${prestamo.monto}</option>
				</c:forEach>
			</select>
			<form method="post" action="servletPrestamo">
				<button type="submit"
					class="mt-4 block bg-white text-[#D14444] font-semibold text-center py-3 rounded-lg shadow hover:bg-gray-50 transition w-full"
					name="btnmostra">Ver Historial</button>
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
						<th class="px-4 py-2">Cuenta</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pago" items="${pagos}">
						<tr class="border-t border-gray-300">
							<td class="px-4 py-2">${pago.numeroCuota}</td>
							<td class="px-4 py-2">$${pago.monto}</td>
							<td class="px-4 py-2">${pago.fechaPago}</td>
							<td class="px-4 py-2">${pago.cuenta}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- Paginador -->
			<div class="mt-6 flex justify-center items-center">
				<nav
					class="relative z-0 inline-flex rounded-md shadow-sm -space-x-px"
					aria-label="Pagination">
					<a href="#"
						class="relative inline-flex items-center px-2 py-2 rounded-l-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
						<span class="sr-only">Previous</span>
					</a> <a href="#" aria-current="page"
						class="z-10 bg-red-100 border-red-700 text-red-800 relative inline-flex items-center px-4 py-2 text-sm font-medium border">
						1 </a> <a href="#"
						class="bg-white border-gray-300 text-gray-500 hover:bg-gray-50 relative inline-flex items-center px-4 py-2 text-sm font-medium border">
						2 </a> <a href="#"
						class="bg-white border-gray-300 text-gray-500 hover:bg-gray-50 relative inline-flex items-center px-4 py-2 text-sm font-medium border">
						3 </a> <a href="#"
						class="relative inline-flex items-center px-2 py-2 rounded-r-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
						<span class="sr-only">Next</span>
					</a>
				</nav>
			</div>

			<!-- Volver al menú -->
			<div class="mt-6 text-center">
				<a href="menuPagoPrestamos.jsp" class="text-red-600 hover:underline">←
					Volver al menú</a>
			</div>
	</div>
</body>
</html>





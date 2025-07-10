<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.util.List"%>
<%@ page import="entidad.Prestamo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Préstamos</title>

<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<script src="https://cdn.tailwindcss.com"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/2.0.8/js/dataTables.min.js"></script>

<style>
body {
	font-family: 'Montserrat', sans-serif;
}

table {
	font-family: 'Montserrat', sans-serif;
	font-size: 0.9rem;
}

#prestamosDataTable th, #prestamosDataTable td {
	text-align: center !important;
}
</style>
</head>
<body class="flex flex-col min-h-screen">

	<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

	<main class="flex-grow bg-gray-100">

	<%
	String mensajeExito = (String) request.getAttribute("mensajeExito");
	String mensajeError = (String) request.getAttribute("mensajeError");
	List<Prestamo> listaPrestamosPendientes = (List<Prestamo>) request.getAttribute("prestamosPendientes");
	%>

	<!-- Mensajes -->
	<div class="max-w-3xl mx-auto mt-4">
		<%
		if (mensajeExito != null) {
		%>
		<div
			class="bg-green-100 text-green-800 p-4 rounded-lg mb-4 border border-green-300 text-center">
			<%=mensajeExito%>
		</div>
		<%
		} else if (mensajeError != null) {
		%>
		<div
			class="bg-red-100 text-red-800 p-4 rounded-lg mb-4 border border-red-300 text-center">
			<%=mensajeError%>
		</div>
		<%
		}
		%>
	</div>

	<header class="mb-8"></header>
	<h1
		class="text-3xl font-semibold text-center border-b pb-3 text-gray-800">
		Autorizar o Rechazar Préstamos</h1>

	<div class="max-w-6xl mx-auto bg-white p-6 rounded-xl shadow-md">
		<h2 class="text-lg font-semibold mb-6 border-b pb-2 text-gray-700">Detalles
			de la Solicitud</h2>
		<div class="overflow-x-auto">
			<table id="prestamosDataTable"
				class="min-w-full table-auto border border-gray-300 text-center text-sm">
				<thead class="bg-red-600 text-white">
					<tr>
						<th class="py-3 px-4 border">Cuenta Destino</th>
						<th class="py-3 px-4 border">Fecha Creación</th>
						<th class="py-3 px-4 border">Fecha Fin</th>
						<th class="py-3 px-4 border">Plazo</th>
						<th class="py-3 px-4 border">Monto Solicitado</th>
						<th class="py-3 px-4 border">Monto a Pagar</th>
						<th class="py-3 px-4 border">Cuotas</th>
						<th class="py-3 px-4 border">Importe por Cuota</th>
						<th class="py-3 px-4 border">Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (listaPrestamosPendientes != null && !listaPrestamosPendientes.isEmpty()) {
						for (Prestamo prestamo : listaPrestamosPendientes) {
							LocalDate fechaInicio = prestamo.getFechaSolicitud().toLocalDate();
							LocalDate fechaFin = fechaInicio.plusMonths(prestamo.getCantidadCuotas());
					%>
					<tr class="hover:bg-gray-50">
						<td class="py-3 px-4 border"><%=prestamo.getCuentaAcreditada().getCbu()%></td>
						<td class="py-3 px-4 border"><%=fechaInicio%></td>
						<td class="py-3 px-4 border"><%=fechaFin%></td>
						<td class="py-3 px-4 border"><%=prestamo.getCantidadCuotas()%>
							meses</td>
						<td class="py-3 px-4 border"><%=prestamo.getImportePedido()%></td>
						<td class="py-3 px-4 border"><%=prestamo.getImporteAPagar()%></td>
						<td class="py-3 px-4 border"><%=prestamo.getCantidadCuotas()%></td>
						<td class="py-3 px-4 border"><%=prestamo.getImporteCuota()%></td>
						<td class="py-3 px-4 border">
							<div class="flex justify-center gap-4">
								<button type="button"
									class="text-green-600 hover:text-green-700 text-xl"
									onclick="abrirModal('modalAprobar', <%=prestamo.getIDPrestamo()%>)"
									title="Autorizar">
									<i class="fa-regular fa-circle-check"></i>
								</button>
								<button type="button"
									class="text-red-600 hover:text-red-700 text-xl"
									onclick="abrirModal('modalRechazar', <%=prestamo.getIDPrestamo()%>)"
									title="Rechazar">
									<i class="fa-regular fa-circle-xmark"></i>
								</button>
							</div>
						</td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="9"
							class="py-3 px-4 text-sm text-gray-500 text-center">No se
							encontraron préstamos pendientes.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Botón volver -->
	<div class="max-w-6xl mx-auto text-end mt-4">
		<a href="<%=request.getContextPath()%>/admin/menuAdministrador.jsp"
			class="text-sm text-red-600 hover:underline"> ← Volver </a>
	</div>

	<!-- Modal Aprobar -->
	<div id="modalAprobar"
		class="hidden fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
		<div class="bg-white p-6 rounded-lg shadow-lg w-96">
			<h2 class="text-xl font-semibold mb-4 text-center">¿Está seguro
				de aprobar el préstamo?</h2>
			<form method="post"
				action="<%=request.getContextPath()%>/GestionDePrestamosServlet">
				<input type="hidden" name="idPrestamoAprobado" id="inputIdAprobar" />
				<div class="flex justify-center gap-2 mt-4">
					<button type="button" onclick="cerrarModal('modalAprobar')"
						class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-semibold py-2 px-4 rounded">
						Cancelar</button>
					<button type="submit"
						class="bg-green-600 hover:bg-green-700 text-white font-semibold py-2 px-4 rounded">
						Sí, aprobar</button>
				</div>
			</form>
		</div>
	</div>

	<!-- Modal Rechazar -->
	<div id="modalRechazar"
		class="hidden fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
		<div class="bg-white p-6 rounded-lg shadow-lg w-96">
			<h2 class="text-xl font-semibold mb-4 text-center">¿Está seguro
				de rechazar el préstamo?</h2>
			<form method="post"
				action="<%=request.getContextPath()%>/GestionDePrestamosServlet">
				<input type="hidden" name="idPrestamoRechazado" id="inputIdRechazar" />
				<div class="flex justify-center gap-2 mt-4">
					<button type="button" onclick="cerrarModal('modalRechazar')"
						class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-semibold py-2 px-4 rounded">
						Cancelar</button>
					<button type="submit"
						class="bg-red-600 hover:bg-red-700 text-white font-semibold py-2 px-4 rounded">
						Sí, rechazar</button>
				</div>
			</form>
		</div>
	</div>

	<script>
	function abrirModal(modalId, idPrestamo) {
		if (modalId === 'modalAprobar') {
			document.getElementById('inputIdAprobar').value = idPrestamo;
		} else if (modalId === 'modalRechazar') {
			document.getElementById('inputIdRechazar').value = idPrestamo;
		}
		document.getElementById(modalId).classList.remove('hidden');
	}

	function cerrarModal(modalId) {
		document.getElementById(modalId).classList.add('hidden');
	}

	// DataTable
	$(document).ready(function () {
		$('#prestamosDataTable').DataTable({
			language: {
				url: 'https://cdn.datatables.net/plug-ins/2.0.0/i18n/es-ES.json'
			}
		});
	});
</script>

	</main>

	<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
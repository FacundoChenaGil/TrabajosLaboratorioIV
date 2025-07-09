<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entidad.Cuenta"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Cuentas Bancarias</title>
<script src="https://cdn.tailwindcss.com"></script>
<script>
	tailwind.config = {
		theme : {
			extend : {
				colors : {
					'bordo-dark' : '#800020',
					'bordo-medium' : '#A52A2A',
					'bordo-light-bg' : '#FFF0F0',
					'bordo-border' : '#E2E8F0',
				}
			}
		}
	}
</script>
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.min.css">

<style>
body {
	font-family: 'Montserrat', sans-serif;
}

table {
    font-family: 'Montserrat', sans-serif;
    font-size: 0.9rem;
}


::-webkit-scrollbar {
	width: 8px;
	height: 8px;
}

::-webkit-scrollbar-track {
	background: #f1f1f1;
	border-radius: 10px;
}

::-webkit-scrollbar-thumb {
	background: #888;
	border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
	background: #555;
}


#modalConfirmacion {
	display: none;
	position: fixed;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	z-index: 1000;
	justify-content: center;
	align-items: center;
}

#modalConfirmacion > div {
	background-color: white;
	padding: 30px;
	border-radius: 8px;
	text-align: center;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
	max-width: 400px;
	width: 90%;
	display: flex;
	flex-direction: column;
	gap: 20px;
}

#modalConfirmacion button {
	cursor: pointer;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	font-weight: 600;
	transition: background-color 0.2s ease;
}

#modalConfirmacion button:hover {
	opacity: 0.9;
}

#modalConfirmacion button:first-of-type {
	background-color: #ef4444;
	color: white;
}

#modalConfirmacion button:last-of-type {
	background-color: #e5e7eb;
	color: #374151;
}


.action-icon-button {
	display: inline-flex;
	align-items: center;
	justify-content: center;
	padding: 0.5rem;
	border-radius: 0.375rem;
	transition: background-color 0.15s ease-in-out;
}

.action-icon-button:hover {
	background-color: #e5e7eb;
}
</style>
</head>
<body class="flex flex-col min-h-screen">

  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

  <main class="flex-grow bg-gray-100">

	<%
	List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
	int totalCuentas = 0;
	int cuentasActivas = 0;
	int cuentasInactivas = 0;

	if (cuentas != null) {
		totalCuentas = cuentas.size();
		for (Cuenta c : cuentas) {
			if (c.isActiva()) {
		cuentasActivas++;
			}
		}
		cuentasInactivas = totalCuentas - cuentasActivas;
	}

	%>

	<main class="flex-1 p-5 overflow-y-auto max-w-7xl mx-auto">
		<div class="flex justify-between items-center mb-6">
			<h1 class="text-xl font-bold text-gray-800">Gestión de Cuentas
				Bancarias</h1>
			<a href="/TPINT_GRUPO_6_LAB4/AltaCuentaServlet"
				class="bg-red-600 text-white px-4 py-2 rounded-md text-sm font-semibold hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-700 focus:ring-offset-2 flex items-center space-x-2">
				<i class="fa-solid fa-plus fa-sm" style="color: #ffffff;"></i> <span>Generar
					Cuenta</span>
			</a>
		</div>

		<div class="grid grid-cols-1 md:grid-cols-3 gap-3 mb-5">
			<div
				class="bg-red-700 p-4 rounded-lg text-white shadow-lg flex flex-col items-center justify-center">
				<p class="text-xs opacity-90">Total Cuentas</p>
				<p class="text-2xl font-bold"><%=totalCuentas%></p>
			</div>

			<div
				class="bg-red-700 p-4 rounded-lg text-white shadow-lg flex flex-col items-center justify-center">
				<p class="text-xs opacity-90">Cuentas Activas</p>
				<p class="text-2xl font-bold"><%=cuentasActivas%></p>
			</div>

			<div
				class="bg-red-700 p-4 rounded-lg text-white shadow-lg flex flex-col items-center justify-center">
				<p class="text-xs opacity-90">Cuentas Inactivas</p>
				<p class="text-2xl font-bold"><%=cuentasInactivas%></p>
			</div>
		</div>
		
		<div class="bg-white p-6 rounded-lg shadow-md overflow-x-auto">
			<table id="cuentasDataTable"
				class="min-w-full divide-y divide-gray-200">
				<thead class="bg-gray-50">
					<tr>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Acciones</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Número
							de Cuenta</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">CBU</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Fecha
							de Creación</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Saldo</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Tipo
							de Cuenta</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nombre
							y Apellido</th>
						<th
							class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Estado</th>
					</tr>
				</thead>
				<tbody class="bg-white divide-y divide-gray-200">
					<%
					if (cuentas != null && !cuentas.isEmpty()) {
						for (Cuenta c : cuentas) {
					%>
					<tr>
						<td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
							<form action="${pageContext.request.contextPath}/CuentaServlet"
								method="get" class="inline-block">
								<input
									type="hidden" name="cbu" value="<%=c.getCbu()%>">
								<button type="submit" class="action-icon-button">
									<i class="fa-regular fa-pen-to-square fa-xl"
										style="color: #fa0000;"></i>
								</button>
							</form>
							
							<button type="button"
								onclick="confirmarEliminar('<%=c.getCbu()%>');"
								class="action-icon-button ml-2">
								<i class="fa-regular fa-trash-can fa-xl" style="color: #fa0000;"></i>
							</button>

						</td>
						<td class="px-6 py-4 text-sm text-gray-900"><%=c.getNumeroCuenta()%></td>
						<td class="px-6 py-4 text-sm text-gray-900"><%=c.getCbu()%></td>
						<td class="px-6 py-4 text-sm text-gray-900"><%=c.getFechaCreacion()%></td>
						<td class="px-6 py-4 text-sm text-gray-900">$<%=String.format("%.2f", c.getSaldo())%></td>
						<td class="px-6 py-4 text-sm text-gray-900"><%=c.getTipoCuenta() != null ? c.getTipoCuenta().getDescripcion() : "N/A"%></td>
						<td class="px-6 py-4 text-sm text-gray-900"><%=c.getCliente() != null ? c.getCliente().getNombre() + " " + c.getCliente().getApellido() : "N/A"%></td>
						<td class="px-6 py-4 text-sm text-gray-900"><%=c.isActiva() ? "Activa" : "Inactiva"%></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="8"
							class="px-6 py-4 text-sm text-gray-500 text-center">No se
							encontraron cuentas.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>

		<a href="<%=request.getContextPath()%>/admin/menuAdministrador.jsp"
			class="flex justify-end text-black underline mt-5">Volver</a>
	</main>

	<div id="modalConfirmacion">
		<div>
			<p class="text-lg font-semibold text-gray-800">¿Está seguro que
				desea eliminar esta cuenta?</p>
			<div class="flex justify-center space-x-4 mt-5">
				<input type="button" value="Sí"
					onclick="eliminarCuentaConfirmado();"
					class="bg-red-500 text-white px-6 py-2 rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2">
				<input type="button" value="No" onclick="cancelarEliminar();"
					class="bg-gray-200 text-gray-800 px-6 py-2 rounded-md hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-300 focus:ring-offset-2">
			</div>
		</div>
	</div>

	<form id="formEliminar"
		action="/TPINT_GRUPO_6_LAB4/CuentaServlet"
		method="post" style="display: none;">
		<input type="hidden" name="action" value="eliminar"> <input
			type="hidden" name="cbu" id="cbuEliminar">
	</form>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/2.0.8/js/dataTables.min.js"></script>

	<script>
		let cbuAEliminar = '';
		const modal = document.getElementById('modalConfirmacion');

		function confirmarEliminar(cbu) {
			cbuAEliminar = cbu;
			modal.style.display = 'flex';
		}

		function eliminarCuentaConfirmado() {
		    document.getElementById('cbuEliminar').value = cbuAEliminar;
		    document.getElementById('formEliminar').submit();
		    modal.style.display = 'none';
		}
		
		function cancelarEliminar() {
			cbuAEliminar = '';
			modal.style.display = 'none';
		}

		$(document)
				.ready(
						function() {
							$('#cuentasDataTable')
									.DataTable(
											{
												language : {
													url: 'https://cdn.datatables.net/plug-ins/2.0.0/i18n/es-ES.json'
												}
											});
							modal.style.display = 'none';
						});
	</script>

  </main>

  <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
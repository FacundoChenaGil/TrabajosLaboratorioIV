
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte de Clientes Nuevos</title>
<script src="https://cdn.tailwindcss.com"></script>
<script>
	tailwind.config = {
		theme : {
			extend : {
				colors : {
					'custom-red' : '#DD1A2F',
				}
			}
		}
	}
</script>
</head>
<body class="flex flex-col min-h-screen">

	<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

	<main class="flex-grow bg-gray-50 p-8">
		<div
			class="max-w-6xl mx-auto bg-white rounded-lg shadow-xl p-8 border-t-4 border-red-700">
			<h1 class="text-3xl font-bold text-gray-800 mb-6 flex items-center">
				<i class="fa-solid fa-paperclip fa-xs mr-3 text-red-700"></i>
				Reporte de Clientes Nuevos
			</h1>

			<div class="mb-6">
				<a href="${pageContext.request.contextPath}/admin/reportes.jsp"
					class="inline-block bg-custom-red text-white px-4 py-2 rounded hover:bg-red-700 transition">
					← Volver al Panel de Reportes </a>
			</div>

			<!-- Formulario -->
			<form
				action="${pageContext.request.contextPath}/ReporteClientesNuevos"
				method="get"
				class="bg-red-700 p-6 rounded-lg mb-8 shadow-md text-white">
				<h2 class="text-xl font-semibold mb-4">Seleccionar Período</h2>

				<div class="grid grid-cols-1 md:grid-cols-3 gap-4 items-end">
					<div>
						<label for="fechaInicio" class="block text-sm font-medium mb-1">Fecha
							de Inicio:</label> <input type="date" id="fechaInicio" name="fechaInicio"
							value="${fechaInicio}"
							class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm text-gray-900 sm:text-sm">
					</div>
					<div>
						<label for="fechaFin" class="block text-sm font-medium mb-1">Fecha
							de Fin:</label> <input type="date" id="fechaFin" name="fechaFin"
							value="${fechaFin}"
							class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm text-gray-900 sm:text-sm">
					</div>
					<div>
						<button type="submit" name="btnAceptar"
							class="w-full bg-white text-red-800 py-2 px-4 rounded-md font-semibold hover:bg-gray-100 transition duration-150 ease-in-out">
							Generar Reporte</button>
					</div>
				</div>

				<c:if test="${not empty error}">
					<p class="mt-4 text-yellow-200 font-semibold">${error}</p>
				</c:if>
			</form>

			<!-- Resultados -->

			<c:if
				test="${not empty fechaInicio and not empty fechaFin and empty clientesNuevos}">
				<div
					class="bg-yellow-100 border-l-4 border-yellow-500 text-yellow-700 p-4 mt-6 rounded">
					<p class="font-semibold">No se encontraron clientes nuevos en
						el período seleccionado.</p>
				</div>
			</c:if>


			<c:if test="${not empty clientesNuevos}">
				<div class="bg-white p-6 rounded-lg shadow-md">
					<h2 class="text-xl font-semibold text-gray-700 mb-4">
						Resultados del Reporte <span class="text-gray-500 text-base">(${fechaInicio}
							- ${fechaFin})</span>
					</h2>

					<div
						class="bg-red-100 p-4 rounded-md mb-6 flex items-center justify-between">
						<p class="text-red-800 text-lg font-medium">Total de Nuevos
							Clientes en el Período:</p>
						<p class="text-red-900 text-3xl font-bold">${cantidadClientes}</p>
					</div>

					<h3 class="text-lg font-semibold text-gray-700 mb-3">Detalles
						de Clientes</h3>
					<div class="overflow-x-auto rounded-lg border border-gray-200">
						<table class="min-w-full divide-y divide-gray-200">
							<thead class="bg-gray-50">
								<tr>
									<th
										class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">CBU</th>
									<th
										class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Nombre
										Completo</th>
									<th
										class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Fecha
										de Alta</th>
									<th
										class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Usuario</th>
								</tr>
							</thead>
							<tbody class="bg-white divide-y divide-gray-200">
								<c:forEach var="cli" items="${clientesNuevos}">
									<tr>
										<!-- CBU -->
										<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
											<c:choose>
												<c:when test="${cli.cuenta != null}">
         									 ${cli.cuenta.cbu}
       										 </c:when>
												<c:otherwise>
       										   (sin cbu)
        									</c:otherwise>
											</c:choose>
										</td>

										<!-- Nombre completo -->
										<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
											<c:out value="${cli.nombre}" default="(sin nombre)" /> <c:out
												value="${cli.apellido}" default="(sin apellido)" />
										</td>

										<!-- Fecha de alta -->
										<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
											<c:out value="${cli.fechaAlta}" default="(sin fecha)" />
										</td>

										<!-- Usuario -->
										<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
											<c:choose>
												<c:when test="${cli.usuario != null}">
         										 ${cli.usuario.usuario}
        									</c:when>
												<c:otherwise>
									          (sin usuario)
									        </c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="mt-6 flex flex-wrap justify-center space-x-2">
						<c:if test="${paginaActual > 1}">
							<form method="get"
								action="${pageContext.request.contextPath}/ReporteClientesNuevos">
								<input type="hidden" name="page" value="${paginaActual - 1}" />
								<input type="hidden" name="fechaInicio" value="${fechaInicio}" />
								<input type="hidden" name="fechaFin" value="${fechaFin}" />
								<button
									class="px-3 py-1 bg-custom-red text-white rounded hover:bg-red-800">Anterior</button>
							</form>
						</c:if>

						<c:forEach begin="1" end="${totalPaginas}" var="i">
							<form method="get"
								action="${pageContext.request.contextPath}/ReporteClientesNuevos">
								<input type="hidden" name="page" value="${i}" /> <input
									type="hidden" name="fechaInicio" value="${fechaInicio}" /> <input
									type="hidden" name="fechaFin" value="${fechaFin}" />
								<button
									class="px-3 py-1 rounded 
        <c:if test='${i == paginaActual}'>bg-red-700 text-white</c:if>
        <c:if test='${i != paginaActual}'>bg-gray-200 text-black</c:if>">
									${i}</button>
							</form>
						</c:forEach>

						<c:if test="${paginaActual < totalPaginas}">
							<form method="get"
								action="${pageContext.request.contextPath}/ReporteClientesNuevos">
								<input type="hidden" name="page" value="${paginaActual + 1}" />
								<input type="hidden" name="fechaInicio" value="${fechaInicio}" />
								<input type="hidden" name="fechaFin" value="${fechaFin}" />
								<button
									class="px-3 py-1 bg-custom-red text-white rounded hover:bg-red-800">Siguiente</button>
							</form>
						</c:if>
					</div>
					<!-- 🔼 Fin del bloque de paginación -->
				</div>
			</c:if>
		</div>
	</main>

	<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>

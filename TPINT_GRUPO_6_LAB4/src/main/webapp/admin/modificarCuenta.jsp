<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entidad.Cuenta"%>
<%@ page import="entidad.TiposDeCuentas"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Cuenta Bancaria</title>
<script src="https://cdn.tailwindcss.com"></script>
<script>
	tailwind.config = {
		theme : {
			extend : {
				colors : {
					'bordo-dark' : '#800020',
					'bordo-medium' : '#A52A2A',
					'bordo-light-bg' : '#FFF0F0', /* Este es el color para los inputs */
					'bordo-border' : '#E2E8F0',
				}
			}
		}
	}
</script>
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap"
	rel="stylesheet">
<style>
body {
	font-family: 'Montserrat', sans-serif;
}

::-webkit-scrollbar {
	width: 8px;
	height: 8px;
}

::-webkit-scrollbar-track {
	background: #e2e8f0;
	border-radius: 10px;
}

::-webkit-scrollbar-thumb {
	background: #888;
	border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
	background: #555;
}
</style>
</head>
<body class="flex flex-col min-h-screen">

  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

  <main class="flex-grow bg-gray-100 flex items-center justify-center p-5 text-gray-700">

	<%
	Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");
	List<TiposDeCuentas> listaTiposCuenta = (List<TiposDeCuentas>) request.getAttribute("tiposCuenta");
	int idTipoActual = cuenta.getTipoCuenta().getID();
	boolean isActivo = cuenta.isActiva();
	%>

	<div class="bg-white rounded-lg shadow-xl p-8 w-full max-w-lg">
		<h1 class="text-2xl font-bold mb-6 text-center text-bordo-dark">Modificar
			Cuenta Bancaria</h1>

		
		<%
		String mensajeError = (String) request.getAttribute("mensajeError");
		String mensajeExito = (String) request.getAttribute("mensajeExito");
		if (mensajeError != null) {
		%>
		<div
			class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-6"
			role="alert">
			<strong class="font-bold">Error: </strong> <span
				class="block sm:inline"><%=mensajeError%></span>
		</div>
		<%
		} else if (mensajeExito != null) {
		%>
		<div
			class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-6"
			role="alert">
			<strong class="font-bold">¡Éxito! </strong> <span
				class="block sm:inline"><%=mensajeExito%></span>
		</div>
		<%
		}
		%>

		<form action="CuentaServlet" method="post">
			<input type="hidden" name="action" value="modificar"> <input
				type="hidden" name="cuentaIdModificar" id="cuentaIdModificar"
				value="CUENTA_ID_EJEMPLO">

			<div class="mb-4">
				<label for="numeroCuentaModificar"
					class="block text-sm font-medium mb-1">Número de Cuenta:</label> <input
					type="text" id="numeroCuentaModificar" name="numeroCuentaModificar" pattern="\d+" required title="Solo se permiten números" minlength="13" maxlength="13"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                              focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                              placeholder-gray-400 transition duration-200 ease-in-out"
					value="<%=cuenta.getNumeroCuenta()%>" readonly>
			</div>

			<div class="mb-4">
				<label for="cbuModificar" class="block text-sm font-medium mb-1">CBU:</label>
				<input type="text" id="cbuModificar" name="cbuModificar" pattern="\d+" required title="Solo se permiten números" minlength="22" maxlength="22"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                              focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                              placeholder-gray-400 transition duration-200 ease-in-out"
					value="<%=cuenta.getCbu()%>" readonly>
			</div>

			<div class="mb-4">
				<label for="saldoModificar" class="block text-sm font-medium mb-1">Saldo:</label>
				<input type="number" step="0.01" min="0" id="saldoModificar"
					name="saldoModificar"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                              focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                              placeholder-gray-400 transition duration-200 ease-in-out"
					value=<%=cuenta.getSaldo()%>>
			</div>

			<div class="mb-4">
				<label for="fechaCreacionModificar"
					class="block text-sm font-medium mb-1">Fecha de Creación:</label> <input
					type="date" id="fechaCreacionModificar"
					name="fechaCreacionModificar" min="1900-01-01" required
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                              focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                              placeholder-gray-400 transition duration-200 ease-in-out"
					value="<%=cuenta.getFechaCreacion()%>">
			</div>

			<div class="mb-4">
				<label for="dniModificar" class="block text-sm font-medium mb-1">DNI:</label>
				<input type="text" id="dniModificar"  pattern="\d{8}" title="Debe ingresar 8 números" maxlength="8" minlength="7" required name="dniModificar"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                              focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                              placeholder-gray-400 transition duration-200 ease-in-out"
					value="<%=cuenta.getCliente().getDni()%>">
				<input type="hidden" name="dniOriginal" value="<%= cuenta.getCliente().getDni() %>">
			</div>

			<div class="mb-4">
				<label for="tipoCuentaModificar"
					class="block text-sm font-medium mb-1">Tipo de Cuenta:</label> <select
					id="tipoCuentaModificar" name="tipoCuentaModificar"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                               focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                               transition duration-200 ease-in-out">
					<%
					for (TiposDeCuentas tipo : listaTiposCuenta) {
						boolean isSelected = tipo.getID() == idTipoActual;
					%>
					<option value="<%=tipo.getID()%>" <%=isSelected ? "selected" : ""%>><%=tipo.getDescripcion()%></option>
					<%
					}
					%>
				</select>
			</div>

			<div class="mb-6">
				<label for="estado" class="block text-sm font-medium mb-1">Estado:</label>
				<select id="estado" name="estado"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                               focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                               transition duration-200 ease-in-out">
					<option value="0" <%=!isActivo ? "selected" : ""%>>Inactiva</option>
					<option value="1" <%=isActivo ? "selected" : ""%>>Activa</option>
				</select>
			</div>

			<div class="flex justify-end space-x-4 mt-6">
				<div class="flex-1">
					<a href="/TPINT_GRUPO_6_LAB4/CuentaServlet?Param=mostrarTodo"
						class="inline-flex justify-center items-center w-full px-6 py-3 border border-bordo-dark 
                              rounded-md text-sm font-bold text-bordo-dark bg-white 
                              hover:bg-bordo-light-bg hover:shadow-md 
                              focus:outline-none focus:ring-4 focus:ring-bordo-dark focus:ring-opacity-20 
                              transition duration-150 ease-in-out">Volver</a>
				</div>
				<div class="flex-1">
					<input type="submit" value="Modificar"
						class="inline-flex justify-center items-center w-full px-6 py-3 border border-transparent 
                               rounded-md text-sm font-bold text-white bg-bordo-dark hover:bg-bordo-medium hover:shadow-md 
                               focus:outline-none focus:ring-4 focus:ring-bordo-dark focus:ring-opacity-40 
                               transition duration-150 ease-in-out cursor-pointer">
				</div>
			</div>
		</form>
	</div>

  </main>

  <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
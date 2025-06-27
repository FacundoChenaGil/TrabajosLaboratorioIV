<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="entidad.Cuenta"  %>
	<%@ page import="entidad.TiposDeCuentas"  %>
	<%@ page import="java.util.List"  %>
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
<body
	class="bg-gray-100 min-h-screen flex items-center justify-center p-5 text-gray-700">

	<%
	Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");
	List<TiposDeCuentas> listaTiposCuenta = (List<TiposDeCuentas>) request.getAttribute("tiposCuenta");
	int idTipoActual = cuenta.getTipoCuenta().getID();
	boolean isActivo = cuenta.isActiva();
	%>

	<div class="bg-white rounded-lg shadow-xl p-8 w-full max-w-lg">
		<h1 class="text-2xl font-bold mb-6 text-center text-bordo-dark">Modificar
			Cuenta Bancaria</h1>

		<form action="CuentaServlet" method="post">
			<input type="hidden" name="action" value="modificar"> <input
				type="hidden" name="cuentaIdModificar" id="cuentaIdModificar"
				value="CUENTA_ID_EJEMPLO">

			<div class="mb-4">
				<label for="numeroCuentaModificar"
					class="block text-sm font-medium mb-1">Número de Cuenta:</label> <input
					type="text" id="numeroCuentaModificar" name="numeroCuentaModificar"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                              focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                              placeholder-gray-400 transition duration-200 ease-in-out"
					value=<%=cuenta.getNumeroCuenta() %>>
			</div>

			<div class="mb-4">
				<label for="cbuModificar" class="block text-sm font-medium mb-1">CBU:</label>
				<input type="text" id="cbuModificar" name="cbuModificar"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                              focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                              placeholder-gray-400 transition duration-200 ease-in-out"
					value=<%=cuenta.getCBU() %>>
			</div>

			<div class="mb-4">
				<label for="saldoModificar" class="block text-sm font-medium mb-1">Saldo:</label>
				<input type="number" step="0.01" min="0" id="saldoModificar"
					name="saldoModificar"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                              focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                              placeholder-gray-400 transition duration-200 ease-in-out"
					value=<%=cuenta.getSaldo() %>>
			</div>

			<div class="mb-4">
				<label for="fechaCreacionModificar"
					class="block text-sm font-medium mb-1">Fecha de Creación:</label> <input
					type="date" id="fechaCreacionModificar"
					name="fechaCreacionModificar"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                              focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                              placeholder-gray-400 transition duration-200 ease-in-out"
					value="<%=cuenta.getFechaCreacion() %>">
			</div>

			<div class="mb-4">
				<label for="dniModificar" class="block text-sm font-medium mb-1">DNI:</label>
				<input type="text" id="dniModificar" name="dniModificar"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                              focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                              placeholder-gray-400 transition duration-200 ease-in-out"
					value="<%=cuenta.getCliente().getDni() %>">
			</div>

			<div class="mb-4">
				<label for="tipoCuentaModificar"
					class="block text-sm font-medium mb-1">Tipo de Cuenta:</label> <select
					id="tipoCuentaModificar" name="tipoCuentaModificar"
					class="block w-full px-3 py-2 rounded-md bg-bordo-light-bg border border-bordo-border 
                               focus:border-bordo-dark focus:ring-3 focus:ring-bordo-dark focus:ring-opacity-20 outline-none
                               transition duration-200 ease-in-out">
					<%
					for(TiposDeCuentas tipo : listaTiposCuenta) {
						boolean isSelected = tipo.getID() == idTipoActual;				
					%>
						<option value="<%=tipo.getID() %>" <%=isSelected ? "selected" : "" %>><%=tipo.getDescripcion() %></option>
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
					<option value="0" <%= !isActivo ? "selected" : "" %>>Inactiva</option>
					<option value="1" <%= isActivo ? "selected" : "" %>>Activa</option>
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
                                  rounded-md text-sm font-bold text-white bg-bordo-dark 
                                  hover:bg-bordo-medium hover:shadow-md 
                                  focus:outline-none focus:ring-4 focus:ring-bordo-dark focus:ring-opacity-40 
                                  transition duration-150 ease-in-out cursor-pointer">
				</div>
			</div>
		</form>
	</div>

</body>
</html>
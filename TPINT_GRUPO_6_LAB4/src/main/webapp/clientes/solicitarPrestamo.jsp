<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.CuentaPrestamoddlDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Solicitar Préstamo</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <%-- This scriptlet retrieves the account list passed from the servlet --%>
    <% 
        List<CuentaPrestamoddlDTO> listaCuentas = (List<CuentaPrestamoddlDTO>) request.getAttribute("listaCuentasDDL");
    %>

    <main class="flex-grow flex items-center justify-center py-12 px-6">
        <div class="max-w-md w-full bg-white p-8 rounded-xl shadow-lg">
            <div class="text-center mb-8">
                <h1 class="text-3xl font-bold text-[#E75E5E]">Solicitud de Préstamo</h1>
                <p class="text-gray-600 mt-2">Simulá tu préstamo y obtené resultados inmediatos.</p>
            </div>

            <form action="<%= request.getContextPath() %>/SolicitarPrestamoServlet" method="post" class="space-y-6">
                
                <div>
                    <label for="cuenta" class="block text-sm font-medium text-gray-700 mb-1">Seleccionar Cuenta de Depósito</label>
                    <select name="cuentaSeleccionada" id="cuenta" class="w-full border-gray-300 rounded-md shadow-sm focus:border-red-500 focus:ring-red-500">
                        <% if (listaCuentas != null && !listaCuentas.isEmpty()) { 
                            for (entidad.CuentaPrestamoddlDTO cuenta : listaCuentas) { %>
                                <option value="<%= cuenta.getNumeroCuenta() %>">
                                    Nº <%= cuenta.getNumeroCuenta() %> (CBU: <%= cuenta.getCBU() %>)
                                </option>
                        <%  } 
                           } else { %>
                            <option disabled selected>No hay cuentas disponibles para recibir el préstamo</option>
                        <% } %>
                    </select>
                </div>

                <div>
                    <label for="Monto_Solicitar" class="block text-sm font-medium text-gray-700 mb-1">Monto a Solicitar</label>
                    <input type="number" id="Monto_Solicitar" name="Monto_Solicitar" step="100" min="1000" required
                           class="w-full border-gray-300 rounded-md shadow-sm focus:border-red-500 focus:ring-red-500" 
                           placeholder="Ej: 50000">
                </div>

		<form action="<%= request.getContextPath() %>/PrestamoServlet" method="get" class="flex flex-col justify-center items-center gap-[35px]">
		<input type="hidden" name="accion" value="mostrar">
			<div
				class="flex flex-col gap-[21px] px-[21px] py-[38px] bg-[#E75E5E] rounded-[9px] w-[292px]"
				style="box-shadow: 7px 8px 4px rgba(0, 0, 0, 0.25)">
				<div class="flex flex-col">
					<label for="Seleccionar_Cuenta">Seleccionar Cuenta:</label>
					 <select name="cuentaSeleccionada" id="cuentaSeleccionada" class="w-full border px-3 py-2 rounded">
				        <% if (listaCuentas != null && !listaCuentas.isEmpty()) {
				            for (entidad.CuentaPrestamoddlDTO cuenta : listaCuentas) { %>
				                <option value="<%= cuenta.getCBU() %>">
				                    CBU: <%= cuenta.getCBU() %> - Nº Cuenta: <%= cuenta.getNumeroCuenta() %>
				                </option>
				        <%  }
				           } else { %>
				            <option disabled selected>No hay cuentas disponibles</option>
				        <% } %>
				    </select>
				</div>
				<div class=" flex flex-col">
					<label for="Monto_Solicitar">Monto a Solicitar:</label> <input
						type="text" id="Monto_Solicitar" name="Monto_Solicitar"
						class="w-[250px] h-[22px] rounded-[5px] border border-black"></input>
				</div>
				<div class="flex flex-col">
					<label for="Cantidad_Cuotas" name="Cantidad_Cuotas">Cantidad de Cuotas:</label> <Select
						for="Cantidad_Cuotas">
							<option value="6">6</option>
							<option value="12">12</option>
							<option value="18">18</option>
							<option value="24">24</option>
						</Select>
				</div>
			</div>
			<input type="submit" value="Solicitar"
				class="flex w-[188px] h-[53px] rounded-[10px] bg-[#E75E53] text-white font-semibold hover:bg-[#cc4b40] hover:cursor-pointer transition duration-300 shadow"
				style="box-shadow: 5px 6px 4px rgba(0, 0, 0, 0.25)"></input>
		</form>
    </main>
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
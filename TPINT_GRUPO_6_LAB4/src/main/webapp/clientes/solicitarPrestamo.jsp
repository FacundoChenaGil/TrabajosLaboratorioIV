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

                <div>
                    <label for="Cantidad_Cuotas" class="block text-sm font-medium text-gray-700 mb-1">Cantidad de Cuotas</label>
                    <select id="Cantidad_Cuotas" name="Cantidad_Cuotas" required class="w-full border-gray-300 rounded-md shadow-sm focus:border-red-500 focus:ring-red-500">
                        <option value="6">6 cuotas</option>
                        <option value="12">12 cuotas</option>
                        <option value="18">18 cuotas</option>
                        <option value="24">24 cuotas</option>
                    </select>
                </div>
                
                <div>
                    <button type="submit" 
                            class="w-full flex justify-center py-3 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 transition duration-300">
                        Simular y Solicitar
                    </button>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
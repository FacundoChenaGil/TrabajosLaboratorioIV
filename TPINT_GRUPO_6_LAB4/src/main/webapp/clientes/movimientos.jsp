<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Cuenta" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Validación de sesión activa
    if (session.getAttribute("userRole") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Historial de Movimientos</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 flex flex-col min-h-screen">
  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

<div class="max-w-4xl mx-auto p-6 bg-white shadow-md rounded mt-10">

  <main class="flex-grow p-6">
    <h1 class="text-2xl font-bold mb-4">Historial de Movimientos</h1>

    <!-- Mensajes -->
    <c:if test="${not empty error}">
        <div class="bg-red-100 text-red-700 px-4 py-2 rounded mb-4">
            ${error}
        </div>
    </c:if>

    <!-- Filtro -->
    <form action="MovimientoServlet" method="post" class="space-y-4">
        <!-- Selector de cuenta -->
        <div>
            <label for="cuenta" class="block font-semibold">Seleccionar cuenta:</label>
            <select name="cuenta" id="cuenta" required class="w-full border rounded p-2">
                <option value="">Seleccione una cuenta</option>
                <% 
                	List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
                    String cuentaSeleccionada = (String) request.getAttribute("cuentaSeleccionada");
                    if (cuentas != null && !cuentas.isEmpty()) {
                        for (Cuenta c : cuentas) {
                %>
                    <option value="<%= c.getCbu() %>" <%= (c.getCbu().equals(cuentaSeleccionada)) ? "selected" : "" %>>
                        CBU: <%= c.getCbu() %>
                    </option>
                <% 
                        }
                    } else {
                %>
                    <option disabled>No se encontraron cuentas activas</option>
                <% } %>
            </select>
        </div>

        <!-- Rango de fechas -->
        <div class="flex space-x-4">
            <div class="w-1/2">
                <label for="desde" class="block font-semibold">Desde:</label>
                <input type="date" id="desde" name="desde" required class="w-full border rounded p-2" value="${param.desde}">
            </div>
            <div class="w-1/2">
                <label for="hasta" class="block font-semibold">Hasta:</label>
                <input type="date" id="hasta" name="hasta" required class="w-full border rounded p-2" value="${param.hasta}">
            </div>
        </div>

        <!-- Tipo de movimiento -->
        <div>
            <label for="tipo" class="block font-semibold">Tipo de Movimiento:</label>
            <select name="tipo" id="tipo" required class="w-full border rounded p-2">
                <option value="0">Todos</option>
                <c:forEach var="tipo" items="${tiposMovimientos}">
                    <option value="${tipo.id}" <c:if test="${tipo.id == param.tipo}">selected</c:if>>${tipo.descripcion}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Botón -->
        <div class="text-right">
            <button type="submit" class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded">
                Filtrar
            </button>
        </div>
    </form>

    <!-- Tabla de movimientos -->
    <c:if test="${not empty movimientos}">
        <table class="w-full mt-6 border-collapse">
            <thead>
                <tr class="bg-gray-200">
                    <th class="border px-4 py-2">Fecha</th>
                    <th class="border px-4 py-2">Detalle</th>
                    <th class="border px-4 py-2">Importe</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="mov" items="${movimientos}">
                    <tr class="text-center">
                        <td class="border px-4 py-2">${mov.fechaMovimiento}</td>
                        <td class="border px-4 py-2">${mov.detalle}</td>
                        <td class="border px-4 py-2">$ ${mov.importe}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty movimientos}">
        <p class="mt-6 text-gray-600">No se encontraron movimientos para esta cuenta.</p>
    </c:if>
  </main>
  <!-- PAGINACIÓN -->
<c:if test="${not empty movimientos && totalPaginas > 1}">
    <div class="flex justify-center mt-6 space-x-2">
        <c:forEach var="i" begin="1" end="${totalPaginas}">
            <form method="post" action="MovimientoServlet">
                <input type="hidden" name="cuenta" value="${cuentaSeleccionada}" />
                <input type="hidden" name="desde" value="${param.desde}" />
                <input type="hidden" name="hasta" value="${param.hasta}" />
                <input type="hidden" name="tipo" value="${param.tipo}" />
                <input type="hidden" name="pagina" value="${i}" />
                <button type="submit"
                        class="${i == paginaActual ? 'bg-blue-600 text-white' : 'bg-gray-300 text-black'} px-3 py-1 rounded">
                    ${i}
                </button>
            </form>
        </c:forEach>
    </div>
</c:if>
</div>

</body>
</html>
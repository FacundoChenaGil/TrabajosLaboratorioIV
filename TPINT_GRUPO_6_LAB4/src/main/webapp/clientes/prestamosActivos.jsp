<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Préstamos Activos - Banco UTN</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="flex-grow container mx-auto p-6">
        <h1 class="text-3xl font-bold mb-6">Mis Préstamos Activos</h1>
        
        <!-- Content for active loans will go here -->
        <div class="bg-white p-6 rounded-lg shadow-md">
            <c:if test="${not empty listaPrestamosActivos}">
                <div class="overflow-x-auto">
                    <table class="min-w-full bg-white">
                        <thead class="bg-gray-800 text-white">
                            <tr>
                                <th class="w-1/6 text-left py-3 px-4 uppercase font-semibold text-sm">ID Préstamo</th>
                                <th class="w-1/6 text-left py-3 px-4 uppercase font-semibold text-sm">Fecha Solicitud</th>
                                <th class="w-1/6 text-right py-3 px-4 uppercase font-semibold text-sm">Importe Pedido</th>
                                <th class="w-1/6 text-right py-3 px-4 uppercase font-semibold text-sm">Importe a Pagar</th>
                                <th class="w-1/6 text-center py-3 px-4 uppercase font-semibold text-sm">Cuotas</th>
                                <th class="w-1/6 text-right py-3 px-4 uppercase font-semibold text-sm">Importe por Cuota</th>
                            </tr>
                        </thead>
                        <tbody class="text-gray-700">
                            <c:forEach var="prestamo" items="${listaPrestamosActivos}">
                                <tr class="border-b border-gray-200 hover:bg-gray-100">
                                    <td class="text-left py-3 px-4">${prestamo.IDPrestamo}</td>
                                    <td class="text-left py-3 px-4">
                                        <fmt:formatDate value="${prestamo.fechaSolicitudAsDate}" type="date" dateStyle="medium" />
                                    </td>
                                    <td class="text-right py-3 px-4">
                                        <fmt:formatNumber value="${prestamo.importePedido}" type="currency" currencySymbol="$ " />
                                    </td>
                                    <td class="text-right py-3 px-4">
                                        <fmt:formatNumber value="${prestamo.importeAPagar}" type="currency" currencySymbol="$ " />
                                    </td>
                                    <td class="text-center py-3 px-4">${prestamo.cantidadCuotas}</td>
                                    <td class="text-right py-3 px-4">
                                        <fmt:formatNumber value="${prestamo.importeCuota}" type="currency" currencySymbol="$ " />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${empty listaPrestamosActivos}">
                <p class="text-center text-gray-500">No tiene préstamos activos en este momento.</p>
            </c:if>
        </div>

    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>

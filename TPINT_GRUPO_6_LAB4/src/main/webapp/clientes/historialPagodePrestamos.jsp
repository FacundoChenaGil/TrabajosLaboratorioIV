<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Historial de Pagos</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen text-gray-800">

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="flex-grow py-10 px-4">
        <div class="max-w-5xl mx-auto bg-white p-8 rounded-2xl shadow-lg">

            <!-- Título -->
            <h2 class="text-3xl font-bold text-red-700 text-center mb-6">Historial de Préstamos Finalizados</h2>

            <!-- Tabla de préstamos finalizados -->
            <c:choose>
                <c:when test="${not empty prestamosFinalizados}">
                    <table class="w-full border border-gray-300 rounded-lg overflow-hidden text-left">
                        <thead class="bg-red-600 text-white">
                            <tr>
                                <th class="px-4 py-2">ID Préstamo</th>
                                <th class="px-4 py-2">Fecha Solicitud</th>
                                <th class="px-4 py-2">Monto Pedido</th>
                                <th class="px-4 py-2">Monto a Pagar</th>
                                <th class="px-4 py-2">Cuotas</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="prestamo" items="${prestamosFinalizados}">
                                <tr class="border-t border-gray-300">
                                    <td class="px-4 py-2">#${prestamo.IDPrestamo}</td>
                                    <td class="px-4 py-2">${prestamo.fechaSolicitud}</td>
                                    <td class="px-4 py-2">$${prestamo.importePedido}</td>
                                    <td class="px-4 py-2">$${prestamo.importeAPagar}</td>
                                    <td class="px-4 py-2">${prestamo.cantidadCuotas}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="text-center text-gray-500 mt-8">No tiene préstamos finalizados en su historial.</p>
                </c:otherwise>
            </c:choose>

            <!-- Volver al menú -->
            <div class="mt-6 text-center">
                <a href="<%=request.getContextPath()%>/clientes/menuMovimientos.jsp" class="text-red-600 hover:underline">← Volver al Menú Principal</a>
            </div>
        </div>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>

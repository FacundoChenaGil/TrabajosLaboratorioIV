<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Informe - Préstamos en Mora</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen py-10 px-6 text-gray-800">

  <div class="max-w-6xl mx-auto bg-white p-8 rounded-xl shadow-xl">
    <h1 class="text-3xl font-bold text-red-700 mb-6 text-center">Informe de Préstamos en Mora</h1>

    <table class="min-w-full bg-white border border-gray-300">
      <thead class="bg-gray-200">
        <tr>
          <th class="py-2 px-4 border-b">ID Préstamo</th>
          <th class="py-2 px-4 border-b">DNI Cliente</th>
          <th class="py-2 px-4 border-b">Fecha Solicitud</th>
          <th class="py-2 px-4 border-b">Importe Pedido</th>
          <th class="py-2 px-4 border-b">Cuotas en Mora</th>
          <th class="py-2 px-4 border-b">Monto en Mora</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="p" items="${prestamos}">
          <tr class="hover:bg-gray-100">
            <td class="py-2 px-4 border-b">${p.idPrestamo}</td>
            <td class="py-2 px-4 border-b">${p.dni}</td>
            <td class="py-2 px-4 border-b">${p.fechaCreacion}</td>
            <td class="py-2 px-4 border-b">$ ${p.montoPedido}</td>
            <td class="py-2 px-4 border-b">${p.cantidadCuotasVencidas}</td>
            <td class="py-2 px-4 border-b">$ ${p.montoTotalVencido}</td>
          </tr>
        </c:forEach>
        <c:if test="${empty prestamos}">
          <tr>
            <td colspan="6" class="py-4 text-center text-gray-500">No hay préstamos en mora.</td>
          </tr>
        </c:if>
      </tbody>
    </table>

    <!-- PAGINACIÓN -->
    <div class="mt-8 flex justify-center space-x-2">
      <c:if test="${paginaActual > 1}">
        <a href="?page=${paginaActual - 1}"
           class="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300">← Anterior</a>
      </c:if>

      <c:forEach begin="1" end="${totalPaginas}" var="i">
        <a href="?page=${i}"
           class="px-4 py-2 rounded
           ${i == paginaActual ? 'bg-red-500 text-white' : 'bg-gray-100 hover:bg-gray-200'}">
           ${i}
        </a>
      </c:forEach>

      <c:if test="${paginaActual < totalPaginas}">
        <a href="?page=${paginaActual + 1}"
           class="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300">Siguiente →</a>
      </c:if>
    </div>

    <div class="mt-6 text-center">
      <a href="${pageContext.request.contextPath}/admin/reportes.jsp"
         class="inline-block bg-custom-red text-white px-4 py-2 rounded hover:bg-red-700 transition">
        ← Volver a Reportes
      </a>
    </div>
  </div>

</body>
</html>
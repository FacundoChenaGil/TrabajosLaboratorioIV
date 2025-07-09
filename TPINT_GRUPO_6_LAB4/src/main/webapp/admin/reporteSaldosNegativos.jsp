<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Reporte - Clientes con Saldo Negativo</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen py-10 px-6 text-gray-800">

  <div class="max-w-5xl mx-auto bg-white p-8 rounded-xl shadow-xl">
    <h1 class="text-3xl font-bold text-red-700 mb-6 text-center">Clientes con Saldo Total Negativo</h1>

    <c:choose>
      <c:when test="${empty clientesNegativos}">
        <p class="text-center text-gray-500 text-lg">No hay clientes con saldo negativo actualmente.</p>
      </c:when>
      <c:otherwise>
        <table class="w-full border border-gray-300 table-auto text-sm">
          <thead class="bg-gray-200 text-gray-700">
            <tr>
              <th class="border px-4 py-2">DNI</th>
              <th class="border px-4 py-2">Nombre</th>
              <th class="border px-4 py-2">Apellido</th>
              <th class="border px-4 py-2">Saldo Total</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="cliente" items="${clientesNegativos}">
              <tr class="text-center hover:bg-gray-100">
                <td class="border px-4 py-2">${cliente.dni}</td>
                <td class="border px-4 py-2">${cliente.nombre}</td>
                <td class="border px-4 py-2">${cliente.apellido}</td>
                <td class="border px-4 py-2 text-red-600 font-semibold">
                  $<fmt:formatNumber value="${cliente.saldoTotal}" type="number" maxFractionDigits="2"/>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

        <!--  Controles de paginación -->
        <div class="flex justify-between items-center mt-6">
          <c:if test="${paginaActual > 1}">
            <a href="${pageContext.request.contextPath}/ReporteSaldoNegativo?page=${paginaActual - 1}"
               class="bg-gray-300 text-gray-800 px-4 py-2 rounded hover:bg-gray-400 transition">
              ← Anterior
            </a>
          </c:if>

          <p class="text-sm text-gray-600">Página ${paginaActual} de ${totalPaginas}</p>

          <c:if test="${paginaActual < totalPaginas}">
            <a href="${pageContext.request.contextPath}/ReporteSaldoNegativo?page=${paginaActual + 1}"
               class="bg-gray-300 text-gray-800 px-4 py-2 rounded hover:bg-gray-400 transition">
              Siguiente →
            </a>
          </c:if>
        </div>
      </c:otherwise>
    </c:choose>

  </div>
    <div class="max-w-6xl mx-auto text-end mt-4">
    <a href="${pageContext.request.contextPath}/admin/reportes.jsp" class="text-sm text-red-600 hover:underline">
      ← Volver
    </a>
  </div>

</body>
</html>
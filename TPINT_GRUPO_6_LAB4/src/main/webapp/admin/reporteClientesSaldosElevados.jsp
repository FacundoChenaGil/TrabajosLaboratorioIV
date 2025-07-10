<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Clientes con Saldos Elevados</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <script>
    tailwind.config = {
      theme: {
        extend: {
          colors: {
            'custom-red': '#DD1A2F',
          }
        }
      }
    }
  </script>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    input[type=number]::-webkit-inner-spin-button,
    input[type=number]::-webkit-outer-spin-button {
      -webkit-appearance: none;
      margin: 0;
    }
    input[type=number] {
      -moz-appearance: textfield;
    }
  </style>
</head>
<body class="bg-gray-100 min-h-screen p-8 text-gray-800">

  <div class="max-w-6xl mx-auto bg-white p-8 rounded-xl shadow-xl">
    <h1 class="text-3xl font-bold text-custom-red mb-6">Clientes con Saldos Elevados</h1>
    <p class="text-sm text-gray-500 mt-2">Total páginas: ${totalPaginas} - Página actual: ${page}</p>

    <!-- Formulario de búsqueda y filtro -->
    <form action="${pageContext.request.contextPath}/ClientesSaldoElevadoServlet" method="get" class="mb-6">
      <div class="flex flex-wrap gap-4 items-center">
        <input type="text" name="busqueda" placeholder="Buscar por nombre o apellido"
               value="${busqueda}" class="border rounded px-4 py-2 w-full sm:w-1/2" />

        <input type="number" name="saldoMinimo" step="0.01" placeholder="Saldo mínimo"
               value="${saldoMinimo}" class="border rounded px-4 py-2 w-full sm:w-1/4" />

        <button type="submit" name="btnAceptar" class="bg-custom-red text-white px-6 py-2 rounded hover:bg-red-800 transition">
          Buscar
        </button>
      </div>
    </form>

    <!-- Tabla de resultados -->
    <c:if test="${not empty clientes}">
      <table class="w-full mt-4 border-collapse border border-gray-300">
        <thead class="bg-gray-200">
          <tr>
            <th class="border border-gray-300 p-2">Nombre</th>
            <th class="border border-gray-300 p-2">Apellido</th>
            <th class="border border-gray-300 p-2">DNI</th>
            <th class="border border-gray-300 p-2">Saldo Total</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="c" items="${clientes}">
            <tr class="hover:bg-gray-100">
              <td class="border border-gray-300 p-2">${c.nombre}</td>
              <td class="border border-gray-300 p-2">${c.apellido}</td>
              <td class="border border-gray-300 p-2">${c.dni}</td>
              <td class="border border-gray-300 p-2">$ ${c.saldoTotal}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>

    <c:if test="${empty clientes}">
      <p class="text-gray-600 mt-6">No se encontraron clientes con los criterios ingresados.</p>
    </c:if>
  </div>

  
 

  <!-- Botón de volver -->
  <div class="max-w-6xl mx-auto text-end mt-4">
    <a href="${pageContext.request.contextPath}/admin/reportes.jsp" class="text-sm text-red-600 hover:underline">
      ← Volver
    </a>
  </div>
<!-- Paginación abajo del contenedor -->
  
    <div class="mt-6 flex justify-center space-x-2">
      <c:forEach begin="1" end="${totalPaginas}" var="i">
        <form method="get" action="${pageContext.request.contextPath}/ClientesSaldoElevadoServlet">
          <input type="hidden" name="page" value="${i}" />
          <input type="hidden" name="busqueda" value="${busqueda}" />
          <input type="hidden" name="saldoMinimo" value="${saldoMinimo}" />

          <c:choose>
            <c:when test="${i == page}">
              <button type="submit" class="px-4 py-2 rounded border bg-custom-red text-white">
                ${i}
              </button>
            </c:when>
            <c:otherwise>
              <button type="submit" class="px-4 py-2 rounded border bg-white text-gray-800 hover:bg-gray-200">
                ${i}
              </button>
            </c:otherwise>
          </c:choose>
        </form>
      </c:forEach>
    </div>
</body>
</html>
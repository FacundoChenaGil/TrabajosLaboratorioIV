<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Buscar Clientes</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <script>
    tailwind.config = {
      theme: {
        extend: {
          fontFamily: {
            montserrat: ['Montserrat', 'sans-serif']
          },
          colors: {
            'custom-red': '#DD1A2F',
          }
        }
      }
    }
  </script>
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Montserrat', sans-serif;
    }
  </style>
</head>
<body class="bg-gray-100 p-8 text-gray-800">

  <div class="max-w-5xl mx-auto bg-white p-8 rounded-xl shadow-xl">
    <h1 class="text-3xl font-bold text-custom-red mb-6">Buscar Clientes</h1>

    

    <!-- Formulario de búsqueda -->
    <form action="${pageContext.request.contextPath}/BuscarClientesServlet" method="get" class="mb-6 flex gap-4">
      <input type="text" name="filtro" value="${param.filtro}" placeholder="Nombre, Apellido o Usuario"
             class="flex-1 border border-gray-300 rounded px-4 py-2" required />
      <button type="submit"
              class="bg-custom-red text-white px-6 py-2 rounded hover:bg-red-800 transition">
        Buscar
      </button>
    </form>

    <!-- Sin resultados -->
    <c:if test="${not empty filtro and empty clientes}">
      <p class="text-red-600 font-semibold">No se encontraron resultados para "<strong>${filtro}</strong>"</p>
    </c:if>

    <!-- Resultados -->
<c:if test="${not empty clientes}">
  <table class="w-full border border-gray-300 rounded">
    <thead class="bg-custom-red text-white">
      <tr>
        <th class="px-4 py-2 text-left">DNI</th>
        <th class="px-4 py-2 text-left">Nombre</th>
        <th class="px-4 py-2 text-left">Correo</th>
        <th class="px-4 py-2 text-left">Teléfono</th>
        <th class="px-4 py-2 text-left">Usuario</th>
        <th class="px-4 py-2 text-left">¿Activo?</th>
      </tr>
    </thead>
    <tbody class="bg-white text-gray-800">
      <c:forEach var="cli" items="${clientes}">
        <tr class="border-t border-gray-200">
          <td class="px-4 py-2">${cli.dni}</td>
          <td class="px-4 py-2">${cli.nombre} ${cli.apellido}</td>
          <td class="px-4 py-2">${cli.correoElectronico}</td>
          <td class="px-4 py-2">${cli.telefono}</td>
          <td class="px-4 py-2">${cli.usuario.usuario}</td>
          <td class="px-4 py-2">
            <c:choose>
              <c:when test="${cli.activo}">Sí</c:when>
              <c:otherwise>No</c:otherwise>
            </c:choose>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <!-- Bloque de paginación -->
  <div class="mt-6 flex flex-wrap justify-center space-x-2">
    <c:if test="${paginaActual > 1}">
      <form method="get" action="${pageContext.request.contextPath}/ListadoClientes">
        <input type="hidden" name="page" value="${paginaActual - 1}" />
        <button class="px-3 py-1 bg-custom-red text-white rounded hover:bg-red-800">Anterior</button>
      </form>
    </c:if>

    <c:forEach begin="1" end="${totalPaginas}" var="i">
      <form method="get" action="${pageContext.request.contextPath}/ListadoClientes">
        <input type="hidden" name="page" value="${i}" />
        <button class="px-3 py-1 rounded 
          <c:if test='${i == paginaActual}'>bg-red-700 text-white</c:if>
          <c:if test='${i != paginaActual}'>bg-gray-200 text-black</c:if>">
          ${i}
        </button>
      </form>
    </c:forEach>

    <c:if test="${paginaActual < totalPaginas}">
      <form method="get" action="${pageContext.request.contextPath}/ListadoClientes">
        <input type="hidden" name="page" value="${paginaActual + 1}" />
        <button class="px-3 py-1 bg-custom-red text-white rounded hover:bg-red-800">Siguiente</button>
      </form>
    </c:if>
  </div>
</c:if>
  </div>
<div class="max-w-6xl mx-auto text-end mt-4">
    <a href="menuAdministrador.jsp" class="text-sm text-red-600 hover:underline">
      ← Volver
    </a>
  </div>

</body>
</html>
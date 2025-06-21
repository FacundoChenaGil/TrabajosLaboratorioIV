<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Historial de Pagos</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen py-10 px-4 text-gray-800">
  <div class="max-w-4xl mx-auto bg-white p-8 rounded-2xl shadow-lg">
    <h2 class="text-3xl font-bold text-red-700 text-center mb-6">Historial de Pagos</h2>
    
    <table class="w-full border border-gray-300 rounded-lg overflow-hidden text-left">
      <thead class="bg-red-600 text-white">
        <tr>
          <th class="px-4 py-2">Cuota</th>
          <th class="px-4 py-2">Monto</th>
          <th class="px-4 py-2">Fecha de Pago</th>
          <th class="px-4 py-2">Cuenta</th>
        </tr>
      </thead>
      <tbody>
        <%-- Aquí iterarías con JSTL sobre la lista de pagos --%>
        <c:forEach var="pago" items="${pagos}">
          <tr class="border-t border-gray-300">
            <td class="px-4 py-2">${pago.numeroCuota}</td>
            <td class="px-4 py-2">$${pago.monto}</td>
            <td class="px-4 py-2">${pago.fechaPago}</td>
            <td class="px-4 py-2">${pago.cuenta}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    
    <div class="mt-6 text-center">
      <a href="menuPagoPrestamos.jsp" class="text-red-600 hover:underline">← Volver al menú</a>
    </div>
  </div>
</body>
</html>

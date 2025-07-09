<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Reporte - Promedio de Saldo por Tipo de Cuenta</title>
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
  
  
  
</head>

<body class="flex flex-col min-h-screen">

  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

  <main class="flex-grow bg-gray-100 p-8 text-gray-800">

  <div class="max-w-5xl mx-auto bg-white p-8 rounded-xl shadow-xl">
    <h1 class="text-3xl font-bold text-red-700 mb-6">Promedio de Saldo por Tipo de Cuenta</h1>
    <div class="mb-6">
  <a href="${pageContext.request.contextPath}/admin/reportes.jsp"
     class="inline-block bg-custom-red text-white px-4 py-2 rounded hover:bg-red-800 transition">
    ← Volver al Panel de Reportes
  </a>
</div>
    
    
    

    <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
      <c:forEach var="tipo" items="${promedios}">
        <div class="bg-red-100 p-6 rounded-lg shadow text-center">
          <h2 class="text-xl font-semibold text-red-800 mb-2">${tipo.tipoCuenta.descripcion}</h2>
          <p class="text-3xl font-bold text-red-900">
			  $ <fmt:formatNumber value="${tipo.promedio}" type="number" minFractionDigits="2" maxFractionDigits="2" />
				</p>
        </div>
      </c:forEach>
    </div>

    <div class="overflow-x-auto">
      <table class="min-w-full bg-white border border-gray-300">
        <thead class="bg-red-600 text-white">
          <tr>
            <th class="px-6 py-3 text-left text-sm font-semibold">Tipo de Cuenta</th>
            <th class="px-6 py-3 text-left text-sm font-semibold">Saldo Promedio</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-gray-200">
          <c:forEach var="tipo" items="${promedios}">
            <tr>
              <td class="px-6 py-4">${tipo.tipoCuenta.descripcion}</td>
              <td class="px-6 py-4">
 				 $ <fmt:formatNumber value="${tipo.promedio}" type="number" minFractionDigits="2" maxFractionDigits="2" />
				</td>
            </tr>
          </c:forEach>
        </tbody>
        </table>
      </div>
    </div>
  </main>

  <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
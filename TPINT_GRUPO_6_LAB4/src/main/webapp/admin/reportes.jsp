<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Panel de Reportes</title>
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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Montserrat', sans-serif;
    }
    .card-link {
      color: inherit;
      text-decoration: none;
      transition: transform 0.3s ease;
    }
    .card-link:hover {
      transform: scale(1.05);
    }
  </style>
</head>

<body class="flex flex-col min-h-screen">

  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

  <main class="flex-grow bg-gray-100 py-10 px-6">

  <div class="max-w-6xl mx-auto bg-white p-8 rounded-xl shadow-xl">
    <h1 class="text-4xl font-extrabold text-custom-red mb-10 text-center">Panel de Reportes e Informes</h1>

    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
      
      <!-- Reporte 1: Clientes Nuevos -->
      <a href="${pageContext.request.contextPath}/ReporteClientesNuevos"
         class="bg-custom-red text-white p-6 rounded-lg shadow-xl text-center flex flex-col items-center justify-center min-h-[200px] card-link">
        <i class="fa-solid fa-user-plus fa-2xl mb-4"></i>
        <h2 class="text-xl font-semibold mb-2">Clientes Nuevos</h2>
        <p class="text-sm opacity-90 leading-relaxed">Listado de clientes dados de alta entre fechas.</p>
      </a>

      <!-- Reporte 2: Saldo Promedio por Tipo de Cuenta -->
      <a href="${pageContext.request.contextPath}/PromedioTipoCuentaServlet"
         class="bg-custom-red text-white p-6 rounded-lg shadow-xl text-center flex flex-col items-center justify-center min-h-[200px] card-link">
        <i class="fa-solid fa-chart-column fa-2xl mb-4"></i>
        <h2 class="text-xl font-semibold mb-2">Saldo Promedio por Tipo</h2>
        <p class="text-sm opacity-90 leading-relaxed">Visualizá el saldo promedio agrupado por tipo de cuenta.</p>
      </a>

		<!-- Reporte 3: Buscar Clientes -->
		<a href="${pageContext.request.contextPath}/admin/listadoClientes.jsp""
		   class="bg-custom-red text-white p-6 rounded-lg shadow-xl text-center flex flex-col items-center justify-center min-h-[200px] card-link">
		  <i class="fa-solid fa-magnifying-glass fa-2xl mb-4"></i>
		  <h2 class="text-xl font-semibold mb-2">Buscar Clientes</h2>
		  <p class="text-sm opacity-90 leading-relaxed">Buscá clientes por nombre, apellido o usuario.</p>
		</a>
      
    </div>
  </div>

  </main>

  <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
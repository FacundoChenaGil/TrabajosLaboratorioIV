<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // Protección básica: Si no hay rol o no es cliente, redirigir al login
    String userRole = (String) session.getAttribute("userRole");
    if (userRole == null || !userRole.equals("cliente")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp?error=notAuthorized");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dashboard Cliente</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen p-6">
<!-- Navbar y header -->
  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
    <h1 class="text-3xl font-bold mb-6">¡Bienvenido a tu panel, cliente! 👋</h1>
    
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        <!-- Card 1 -->
        <div class="bg-white rounded-2xl shadow p-5 hover:shadow-lg transition">
            <h3 class="text-xl font-semibold mb-2">👤 Mi cuenta</h3>
            <p class="text-gray-600 text-sm">Visualiza los datos de tu cuenta.</p>
        </div>

        <!-- Card 2 -->
        <div class="bg-white rounded-2xl shadow p-5 hover:shadow-lg transition">
            <h3 class="text-xl font-semibold mb-2"> 🔄  Transferencias</h3>
            <p class="text-gray-600 text-sm">Realizá transferencias a tus propias cuentas o a terceros.</p>
        </div>

        <!-- Card 3 -->
        <div class="bg-white rounded-2xl shadow p-5 hover:shadow-lg transition">
            <h3 class="text-xl font-semibold mb-2">📄 Historial</h3>
            <p class="text-gray-600 text-sm">Revisá tus últimos movimientos, pagos y transferencias.</p>
        </div>

        <!-- Card 4 -->
        <div class="bg-white rounded-2xl shadow p-5 hover:shadow-lg transition">
            <h3 class="text-xl font-semibold mb-2">💳 Préstamos</h3>
            <p class="text-gray-600 text-sm">Consultá el estado de tus préstamos o solicitá uno nuevo.</p>
        </div>
    </div>
</body>
</html>
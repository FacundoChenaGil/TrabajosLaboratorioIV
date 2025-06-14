<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // Protección básica: Si no hay rol o no es admin, redirigir al login
    String userRole = (String) session.getAttribute("userRole");
    if (userRole == null || !userRole.equals("admin")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp?error=notAuthorized");
        return;
    }
    String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard Administrador</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="flex-grow flex items-center justify-center p-6">
        <div class="bg-white p-8 rounded-lg shadow-lg text-center max-w-lg w-full">
            <h2 class="text-3xl font-extrabold text-purple-700 mb-6">Panel de Administración, Usuario: <%= username %></h2>
        </div>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
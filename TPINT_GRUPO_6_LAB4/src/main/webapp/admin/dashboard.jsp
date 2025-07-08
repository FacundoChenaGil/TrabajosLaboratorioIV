<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // El filtro AdminAuthFilter ya ha verificado que el usuario es un administrador.
    // Solo necesitamos obtener el nombre de usuario para mostrarlo.
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
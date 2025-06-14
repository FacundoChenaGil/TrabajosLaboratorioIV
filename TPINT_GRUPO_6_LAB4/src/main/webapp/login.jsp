<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Iniciar Sesión</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

	<%
        HttpSession currentSession = request.getSession(false);
        if (currentSession != null && currentSession.getAttribute("userRole") != null) {
            currentSession.invalidate();
            request.setAttribute("sessionClosedMessage", "Tu sesión anterior ha sido cerrada.");
        }
    %>

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="flex-grow flex items-center justify-center p-6">
        <div class="bg-white p-8 rounded-lg shadow-lg text-center max-w-md w-full">
            <h2 class="text-3xl font-extrabold text-gray-800 mb-6">Iniciar Sesión</h2>

            <%-- Mostrar mensaje de error si el login falló (viene del Servlet) --%>
            <% if (request.getAttribute("loginError") != null) { %>
                <p class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <span class="block sm:inline"><%= request.getAttribute("loginError") %></span>
                </p>
            <% } %>
            
            <% if (request.getAttribute("sessionClosedMessage") != null) { %>
                <p class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <span class="block sm:inline"><%= request.getAttribute("sessionClosedMessage") %></span>
                </p>
            <% } %>

            <form action="LoginServlet" method="POST" class="space-y-4">
                <div>
                    <input type="text" name="username" placeholder="Usuario" required
                           class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                           value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>">
                </div>
                <div>
                    <input type="password" name="password" placeholder="Contraseña" required
                           class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                </div>
                <button type="submit" class="w-full bg-blue-500 hover:bg-blue-600 text-white font-bold py-3 px-6 rounded-lg text-lg transition duration-300 ease-in-out shadow-md">
                    Entrar
                </button>
            </form>
        </div>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
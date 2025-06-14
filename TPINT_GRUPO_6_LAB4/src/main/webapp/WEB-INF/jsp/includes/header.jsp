<%-- header.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Obtener el rol del usuario de la sesión
    String userRole = (String) session.getAttribute("userRole");
    // Obtener el nombre de usuario de la sesión
    String username = (String) session.getAttribute("username");
%>

<header class="bg-gradient-to-r from-blue-700 to-blue-900 text-white p-4 shadow-lg">
    <div class="container mx-auto flex flex-col md:flex-row justify-between items-center">
        <div class="flex items-center mb-4 md:mb-0">
            <h1 class="text-3xl font-extrabold tracking-tight">Banco UTN</h1>
        </div>

        <nav class="flex space-x-6 items-center">
            <% if (userRole == null) { %>
                <%-- Navbar para usuarios no logueados --%>
                <a href="<%= request.getContextPath() %>/" class="text-white hover:text-blue-200 transition duration-300 ease-in-out text-lg font-medium">Inicio</a>
                <a href="<%= request.getContextPath() %>/login.jsp" class="bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded-full transition duration-300 ease-in-out shadow-md">
                    Iniciar Sesión
                </a>
            <% } else if (userRole.equals("admin")) { %>
                <%-- Navbar para Administradores --%>
                <a href="<%= request.getContextPath() %>/admin/dashboard.jsp" class="text-white hover:text-blue-200 transition duration-300 ease-in-out text-lg font-medium">Inicio</a>
                <span class="text-blue-200"><%= username %></span>
                <a href="<%= request.getContextPath() %>/LogoutServlet" class="bg-red-600 hover:bg-red-700 text-white py-2 px-4 rounded-full transition duration-300 ease-in-out shadow-md">
                    Cerrar Sesión
                </a>
            <% } else if (userRole.equals("cliente")) { %>
                <%-- Navbar para Clientes --%>
                <a href="<%= request.getContextPath() %>/clientes/dashboard.jsp" class="text-white hover:text-blue-200 transition duration-300 ease-in-out text-lg font-medium">Mi Cuenta</a>
                <span class="text-blue-200"><%= username %></span>
                <a href="<%= request.getContextPath() %>/LogoutServlet" class="bg-red-600 hover:bg-red-700 text-white py-2 px-4 rounded-full transition duration-300 ease-in-out shadow-md">
                    Cerrar Sesión
                </a>
            <% } %>
        </nav>
    </div>
</header>
<%-- header.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Obtener el rol del usuario de la sesión
    String userRole = (String) session.getAttribute("userRole");
    // Obtener el nombre de usuario de la sesión
    String username = (String) session.getAttribute("username");
%>
<header class="bg-gradient-to-r from-red-700 to-red-900 text-white p-4 shadow-lg">
  <div class="container mx-auto flex flex-col md:flex-row justify-between items-center">
    <div class="flex items-center mb-4 md:mb-0">
      <h1 class="text-3xl font-extrabold tracking-tight">Banco UTN</h1>
    </div>

    <nav class="flex space-x-6 items-center">
      <% if (userRole == null) { %>
        <!-- No logueado -->
        <a href="<%= request.getContextPath() %>/" class="text-white hover:text-blue-200 text-lg font-medium">Inicio</a>
        <a href="<%= request.getContextPath() %>/login.jsp"  class="border border-white text-white hover:bg-white hover:text-red-700 font-semibold py-2 px-4 rounded-full transition"> Iniciar Sesión</a>

      <% } else if (userRole.equals("administrador")) { %>
        <!-- Admin -->
        <ul class="flex space-x-6 relative items-center">
          <!-- Inicio -->
          <li>
            <a href="<%= request.getContextPath() %>/admin/menuAdministrador.jsp" class="hover:text-gray-300 font-medium">Inicio</a>
          </li>

          <!-- Perfil Dropdown -->
          <li class="relative">
            <button onclick="toggleMenu('perfilMenu')" class="hover:text-gray-300 font-medium">Mi Perfil</button>
            <ul id="perfilMenu" class="absolute hidden bg-white text-black mt-2 rounded shadow-md w-48 z-50">
              <li><a href="<%= request.getContextPath() %>/admin/cambiarContrasena.jsp" class="block px-4 py-2 hover:bg-gray-200">Cambiar Contraseña</a></li>
            </ul>
          </li>

          <!-- User Info and Logout -->
          <li class="flex items-center space-x-4">
            <div class="flex items-center bg-red-800 px-3 py-1 rounded-full">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-blue-200" viewBox="0 0 20 20" fill="currentColor">
                    <path fill-rule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clip-rule="evenodd" />
                </svg>
                <span class="text-blue-200 font-semibold"><%= username %></span>
            </div>
            <a href="<%= request.getContextPath() %>/LogoutServlet" class="bg-red-600 hover:bg-red-700 py-2 px-4 rounded-full">Cerrar Sesión</a>
          </li>
        </ul>

      <% } else if ("cliente".equals(userRole)) { %>
		   <!-- Cliente -->
        <ul class="flex space-x-6 relative items-center">
          <!-- Historial -->
          <li>
            <a href="<%= request.getContextPath() %>/clientes/menuMovimientos.jsp" class="hover:text-gray-300">Inicio</a>
          </li>
          
          <!-- Mi cuenta -->
          <li>
            <a href="<%= request.getContextPath() %>/clientes/miCuenta.jsp" class="hover:text-gray-300">Mi cuenta</a>
          </li>

          <!-- User Info and Logout -->
          <li class="flex items-center space-x-4">
            <div class="flex items-center bg-red-800 px-3 py-1 rounded-full">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-blue-200" viewBox="0 0 20 20" fill="currentColor">
                    <path fill-rule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clip-rule="evenodd" />
                </svg>
                <span class="text-blue-200 font-semibold"><%= username %></span>
            </div>
            <form action="<%= request.getContextPath() %>/LogoutServlet" method="post">
              <button class="bg-red-600 hover:bg-red-700 px-4 py-2 rounded-md">Logout</button>
            </form>
          </li>
        </ul>
      <% } %>
    </nav>
  </div>

  <!-- Script para ocultar/mostrar submenús -->
  <script>
    function toggleMenu(id) {
      const menus = document.querySelectorAll("ul[id$='Menu']");
      menus.forEach(menu => {
        if (menu.id !== id) menu.classList.add("hidden");
      });
      const currentMenu = document.getElementById(id);
      currentMenu.classList.toggle("hidden");
    }

    window.addEventListener("click", function (e) {
      const buttons = document.querySelectorAll("button[onclick^='toggleMenu']");
      const isClickInsideMenu = Array.from(buttons).some(button => button.contains(e.target))
        || Array.from(document.querySelectorAll("ul[id$='Menu']")).some(menu => menu.contains(e.target));

      if (!isClickInsideMenu) {
        document.querySelectorAll("ul[id$='Menu']").forEach(menu => menu.classList.add("hidden"));
      }
    });
  </script>
</header>
		      
        
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
        <a href="<%= request.getContextPath() %>/admin/menuAdministrador.jsp" class="text-white hover:text-blue-200">Inicio</a>
        <span class="text-blue-200"><%= username %></span>
        <a href="<%= request.getContextPath() %>/LogoutServlet" class="bg-red-600 hover:bg-red-700 py-2 px-4 rounded-full">Cerrar Sesión</a>

      <% } else if ("cliente".equals(userRole)) { %>
		   <!-- Cliente -->
        <ul class="flex space-x-6 relative items-center">
          <!-- HOME -->
          <li>
            <a href="<%= request.getContextPath() %>/clientes/dashboard.jsp" class="hover:text-gray-300 font-medium">🏠 Home</a>
          </li>

          <!-- Mi cuenta -->
          <li>
            <a href="<%= request.getContextPath() %>/clientes/miCuenta.jsp" class="hover:text-gray-300">Mi cuenta</a>
          </li>

          <!-- Historial -->
          <li class="relative">
            <button onclick="toggleMenu('historialMenu')" class="hover:text-gray-300">Historial</button>
            <ul id="historialMenu" class="absolute hidden bg-white text-black mt-2 rounded shadow-md w-48 z-50">
              <li><a href="movimientos.jsp" class="block px-4 py-2 hover:bg-gray-200">Movimientos recientes</a></li>
            </ul>
          </li>

          <!-- Transferencias -->
          <li class="relative">
            <button onclick="toggleMenu('transferenciasMenu')" class="hover:text-gray-300">Transferencias</button>
            <ul id="transferenciasMenu" class="absolute hidden bg-white text-black mt-2 rounded shadow-md w-48 z-50">
              <li><a href="nuevaTransferencia.jsp" class="block px-4 py-2 hover:bg-gray-200">Nueva</a></li>
            </ul>
          </li>

          <!-- Préstamos -->
          <li class="relative">
            <button onclick="toggleMenu('prestamosMenu')" class="hover:text-gray-300">Préstamos</button>
            <ul id="prestamosMenu" class="absolute hidden bg-white text-black mt-2 rounded shadow-md w-48 z-50">
              <li><a href="prestamosActivos.jsp" class="block px-4 py-2 hover:bg-gray-200">Activos</a></li>
              <li><a href="cuotasPendientes.jsp" class="block px-4 py-2 hover:bg-gray-200">Solicitar préstamo</a></li>
            </ul>
          </li>

          <!-- Logout -->
          <li>
            <form action="<%= request.getContextPath() %>/LogoutServlet" method="post">
              <button class="bg-red-600 hover:bg-red-700 px-4 py-2 rounded-md ml-4">Logout</button>
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
		      
        
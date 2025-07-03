<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Protección: solo clientes pueden acceder
    String userRole = (String) session.getAttribute("userRole");
    if (userRole == null || !userRole.equals("cliente")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Nueva transferencia</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 flex flex-col min-h-screen">
  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

  <main class="flex-grow p-6">
    <h1 class="text-2xl font-bold text-red-900 mb-6">Nueva Transferencia</h1>

    <!-- Formulario para buscar destinatario por CBU -->
    <div class="bg-white shadow p-6 rounded mb-6">
      <form action="<%= request.getContextPath() %>/TransferenciaServlet" method="post">
        <label class="block mb-2 font-semibold">Ingresar CBU destino</label>
        <input type="text" name="cbuDestino" class="border p-2 rounded w-full mb-4" placeholder="CBU del destinatario" required>
        <button type="submit" name="accion" value="buscar" class="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded">
          Buscar destinatario
        </button>
      </form>
    </div>

    <!-- Datos del destinatario, solo si existen en la request -->
    <%
      String nombreDest = (String) request.getAttribute("nombreDestinatario");
      String cbuDest = (String) request.getAttribute("cbuDestino");
      String dniDest = (String) request.getAttribute("dniDestinatario");
      boolean mostrarDatos = (nombreDest != null && cbuDest != null);
    %>

    <% if (mostrarDatos) { %>
      <div class="bg-white shadow p-6 rounded mb-6">
        <h2 class="text-lg font-bold mb-4 text-gray-800">Datos del destinatario</h2>
        <p><strong>DNI:</strong> <%= dniDest %></p>
        <p><strong>Nombre:</strong> <%= nombreDest %></p>
        <p><strong>CBU:</strong> <%= cbuDest %></p>

        <!-- Formulario para ingresar el monto y confirmar la transferencia -->
       <form action="<%= request.getContextPath() %>/TransferenciaServlet" method="post">
          <input type="hidden" name="cbuDestino" value="<%= cbuDest %>">
          <label class="block mb-2 font-semibold">Monto a transferir</label>
          <input type="number" name="monto" step="0.01" min="0.01" class="border p-2 rounded w-full mb-4" placeholder="Ej: 10000" required>

          <button type="submit" name="accion" value="confirmar" class="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded">
            Confirmar transferencia
          </button>
        </form>
      </div>
    <% } %>

    <!-- Mensajes de error o éxito -->
    <div class="mt-4 text-center font-semibold">
      <p class="text-red-600">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
      </p>
      <p class="text-green-600">
        <%= request.getAttribute("exito") != null ? request.getAttribute("exito") : "" %>
      </p>
    </div>
  </main>

  <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
</body>
</html>
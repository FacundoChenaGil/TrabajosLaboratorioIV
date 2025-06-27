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
  <script>
    function mostrarDatos() {
      document.getElementById("datosDestinatario").classList.remove("hidden");
    }

    function confirmarTransferencia(opcion) {
      if (opcion === 'aceptar') {
        document.getElementById("formMonto").classList.remove("hidden");
        document.getElementById("opciones").classList.add("hidden");
      } else {
        alert("Transferencia cancelada. Serás redirigido al inicio.");
        window.location.href = '<%= request.getContextPath() %>/clientes/dashboard.jsp';
      }
    }

    function validarMonto() {
      const monto = parseFloat(document.getElementById("monto").value);
      const saldoDisponible = 50000; // Simulado

      if (isNaN(monto) || monto <= 0) {
        alert("Ingresá un monto válido.");
        return false;
      }

      if (monto > saldoDisponible) {
        alert("Saldo insuficiente para realizar esta transferencia.");
        return false;
      }

      return true; // Permite el submit del form
    }
  </script>
</head>

<body class="bg-gray-100 flex flex-col min-h-screen">
  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

  <main class="flex-grow p-6">
    <h1 class="text-2xl font-bold text-red-900 mb-6">Nueva Transferencia</h1>

    <!-- Paso 1: Ingreso de CBU -->
    <div class="bg-white shadow p-6 rounded mb-6">
      <label class="block mb-2 font-semibold">Ingresar CBU destino</label>
      <input type="text" class="border p-2 rounded w-full mb-4" placeholder="CBU del destinatario">
      <button onclick="mostrarDatos()" class="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded">
        Buscar destinatario
      </button>
    </div>

    <!-- Paso 2: Datos del destinatario (simulado) -->
    <div id="datosDestinatario" class="bg-white shadow p-6 rounded mb-6 hidden">
      <h2 class="text-lg font-bold mb-4 text-gray-800">Datos del destinatario</h2>
      <p><strong>Nombre:</strong> Juan Pérez</p>
      <p><strong>CBU:</strong> 1234567890123456789012</p>
      <p><strong>Alias:</strong> juan.perez.ok</p>

      <!-- Confirmación -->
      <div id="opciones" class="mt-6">
        <p class="mb-2 font-semibold">¿Deseás continuar con la transferencia?</p>
        <button onclick="confirmarTransferencia('aceptar')" class="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded mr-2">Aceptar</button>
        <button onclick="confirmarTransferencia('cancelar')" class="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded">Cancelar</button>
      </div>
    </div>

    <!-- Paso 3: Ingreso de monto -->
    <div id="formMonto" class="bg-white shadow p-6 rounded hidden">
      <form action="<%= request.getContextPath() %>/TransferenciaServlet" method="post" onsubmit="return validarMonto()">
        <label class="block mb-2 font-semibold">Ingresar monto a transferir</label>
        <input id="monto" name="monto" type="number" step="0.01" class="border p-2 rounded w-full mb-4" placeholder="Ej: 10000">
        <!-- Simulamos pasar el CBU del destinatario -->
        <input type="hidden" name="cbu" value="1234567890123456789012">

        <button type="submit" class="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded">
          Confirmar transferencia
        </button>
      </form>
    </div>
  </main>

  <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
</body>
</html>
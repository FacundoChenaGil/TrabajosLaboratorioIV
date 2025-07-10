<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String username = (String) session.getAttribute("username");
    // Datos simulados — luego los vas a traer desde la base
    String tipoCuenta = "Caja de ahorro";
    String cbu = "1234567890123456789012";
    double saldo = 120000.00;
    String estadoCuenta = "Activa";

    // Formateamos el saldo para mostrarlo en el JS
    String saldoFormateado = String.format("%.2f", saldo);
%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mi cuenta</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
</head>

<body class="bg-gray-100 flex flex-col min-h-screen">

  <!-- Navbar y header -->
  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

  <!-- Contenido principal -->
  <main class="flex-grow flex items-center justify-center p-6">
    <div class="bg-white p-8 rounded-lg shadow-lg text-center max-w-lg w-full">
      <h2 class="text-3xl font-extrabold text-green-700 mb-6">
        Bienvenido, <%= username %>
      </h2>

      <!-- Información de la cuenta -->
      <div class="text-left space-y-3 text-gray-700 text-lg">
        <p><strong>Tipo de cuenta:</strong> <%= tipoCuenta %></p>
        <p><strong>CBU:</strong> <%= cbu %></p>
        <p>
          <strong>Saldo actual:</strong>
          <span id="saldo">••••••</span>
          <button id="toggleBtn" class="ml-2 text-gray-500 hover:text-gray-700 focus:outline-none">
            <i id="icono" class="fa-solid fa-eye-slash"></i>
          </button>
        </p>
        <p><strong>Estado de la cuenta:</strong> <%= estadoCuenta %></p>
      </div>
    </div>
  </main>

  <!-- Footer -->
  <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

  <!-- Script para mostrar/ocultar saldo -->
  <script>
    const saldo = document.getElementById('saldo');
    const toggleBtn = document.getElementById('toggleBtn');
    const icono = document.getElementById('icono');

    let visible = false;
    const valorReal = '$<%= saldoFormateado %>';

    toggleBtn.addEventListener('click', () => {
      visible = !visible;
      saldo.textContent = visible ? valorReal : '••••••';
      icono.className = visible ? 'fa-solid fa-eye' : 'fa-solid fa-eye-slash';
    });
  </script>
</body>
</html>
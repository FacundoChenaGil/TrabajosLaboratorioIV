<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Menú de Gestión</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="flex-grow flex items-center justify-center p-6">
        <!-- Tarjeta con fondo rojo y esquinas redondeadas -->
        <div class="max-w-xs w-full bg-[#D14444] rounded-3xl p-6 shadow-lg">

            <!-- Titulo grande centrado -->
            <h1 class="text-2xl font-bold mb-6 text-white text-center">Menú de Gestión</h1>

            <!-- Botones grandes y claros -->
            <div class="flex flex-col gap-4">
                <a href="<%=request.getContextPath()%>/CargarPagoPrestamosServlet"
                    class="block bg-white text-[#D14444] font-semibold text-center py-3 rounded-lg shadow hover:bg-gray-50 transition">
                    Pagar Préstamo
                </a>
                <a href="<%=request.getContextPath()%>/SeleccionarPrestamoServlet"
                    class="block bg-white text-[#D14444] font-semibold text-center py-3 rounded-lg shadow hover:bg-gray-50 transition">
                    Ver Historial de Pagos
                </a>
            </div>

        </div>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>

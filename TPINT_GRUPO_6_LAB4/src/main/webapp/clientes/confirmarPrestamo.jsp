<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmar Préstamo</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="flex-grow flex flex-col justify-center items-center p-4">

        <!-- Título -->
        <h1 class="text-3xl font-bold text-[#D14444] mb-8">Resumen del Préstamo</h1>

        <!-- Contenedor principal -->
        <div class="flex flex-col items-center gap-10 bg-white p-10 rounded-xl shadow-lg w-full max-w-3xl">

            <!-- Contenedor de columnas -->
            <div class="flex flex-wrap justify-center gap-10 w-full">

                <!-- Columna izquierda -->
                <div class="bg-white p-6 rounded-lg shadow border border-gray-200 w-full sm:w-[280px] flex flex-col gap-4">
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Cuenta Destino:</span>
                        <span class="text-gray-900 font-semibold">CBU</span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Fecha de Creación:</span>
                        <span class="text-gray-900 font-semibold">10/10/2010</span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Fecha Fin:</span>
                        <span class="text-gray-900 font-semibold">11/11/2011</span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Plazo de Pago:</span>
                        <span class="text-gray-900 font-semibold">6 meses</span>
                    </div>
                </div>

                <!-- Columna derecha -->
                <div class="bg-white p-6 rounded-lg shadow border border-gray-200 w-full sm:w-[280px] flex flex-col gap-4">
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Monto Solicitado:</span>
                        <span class="text-gray-900 font-semibold">$100.000</span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Monto a Pagar:</span>
                        <span class="text-gray-900 font-semibold">$120.000</span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Cantidad de Cuotas:</span>
                        <span class="text-gray-900 font-semibold">6</span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Importe por Cuota:</span>
                        <span class="text-gray-900 font-semibold">$20.000</span>
                    </div>
                </div>
            </div>

            <!-- Botón Confirmar -->
            <button class="w-[220px] h-[48px] bg-[#D14444] text-white font-semibold rounded-lg shadow hover:bg-[#ba3838] transition duration-200">
                Confirmar
            </button>

        </div>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
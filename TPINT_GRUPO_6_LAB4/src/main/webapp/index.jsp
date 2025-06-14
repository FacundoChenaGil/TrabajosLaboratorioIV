<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bienvenido a Banco UTN</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="flex-grow flex items-center justify-center p-6">
        <div class="bg-white p-8 rounded-lg shadow-lg text-center max-w-md w-full">
            <h2 class="text-3xl font-extrabold text-gray-800 mb-6">Bienvenido</h2>
            <a href="/TPINT_GRUPO_6_LAB4/login.jsp" class="inline-block bg-blue-500 hover:bg-blue-600 text-white font-bold py-3 px-6 rounded-lg text-lg transition duration-300 ease-in-out transform hover:scale-105 shadow-md">
                Ir a Iniciar Sesión
            </a>
        </div>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
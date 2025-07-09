<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Préstamos Activos - Banco UTN</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="flex-grow container mx-auto p-6">
        <h1 class="text-3xl font-bold mb-6">Mis Préstamos Activos</h1>
        
        <!-- Content for active loans will go here -->
        <div class="bg-white p-6 rounded-lg shadow-md">
            <p>Aquí se mostrará la lista de sus préstamos activos.</p>
        </div>

    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>

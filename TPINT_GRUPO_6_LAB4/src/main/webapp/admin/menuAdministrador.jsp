<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menú Administrador</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        'custom-red': '#DD1A2F',
                    }
                }
            }
        }
    </script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Montserrat', sans-serif;
            background-color: #f3f4f6;
        }
       
        .card-link {
            color: inherit;
            text-decoration: none;
        }
        
        .card-link:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="flex-grow">
        <div class="relative z-10 bg-white min-h-screen pt-12 pb-24 mx-auto mt-8 mb-8 max-w-7xl"
         style="background-image: url('../imagenes/menuAdmin.jpg'); background-size: cover; background-position: center bottom; background-repeat: no-repeat;">
        <div class="absolute inset-0 bg-custom-red opacity-80"></div>

        <div class="relative z-20">
            <header class="py-8 px-4 sm:px-6 lg:px-8 text-center max-w-3xl mx-auto mb-10">
                <h1 class="text-4xl sm:text-5xl font-extrabold text-white mb-4">Panel de Administración</h1>
                <p class="text-gray-200 text-lg sm:text-xl leading-relaxed">
                    Este panel centraliza las herramientas necesarias para la gestión completa del banco, permitiendo un control eficiente sobre clientes, cuentas, préstamos y la generación de informes detallados para la toma de decisiones.
                </p>
            </header>

            <section class="px-4 sm:px-6 lg:px-8">
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 max-w-7xl mx-auto">

                    <a href="${pageContext.request.contextPath}/ClienteServlet?Param=mostrarClientes" class="bg-custom-red text-white p-6 rounded-lg shadow-xl text-center flex flex-col items-center justify-center min-h-[280px] transform hover:scale-105 transition-transform duration-300 card-link">
                        <i class="fa-solid fa-users fa-2xl mb-6" style="color: #ffffff;"></i> <h3 class="text-2xl font-semibold mb-2">Gestión de Clientes</h3>
                        <p class="text-sm opacity-90 leading-relaxed">
                            Administre altas, bajas y modificaciones de clientes, incluyendo usuarios y contraseñas.
                        </p>
                    </a>

                    <a href="${pageContext.request.contextPath}/CuentaServlet?Param=mostrarTodo" class="bg-custom-red text-white p-6 rounded-lg shadow-xl text-center flex flex-col items-center justify-center min-h-[280px] transform hover:scale-105 transition-transform duration-300 card-link">
                        <i class="fa-solid fa-landmark fa-2xl mb-6" style="color: #ffffff;"></i> <h3 class="text-2xl font-semibold mb-2">Gestión de Cuentas</h3>
                        <p class="text-sm opacity-90 leading-relaxed">
                            Gestione cuentas y su asignación a clientes (máximo 3 por cliente), con un monto inicial de $10.000.
                        </p>
                    </a>

                    <a href="<%=request.getContextPath()%>/GestionDePrestamosServlet" class="bg-custom-red text-white p-6 rounded-lg shadow-xl text-center flex flex-col items-center justify-center min-h-[280px] transform hover:scale-105 transition-transform duration-300 card-link">
                        <i class="fa-solid fa-money-bill-transfer fa-2xl mb-6" style="color: #ffffff;"></i> <h3 class="text-2xl font-semibold mb-2">Autorización de Préstamos</h3>
                        <p class="text-sm opacity-90 leading-relaxed">
                            Autorice o rechace préstamos solicitados por clientes. Al aprobar, el monto se acredita y se generan las cuotas.
                        </p>
                    </a>

                    <a href="${pageContext.request.contextPath}/admin/reportes.jsp"class="bg-custom-red text-white p-6 rounded-lg shadow-xl text-center flex flex-col items-center justify-center min-h-[280px] transform hover:scale-105 transition-transform duration-300 card-link">
                        <i class="fa-solid fa-square-poll-vertical fa-2xl mb-6" style="color: #ffffff;"></i> <h3 class="text-2xl font-semibold mb-2">Reportes e Informes</h3>
                        <p class="text-sm opacity-90 leading-relaxed">
                             Acceda a informes y reportes estadísticos, con parámetros personalizables para el análisis de datos del banco.
                        </p>
                    </a>
                </div>
            </section>
        </div>
    </div>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
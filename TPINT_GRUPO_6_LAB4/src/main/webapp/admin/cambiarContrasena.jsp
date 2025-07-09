<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "entidad.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cambiar Contraseña</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
        rel="stylesheet">

    <style>
        @layer base {
            body {
                font-family: 'Montserrat', sans-serif;
            }
        }

        .input-glow-on-hover-focus:hover {
            box-shadow: 0 0 8px 2px rgba(128, 128, 128, 0.2);
        }

        .input-glow-on-hover-focus:focus {
            box-shadow: none;
        }
    </style>
</head>
<body class="flex flex-col min-h-screen">

<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

<main class="flex-grow bg-cover bg-center bg-no-repeat bg-fixed antialiased text-gray-900 flex items-center justify-center p-4"
      style="background-image: url('../img/5594016.jpg');">
    
    <%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    %>
    
    <div class="max-w-xl w-full bg-white p-10 md:p-12 rounded-2xl shadow-2xl">
        <h1 class="text-3xl font-bold text-center text-gray-800 mb-6 tracking-tight">
            Cambiar Contraseña
        </h1>
        
        <%
		String mensajeError = (String) request.getAttribute("mensajeError");
		String mensajeExito = (String) request.getAttribute("mensajeExito");
		if (mensajeError != null) {
		%>
		<div
			class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-6"
			role="alert">
			<strong class="font-bold">Error: </strong> <span
				class="block sm:inline"><%=mensajeError%></span>
		</div>
		<%
		} else if (mensajeExito != null) {
		%>
		<div
			class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-6"
			role="alert">
			<strong class="font-bold">¡Éxito! </strong> <span
				class="block sm:inline"><%=mensajeExito%></span>
		</div>
		<%
		}
		%>
        
        <hr class="border-t border-gray-300 my-6">

        <form action="<%=request.getContextPath()%>/UsuarioServlet" method="post">
        <input type="hidden" name="nombreUsuario" value="<%= usuario.getUsuario() %>">
            <div class="space-y-6">
                <!-- Usuario -->
                <div>
                    <label for="username" class="block mb-2 text-gray-700 font-medium">Nombre de Usuario:</label>
                    <input type="text" id="username" name="username" value="<%= usuario.getUsuario() %>" disabled
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-gray-100 text-gray-600 text-base
                        cursor-not-allowed input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>

                <!-- Nueva contraseña -->
                <div>
                    <label for="new-password" class="block mb-2 text-gray-700 font-medium">Ingrese nueva contraseña:</label>
                    <input type="password" id="new-password" name="new-password" required
                        placeholder="Letras, números y símbolos"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                        focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                        input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>

                <!-- Repetir contraseña -->
                <div>
                    <label for="confirm-password" class="block mb-2 text-gray-700 font-medium">Repita la contraseña:</label>
                    <input type="password" id="confirm-password" name="confirm-password" required
                        placeholder="Debe coincidir con la anterior"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                        focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                        input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>
            </div>

            <!-- Botones -->
            <div class="flex items-center justify-between pt-8 gap-4">
                <a href="<%=request.getContextPath()%>/ClienteServlet?Param=mostrarClientes"
                    class="py-3 px-8 text-center bg-gray-100 border border-gray-400 rounded-lg text-base font-semibold w-full hover:bg-gray-300 transition duration-200 ease-in-out">
                    Volver
                </a>
                <input type="submit" value="Cambiar Contraseña"
                    class="py-3 px-8 bg-white text-black border border-black rounded-lg cursor-pointer w-full text-base font-bold hover:bg-black hover:text-white transition duration-200 ease-in-out" />
            </div>
        </form>
    </div>

</main>

<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
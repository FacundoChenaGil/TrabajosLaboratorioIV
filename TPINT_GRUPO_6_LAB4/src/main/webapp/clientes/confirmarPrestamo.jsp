<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidad.PrestamoBackup" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmar Préstamo</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col min-h-screen">

	<%
    	PrestamoBackup prestamo = (PrestamoBackup) session.getAttribute("prestamoPendiente");
	%>

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="flex-grow flex flex-col justify-center items-center p-4">

        <!-- Título -->
        <h1 class="text-3xl font-bold text-[#D14444] mb-8">Resumen del Préstamo</h1>
        
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

        <!-- Contenedor principal -->
        <form action="<%=request.getContextPath() %>/PrestamoServlet" method="post" class="flex flex-col items-center gap-10 bg-white p-10 rounded-xl shadow-lg w-full max-w-3xl">
			<input type="hidden" name="accion" value="alta">
            <!-- Contenedor de columnas -->
            <div class="flex flex-wrap justify-center gap-10 w-full">

                <!-- Columna izquierda -->
                <div class="bg-white p-6 rounded-lg shadow border border-gray-200 w-full sm:w-[280px] flex flex-col gap-4">
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Cuenta Destino:</span>
                        <span class="text-gray-900 font-semibold"><%=prestamo.getCuentaAcreditada().getCbu()%></span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Fecha de Creación:</span>
                        <span class="text-gray-900 font-semibold"><%= prestamo.getFechaSolicitud().toLocalDate() %></span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Fecha Fin:</span>
                        <span class="text-gray-900 font-semibold">11/11/2011</span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Plazo de Pago:</span>
                        <span class="text-gray-900 font-semibold"><%=prestamo.getCantidadCuotas() %> meses</span>
                    </div>
                </div>

                <!-- Columna derecha -->
                <div class="bg-white p-6 rounded-lg shadow border border-gray-200 w-full sm:w-[280px] flex flex-col gap-4">
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Monto Solicitado:</span>
                        <span class="text-gray-900 font-semibold">$<%=prestamo.getImportePedido() %></span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Monto a Pagar:</span>
                        <span class="text-gray-900 font-semibold">$<%=prestamo.getImporteAPagar() %></span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Cantidad de Cuotas:</span>
                        <span class="text-gray-900 font-semibold"><%=prestamo.getCantidadCuotas() %></span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span class="text-gray-600 font-medium">Importe por Cuota:</span>
                        <span class="text-gray-900 font-semibold">$<%=prestamo.getImporteCuota() %></span>
                    </div>
                </div>
            </div>

            <!-- Botón Confirmar -->
            <button class="w-[220px] h-[48px] bg-[#D14444] text-white font-semibold rounded-lg shadow hover:bg-[#ba3838] transition duration-200">
                Confirmar
            </button>

        </form>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
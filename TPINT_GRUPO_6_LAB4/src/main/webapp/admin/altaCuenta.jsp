<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entidad.TiposDeCuentas"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta y Asignación de Cuenta</title>
<script src="https://cdn.tailwindcss.com"></script>
<link
    href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap"
    rel="stylesheet">
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
    integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />

<style>
body {
    font-family: 'Montserrat', sans-serif;
}

.input-glow-effect {
    transition: border-color 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
    border: 1px solid #d1d5db;
    outline: none;
}

.input-glow-effect:hover, .input-glow-effect:focus {
    border-color: #800020; /* Bordo-dark */
    box-shadow: 0 0 8px 3px rgba(128, 0, 32, 0.4);
    outline: none;
}

.input-glow-effect::placeholder {
    color: #9ca3af;
}

.button-secondary {
    background-color: white;
    color: #800020;
    padding: 0.5rem 1rem;
    border-radius: 0.375rem;
    font-weight: 600;
    cursor: pointer;
    transition: background-color 0.15s ease-in-out, border-color 0.15s
        ease-in-out, color 0.15s ease-in-out;
    border: 1px solid #800020;
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 100%;
}

.button-secondary:hover {
    background-color: #f2f2f2;
    border-color: #5e0016;
    color: #5e0016;
}

.button-primary {
    background-color: #800020;
    color: white;
    padding: 0.5rem 1rem;
    border-radius: 0.375rem;
    font-weight: 600;
    cursor: pointer;
    transition: background-color 0.15s ease-in-out, border-color 0.15s
        ease-in-out;
    border: 2px solid #800020;
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 100%;
}

.button-primary:hover {
    background-color: #6a001a;
    border-color: #6a001a;
}

.card-style {
    background-color: white;
    padding: 1.5rem;
    border-radius: 0.5rem;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0
        rgba(0, 0, 0, 0.06);
}
</style>
</head>
<body class="flex flex-col min-h-screen bg-gray-100"> 

<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
<%
    String mensajeError = (String) request.getAttribute("error");
    String mensajeExito = (String) request.getAttribute("mensaje");
    String dniCliente= (String) request.getAttribute("dniCliente");
    String numeroCuenta = (String) request.getAttribute("numeroCuenta");
    String cbu = (String) request.getAttribute("cbu");
    String idTipoCuentaSelected = (String) request.getAttribute("idTipoCuenta");
    List<TiposDeCuentas> tiposDeCuenta = (List<TiposDeCuentas>) request.getAttribute("tiposCuenta");
%>

<main class="flex-grow bg-cover bg-center bg-no-repeat bg-fixed p-4 md:p-8 flex items-center justify-center">
    <div class="card-style">
        <h1 class="text-2xl font-bold text-center text-[#800020] mb-6">Alta
        y Asignación de Cuenta</h1>

        <form action="AltaCuentaServlet" method="post" id="formCrearCuenta">
            <input type="hidden" name="action" value="Guardar">

            <div class="mb-4">
                <label for="tipoCuenta"
                    class="block text-sm font-medium text-gray-700 mb-1">Tipo
                    de Cuenta:</label> 
                <select id="tipoCuenta" name="idTipoCuenta"
                    class="mt-1 block w-full pl-3 pr-8 py-2 text-base border-gray-300 focus:outline-none focus:ring-[#800020] focus:border-[#800020] rounded-md input-glow-effect"
                    required>
                    <option value="">Seleccione un tipo de cuenta</option>

                    <%
                    if (tiposDeCuenta != null) {
                        for (TiposDeCuentas tipo : tiposDeCuenta) {
                            String selected = "";
                            if (idTipoCuentaSelected != null && idTipoCuentaSelected.equals(String.valueOf(tipo.getID()))) {
                                selected = "selected";
                            }
                    %>
                    <option value="<%=tipo.getID()%>" <%=selected%>>
                        <%=tipo.getDescripcion()%></option>
                    <%
                        }
                    }
                    %>
                </select>
            </div>

            <div class="mb-4">
                <label for="dniCliente"
                    class="block text-sm font-medium text-gray-700 mb-1">DNI
                    del Cliente:</label> 
                <input type="text" id="dniCliente" name="dniCliente"
                    placeholder="Ej: 12345678" minlength="7" maxlength="8"
                    pattern="\d+"
                    class="mt-1 block w-full pl-3 pr-3 py-2 rounded-md leading-5 bg-white text-base input-glow-effect"
                    required title="Solo se aceptan números"
                    value="<%=dniCliente != null ? dniCliente : ""%>">
            </div>

            <div class="mb-4">
                <label for="numeroCuenta"
                    class="block text-sm font-medium text-gray-700 mb-1">Número
                    de Cuenta:</label> 
                <input type="text" id="numeroCuenta"
                    name="numeroCuenta" pattern="\d+" minlength="13" maxlength="13"
                    placeholder="1234567890123"
                    class="mt-1 block w-full pl-3 pr-3 py-2 rounded-md leading-5 bg-white text-base input-glow-effect"
                    required title="Solo se aceptan números"
                    value="<%=numeroCuenta != null ? numeroCuenta : ""%>">
            </div>

            <div class="mb-4">
                <label for="cbu"
                    class="block text-sm font-medium text-gray-700 mb-1">CBU:</label>
                <input type="text" id="cbu" name="cbu" minlength="22"
                    maxlength="22" placeholder="Ej: 0000000000000000000000"
                    pattern="\d+"
                    class="mt-1 block w-full pl-3 pr-3 py-2 rounded-md leading-5 bg-white text-base input-glow-effect"
                    required title="Solo se aceptan números"
                    value="<%=cbu != null ? cbu : ""%>">
            </div>

            <div class="mb-6">
                <label for="montoInicial"
                    class="block text-sm font-medium text-gray-700 mb-1">Monto
                    Inicial:</label> 
                <input type="text" id="montoInicial" name="montoInicial"
                    value="10000.00"
                    class="mt-1 block w-full pl-3 pr-3 py-2 text-base border-gray-300 rounded-md bg-gray-100 cursor-not-allowed"
                    readonly>
                <p class="text-sm text-gray-500 mt-1">El monto inicial de la
                    cuenta es fijo en $10,000.00 según la política del banco.</p>
            </div>

            <div class="flex space-x-4 mt-6">
                <div class="flex-1">
                    <a href="/TPINT_GRUPO_6_LAB4/CuentaServlet?Param=mostrarTodo" class="button-secondary">Volver</a>
                </div>
                <div class="flex-1">
                    <input type="submit" name="action" value="Guardar"
                        class="button-primary cursor-pointer w-full py-2 rounded-md text-white font-semibold bg-[#800020] hover:bg-[#6a001a]" />
                </div>
            </div>
        </form>
    </div>

    <!-- Modal de Error -->
    <div id="modalError" class="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center hidden z-50">
        <div class="bg-white border-l-4 border-red-800 p-6 rounded-lg shadow-lg max-w-sm w-full relative">
            <h2 class="text-lg font-bold text-red-800 mb-2">
                <i class="fa-solid fa-triangle-exclamation"></i> Error
            </h2>
            <p class="text-gray-800 mb-4"><%=mensajeError != null ? mensajeError : ""%></p>
            <div class="text-right">
                <button onclick="cerrarModalError()"
                class="bg-red-800 text-white px-4 py-2 rounded hover:bg-red-900">Cerrar</button>
            </div>
        </div>
    </div>

    <!-- Modal de Exito -->
    <div id="modalExito" class="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center hidden z-50">
        <div class="bg-white border-l-4 border-green-700 p-6 rounded-lg shadow-lg max-w-sm w-full relative">
            <h2 class="text-lg font-bold text-green-700 mb-2">
                <i class="fa-solid fa-circle-check"></i> Éxito
            </h2>
            <p class="text-gray-800 mb-4"><%=mensajeExito != null ? mensajeExito : ""%></p>
            <div class="text-right">
                <button onclick="cerrarModalExito()"
                class="bg-green-700 text-white px-4 py-2 rounded hover:bg-green-800">Cerrar</button>
            </div>
        </div>
    </div>
    
</main>

<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

<script>
    //FUNCIONES PARA EL MODAL DE ERROR
    function mostrarModalError() {
        document.getElementById('modalError').classList.remove('hidden');
    }

    function cerrarModalError() {
        document.getElementById('modalError').classList.add('hidden');
    }

    //FUNCIONES PARA EL MODAL DE EXITO
    function mostrarModalExito() {
        document.getElementById('modalExito').classList.remove('hidden');
    }

    function cerrarModalExito() {
        document.getElementById('modalExito').classList.add('hidden');
    }
    
    
    //MOSTRAR MODALES AUTOMATICAMENTE SI HAY MENSAJES DE ERROR O EXITO
    window.addEventListener('DOMContentLoaded', function () {
        const tieneError = "<%=mensajeError != null ? "true" : "false"%>";
        const tieneExito = "<%=mensajeExito != null ? "true" : "false"%>";

        if (tieneError === "true") {
            mostrarModalError();
        }

        if (tieneExito === "true") {
            mostrarModalExito();
        }
    });

    
</script>
</body>
</html>

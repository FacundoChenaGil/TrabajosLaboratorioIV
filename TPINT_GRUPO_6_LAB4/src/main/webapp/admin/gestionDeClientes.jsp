<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entidad.Cliente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Clientes</title>
<script src="https://cdn.tailwindcss.com"></script>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.min.css">

<style>
    body {
        font-family: 'Montserrat', sans-serif;
    }

    .input-glow-effect:focus {
        border-color: #ff0000;
        box-shadow: 0 0 5px 2px rgba(255, 0, 0, 0.5);
        outline: none;
    }

    .icon-input {
        background: none;
        border: none;
        cursor: pointer;
        padding: 0.4rem;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .icon-input:hover {
        background-color: #f3f4f6;
        border-radius: 0.375rem;
    }

    .tabla-responsive {
        overflow-x: auto;
    }

    table {
        width: 100%;
        min-width: max-content;
    }

    th, td {
        white-space: nowrap;
    }
</style>
</head>
<body class="bg-gray-200 min-h-screen">

<%
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
%>

<main class="max-w-7xl mx-auto p-6">

    <!-- H1 y Alta Cliente -->
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-bold text-gray-800">Gestión de Clientes</h1>
        <a href="altaCliente.jsp"
           class="bg-[#ff0000] text-white px-4 py-2 rounded-md text-sm font-semibold hover:bg-red-700 flex items-center gap-2">
            <i class="fa-solid fa-plus"></i> Agregar Cliente
        </a>
    </div>

    <!-- Tabla de Clientes -->
    <div class="bg-white p-6 rounded-lg shadow tabla-responsive">
        <table id="clientesDataTable" class="divide-y divide-gray-200">
            <thead class="bg-gray-50">
                <tr>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Acciones</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">DNI</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">CUIL</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Nombre</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Apellido</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Género</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Fecha Nac.</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Teléfono</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Nacionalidad</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Provincia</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Localidad</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Dirección</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Email</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Usuario</th>
                    <th class="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Estado</th>
                </tr>
            </thead>
            <tbody class="divide-y divide-gray-200 text-sm text-gray-800">
            <%
                if (listaClientes != null && !listaClientes.isEmpty()) {
                    for (Cliente cliente : listaClientes) {
            %>
            <tr>
                <td class="px-4 py-3 whitespace-nowrap flex items-center gap-4">
                    <form action="<%=request.getContextPath()%>/ClienteServlet" method="get">
                        <input type="hidden" name="dni" value="<%=cliente.getDni()%>">
                        <input type="submit" class="icon-input" title="Editar" value='&#xf044;'
                               style="font-family: FontAwesome; color: #ff0000; font-size: 1.25rem;">
                    </form>

                    <button type="button" onclick="abrirModal('<%=cliente.getDni()%>')" class="icon-input" title="Eliminar"
                            style="font-family: FontAwesome; color: #ff0000; font-size: 1.25rem;">
                        &#xf2ed;
                    </button>

                    <form action="cambiarContraseña.jsp" method="get">
                        <input type="hidden" name="dni" value="<%=cliente.getDni()%>">
                        <input type="submit" class="icon-input" title="Cambiar Clave" value='&#xf084;'
                               style="font-family: FontAwesome; color: #ff0000; font-size: 1.25rem;">
                    </form>
                </td>
                <td class="px-4 py-3"><%=cliente.getDni()%></td>
                <td class="px-4 py-3"><%=cliente.getCuil()%></td>
                <td class="px-4 py-3"><%=cliente.getNombre()%></td>
                <td class="px-4 py-3"><%=cliente.getApellido()%></td>
                <td class="px-4 py-3"><%=cliente.getSexo()%></td>
                <td class="px-4 py-3"><%=cliente.getFechaNacimiento()%></td>
                <td class="px-4 py-3"><%=cliente.getTelefono()%></td>
                <td class="px-4 py-3"><%=cliente.getNacionalidad()%></td>
                <td class="px-4 py-3"><%=cliente.getProvincia()%></td>
                <td class="px-4 py-3"><%=cliente.getLocalidad()%></td>
                <td class="px-4 py-3"><%=cliente.getDireccion()%></td>
                <td class="px-4 py-3"><%=cliente.getCorreoElectronico()%></td>
                <td class="px-4 py-3"><%=cliente.getUsuario().getUsuario()%></td>
                <td class="px-4 py-3"><%=cliente.isActivo()%></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="15" class="px-6 py-4 text-sm text-gray-500 text-center">No se encontraron clientes.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</main>

<!-- Modal Eliminar -->
<div id="modalEliminar" class="fixed inset-0 bg-black bg-opacity-50 hidden items-center justify-center z-50">
    <div class="bg-white rounded-lg p-6 shadow-lg max-w-sm w-full">
        <h2 class="text-lg font-semibold mb-4 text-gray-800">¿Está seguro de eliminar este cliente?</h2>
        <form id="formEliminar" action="" method="post">
            <input type="hidden" name="dni" id="dniEliminar" value="">
            <div class="flex justify-center gap-4">
                <button type="button" onclick="cerrarModal()"
                        class="bg-gray-300 text-gray-800 px-4 py-2 rounded hover:bg-gray-400">No</button>
                <button type="submit" class="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700">Sí</button>
            </div>
        </form>
    </div>
</div>

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdn.datatables.net/2.0.8/js/dataTables.min.js"></script>

<script>
    let dniAEliminar = '';
    const modal = document.getElementById('modalEliminar');

    function abrirModal(dni) {
        dniAEliminar = dni;
        document.getElementById('dniEliminar').value = dni;
        modal.classList.remove('hidden');
        modal.classList.add('flex');
    }

    function cerrarModal() {
        modal.classList.add('hidden');
        modal.classList.remove('flex');
    }

    $(document).ready(function () {
        $('#clientesDataTable').DataTable({
            scrollX: true,
            language: {
                url: 'https://cdn.datatables.net/plug-ins/2.0.0/i18n/es-ES.json'
            }
        });
    });
</script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entidad.Cliente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Clientes</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
        crossorigin="anonymous" referrerpolicy="no-referrer" />
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

        <!-- Búsqueda -->
        <div class="bg-white p-6 rounded-lg shadow mb-6">
            <label for="dni" class="block text-sm font-medium text-gray-700 mb-2">Buscar por DNI</label>
            <div class="flex gap-3">
                <input type="text" id="dni" placeholder="Ingrese DNI"
                    class="input-glow-effect border px-3 py-2 rounded w-full" />
                <input type="submit" value="Buscar"
                    class="bg-[#ff0000] text-white px-4 py-2 rounded-md text-sm font-semibold hover:bg-red-700 cursor-pointer" />
                <input type="submit" value="Mostrar Todo"
                    class="bg-gray-700 text-white px-4 py-2 rounded-md text-sm font-semibold hover:bg-gray-800 cursor-pointer" />
            </div>
        </div>

        <!-- Tabla de Clientes -->
        <div class="bg-white p-6 rounded-lg shadow overflow-x-auto">
            <table class="min-w-full divide-y divide-gray-200">
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
                		if(listaClientes != null && !listaClientes.isEmpty()) {
                			for(Cliente cliente : listaClientes) {
                	%>
                    <tr>
                        <td class="px-4 py-3 whitespace-nowrap flex items-center gap-4">
                            <!-- Editar -->
                            <form action="modificarCliente.jsp" method="get">
                                <input type="hidden" name="dni" value="12345678">
                                <input type="submit" class="icon-input" title="Editar" value='&#xf044;'
                                    style="font-family: FontAwesome; color: #ff0000; font-size: 1.25rem;">
                            </form>

                            <!-- Eliminar -->
                            <button type="button" onclick="abrirModal('12345678')" class="icon-input" title="Eliminar"
                                style="font-family: FontAwesome; color: #ff0000; font-size: 1.25rem;">
                                &#xf2ed;
                            </button>

                            <!-- Cambiar Contraseña -->
                            <form action="cambiarContraseña.jsp" method="get">
                                <input type="hidden" name="dni" value="12345678">
                                <input type="submit" class="icon-input" title="Cambiar Clave" value='&#xf084;'
                                    style="font-family: FontAwesome; color: #ff0000; font-size: 1.25rem;">
                            </form>
                        </td>
                        <td class="px-4 py-3"><%=cliente.getDni() %></td>
                        <td class="px-4 py-3"><%=cliente.getCuil() %></td>
                        <td class="px-4 py-3"><%=cliente.getNombre() %></td>
                        <td class="px-4 py-3"><%=cliente.getApellido() %></td>
                        <td class="px-4 py-3"><%=cliente.getSexo() %></td>
                        <td class="px-4 py-3"><%=cliente.getFechaNacimiento() %></td>
                        <td class="px-4 py-3"><%=cliente.getTelefono() %></td>
                        <td class="px-4 py-3"><%=cliente.getNacionalidad() %></td>
                        <td class="px-4 py-3"><%=cliente.getProvincia() %></td>
                        <td class="px-4 py-3"><%=cliente.getLocalidad() %></td>
                        <td class="px-4 py-3"><%=cliente.getDireccion() %></td>
                        <td class="px-4 py-3"><%=cliente.getCorreoElectronico() %></td>
                        <td class="px-4 py-3"><%=cliente.getUsuario().getUsuario() %></td>
                        <td class="px-4 py-3"><%=cliente.isActivo() %></td>
                    </tr>
                    <%
                		}
                		}else {
                    %>
                    	tr>
						<td colspan="8"
							class="px-6 py-4 text-sm text-gray-500 text-center">No se
							encontraron clientes.</td>
					</tr>
					<%
					}
					%>
                </tbody>
            </table>
        </div>

        <!-- Paginación -->
        <div class="flex justify-center mt-6">
            <nav class="inline-flex space-x-2" aria-label="Paginación">
                <a href="?page=1"
                    class="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded hover:bg-gray-100">1</a>
                <a href="?page=2"
                    class="px-4 py-2 text-sm font-medium text-white bg-red-600 border border-red-600 rounded hover:bg-red-700">2</a>
                <a href="?page=3"
                    class="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded hover:bg-gray-100">3</a>
            </nav>
        </div>

        <div class="flex justify-end mt-4">
            <a href="menuAdministrador.jsp" class="text-sm text-black underline">Volver</a>
        </div>

    </main>

    <!-- Modal -->
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

    <script>
        function abrirModal(dni) {
            document.getElementById('dniEliminar').value = dni;
            document.getElementById('modalEliminar').classList.remove('hidden');
            document.getElementById('modalEliminar').classList.add('flex');
        }

        function cerrarModal() {
            document.getElementById('modalEliminar').classList.add('hidden');
            document.getElementById('modalEliminar').classList.remove('flex');
        }
    </script>


</body>
</html>
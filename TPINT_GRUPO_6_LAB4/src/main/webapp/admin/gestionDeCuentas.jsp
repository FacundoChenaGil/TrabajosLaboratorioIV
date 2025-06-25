<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Cuentas Bancarias</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        'bordo-dark': '#800020',
                        'bordo-medium': '#A52A2A',
                        'bordo-light-bg': '#FFF0F0',
                        'bordo-border': '#E2E8F0',
                    }
                }
            }
        }
    </script>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body { font-family: 'Montserrat', sans-serif; }
        ::-webkit-scrollbar { width: 8px; height: 8px; }
        ::-webkit-scrollbar-track { background: #f1f1f1; border-radius: 10px; }
        ::-webkit-scrollbar-thumb { background: #888; border-radius: 10px; }
        ::-webkit-scrollbar-thumb:hover { background: #555; }
        .input-glow-effect {
            transition: border-color 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
            border: 1px solid #d1d5db;
            outline: none;
        }
        .input-glow-effect:hover, .input-glow-effect:focus {
            border-color: #ff0000;
            box-shadow: 0 0 8px 3px rgba(255, 0, 0, 0.4);
            outline: none;
        }
        .input-glow-effect::placeholder { color: #9ca3af; }
        .button-input {
            font-size: 0.875rem;
            border-radius: 0.375rem;
            line-height: 1.25rem;
            transition: background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            cursor: pointer;
            flex-shrink: 0;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            text-align: center;
        }

        #modalConfirmacion {
            display: flex;
            position: fixed;
            left: 0; top: 0; width: 100%; height: 100%;
            background-color: rgba(0,0,0,0.5);
            z-index: 1000;
            justify-content: center;
            align-items: center;
        }
        #modalConfirmacion > div {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            text-align: center;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
            max-width: 400px;
            width: 90%;
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        #modalConfirmacion button {
            cursor: pointer;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-weight: 600;
            transition: background-color 0.2s ease;
        }
        #modalConfirmacion button:hover {
            opacity: 0.9;
        }
        #modalConfirmacion button:first-of-type {
            background-color: #ef4444;
            color: white;
        }
        #modalConfirmacion button:last-of-type {
            background-color: #e5e7eb;
            color: #374151;
        }

        .card-no-border {
            background-color: white;
            padding: 1.25rem; 
            border-radius: 0.5rem; 
            box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
            border: none;
        }

        .action-icon-button {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            padding: 0.5rem;
            border-radius: 0.375rem;
            transition: background-color 0.15s ease-in-out;
        }
        .action-icon-button:hover {
            background-color: #e5e7eb;
        }
    </style>
</head>
<body class="bg-gray-100 min-h-screen">

    <main class="flex-1 p-5 overflow-y-auto max-w-7xl mx-auto">
        <div class="flex justify-between items-center mb-6">
            <h1 class="text-xl font-bold text-gray-800">Gestión de Cuentas Bancarias</h1>
            <a href="altaCliente.jsp" class="bg-red-600 text-white px-4 py-2 rounded-md text-sm font-semibold hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-700 focus:ring-offset-2 flex items-center space-x-2">
                <i class="fa-solid fa-plus fa-sm" style="color: #ffffff;"></i>
                <span>Generar Cuenta</span>
            </a>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-3 gap-3 mb-5">
            <div class="bg-red-700 p-4 rounded-lg text-white shadow-lg flex flex-col items-center justify-center">
                <p class="text-xs opacity-90">Total Cuentas</p>
                <p class="text-2xl font-bold">125</p>
            </div>
            <div class="bg-red-700 p-4 rounded-lg text-white shadow-lg flex flex-col items-center justify-center">
                <p class="text-xs opacity-90">Cuentas Activas</p>
                <p class="text-2xl font-bold">100</p>
            </div>
            <div class="bg-red-700 p-4 rounded-lg text-white shadow-lg flex flex-col items-center justify-center">
                <p class="text-xs opacity-90">Cuentas Inactivas</p>
                <p class="text-2xl font-bold">25</p>
            </div>
        </div>

        <h3 class="text-lg font-semibold text-gray-800 mb-4">Filtros de Búsqueda</h3>
        <div class="grid grid-cols-1 gap-4 mb-5">
            <div class="card-no-border">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4 items-end">
                    <div>
                        <label for="tipoCuenta" class="block text-sm font-medium text-gray-700 mb-1">Tipo de Cuenta</label>
                        <select id="tipoCuenta" name="tipoCuenta" class="mt-1 block w-full pl-3 pr-8 py-2 text-base border-gray-300 focus:outline-none focus:ring-blue-500 focus:border-blue-500 rounded-md">
                            <option>Cuenta Corriente</option>
                            <option>Caja de Ahorro</option>
                        </select>
                    </div>
                    <div>
                        <label for="estado" class="block text-sm font-medium text-gray-700 mb-1">Estado</label>
                        <select id="estado" name="estado" class="mt-1 block w-full pl-3 pr-8 py-2 text-base border-gray-300 focus:outline-none focus:ring-blue-500 focus:border-blue-500 rounded-md">
                            <option>Activa</option>
                            <option>Inactiva</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="card-no-border">
                <div>
                    <label for="buscarDni" class="block text-sm font-medium text-gray-700 mb-1">Buscar por CBU</label>
                    <div class="flex items-end gap-3 h-10">
                        <div class="relative flex-grow h-full">
                            <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-400" viewBox="0 0 20 20" fill="currentColor">
                                    <path fill-rule="evenodd" d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z" clip-rule="evenodd" />
                                </svg>
                            </div>
                            <input type="text" id="buscarDni" placeholder="Ingrese el CBU" class="block w-full pl-10 pr-3 py-2 rounded-md leading-5 bg-white text-base input-glow-effect h-full">
                        </div>
                        <div class="flex gap-3 h-full">
                            <input type="button" value="Buscar" class="button-input bg-red-600 text-white hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-700 focus:ring-offset-2 w-28 h-full">
                            <input type="button" value="Mostrar todo" class="button-input bg-gray-700 text-white hover:bg-gray-800 focus:outline-none focus:ring-2 focus:ring-gray-900 focus:ring-offset-2 w-28 h-full">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="bg-white p-6 rounded-lg shadow-md overflow-x-auto">
            <table class="min-w-full divide-y divide-gray-200">
                <thead class="bg-gray-50">
                    <tr>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Acciones</th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Número de Cuenta</th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">CBU</th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Fecha de Creación</th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Saldo</th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Tipo de Cuenta</th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nombre y Apellido</th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Estado</th>
                    </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                    <tr>
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                            <form action="modificarCuenta.jsp" method="get" class="inline-block">
                                <input type="hidden" name="cuentaId" value="cuenta_001">
                                <button type="submit" class="action-icon-button">
                                    <i class="fa-regular fa-pen-to-square fa-xl" style="color: #fa0000;"></i>
                                </button>
                            </form>
                            <button type="button" onclick="confirmarEliminar('cuenta_001');" class="action-icon-button ml-2">
                                <i class="fa-regular fa-trash-can fa-xl" style="color: #fa0000;"></i>
                            </button>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">12345</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">1234-5678-90123456</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">10/01/2022</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">$15,200.50</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Caja de Ahorro</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Juan Perez</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Activa</td>
                    </tr>
                    <tr>
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                            <form action="modificarCuenta.jsp" method="get" class="inline-block">
                                <input type="hidden" name="cuentaId" value="cuenta_002">
                                <button type="submit" class="action-icon-button">
                                    <i class="fa-regular fa-pen-to-square fa-xl" style="color: #fa0000;"></i>
                                </button>
                            </form>
                            <button type="button" onclick="confirmarEliminar('cuenta_002');" class="action-icon-button ml-2">
                                <i class="fa-regular fa-trash-can fa-xl" style="color: #fa0000;"></i>
                            </button>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">77778</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">9876-5432-10987654</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">05/03/2023</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">$5,000.00</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Cuenta Corriente</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Maria Gomez</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Inactiva</td>
                    </tr>
                    <tr>
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                            <form action="modificarCuenta.jsp" method="get" class="inline-block">
                                <input type="hidden" name="cuentaId" value="cuenta_003">
                                <button type="submit" class="action-icon-button">
                                    <i class="fa-regular fa-pen-to-square fa-xl" style="color: #fa0000;"></i>
                                </button>
                            </form>
                            <button type="button" onclick="confirmarEliminar('cuenta_003');" class="action-icon-button ml-2">
                                <i class="fa-regular fa-trash-can fa-xl" style="color: #fa0000;"></i>
                            </button>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">98765</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">2468-1357-97531864</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">22/07/2021</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">$2,100.75</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Caja de Ahorro</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Carlos López</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Activa</td>
                    </tr>
                    <tr>
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                            <form action="modificarCuenta.jsp" method="get" class="inline-block">
                                <input type="hidden" name="cuentaId" value="cuenta_004">
                                <button type="submit" class="action-icon-button">
                                    <i class="fa-regular fa-pen-to-square fa-xl" style="color: #fa0000;"></i>
                                </button>
                            </form>
                            <button type="button" onclick="confirmarEliminar('cuenta_004');" class="action-icon-button ml-2">
                                <i class="fa-regular fa-trash-can fa-xl" style="color: #fa0000;"></i>
                            </button>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">45458</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">1357-2468-08642975</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">15/11/2020</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">$8,900.00</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Cuenta Corriente</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Ana Rodríguez</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">Activa</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="flex justify-center items-center mt-5">
            <nav class="relative z-0 inline-flex rounded-md shadow-sm -space-x-px" aria-label="Pagination">
                <a href="?page=1" class="relative inline-flex items-center px-4 py-2 text-sm font-semibold text-gray-700 bg-white border border-gray-300 rounded-l-md hover:bg-gray-50 focus:z-20 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2">1</a>
                <a href="?page=2" aria-current="page" class="relative inline-flex items-center px-4 py-2 text-sm font-semibold text-red-600 bg-red-50 border border-red-500 z-10 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2">2</a>
                <a href="?page=3" class="relative inline-flex items-center px-4 py-2 text-sm font-semibold text-gray-700 bg-white border border-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2">3</a>
                <a href="?page=4" class="relative inline-flex items-center px-4 py-2 text-sm font-semibold text-gray-700 bg-white border border-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2">4</a>
                <a href="?page=5" class="relative inline-flex items-center px-4 py-2 text-sm font-semibold text-gray-700 bg-white border border-gray-300 rounded-r-md hover:bg-gray-50 focus:z-20 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2">5</a>
                </nav>
        </div>

        <a href="menuAdministrador.jsp" class="flex justify-end text-black underline">Volver</a>
    </main>

    <div id="modalConfirmacion" style="display: none;">
        <div>
            <p class="text-lg font-semibold text-gray-800">¿Está seguro que desea eliminar esta cuenta?</p>
            <div class="flex justify-center space-x-4 mt-5">
                <input type="button" value="Sí" onclick="eliminarCuentaConfirmado();" class="bg-red-500 text-white px-6 py-2 rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2">
                <input type="button" value="No" onclick="cancelarEliminar();" class="bg-gray-200 text-gray-800 px-6 py-2 rounded-md hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-300 focus:ring-offset-2">
            </div>
        </div>
    </div>

    <form id="formEliminar" action="CuentaServlet" method="post" style="display:none;">
        <input type="hidden" name="action" value="eliminar">
        <input type="hidden" name="cuentaIdEliminar" id="cuentaIdEliminar">
    </form>

    <script>
        let idCuentaAEliminar = '';

        function confirmarEliminar(id) {
            idCuentaAEliminar = id;
            document.getElementById('modalConfirmacion').style.display = 'flex';
        }

        function eliminarCuentaConfirmado() {
            document.getElementById('cuentaIdEliminar').value = idCuentaAEliminar;
            document.getElementById('formEliminar').submit();
            document.getElementById('modalConfirmacion').style.display = 'none';
        }

        function cancelarEliminar() {
            idCuentaAEliminar = '';
            document.getElementById('modalConfirmacion').style.display = 'none';
        }
    </script>

</body>
</html>
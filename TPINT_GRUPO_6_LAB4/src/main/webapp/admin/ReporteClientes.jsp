<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte de Clientes Nuevos</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body class="bg-gray-50 p-8">
    <div class="max-w-6xl mx-auto bg-white rounded-lg shadow-xl p-8 border-t-4 border-red-700">
        <h1 class="text-3xl font-bold text-gray-800 mb-6 flex items-center">
            <i class="fa-solid fa-paperclip fa-xs mr-3 text-red-700"></i>
            Reporte de Clientes Nuevos
        </h1>

        <div class="bg-red-700 p-6 rounded-lg mb-8 shadow-md text-white">
            <h2 class="text-xl font-semibold mb-4">Seleccionar Período</h2>
            <div class="grid grid-cols-1 md:grid-cols-3 gap-4 items-end">
                <div>
                    <label for="fechaInicio" class="block text-sm font-medium mb-1">Fecha de Inicio:</label>
                    <input type="date" id="fechaInicio" name="fechaInicio"
                           class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm text-gray-900 focus:outline-none focus:ring-red-500 focus:border-red-500 sm:text-sm">
                </div>
                <div>
                    <label for="fechaFin" class="block text-sm font-medium mb-1">Fecha de Fin:</label>
                    <input type="date" id="fechaFin" name="fechaFin"
                           class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm text-gray-900 focus:outline-none focus:ring-red-500 focus:border-red-500 sm:text-sm">
                </div>
                <div>
                    <button type="button" class="w-full bg-red-900 text-white py-2 px-4 rounded-md hover:bg-red-950 focus:outline-none focus:ring-0 transition duration-150 ease-in-out font-semibold">
                        Generar Reporte
                    </button>
                </div>
            </div>
        </div>

        <div id="reporteResultados" class="bg-white p-6 rounded-lg shadow-md">
            <h2 class="text-xl font-semibold text-gray-700 mb-4">Resultados del Reporte <span class="text-gray-500 text-base">(01/01/2024 - 31/01/2024)</span></h2>

            <div class="bg-red-100 p-4 rounded-md mb-6 flex items-center justify-between">
                <p class="text-red-800 text-lg font-medium">Total de Nuevos Clientes en el Período:</p>
                <p class="text-red-900 text-3xl font-bold">99</p>
            </div>

            <h3 class="text-lg font-semibold text-gray-700 mb-3">Detalles de Clientes</h3>
            <div class="mb-4">
                <input type="text" placeholder="Buscar por nombre de usuario"
                       class="px-3 py-2 border border-gray-300 rounded-md shadow-sm text-sm focus:outline-none focus:ring-red-500 focus:border-red-500 w-full">
            </div>

            <div class="overflow-x-auto rounded-lg border border-gray-200">
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                        <tr>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                CBU
                            </th>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Nombre Completo
                            </th>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Fecha de Alta
                            </th>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Usuario
                             </th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                        <tr>
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                0001000212345678901234
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
                                Juan Pérez Gómez
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
                                05/01/2024
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
                                jperezg
                            </td>
                        </tr>
                        <tr>
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                0001000298765432109876
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
                                María López Díaz
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
                                12/01/2024
                             </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
                                mlopez
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="mt-6 flex justify-center items-center">
                <nav class="relative z-0 inline-flex rounded-md shadow-sm -space-x-px" aria-label="Pagination">
                    <a href="#" class="relative inline-flex items-center px-2 py-2 rounded-l-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
                        <span class="sr-only">Previous</span>
                        <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                            <path fill-rule="evenodd" d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z" clip-rule="evenodd" />
                        </svg>
                    </a>
                    <a href="#" aria-current="page" class="z-10 bg-red-100 border-red-700 text-red-800 relative inline-flex items-center px-4 py-2 text-sm font-medium border">
                        1
                    </a>
                    <a href="#" class="bg-white border-gray-300 text-gray-500 hover:bg-gray-50 relative inline-flex items-center px-4 py-2 text-sm font-medium border">
                        2
                    </a>
                    <a href="#" class="bg-white border-gray-300 text-gray-500 hover:bg-gray-50 relative inline-flex items-center px-4 py-2 text-sm font-medium border">
                        3
                    </a>
                    <a href="#" class="relative inline-flex items-center px-2 py-2 rounded-r-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
                        <span class="sr-only">Next</span>
                        <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                            <path fill-rule="evenodd" d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z" clip-rule="evenodd" />
                        </svg>
                    </a>
                </nav>
            </div>

            <div class="mt-6 text-right">
                <a href="menuAdministrador.jsp" class="inline-flex items-center px-4 py-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-0">
                    <svg class="-ml-1 mr-2 h-5 w-5 text-gray-500" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                        <path fill-rule="evenodd" d="M9.707 16.707a1 1 0 01-1.414 0l-6-6a1 1 0 010-1.414l6-6a1 1 0 011.414 1.414L5.414 9H17a1 1 0 110 2H5.414l4.293 4.293a1 1 0 010 1.414z" clip-rule="evenodd" />
                    </svg>
                    Volver
                </a>
            </div>

        </div>
    </div>

</body>
</html>
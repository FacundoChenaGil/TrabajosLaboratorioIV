<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Préstamos</title>

  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
  <script src="https://cdn.tailwindcss.com"></script>

  <style>
    body {
      font-family: 'Montserrat', sans-serif;
    }

    .input-wrapper {
      position: relative;
      width: 100%;
    }

    .input-wrapper i {
      position: absolute;
      top: 50%;
      left: 12px;
      transform: translateY(-50%);
      color: #bbb;
    }

    .input-rojo {
      width: 100%;
      padding: 10px 12px 10px 36px;
      border: 2px solid #ccc;
      border-radius: 6px;
      font-size: 14px;
      outline: none;
      transition: box-shadow 0.2s, border-color 0.2s;
    }

    .input-rojo:hover,
    .input-rojo:focus {
      border-color: #ff0000;
      box-shadow: 0 0 6px 2px rgba(255, 0, 0, 0.5);
    }
  </style>
</head>
<body class="bg-gray-100 text-gray-800 p-6">

  <header class="mb-8">
    <h1 class="text-3xl font-semibold text-center border-b pb-3 text-gray-800">
      Autorizar o Rechazar Préstamos
    </h1>
  </header>

  <div class="max-w-6xl mx-auto mb-6 bg-white p-6 rounded-xl shadow-md">
    <form action="BuscarPrestamoServlet" method="get" class="flex flex-col md:flex-row md:items-end gap-4">
      <div class="flex-1">
        <label for="cbu" class="block text-sm font-medium mb-1">Buscar por CBU</label>
        <div class="input-wrapper">
          <i class="fa-solid fa-magnifying-glass"></i>
          <input type="text" name="cbu" id="cbu" placeholder="Ingrese el CBU" class="input-rojo" />
        </div>
      </div>
      <div class="flex-shrink-0">
        <label class="invisible block text-sm font-medium mb-1">.</label>
        <button type="submit" class="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded-md shadow-sm w-full">
          Buscar
        </button>
      </div>
      <div class="flex-shrink-0">
        <label class="invisible block text-sm font-medium mb-1">.</label>
        <a href="PrestamosPendientesServlet"
           class="bg-gray-800 hover:bg-gray-900 text-white px-4 py-2 rounded-md shadow-sm w-full text-center block">
          Mostrar Todo
        </a>
      </div>
    </form>
  </div>

  <div class="max-w-6xl mx-auto bg-white p-6 rounded-xl shadow-md">
    <h2 class="text-lg font-semibold mb-6 border-b pb-2 text-gray-700">Detalles de la Solicitud</h2>
    <div class="overflow-x-auto">
      <table class="min-w-full table-auto border border-gray-300 text-center text-sm">
        <thead class="bg-red-600 text-white">
          <tr>
            <th class="py-3 px-4 border">Cuenta Destino</th>
            <th class="py-3 px-4 border">Fecha Creación</th>
            <th class="py-3 px-4 border">Fecha Fin</th>
            <th class="py-3 px-4 border">Plazo</th>
            <th class="py-3 px-4 border">Monto Solicitado</th>
            <th class="py-3 px-4 border">Monto a Pagar</th>
            <th class="py-3 px-4 border">Cuotas</th>
            <th class="py-3 px-4 border">Importe por Cuota</th>
            <th class="py-3 px-4 border">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr class="hover:bg-gray-50">
            <td class="py-3 px-4 border">017005703123456789012345</td>
            <td class="py-3 px-4 border">10/10/2010</td>
            <td class="py-3 px-4 border">11/11/2011</td>
            <td class="py-3 px-4 border">6 meses</td>
            <td class="py-3 px-4 border">$100.000</td>
            <td class="py-3 px-4 border">$120.000</td>
            <td class="py-3 px-4 border">6</td>
            <td class="py-3 px-4 border">$20.000</td>
            <td class="py-3 px-4 border">
              <div class="flex justify-center gap-4">
                <form action="AutorizarPrestamoServlet" method="post">
                  <input type="hidden" name="idPrestamo" value="1" />
                  <button type="submit" class="text-green-600 hover:text-green-700 text-xl" title="Autorizar">
                    <i class="fa-regular fa-circle-check"></i>
                  </button>
                </form>
                <form action="RechazarPrestamoServlet" method="post">
                  <input type="hidden" name="idPrestamo" value="1" />
                  <button type="submit" class="text-red-600 hover:text-red-700 text-xl" title="Rechazar">
                    <i class="fa-regular fa-circle-xmark"></i>
                  </button>
                </form>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

  <div class="max-w-6xl mx-auto flex justify-center mt-6">
    <nav class="inline-flex shadow-sm rounded-md overflow-hidden" aria-label="Pagination">
      <a href="?page=1"
         class="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 hover:bg-gray-100">
        1
      </a>
      <a href="?page=2"
         class="px-4 py-2 text-sm font-medium text-gray-700 bg-white border-t border-b border-gray-300 hover:bg-gray-100">
        2
      </a>
      <a href="?page=3"
         class="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 hover:bg-gray-100">
        3
      </a>
    </nav>
  </div>

  <div class="max-w-6xl mx-auto text-end mt-4">
    <a href="menuAdministrador.jsp" class="text-sm text-red-600 hover:underline">
      ← Volver
    </a>
  </div>

</body>
</html>
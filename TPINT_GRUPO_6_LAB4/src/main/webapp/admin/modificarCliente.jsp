<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Cliente</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet" />
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

    .form-radio {
      color: #850321;
    }
  </style>
</head>
<body class="bg-cover bg-center bg-no-repeat bg-fixed p-8 antialiased text-gray-900"
  style="background-image: url('../imagenes/5594016.jpg')">
  <div class="max-w-3xl md:max-w-5xl mx-auto bg-white p-10 md:p-12 rounded-2xl shadow-2xl">
    <h1 class="text-3xl font-bold text-center text-gray-800 mb-6 tracking-tight">
      Modificar Cliente
    </h1>
    <hr class="border-t border-gray-300 my-8" />
    <form action="" method="">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-x-8 md:gap-x-12 gap-y-6 mb-6">
        <!-- DNI -->
        <div>
          <label for="dni" class="block mb-2 text-gray-700 font-medium">DNI:</label>
          <input type="text" id="dni" name="dni" required value="12345678"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- CUIL -->
        <div>
          <label for="cuil" class="block mb-2 text-gray-700 font-medium">CUIL:</label>
          <input type="text" id="cuil" name="cuil" required value="27-12345678-9"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- Nombre -->
        <div>
          <label for="nombre" class="block mb-2 text-gray-700 font-medium">Nombre:</label>
          <input type="text" id="nombre" name="nombre" required value="Ana"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- Apellido -->
        <div>
          <label for="apellido" class="block mb-2 text-gray-700 font-medium">Apellido:</label>
          <input type="text" id="apellido" name="apellido" required value="López"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- Género -->
        <div>
          <label class="block mb-2 text-gray-700 font-medium">Género:</label>
          <div class="mt-2 flex flex-wrap gap-x-6 gap-y-2 justify-center">
            <label class="inline-flex items-center">
              <input type="radio" name="genero" value="Masculino" class="form-radio w-5 h-5" />
              <span class="ml-2 text-gray-700">Masculino</span>
            </label>
            <label class="inline-flex items-center">
              <input type="radio" name="genero" value="Femenino" checked class="form-radio w-5 h-5" />
              <span class="ml-2 text-gray-700">Femenino</span>
            </label>
            <label class="inline-flex items-center">
              <input type="radio" name="genero" value="Otro" class="form-radio w-5 h-5" />
              <span class="ml-2 text-gray-700">Otro</span>
            </label>
          </div>
        </div>

        <!-- Teléfono -->
        <div>
          <label for="telefono" class="block mb-2 text-gray-700 font-medium">Teléfono:</label>
          <input type="tel" id="telefono" name="telefono" value="1123456789"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- Fecha de nacimiento -->
        <div>
          <label for="fecha" class="block mb-2 text-gray-700 font-medium">Fecha de Nacimiento:</label>
          <input type="date" id="fecha" name="fecha" value="1990-01-01"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" required />
        </div>

        <!-- Nacionalidad -->
        <div>
          <label for="nacionalidad" class="block mb-2 text-gray-700 font-medium">Nacionalidad:</label>
          <select name="nacionalidad"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out">
            <option value="">Seleccione una nacionalidad</option>
            <option value="Argentina" selected>Argentina</option>
            <option value="Brasil">Brasil</option>
            <option value="Chile">Chile</option>
          </select>
        </div>

        <!-- Provincia -->
        <div>
          <label for="provincia" class="block mb-2 text-gray-700 font-medium">Provincia:</label>
          <select name="provincia" id="provincia"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out">
            <option value="">Seleccione una provincia</option>
            <option value="Buenos Aires" selected>Buenos Aires</option>
            <option value="CABA">CABA</option>
          </select>
        </div>

        <!-- Localidad -->
        <div>
          <label for="localidad" class="block mb-2 text-gray-700 font-medium">Localidad:</label>
          <select name="localidad" id="localidad"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out">
            <option value="">Seleccione una localidad</option>
            <option value="San Isidro">San Isidro</option>
            <option value="Tigre" selected>Tigre</option>
          </select>
        </div>

        <!-- Dirección -->
        <div>
          <label for="direccion" class="block mb-2 text-gray-700 font-medium">Dirección:</label>
          <input type="text" id="direccion" name="direccion" required value="Corrientes 123"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- Email -->
        <div>
          <label for="email" class="block mb-2 text-gray-700 font-medium">Email:</label>
          <input type="email" id="email" name="email" required value="ana.lopez@gmail.com"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- Estado -->
        <div>
          <label for="estado" class="block mb-2 text-gray-700 font-medium">Estado:</label>
          <select id="estado" name="estado"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out">
            <option value="Activo" selected>Activo</option>
            <option value="Inactivo">Inactivo</option>
          </select>
        </div>
      </div>

      <div class="flex items-center justify-between pt-4 gap-4">
        <a href="gestionDeClientes.jsp"
          class="py-3 px-8 text-center bg-gray-100 border border-gray-400 rounded-lg text-base font-semibold w-full hover:bg-gray-300 transition duration-200 ease-in-out">Volver</a>
        <input type="submit" value="Modificar Cliente"
          class="py-3 px-8 bg-white text-black border border-black rounded-lg cursor-pointer w-full text-base font-bold hover:bg-black hover:text-white transition duration-200 ease-in-out" />
        
      </div>
    </form>
  </div>
</body>
</html>
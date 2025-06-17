<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta Cliente</title>
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
        .form-radio {
            color: #850321;
        }
    </style>
</head>
<body class="bg-cover bg-center bg-no-repeat bg-fixed p-8 antialiased text-gray-900" style="background-image: url('../imagenes/5594016.jpg');">
    <div class="max-w-3xl md:max-w-5xl mx-auto bg-white p-10 md:p-12 rounded-2xl shadow-2xl">
        <h1 class="text-3xl font-bold text-center text-gray-800 mb-6 tracking-tight">
            Alta Cliente
        </h1>
        <hr class="border-t border-gray-300 my-8">
        <form action="" method="">
            <div class="grid grid-cols-1 md:grid-cols-2 gap-x-8 md:gap-x-12 gap-y-6 mb-6">
                <div>
                    <label for="dni" class="block mb-2 text-gray-700 font-medium">DNI:</label>
                    <input type="text" id="dni" name="dni" required placeholder="Ej: 12345678"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>

                <div>
                    <label for="cuil" class="block mb-2 text-gray-700 font-medium">CUIL:</label>
                    <input type="text" id="cuil" name="cuil" required placeholder="Ej: 27-12345678-9"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>

                <div>
                    <label for="nombre" class="block mb-2 text-gray-700 font-medium">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required placeholder="Ej: Ana"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>

                <div>
                    <label for="apellido" class="block mb-2 text-gray-700 font-medium">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" required placeholder="Ej: López"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>

                <div>
                    <label class="block mb-2 text-gray-700 font-medium">Género:</label>
                    <div class="mt-2 flex flex-wrap gap-x-6 gap-y-2 justify-center">
                        <label class="inline-flex items-center">
                            <input type="radio" id="masculino" name="genero" value="Masculino" required
                                class="form-radio w-5 h-5"> <span class="ml-2 text-gray-700">Masculino</span>
                        </label>
                        <label class="inline-flex items-center">
                            <input type="radio" id="femenino" name="genero" value="Femenino" required
                                class="form-radio w-5 h-5"> <span class="ml-2 text-gray-700">Femenino</span>
                        </label>
                        <label class="inline-flex items-center">
                            <input type="radio" id="otro" name="genero" value="Otro" required
                                class="form-radio w-5 h-5"> <span class="ml-2 text-gray-700">Otro</span>
                        </label>
                    </div>
                </div>

                <div>
                    <label for="telefono" class="block mb-2 text-gray-700 font-medium">Teléfono:</label>
                    <input type="tel" id="telefono" name="telefono" placeholder="Ej: 11 2345 6789"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>

                <div>
                    <label for="fecha" class="block mb-2 text-gray-700 font-medium">Fecha de Nacimiento:</label>
                    <input type="date" id="fecha" name="fecha"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out" required>
                </div>

                <div>
                    <label for="nacionalidad" class="block mb-2 text-gray-700 font-medium">Nacionalidad:</label>
                    <select name="nacionalidad"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                        <option value="">Seleccione una nacionalidad</option>
                        <option value="Argentina">Argentina</option>
                        <option value="Brasil">Brasil</option>
                        <option value="Chile">Chile</option>
                        <option value="Uruguay">Uruguay</option>
                        <option value="Paraguay">Paraguay</option>
                    </select>
                </div>

                <div>
                    <label for="provincia" class="block mb-2 text-gray-700 font-medium">Provincia:</label>
                    <select name="provincia" id="provincia"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                        <option value="">Seleccione una provincia</option>
                        <option value="Buenos Aires">Buenos Aires</option>
                        <option value="CABA">Ciudad Autónoma de Buenos Aires</option>
                        <option value="Catamarca">Catamarca</option>
                        <option value="Chaco">Chaco</option>
                        <option value="Chubut">Chubut</option>
                        <option value="Córdoba">Córdoba</option>
                        <option value="Corrientes">Corrientes</option>
                        <option value="Entre Ríos">Entre Ríos</option>
                        <option value="Formosa">Formosa</option>
                        <option value="Jujuy">Jujuy</option>
                        <option value="La Pampa">La Pampa</option>
                        <option value="La Rioja">La Rioja</option>
                        <option value="Mendoza">Mendoza</option>
                        <option value="Misiones">Misiones</option>
                        <option value="Neuquén">Neuquén</option>
                    </select>
                </div>

                <div>
                    <label for="localidad" class="block mb-2 text-gray-700 font-medium">Localidad:</label>
                    <select name="localidad" id="localidad"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                        <option value="">Seleccione una localidad</option>
                        <option value="Localidad1">Localidad1</option>
                        <option value="Localidad2">Localidad2</option>
                        <option value="Localidad3">Localidad3</option>
                        <option value="Localidad4">Localidad4</option>
                    </select>
                </div>

                <div>
                    <label for="direccion" class="block mb-2 text-gray-700 font-medium">Dirección:</label>
                    <input type="text" id="direccion" name="direccion" required placeholder="Calle, Número, Piso, Dpto."
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>

                <div>
                    <label for="email" class="block mb-2 text-gray-700 font-medium">Email:</label>
                    <input type="email" id="email" name="email" required placeholder="Ej: ana.lopez@gmail.com"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>

                <div>
                    <label for="username" class="block mb-2 text-gray-700 font-medium">Nombre de Usuario:</label>
                    <input type="text" id="username" name="username" required placeholder="Ej: alopez"
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>

                <div>
                    <label for="password" class="block mb-2 text-gray-700 font-medium">Clave:</label>
                    <input type="password" id="password" name="password" required placeholder="Letras mayúsculas, minúsculas, números y símbolos."
                        class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                input-glow-on-hover-focus transition duration-300 ease-in-out">
                </div>
            </div>

            <div class="flex items-center justify-center pt-4">
                <input type="submit" value="Registrar Cliente"
                    class="py-3 px-8 bg-white text-black border border-black rounded-lg cursor-pointer w-full text-base font-bold
                            hover:bg-black hover:text-white transition duration-200 ease-in-out">
            </div>
        </form>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "entidad.Cliente" %>
    
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
<body class="flex flex-col min-h-screen">

  <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

  <main class="flex-grow bg-cover bg-center bg-no-repeat bg-fixed p-8 antialiased text-gray-900"
        style="background-image: url('../imagenes/5594016.jpg')">
  
  <%
  Cliente cliente = (Cliente) request.getAttribute("cliente");
  boolean isActivo = cliente.isActivo();
  String generoCliente = cliente.getSexo();
  %>
  
  <script>
   const nacionalidadCliente = "<%= cliente.getNacionalidad() %>";
   const provinciaCliente = "<%= cliente.getProvincia() %>";
   const localidadCliente = "<%= cliente.getLocalidad() %>";
  </script>
  
  
  <div class="max-w-3xl md:max-w-5xl mx-auto bg-white p-10 md:p-12 rounded-2xl shadow-2xl">
    <h1 class="text-3xl font-bold text-center text-gray-800 mb-6 tracking-tight">
      Modificar Cliente
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
    
    
    <hr class="border-t border-gray-300 my-8" />
    <form action="ClienteServlet" method="post">
    <input type="hidden" name="accion" value="modificar">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-x-8 md:gap-x-12 gap-y-6 mb-6">
        <!-- DNI -->
        <div>
          <label for="dni" class="block mb-2 text-gray-700 font-medium">DNI:</label>
          <input type="text" id="dni" name="dni" required minlength="7" maxlength="8" pattern="\d+" title="Solo se aceptan números" required value="<%=cliente.getDni() %>"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- CUIL -->
        <div>
          <label for="cuil" class="block mb-2 text-gray-700 font-medium">CUIL:</label>
          <input type="text" id="cuil" name="cuil" minlength="11" maxlength="11" pattern="\d+" title="Solo se aceptan números" required value="<%=cliente.getCuil() %>"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- Nombre -->
        <div>
          <label for="nombre" class="block mb-2 text-gray-700 font-medium">Nombre:</label>
          <input type="text" id="nombre" name="nombre" minlength="2" maxlength="100" pattern="[A-Za-zñÑáéíóúÁÉÍÓÚüÜ\s]*[A-Za-zñÑáéíóúÁÉÍÓÚüÜ]+" title="Ingrese solo letras" required value="<%=cliente.getNombre() %>"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- Apellido -->
        <div>
          <label for="apellido" class="block mb-2 text-gray-700 font-medium">Apellido:</label>
          <input type="text" id="apellido" name="apellido" minlength="2" maxlength="100" pattern="[A-Za-zñÑáéíóúÁÉÍÓÚüÜ\s]*[A-Za-zñÑáéíóúÁÉÍÓÚüÜ]+" title="Ingrese solo letras" required value="<%=cliente.getApellido() %>"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

		<!-- Género -->
		<div>
			<label class="block mb-2 text-gray-700 font-medium">Género:</label>
			<div class="mt-2 flex flex-wrap gap-x-6 gap-y-2 justify-center">
				<label class="inline-flex items-center"> <input
					type="radio" name="genero" value="M"
					class="form-radio w-5 h-5"
					<%="M".equals(generoCliente) ? "checked" : ""%> /> <span
					class="ml-2 text-gray-700">Masculino</span>
				</label> <label class="inline-flex items-center"> <input
					type="radio" name="genero" value="F"
					class="form-radio w-5 h-5"
					<%="F".equals(generoCliente) ? "checked" : ""%> /> <span
					class="ml-2 text-gray-700">Femenino</span>
				</label> <label class="inline-flex items-center"> <input
					type="radio" name="genero" value="O"
					class="form-radio w-5 h-5"
					<%="O".equals(generoCliente) ? "checked" : ""%> /> <span
					class="ml-2 text-gray-700">Otro</span>
				</label>
			</div>
		</div>

				<!-- Teléfono -->
        <div>
          <label for="telefono" class="block mb-2 text-gray-700 font-medium">Teléfono:</label>
          <input type="tel" id="telefono" name="telefono" minlength="10" maxlength="20" pattern="\d+" title="Solo se aceptan números" value="<%=cliente.getTelefono() %>"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- Fecha de nacimiento -->
        <div>
          <label for="fecha" class="block mb-2 text-gray-700 font-medium">Fecha de Nacimiento:</label>
          <input type="date" id="fecha" name="fecha" required value="<%=cliente.getFechaNacimiento() %>"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" required />
        </div>

        <!-- Nacionalidad -->
        <div>
          <label for="nacionalidad" class="block mb-2 text-gray-700 font-medium">Nacionalidad:</label>
          <select name="nacionalidad" id="nacionalidad" required
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out">
            <option value="">Seleccione una nacionalidad</option>
          </select>
        </div>

        <!-- Provincia -->
        <div>
          <label for="provincia" class="block mb-2 text-gray-700 font-medium">Provincia:</label>
          <select name="provincia" id="provincia" required
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out">
            <option value="">Seleccione una provincia</option>
          </select>
        </div>

        <!-- Localidad -->
        <div>
          <label for="localidad" class="block mb-2 text-gray-700 font-medium">Localidad:</label>
          <select name="localidad" id="localidad" required
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out">
            <option value="">Seleccione una localidad</option>
          </select>
        </div>

        <!-- Dirección -->
        <div>
          <label for="direccion" class="block mb-2 text-gray-700 font-medium">Dirección:</label>
          <input type="text" id="direccion" name="direccion" minlength="5" maxlength="255" required value="<%= cliente.getDireccion() %>"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
        </div>

        <!-- Email -->
        <div>
          <label for="email" class="block mb-2 text-gray-700 font-medium">Email:</label>
          <input type="email" id="email" name="email" required value=<%= cliente.getCorreoElectronico() %>
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out" />
          <input type="hidden" name="emailOriginal" value="<%= cliente.getCorreoElectronico() %>">
        </div>

        <!-- Estado -->
        <div>
          <label for="estado" class="block mb-2 text-gray-700 font-medium">Estado:</label>
          <select id="estado" name="estado"
            class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base input-glow-on-hover-focus focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50 transition duration-300 ease-in-out">
            <option value="0" <%=!isActivo ? "selected" : ""%>>Inactiva</option>
			<option value="1" <%=isActivo ? "selected" : ""%>>Activa</option>
          </select>
        </div>
      </div>

      <div class="flex items-center justify-between pt-4 gap-4">
        <a href="/TPINT_GRUPO_6_LAB4/ClienteServlet?Param=mostrarClientes"
          class="py-3 px-8 text-center bg-gray-100 border border-gray-400 rounded-lg text-base font-semibold w-full hover:bg-gray-300 transition duration-200 ease-in-out">Volver</a>
        <input type="submit" value="Modificar Cliente"
          class="py-3 px-8 bg-white text-black border border-black rounded-lg cursor-pointer w-full text-base font-bold hover:bg-black hover:text-white transition duration-200 ease-in-out" />
        
      </div>
    </form>
  </div>
	  <script>
	  document.addEventListener("DOMContentLoaded", () => {
	    const selectNacionalidad = document.getElementById("nacionalidad");
	    const selectProvincia = document.getElementById("provincia");
	    const selectLocalidad = document.getElementById("localidad");
	
	    // --- NACIONALIDADES ---
	    fetch("https://restcountries.com/v3.1/all?fields=name")
	      .then(res => res.json())
	      .then(data => {
	        const nombres = data.map(pais => pais.name.common).sort();
	        selectNacionalidad.innerHTML = "<option value=''>Seleccione una nacionalidad</option>";
	        nombres.forEach(nombre => {
	          const option = document.createElement("option");
	          option.value = nombre;
	          option.textContent = nombre;
	          if (nombre === nacionalidadCliente) option.selected = true;
	          selectNacionalidad.appendChild(option);
	        });
	      })
	      .catch(error => {
	        console.error("Error al cargar nacionalidades:", error);
	        selectNacionalidad.innerHTML = "<option>Error al cargar</option>";
	      });
	
	    // --- PROVINCIAS ---
	    fetch("https://apis.datos.gob.ar/georef/api/provincias")
	      .then(res => res.json())
	      .then(data => {
	        const provincias = data.provincias.map(p => p.nombre).sort();
	        selectProvincia.innerHTML = "<option value=''>Seleccionar provincia...</option>";
	        provincias.forEach(nombre => {
	          const option = document.createElement("option");
	          option.value = nombre;
	          option.textContent = nombre;
	          if (nombre === provinciaCliente) option.selected = true;
	          selectProvincia.appendChild(option);
	        });
	
	        // Cargar localidades después de seleccionar la provincia correcta
	        if (provinciaCliente) {
	          selectProvincia.dispatchEvent(new Event("change"));
	        }
	      });
	
	    // --- LOCALIDADES por provincia ---
	    selectProvincia.addEventListener("change", () => {
	      const provinciaSeleccionada = selectProvincia.value;
	
	      if (!provinciaSeleccionada) {
	        selectLocalidad.innerHTML = "<option value=''>Seleccioná una provincia primero</option>";
	        selectLocalidad.disabled = true;
	        return;
	      }
	
	      const url = "https://apis.datos.gob.ar/georef/api/localidades?provincia=" +
	        encodeURIComponent(provinciaSeleccionada) + "&max=1000";
	
	      fetch(url)
	        .then(res => res.json())
	        .then(data => {
	          const localidades = data.localidades.map(l => l.nombre).sort();
	          selectLocalidad.innerHTML = "<option value=''>Seleccionar localidad...</option>";
	          localidades.forEach(nombre => {
	            const option = document.createElement("option");
	            option.value = nombre;
	            option.textContent = nombre;
	            if (nombre === localidadCliente) option.selected = true;
	            selectLocalidad.appendChild(option);
	          });
	          selectLocalidad.disabled = false;
	        })
	        .catch(err => {
	          console.error("Error al cargar localidades:", err);
	          selectLocalidad.innerHTML = "<option>Error al cargar localidades</option>";
	        });
	    });
	  });
	</script>
  
  
  </main>

  <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
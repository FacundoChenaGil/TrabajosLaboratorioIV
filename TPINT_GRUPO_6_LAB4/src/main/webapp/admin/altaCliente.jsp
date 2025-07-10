<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="entidad.Cliente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta de Cliente</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
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
<body class="flex flex-col min-h-screen bg-gray-100">
	<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
	<main class="flex-grow flex items-center justify-center p-4 md:p-8">
		<div
			class="max-w-3xl md:max-w-5xl mx-auto bg-white p-10 md:p-12 rounded-2xl shadow-2xl">
			<h1
				class="text-3xl font-bold text-center text-gray-800 mb-6 tracking-tight">
				Alta Cliente</h1>

			<%
			HttpSession sessionClientes = request.getSession();
			String mensajeError = (String) session.getAttribute("mensajeError");
			String mensajeExito = (String) session.getAttribute("mensajeExito");
			Cliente clienteForm = (Cliente) session.getAttribute("clienteForm");

			if (mensajeError != null) {
			%>
			<div
				class="bg-red-100 text-red-700 px-4 py-3 rounded mb-4 text-center font-semibold">
				<%=mensajeError%>
			</div>
			<%
			} else if (mensajeExito != null) {
			%>
			<div
				class="bg-green-100 text-green-700 px-4 py-3 rounded mb-4 text-center font-semibold">
				<%=mensajeExito%>
			</div>
			<%
			}
			// Limpieza
			session.removeAttribute("mensajeError");
			session.removeAttribute("mensajeExito");
			session.removeAttribute("clienteForm");
			%>

			<hr class="border-t border-gray-300 my-8">
			<form action="<%=request.getContextPath()%>/ClienteServlet"
				method="post">
				<input type="hidden" name="accion" value="alta">
				<div
					class="grid grid-cols-1 md:grid-cols-2 gap-x-8 md:gap-x-12 gap-y-6 mb-6">
					<div>
						<label for="dni" class="block mb-2 text-gray-700 font-medium">DNI:</label>
						<input type="text" id="dni" name="dni" required minlength="7"
							maxlength="8" pattern="\d+" title="Solo se aceptan números"
							placeholder="Ej: 12345678" value="<%= (clienteForm != null) ? clienteForm.getDni() : "" %>"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out">
					</div>

					<div>
						<label for="cuil" class="block mb-2 text-gray-700 font-medium">CUIL:</label>
						<input type="text" id="cuil" name="cuil" required minlength="11"  value="<%= (clienteForm != null) ? clienteForm.getCuil() : "" %>"
							maxlength="11" pattern="\d+" title="Solo se aceptan números"
							placeholder="Ej: 27-12345678-9"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out">
					</div>

					<div>
						<label for="nombre" class="block mb-2 text-gray-700 font-medium">Nombre:</label>
						<input type="text" id="nombre" name="nombre" required minlength="2"
							maxlength="100" value="<%= (clienteForm != null) ? clienteForm.getNombre() : "" %>"
							pattern="[A-Za-zñÑáéíóúÁÉÍÓÚüÜ\s]*[A-Za-zñÑáéíóúÁÉÍÓÚüÜ]+"
							title="Ingrese solo letras" placeholder="Ej: Ana"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out">
					</div>

					<div>
						<label for="apellido" class="block mb-2 text-gray-700 font-medium">Apellido:</label>
						<input type="text" id="apellido" name="apellido" required
							minlength="2" maxlength="100" value="<%= (clienteForm != null) ? clienteForm.getApellido() : "" %>"
							pattern="[A-Za-zñÑáéíóúÁÉÍÓÚüÜ\s]*[A-Za-zñÑáéíóúÁÉÍÓÚüÜ]+"
							title="Ingrese solo letras" placeholder="Ej: López"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out">
					</div>

					<div>
						<label class="block mb-2 text-gray-700 font-medium">Género:</label>
						<div class="mt-2 flex flex-wrap gap-x-6 gap-y-2 justify-center">
							<label class="inline-flex items-center"> <input
								type="radio" id="masculino" name="sexo" value="M"  <%= (clienteForm != null && "M".equals(clienteForm.getSexo())) ? "checked" : "" %>required
								class="form-radio w-5 h-5"> <span
								class="ml-2 text-gray-700">Masculino</span>
							</label> <label class="inline-flex items-center"> <input
								type="radio" id="femenino" name="sexo" value="F" required <%= (clienteForm != null && "F".equals(clienteForm.getSexo())) ? "checked" : "" %>
								class="form-radio w-5 h-5"> <span
								class="ml-2 text-gray-700">Femenino</span>
							</label> <label class="inline-flex items-center"> <input
								type="radio" id="otro" name="sexo" value="O" required <%= (clienteForm != null && "O".equals(clienteForm.getSexo())) ? "checked" : "" %>
								class="form-radio w-5 h-5"> <span
								class="ml-2 text-gray-700">Otro</span>
							</label>
						</div>
					</div>

					<div>
						<label for="telefono" class="block mb-2 text-gray-700 font-medium">Teléfono:</label>
						<input type="tel" id="telefono" name="telefono" required value="<%= (clienteForm != null) ? clienteForm.getTelefono() : "" %>"
							minlength="10" maxlength="20" pattern="\d+"
							title="Solo se aceptan números" placeholder="Ej: 11 2345 6789"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out">
					</div>

					<div>
						<label for="fecha" class="block mb-2 text-gray-700 font-medium">Fecha
							de Nacimiento:</label> <input type="date" id="fecha" name="fecha" value="<%= (clienteForm != null) ? clienteForm.getFechaNacimiento() : "" %>"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out"
							required>
					</div>

					<div>
						<label for="nacionalidad"
							class="block mb-2 text-gray-700 font-medium">Nacionalidad:</label>
						<select id="nacionalidad" name="nacionalidad" value="<%= (clienteForm != null) ? clienteForm.getNacionalidad() : "" %>"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out">
							<option value="">Cargando nacionalidades...</option>
						</select>
					</div>

					<div>
						<label for="provincia" class="block mb-2 text-gray-700 font-medium">Provincia:</label>
						<select name="provincia" id="provincia" value="<%= (clienteForm != null) ? clienteForm.getProvincia() : "" %>"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                   focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                   input-glow-on-hover-focus transition duration-300 ease-in-out">
							<option value="">Cargando provincias...</option>
						</select>
					</div>

					<div>
						<label for="localidad" class="block mb-2 text-gray-700 font-medium">Localidad:</label>
						<select name="localidad" id="localidad" disabled value="<%= (clienteForm != null) ? clienteForm.getLocalidad() : "" %>"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                   focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                   input-glow-on-hover-focus transition duration-300 ease-in-out">
							<option value="">Seleccioná una provincia primero</option>
						</select>
					</div>

					<div>
						<label for="direccion" class="block mb-2 text-gray-700 font-medium">Dirección:</label>
						<input type="text" id="direccion" name="direccion" required value="<%= (clienteForm != null) ? clienteForm.getDireccion() : "" %>"
							minlength="5" maxlength="255"
							placeholder="Calle, Número, Piso, Dpto."
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out">
					</div>

					<div>
						<label for="email" class="block mb-2 text-gray-700 font-medium">Email:</label>
						<input type="email" id="email" name="email" required value="<%= (clienteForm != null) ? clienteForm.getCorreoElectronico() : "" %>"
							placeholder="Ej: ana.lopez@gmail.com"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out">
					</div>

					<div>
						<label for="username" class="block mb-2 text-gray-700 font-medium">Nombre
							de Usuario:</label> <input type="text" id="username" name="username" value="<%= (clienteForm != null) ? clienteForm.getUsuario().getUsuario() : "" %>"
							required placeholder="Ej: alopez"
							pattern="[A-Za-z0-9ñÑáéíóúÁÉÍÓÚüÜ_\-]+"
							title="El nombre de usuario debe contener solo letras, números, guiones bajos o guiones medios. No se permiten espacios."
							minlength="4" maxlength="50"
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out">
					</div>

					<div>
						<label for="password" class="block mb-2 text-gray-700 font-medium">Clave:</label> 
						<input type="password" id="password" name="password" pattern="^\S+$" title="La contraseña no debe contener espacios" minlength="6" maxlength="255"
							requiredminlength="6" maxlength="255" value="<%= (clienteForm != null) ? clienteForm.getUsuario().getClave() : "" %>"
							placeholder="Letras mayúsculas, minúsculas, números y símbolos."
							class="w-full py-3 px-4 border border-gray-200 rounded-lg bg-white text-gray-800 text-base
                                    focus:outline-none focus:border-red-500 focus:ring-2 focus:ring-red-200 focus:ring-opacity-50
                                    input-glow-on-hover-focus transition duration-300 ease-in-out">
					</div>
				</div>

				<!-- Botones -->
				<div class="flex items-center justify-between pt-4 gap-4">
					<a href="/TPINT_GRUPO_6_LAB4/ClienteServlet?Param=mostrarClientes"
						class="py-3 px-8 text-center bg-gray-100 border border-gray-400 rounded-lg text-base font-semibold w-full hover:bg-gray-300 transition duration-200 ease-in-out">
						Volver </a> <input type="submit" value="Registrar Cliente"
						class="py-3 px-8 bg-white text-black border border-black rounded-lg cursor-pointer w-full text-base font-bold hover:bg-black hover:text-white transition duration-200 ease-in-out" />
				</div>
			</form>
		</div>
	</main>

	<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

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
	        selectProvincia.appendChild(option);
	      });
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

</body>
</html>
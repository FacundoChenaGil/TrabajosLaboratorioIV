<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solicitar Prestamo</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>

<!-- Navbar y header -->
<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

<body>
	<main
		class="flex flex-grow flex-col gap-[27px] bg-[#fef9f9] justify-center items-center min-h-screen bg-white">

		<h1 class="text-2xl font-bold text-[#E75E5E] mb-6">Solicitud de
			Préstamo</h1>
		<p class="text-sm text-gray-600 mt-2">Simulá tu préstamo y obtené
			resultados inmediatos.</p>

		<form class="flex flex-col justify-center items-center gap-[35px]">
			<div
				class="flex flex-col gap-[21px] px-[21px] py-[38px] bg-[#E75E5E] rounded-[9px] w-[292px]"
				style="box-shadow: 7px 8px 4px rgba(0, 0, 0, 0.25)">
				<div class="flex flex-col">
					<label for="Seleccionar_Cuenta">Seleccionar Cuenta:</label> <Select
						for="Seleccionar_Cuenta"></Select>
				</div>
				<div class=" flex flex-col">
					<label for="Monto_Solicitar">Monto a Solicitar:</label> <input
						type="text" id="Monto_Solicitar" name="Monto_Solicitar"
						class="w-[250px] h-[22px] rounded-[5px] border border-black"></input>
				</div>
				<div class="flex flex-col">
					<label for="Cantidad_Cuotas">Cantidad de Cuotas:</label> <Select
						for="Cantidad_Cuotas"></Select>
				</div>
			</div>
			<input type="submit" value="Solicitar"
				class="flex w-[188px] h-[53px] rounded-[10px] bg-[#E75E53] text-white font-semibold hover:bg-[#cc4b40] hover:cursor-pointer transition duration-300 shadow"
				style="box-shadow: 5px 6px 4px rgba(0, 0, 0, 0.25)"></input>
		</form>


	</main>

</body>

<!-- Footer -->
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</html>
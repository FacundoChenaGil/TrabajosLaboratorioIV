<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.clip-diagonal {
	clip-path: polygon(70% 0%, 100% 0%, 100% 100%, 20% 100%);
}
</style>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<!-- Navbar y header -->
<jsp:include page="/WEB-INF/jsp/includes/header2.jsp" />

<body>

	<main class="py-12 px-6 min-h-screen pt-[100px] pb-[150px] bg-gray-50">
		<div class="max-w-6xl mx-auto mb-10 text-center">
			<h2 class="text-3xl font-bold text-[#D14444] mb-2">Panel de
				Movimientos</h2>
			<p class="text-gray-600 text-base">Accedé rápidamente a tus
				operaciones bancarias más frecuentes.</p>
		</div>
		<form class="max-w-6xl mx-auto grid grid-cols-1 sm:grid-cols-2 gap-8">
			<!-- Tarjeta 1 -->
			<a href="#"
				class="relative w-full h-52 border-2 border-gray-200
 rounded-xl overflow-hidden shadow-lg group transform transition duration-300 hover:shadow-2xl hover:-translate-y-1 cursor-pointer bg-white">
				<div
					class="absolute inset-0 bg-gradient-to-tr from-[#D14444] to-[#A83232] clip-diagonal flex flex-col items-end justify-center pr-8 space-y-2 text-white">
					<svg xmlns="http://www.w3.org/2000/svg"
						class="h-10 w-10 self-start mb-2" fill="none" viewBox="0 0 24 24"
						stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round"
							d="M12 8c-1.657 0-3 1.343-3 3v5h6v-5c0-1.657-1.343-3-3-3z" />
              <path stroke-linecap="round" stroke-linejoin="round"
							d="M9 21h6" />
            </svg>
					<h3 class="text-2xl font-bold leading-tight">Solicitar Cuenta</h3>
					<p class="text-sm font-light max-w-xs text-right">Abre una
						nueva cuenta con beneficios exclusivos y fácil acceso a tu dinero.
					</p>
				</div>
			</a>

			<!-- Tarjeta 2 -->
			<a href="#"
				class="relative w-full h-52 border-2 border-gray-200 rounded-xl overflow-hidden shadow-lg group transform transition duration-300 hover:shadow-2xl hover:-translate-y-1 cursor-pointer bg-white">
				<div
					class="absolute inset-0 bg-gradient-to-tr from-[#D14444] to-[#A83232] clip-diagonal flex flex-col items-end justify-center pr-8 space-y-2 text-white">
					<svg xmlns="http://www.w3.org/2000/svg"
						class="h-10 w-10 self-start mb-2" fill="none" viewBox="0 0 24 24"
						stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round"
							d="M17 9V7a5 5 0 00-10 0v2M7 13h10l1 7H6l1-7z" />
            </svg>
					<h3 class="text-2xl font-bold leading-tight">Transferencia</h3>
					<p class="text-sm font-light max-w-xs text-right">Envía dinero
						rápida y fácilmente a cuentas nacionales e internacionales.</p>
				</div>
			</a>

			<!-- Tarjeta 3 -->
			<a href="#"
				class="relative w-full h-52 border-2 border-gray-200 rounded-xl overflow-hidden shadow-lg group transform transition duration-300 hover:shadow-2xl hover:-translate-y-1 cursor-pointer bg-white">
				<div
					class="absolute inset-0 bg-gradient-to-tr from-[#D14444] to-[#A83232] clip-diagonal flex flex-col items-end justify-center pr-8 space-y-2 text-white">
					<svg xmlns="http://www.w3.org/2000/svg"
						class="h-10 w-10 self-start mb-2" fill="none" viewBox="0 0 24 24"
						stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round"
							d="M12 8c-1.657 0-3 1.343-3 3v5h6v-5c0-1.657-1.343-3-3-3z" />
              <path stroke-linecap="round" stroke-linejoin="round"
							d="M9 21h6" />
              <path stroke-linecap="round" stroke-linejoin="round"
							d="M12 2v4" />
            </svg>
					<h3 class="text-2xl font-bold leading-tight">Pagar Préstamo</h3>
					<p class="text-sm font-light max-w-xs text-right">Administra y
						realiza pagos de tus préstamos de manera rápida y segura.</p>
				</div>
			</a>

			<!-- Tarjeta 4 -->
			<a href="<%=request.getContextPath()%>/CuentaServlet?Param=solicitarPrestamo"
				class="relative w-full h-52 border-2 border-gray-200 rounded-xl overflow-hidden shadow-lg group transform transition duration-300 hover:shadow-2xl hover:-translate-y-1 cursor-pointer bg-white">
				<div
					class="absolute inset-0 bg-gradient-to-tr from-[#D14444] to-[#A83232] clip-diagonal flex flex-col items-end justify-center pr-8 space-y-2 text-white">
					<svg xmlns="http://www.w3.org/2000/svg"
						class="h-10 w-10 self-start mb-2" fill="none" viewBox="0 0 24 24"
						stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round"
							d="M15 12H9m6 0a3 3 0 00-6 0m6 0v6m-6-6v6" />
            </svg>
					<h3 class="text-2xl font-bold leading-tight">Solicitar
						Préstamo</h3>
					<p class="text-sm font-light max-w-xs text-right">Solicita un
						préstamo personalizado con tasas competitivas.</p>
				</div>
			</a>
		</form>
	</main>

</body>

<!-- Footer -->
<jsp:include page="/WEB-INF/jsp/includes/footer2.jsp" />
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<header
	class="px-[22px] py-[8px] flex justify-between items-center w-full bg-[#D14444]">
	<div class="flex items-center gap-[10px]">
		<a
			class="w-16 h-16 flex bg-[#8B0000] rounded-full items-center justify-center shadow-lg">
			<img src="LogoUTN_nvgsb.svg" width="36" />
		</a>
		<h1 class="text-2xl font-bold text-white">Banco UTN</h1>
	</div>
	<nav class="flex gap-[42px] items-center">
		<ul class="flex gap-[46px] text-white font-medium">
			<li class="hover:underline cursor-pointer transition">Personas</li>
			<li class="hover:underline cursor-pointer transition">Admin</li>
			<li class="hover:underline cursor-pointer transition">Nosotros</li>
		</ul>
		<div class="flex gap-[9px]">
			<button
				class="w-[170px] h-[40px] rounded-[10px] bg-white text-[#D14444] font-semibold hover:shadow-lg transition transform hover:-translate-y-0.5 shadow-md">
				Online Banking</button>
			<button
				class="w-[170px] h-[40px] rounded-[10px] bg-[#8B0000] text-white font-semibold hover:bg-[#6a0000] transform hover:-translate-y-0.5 shadow-md transition">
				Crear Cuenta</button>
		</div>
	</nav>
</header>
</html>
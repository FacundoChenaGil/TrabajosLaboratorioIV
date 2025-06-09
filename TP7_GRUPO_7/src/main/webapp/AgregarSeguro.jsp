<%@page import="java.util.ArrayList"%>
<%@page import="dominio.TipoSeguro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav>
        <a href="Inicio.jsp">Inicio</a>
        <a href="servletSeguro?Param=agregar">Agregar Seguros</a>
        <a href="ListarSeguro.jsp">Listar Seguros</a>
    </nav>

<%
int proximoId = 0;
ArrayList<TipoSeguro> tiposSeguro = null;
if(request.getAttribute("listaTiposSeguro") != null) {
	tiposSeguro = (ArrayList<TipoSeguro>)request.getAttribute("listaTiposSeguro");
}

if(request.getAttribute("nuevoId") != null) {
	proximoId = Integer.parseInt(request.getAttribute("nuevoId").toString());
}
 %>


<h1>Agregar Seguros</h1>
<form action="servletSeguro" method="post">
	<table>
		<tr>
			<td>Id Seguro:</td>
			<td><%=proximoId%> <input type="hidden" name="idSeguro" value="<%=proximoId%>" /></td>
		</tr>
		<tr>
			<td>Descripción:</td>
			<td><input type="text" name="descripcion" required/></td>
		</tr>
		<tr>
			<td>Tipo de Seguro:</td>
			<td>
				<select name="tipoSeguro" required>
					<%
					if(tiposSeguro != null)
						for(TipoSeguro tipoSeguro : tiposSeguro) {
					
					 %>
						<option value="<%=tipoSeguro.getIdTipo()%>"><%=tipoSeguro.getDescripcion()%></option>
					<%
						}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Costo contratación:</td>
			<td><input type="text" name="contratacion" required/></td>
		</tr>
		<tr>
			<td>Costo Máximo Asegurado:</td>
			<td><input type="text" name="costoMaximo" required/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Aceptar" name="btnAceptar" /></td>
		</tr>
	</table>
</form>

<%
	int filas = 0;
	String mensajeCompletar = null;
	if (request.getAttribute("cantFilas") != null) {
		filas = Integer.parseInt(request.getAttribute("cantFilas").toString());
	}
	if (request.getAttribute("noCompleto") != null) {
		mensajeCompletar = request.getAttribute("noCompleto").toString();
	}
	%>

	<%
	if (filas != 0 && filas == 1) {
	%>
	<p style="color: blue;">Seguro agregado correctamente.</p>
	<%
	}
	%>

	<%
	if (mensajeCompletar != null) {
	%>
	<strong style="color: red;"> <%=mensajeCompletar%></strong>
	<%
	}
%>


</body>
</html>
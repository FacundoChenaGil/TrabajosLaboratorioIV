<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int proximoId = 0;
ArrayList<String> tiposSeguro = null;
if(request.getAttribute("listaTiposSeguro") != null) {
	tiposSeguro = (ArrayList<String>)request.getAttribute("listaTiposSeguro");
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
			<td><input type="text" name="descripcion" /></td>
		</tr>
		<tr>
			<td>Tipo de Seguro:</td>
			<td>
				<select name="tipoSeguro">
					<%
					if(tiposSeguro != null)
						for(String tipoSeguro : tiposSeguro) {
					
					 %>
						<option value=<%=tipoSeguro%>><%=tipoSeguro%></option>
					<%
						}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Costo contratación:</td>
			<td><input type="text" name="contratacion" /></td>
		</tr>
		<tr>
			<td>Costo Máximo Asegurado:</td>
			<td><input type="text" name="costoMaximo" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Aceptar" name="btnAceptar" /></td>
		</tr>
	</table>
</form>

<%
	Integer filas = null;
	String mensajeCompletar = null;
	if (request.getAttribute("cantFilas") != null) {
		filas = (Integer) request.getAttribute("cantFilas");
	}
	if (request.getAttribute("noCompleto") != null) {
		mensajeCompletar = request.getAttribute("noCompleto").toString();
	}
	%>

	<%
	if (filas != null && filas == 1) {
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
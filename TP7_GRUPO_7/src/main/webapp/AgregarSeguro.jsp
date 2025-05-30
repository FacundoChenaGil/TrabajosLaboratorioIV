<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Agregar Seguros</h1>
<table>
	<tr>
		<td>Id Seguro:</td>
		<td>3</td>
	</tr>
	<tr>
		<td>Descripción:</td>
		<td><input type="text" name="descripcion" /></td>
	</tr>
	<tr>
		<td>Tipo de Seguro:</td>
		<td><select name="tipoSeguro"></select></td>
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


</body>
</html>
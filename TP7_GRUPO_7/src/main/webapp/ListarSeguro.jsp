<%@page import="dominio.Seguro"%>
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
	<nav>
        <a href="Inicio.jsp">Inicio</a>
        <a href="servletSeguro?Param=agregar">Agregar Seguros</a>
        <a href="ListarSeguro.jsp">Listar Seguros</a>
    </nav>

    <h2>"Tipo de seguros en la base de datos"</h2>

    <form action="servletSeguro" method="get" name="formFiltro">
        <label for="tipoSeguro">Búsqueda por tipo de seguros:</label>
        <select id="tipoSeguro" name="tipoSeguro">
            <option value="0">Todos los tipos</option>
            <option value="1">Seguro de casas</option>
            <option value="2">Seguro de vida</option>
            <option value="3">Seguro de motos</option>
            </select>
        <input type="submit" value="Filtrar" name="btnFiltrar">
    </form>


	<% 
		ArrayList<Seguro> listaSeguros = null;
		if(request.getAttribute("listaSeguros")!=null)
		{
			listaSeguros = (ArrayList<Seguro>)request.getAttribute("listaSeguros");
		}
 	%>

    <table>
        <thead>
            <tr>
                <th>ID Seguro</th>
                <th>Descripción Seguro</th>
                <th>Descripción Tipo Seguro</th>
                <th>Costo Contratación</th>
                <th>Costo Máximo Asegurado</th>
            </tr>
        </thead>
        <tbody>
            <%
            	if(listaSeguros!=null)
        			for(Seguro seguro : listaSeguros) 
        		{
            %>
            <tr>
                <td><%=seguro.getId() %></td>
                <td><%=seguro.getDescripcion() %></td>
                <td><%=seguro.getTipoSeguro().getDescripcion() %></td>
                <td><%=seguro.getCostoContratacion() %></td>
                <td><%=seguro.getCostoAsegurado() %></td>
            </tr>
            <%
        		}
            %>
        </tbody>
    </table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%
    String tipoFiltro = request.getParameter("tipo");
    String fechaDesde = request.getParameter("fechaDesde");
    String fechaHasta = request.getParameter("fechaHasta");

    // Simulación de movimientos
    class Movimiento {
        String fecha, tipo, descripcion;
        double monto;
        Movimiento(String fecha, String tipo, String descripcion, double monto) {
            this.fecha = fecha;
            this.tipo = tipo;
            this.descripcion = descripcion;
            this.monto = monto;
        }
    }

    List<Movimiento> movimientos = new ArrayList<>();
    movimientos.add(new Movimiento("2025-06-10", "transferencia", "Transferencia a Juan", -5000));
    movimientos.add(new Movimiento("2025-06-08", "prestamo", "Pago de préstamo", -2000));
    movimientos.add(new Movimiento("2025-06-05", "alta", "Alta de cuenta", 10000));
    movimientos.add(new Movimiento("2025-06-01", "transferencia", "Transferencia a María", -3000));

    // Filtro simple
    List<Movimiento> filtrados = new ArrayList<>();
    for (Movimiento m : movimientos) {
        boolean coincideTipo = (tipoFiltro == null || tipoFiltro.isEmpty() || m.tipo.equals(tipoFiltro));
        boolean coincideFecha = true;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date movFecha = sdf.parse(m.fecha);
            if (fechaDesde != null && !fechaDesde.isEmpty()) {
                Date desde = sdf.parse(fechaDesde);
                if (movFecha.before(desde)) coincideFecha = false;
            }
            if (fechaHasta != null && !fechaHasta.isEmpty()) {
                Date hasta = sdf.parse(fechaHasta);
                if (movFecha.after(hasta)) coincideFecha = false;
            }
        } catch (Exception e) {
            coincideFecha = true;
        }

        if (coincideTipo && coincideFecha) filtrados.add(m);
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Historial de Movimientos</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen p-6">
<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

<h1 class="text-2xl font-bold mb-6 text-red-900">Historial de Movimientos</h1>

<form method="get" class="mb-6 flex flex-wrap gap-4">
    <select name="tipo" class="border p-2 rounded">
        <option value="">Todos</option>
        <option value="transferencia" <%= "transferencia".equals(tipoFiltro) ? "selected" : "" %>>Transferencias</option>
        <option value="prestamo" <%= "prestamo".equals(tipoFiltro) ? "selected" : "" %>>Pago de préstamo</option>
        <option value="alta" <%= "alta".equals(tipoFiltro) ? "selected" : "" %>>Alta</option>
    </select>

    <input type="date" name="fechaDesde" class="border p-2 rounded" value="<%= fechaDesde != null ? fechaDesde : "" %>" />
    <input type="date" name="fechaHasta" class="border p-2 rounded" value="<%= fechaHasta != null ? fechaHasta : "" %>" />

    <button type="submit" class="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700">
        Filtrar
    </button>
</form>

<% if (filtrados.isEmpty()) { %>
    <p class="text-red-600 font-semibold">No se encontraron movimientos con los criterios seleccionados.</p>
<% } else { %>
    <table class="table-auto w-full bg-white shadow rounded-lg">
        <thead class="bg-red-600 text-white">
            <tr>
                <th class="px-4 py-2">Fecha</th>
                <th class="px-4 py-2">Tipo</th>
                <th class="px-4 py-2">Descripción</th>
                <th class="px-4 py-2">Monto</th>
            </tr>
        </thead>
        <tbody>
        <% for (Movimiento m : filtrados) { %>
            <tr class="text-center border-t">
                <td class="px-4 py-2"><%= m.fecha %></td>
                <td class="px-4 py-2 capitalize"><%= m.tipo %></td>
                <td class="px-4 py-2"><%= m.descripcion %></td>
                <td class="px-4 py-2 text-right"><%= String.format("$ %.2f", m.monto) %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
<% } %>

</body>
</html>
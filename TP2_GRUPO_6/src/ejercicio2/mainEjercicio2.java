package ejercicio2;

public class mainEjercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Producto producto = new Producto("2025-12-01", "L001");
		ProductoCongelado congelado = new ProductoCongelado("2026-01-15", "C002", "-18°C");
		ProductoFresco fresco = new ProductoFresco("2025-11-10", "F003", "2025-10-01", "Argentina");
		ProductoRefrigerado refrigerado = new ProductoRefrigerado("2025-10-30", "R004", "OSA1234");

		System.out.println("=== Producto ===");
		producto.mostrar();

		System.out.println("\n=== Producto Congelado ===");
		congelado.mostrar();

		System.out.println("\n=== Producto Fresco ===");
		fresco.mostrar();

		System.out.println("\n=== Producto Refrigerado ===");
		refrigerado.mostrar();
	}
}



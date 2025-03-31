package EJERCICIO1;

public class Principal {

	public static void main(String[] args) {
		Empleado[] vEmpleados = new Empleado[5];
		
		String[] nombres = {"Justina", "Victoria", "Gabriela", "Facundo", "Ernesto"};
		int[] edades = {22, 21, 20, 19, 18};
		
		
		// Creacion de Empleados
		for (int i=0;i<5;i++) {
			if (i==2) {
				vEmpleados[i] = new Empleado();
			}
			else {
				vEmpleados[i] = new Empleado(nombres[i], edades[i]); // Constructor con parámetros
			}
			
			Empleado emp = vEmpleados[i];
			
			System.out.println(emp.toString());
			System.out.println("El próximo ID será el " + Empleado.devuelveProximoID());
		}
		
		
	}
	
	

}

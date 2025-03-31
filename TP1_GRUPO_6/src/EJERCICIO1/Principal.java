package EJERCICIO1;

public class Principal {

	public static void main(String[] args) {
		Empleado[] vEmpleados = new Empleado[5];
		
		// Creacion de Empleados
		for (int i=0;i<5;i++) {
			if (i==2) {
				vEmpleados[i] = new Empleado();
			}
			else {
				vEmpleados[i] = new Empleado("Empleado " + (i + 1), i + 20);
			}
			
			Empleado emp = vEmpleados[i];
			
			System.out.println(emp.toString());
			System.out.println("El próximo ID será el " + Empleado.devuelveProximoID());
		}
	}

}

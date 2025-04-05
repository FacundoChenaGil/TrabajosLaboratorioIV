package ejercicio1;
import java.util.TreeSet;

public class MainEjercicio1_b {

	public static void main(String[] args) {
		
		TreeSet<Profesor> listaProfesores = new TreeSet<Profesor>();
		listaProfesores.add(new Profesor("Justina", 22, "Programación II", 5));
		listaProfesores.add(new Profesor("Facundo", 26, "Laboratorio IV", 3));
		listaProfesores.add(new Profesor("Victoria", 21, "Base de Datos II", 5));
		listaProfesores.add(new Profesor("Gabriela", 26, "Base de Datos I", 4));
		listaProfesores.add(new Profesor("Ernesto", 28, "Programación III", 2));

	}

}

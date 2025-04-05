package ejercicio1;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainEjercicio1_a {
	public static void main(String[] args) {
		// Utilidades
		String[] nombres = {"Justina", "Victoria", "Gabriela", "Facundo", "Ernesto"};
		int[] edades = {22, 21, 20, 19, 18};
		
		ArrayList<Profesor> profesores = new ArrayList<Profesor>();
		
		for (int i=0;i<5;i++) {
			profesores.add(new Profesor(nombres[i], edades[i], "Profesor", i+8));
		}
		
		ListIterator<Profesor> it = profesores.listIterator();
		
		while(it.hasNext()) {
			Profesor profesor = it.next();
			System.out.println(profesor.toString());
		}
	}
}

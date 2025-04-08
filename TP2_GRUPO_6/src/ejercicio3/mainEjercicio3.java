package ejercicio3;
import java.util.ArrayList;
import java.util.Iterator;
public class mainEjercicio3 {

	public static void main(String[] args) {
		
		ArrayList <Object> listaEdificios = new ArrayList<Object>(5);
		// 3 Polideportivos
        listaEdificios.add(new Polideportivo("Poli Norte", 1200.0, 1));
        listaEdificios.add(new Polideportivo("Poli Centro", 950.5, 2));
        listaEdificios.add(new Polideportivo("Poli Sur", 1100.0, 3));

        // 2 Edificios de Oficinas
        listaEdificios.add(new EdificioOficinas(10,800.0 ));
        listaEdificios.add(new EdificioOficinas(6, 650.0));

        Iterator<Object> it = listaEdificios.iterator();
        
        while (it.hasNext()) {
            Object obj = it.next();

            if (obj instanceof Polideportivo) {
                Polideportivo p = (Polideportivo) obj;
                System.out.println("== Polideportivo ==");
                System.out.println("Nombre: " + p.getName());
                System.out.println("Superficie: " + p.getSuperficieEdificio());
                System.out.println("Tipo de instalación: " + p.getTipoDeInstalacion());
            } else if (obj instanceof EdificioOficinas) {
                EdificioOficinas e = (EdificioOficinas) obj;
                System.out.println("== Edificio de Oficinas ==");
                System.out.println("Superficie: " + e.getSuperficie());
                System.out.println("Cantidad de oficinas: " + e.getCantidadOficinas());
            }

            System.out.println();
        }
	}
}
		
	



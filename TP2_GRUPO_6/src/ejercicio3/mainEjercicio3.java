package ejercicio3;
import java.util.ArrayList;
import java.util.Iterator;
public class mainEjercicio3 {

	public static void main(String[] args) {
		
		ArrayList <IEdificio> listaEdificios = new ArrayList<IEdificio>(5);
		// 3 Polideportivos
        listaEdificios.add(new Polideportivo("Poli Norte", 1200.0, 1));
        listaEdificios.add(new Polideportivo("Poli Centro", 950.5, 2));
        listaEdificios.add(new Polideportivo("Poli Sur", 1100.0, 3));

        // 2 Edificios de Oficinas
        listaEdificios.add(new EdificioOficinas(10,800.0 ));
        listaEdificios.add(new EdificioOficinas(6, 650.0));

        Iterator<IEdificio> it = listaEdificios.iterator();
        while (it.hasNext()) {
            IEdificio obj = it.next();
            System.out.println(obj.toString());
        }
	}
}
		
	



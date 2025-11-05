package ejercicio;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Saludo s = new Saludo();
		Personal e1 = new Personal("Antonio", s, false);
		Personal e2 = new Personal("Jorge", s, false);
		Personal e3 = new Personal("Juan", s, false);
		Personal e4 = new Personal("JEFE", s, true);
		ArrayList<Personal> plantilla = new ArrayList<>();
		plantilla.add(e1);
		plantilla.add(e2);
		plantilla.add(e3);
		plantilla.add(e4);

		

		System.out.printf("%s llego\n", e1.getNombre());
		System.out.printf("%s llego\n", e2.getNombre());
		System.out.printf("%s llego\n", e3.getNombre());
		System.out.printf("%s llego\n", e4.getNombre());
		
		for (Personal trabajador : plantilla) {
			try {
				trabajador.start();
			} catch (IllegalThreadStateException ex) {
				System.out.println(ex.toString());
			}

		}

	}
}

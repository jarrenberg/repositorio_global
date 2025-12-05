package ejercicio;

import java.util.ArrayList;

public class Main {
	private static ArrayList<Thread> almacenHilos = new ArrayList<>();
	private static int tamanyoMaximoAlmacen = 50;

	public static void main(String args[]) {
		Servidor s = null;
		int port = 5000;

		while (true) {
			try {
				if (almacenHilos.size() <= tamanyoMaximoAlmacen) {
					s = new Servidor(port);
					almacenHilos.add(s);
					s.start();
					if (!s.isAlive()) {
						almacenHilos.remove(s);
					}
				}
			} catch (Exception e) {
				System.out.println("Error en main");
			}
		}
	}
}

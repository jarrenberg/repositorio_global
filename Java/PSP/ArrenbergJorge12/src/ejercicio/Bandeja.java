package ejercicio;

import java.util.ArrayList;

public class Bandeja {
	private static ArrayList<Pizza> bandeja = new ArrayList<>();

	public ArrayList<Pizza> getBandeja() {
		return this.bandeja;
	}

	public static void anyadirComanda(Pizza p) {
		synchronized (bandeja) {
			long duracion = (long) ((Math.random() * 5) * 1000);
			int tamanyoInicial = bandeja.size();
			bandeja.add(p);
			int tamanyoFinal = bandeja.size();
			if (tamanyoFinal > tamanyoInicial) {
				System.out.println("Se ha añadido la comanda.");
			} else {
				System.out.println("No se ha añadido la comanda");
			}
			try {
				Thread.sleep(duracion);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void cogerComanda(Pizza p) {
		synchronized (bandeja) {
			boolean eliminado = bandeja.remove(p);
			if (eliminado) {
				System.out.println("Se ha entregado la comanda");
			} else {
				System.out.println("No se ha entregado la comanda");
			}
		}
	}

}

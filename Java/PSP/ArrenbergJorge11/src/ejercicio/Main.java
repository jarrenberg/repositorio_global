package ejercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) {
		int tamanyoBanyos = 3;
		Semaphore banyo = new Semaphore(tamanyoBanyos);

		List<Persona> colaBanyo = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			colaBanyo.add(new Persona("Persona " + i, banyo));
			Thread t = new Thread(colaBanyo.get(i));
			t.start();
		}

	}
}

package ejercicio;

import java.util.concurrent.Semaphore;

public class Persona implements Runnable {

	private String nombre;
	private Semaphore s;

	public Persona(String nombre, Semaphore s) {
		super();
		this.nombre = nombre;
		this.s = s;
	}

	@Override
	public void run() {
		long tiempoRandom = (long) (Math.random() * 3000);
		try {
			s.acquire();
			System.out.printf("%s entra al baño.\n", nombre);
			Thread.sleep(tiempoRandom);
		} catch (InterruptedException ex) {
			System.out.println(ex.toString());

		} catch (Exception ex) {
			System.out.println(ex.toString());
		} finally {
			System.out.printf("%s sale del baño.\n", nombre);
			s.release();
		}

	}

}

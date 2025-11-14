package ejercicio;

import java.util.concurrent.Semaphore;

public class Grunyon implements Runnable {
	private String principal;
	private String postre;
	private Semaphore s;

	public Grunyon(String principal, String postre, Semaphore s) {
		this.principal = principal;
		this.postre = postre;
		this.s = s;
	}

	public void come(String comida) {
		System.out.printf("El pitufo %s come %s\n", this.getClass().getSimpleName(), comida);
	}

	@Override
	public void run() {
		long tiempoComida = (long) (Math.random() * 3000);
		try {
			s.acquire();
			System.out.printf("Entra el pitufo: %s\n", this.getClass().getSimpleName());
			this.come(principal);
			Thread.sleep(tiempoComida);
			this.come(postre);

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} finally {
			System.out.printf("%s ha terminado de comer\n", this.getClass().getSimpleName());
			s.release();// El hilo libera el permiso
			System.out.printf("\tHuecos disponible para comer: %d\n", s.availablePermits());

		}

	}
}

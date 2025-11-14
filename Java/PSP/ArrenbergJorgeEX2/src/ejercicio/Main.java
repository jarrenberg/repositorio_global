package ejercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) {

		// Creamos un array con la lista de platos principales y postres
		String[] principales = new String[4];
		principales[0] = "Berenjenas rellenas";
		principales[1] = "Fabada";
		principales[2] = "Ajoblanco casero";
		principales[3] = "Ensalada tropical";

		String[] postres = new String[5];
		postres[0] = "Flan";
		postres[1] = "Arroz con leche";
		postres[2] = "Tiramisu";
		postres[3] = "Tocino de cielo";
		postres[4] = "Natillas";

		// Creamos el Semaphore que se encargara de dejar entrar e ir sacando a los
		// pitufos
		Semaphore s = new Semaphore(3);

		// Creamos una lista donde guardar los pitufos
		List<Thread> listaPitufos = new ArrayList<>();
		listaPitufos.add(new Thread(new Bonachon(principales[numeroAleatorio(4)], postres[numeroAleatorio(5)], s)));
		listaPitufos.add(new Thread(new Bromista(principales[numeroAleatorio(4)], postres[numeroAleatorio(5)], s)));
		listaPitufos.add(new Thread(new Dormilon(principales[numeroAleatorio(4)], postres[numeroAleatorio(5)], s)));
		listaPitufos.add(new Thread(new Filosofo(principales[numeroAleatorio(4)], postres[numeroAleatorio(5)], s)));
		listaPitufos.add(new Thread(new Grunyon(principales[numeroAleatorio(4)], postres[numeroAleatorio(5)], s)));
		listaPitufos.add(new Thread(new PapaPitufo(principales[numeroAleatorio(4)], postres[numeroAleatorio(5)], s)));
		listaPitufos.add(new Thread(new Pintor(principales[numeroAleatorio(4)], postres[numeroAleatorio(5)], s)));
		listaPitufos.add(new Thread(new Pitufina(principales[numeroAleatorio(4)], postres[numeroAleatorio(5)], s)));
		listaPitufos.add(new Thread(new Romantico(principales[numeroAleatorio(4)], postres[numeroAleatorio(5)], s)));
		listaPitufos.add(new Thread(new Timido(principales[numeroAleatorio(4)], postres[numeroAleatorio(5)], s)));

		System.out.println("MESAS PARA COMER: 3\n");
		// Inicializamos los hilos pitufos
		for (Thread pitufo : listaPitufos) {
			pitufo.start();
		}
		for (Thread pitufo : listaPitufos) {
			try {
				pitufo.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("\nFIN DE LA COMIDA");

	}

	public static int numeroAleatorio(int max) {
		int resultado = (int) (Math.random() * (max));
		return resultado;

	}

}

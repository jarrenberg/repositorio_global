package ejercicio1;

public class TareaHilo implements Runnable {
	private String nombreHilo;

	//Constructor de la clase TareaHilo con un String como parametro
	public TareaHilo(String nombre) {
		this.nombreHilo = nombre;
		System.out.printf("Creando %s\n", nombreHilo);
	}

	@Override
	public void run() {
		System.out.printf("Ejecutando %s\n", nombreHilo);
		try {
			// El hilo creara una tarea sencilla (contar)
			for (int i = 1; i <= 5; i++) {
				System.out.printf("Hilo %s Contador %d\n", this.nombreHilo, i);
				// Hace una pequeña pausa para simular trabajo y mostrar concurrencia
				Thread.sleep(aleatorioEnRango(10000, 30000)); // Pausa aleatoria entre 10 y 30s
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("FIN");
	}

	//Metodo que devuelve un numero aleatorio en un rango pasado como parametro
	public static int aleatorioEnRango(int min, int max) {
		int res = (int) ((Math.random() * (max - min)) + min);
		return res;

	}

}

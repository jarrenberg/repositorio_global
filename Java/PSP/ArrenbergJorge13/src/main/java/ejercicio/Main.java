package ejercicio;

public class Main {

	public static void main(String args[]) {
		while (true) {
			try {
				Servidor s = new Servidor(5000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

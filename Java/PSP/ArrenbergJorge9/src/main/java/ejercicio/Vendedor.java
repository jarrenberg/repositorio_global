package ejercicio;

public class Vendedor extends Thread {
	private String nombre;
	private final String dni;

	public Vendedor(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDNI() {
		return this.dni;
	}

	public String toString() {
		return String.format(("Nombre: %s, DNI: %s"), this.nombre, this, dni);
	}

	public Ticket vender() throws IllegalStateException {
		Ticket resultado = null;
		try {
			resultado = new Ticket();
		} catch (IllegalStateException ex) {
			// System.out.println(ex.toString());
		} catch (Exception ex) {
			// System.out.println(ex.toString());
		}
		return resultado;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(500);
				Ticket t = this.vender();
				if (t != null) {
					System.out.printf("%s ha vendido la entrada con codigo %d\n", this.nombre, t.getNumeroTicket());
				}
			} catch (IllegalStateException ex) {
				// System.out.println(ex.toString());
			} catch (InterruptedException ex) {
				// System.out.println(ex.toString());
			}
		}
	}

}

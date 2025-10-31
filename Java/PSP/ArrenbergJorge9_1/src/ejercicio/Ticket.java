package ejercicio;

public class Ticket {
	private static int numeroTicketsEmitidos = 0;
	private static final int MAX_TICKETS = 25;
	private int numeroTicket;
	private String vendedor;
	private boolean valido;

	public Ticket(String vendedor) {
		// El constructor ahora llama a un método que devuelve el resultado completo
		ResultadoTicket resultado = generarNumeroTicket(vendedor);
		this.numeroTicket = resultado.numero;
		this.vendedor = vendedor;
		this.valido = resultado.valido;
	}

	// Clase interna para devolver múltiples valores
	private static class ResultadoTicket {
		int numero;
		boolean valido;

		ResultadoTicket(int numero, boolean valido) {
			this.numero = numero;
			this.valido = valido;
		}
	}

	// MÉTODO MEJORADO - siempre crea un objeto pero controla si es válido
	private static synchronized ResultadoTicket generarNumeroTicket(String vendedor) {
		if (numeroTicketsEmitidos >= MAX_TICKETS) {
			// Límite alcanzado - ticket no válido
			return new ResultadoTicket(-1, false);
		}

		numeroTicketsEmitidos++;
		return new ResultadoTicket(numeroTicketsEmitidos, true);
	}

	public static int getNumeroTicketsEmitidos() {
		return numeroTicketsEmitidos;
	}

	public static int getMaxTickets() {
		return MAX_TICKETS;
	}

	public int getNumeroTicket() {
		return numeroTicket;
	}

	public String getVendedor() {
		return vendedor;
	}

	public boolean esValido() {
		return valido;
	}

	@Override
	public String toString() {
		String resultado;
		if (!valido) {
			resultado = "Ticket NO emitido - Límite alcanzado - Vendedor: " + vendedor;
		} else {
			resultado = "Ticket{" + "numeroTicket=" + numeroTicket + ", vendedor='" + vendedor + '\'' + '}';
		}
		return resultado;
	}
}
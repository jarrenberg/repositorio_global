package ejercicio;

public class Ticket {
    public static final int MAXIMO_ENTRADAS = 25;
    private static int numeroTicketsEmitidos = 0;
    private final int numeroTicket;

    public Ticket() {
        if (numeroTicketsEmitidos >= MAXIMO_ENTRADAS) {
            throw new IllegalStateException("No se pueden emitir mas tickets");
        }
        numeroTicketsEmitidos++;
        this.numeroTicket = numeroTicketsEmitidos;
    }

    public int getNumeroTicket() {
        return this.numeroTicket;
    }

    public String toString() {
        return String.format("Numero Ticket: %d", this.numeroTicket);
    }

    public static int getNumeroTicketsEmitidos() {
        return numeroTicketsEmitidos;
    }
}

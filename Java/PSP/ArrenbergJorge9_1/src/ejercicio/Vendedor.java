package ejercicio;

public class Vendedor extends Thread {
    private String nombre;
    private String dni;

    public Vendedor(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket(nombre);
            
            // Solo mostrar si el ticket es válido
            if (ticket.esValido()) {
                System.out.println(ticket.toString());
            } else {
                System.out.println(nombre + " no puede emitir más tickets - Límite alcanzado");
                break;
            }
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }
    }
}
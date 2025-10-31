package ejercicio;

public class Main {
    public static void main(String[] args) {
        Vendedor v1 = new Vendedor("Antonio", "66666666X");
        Vendedor v2 = new Vendedor("Jorge", "66666666X");
        Vendedor v3 = new Vendedor("Juan", "66666666X");

        try {
            v1.start();
            v2.start();
            v3.start();
        } catch (IllegalThreadStateException ex) {
            System.out.println(ex.toString());
        }
        try {
            v1.join();
            v2.join();
            v3.join();
        } catch (InterruptedException ex) {
            System.out.println(ex.toString());
        }
        System.out.printf("\nNumero de tickets emitidos: %d\n",Ticket.getNumeroTicketsEmitidos());

    }

}

package ejercicio;

// Clase que simula el comportamiento de un raton
public class Raton extends Thread {

    // Atributos
    private final String nombre;
    private final int tiempo;

    // Constructor con 2 parametros
    public Raton(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    // Metodo que muestra la informacion de un objeto Raton
    @Override
    public String toString(){
        return String.format("Nombre: %s, Tiempo: %d\n", this.nombre,this.tiempo);
    }

    // Metodo que permite ejecutar 
    @Override
    public void run() {
        try {
            System.out.printf("%s empieza comiendo la merienda\n", this.nombre);
            Thread.sleep(1000*this.tiempo);
            System.out.printf("%s terminó de comer\n",this.nombre);
        } catch (InterruptedException ex) {
            System.out.println(ex.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    // Metodo que permite ver el tiempo que tarda en comer
    public int getTiempo(){
        return this.tiempo;
    }
}
package ejercicio;

public class Cocinero extends Thread {

	private String nombre;
	private Pizza pedido;

	public Cocinero(String nombre, Pizza p) {
		this.nombre = nombre;
		this.pedido = p;
	}

	@Override
	public void run() {
		Bandeja.anyadirComanda(pedido);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pizza getPedido() {
		return pedido;
	}

	public void setPedido(Pizza pedido) {
		this.pedido = pedido;
	}

}

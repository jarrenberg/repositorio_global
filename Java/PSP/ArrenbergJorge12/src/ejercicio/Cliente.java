package ejercicio;

public class Cliente extends Thread {

	private String nombre;
	private Pizza pedido;

	public Cliente(String nombre, Pizza pedido) {
		this.nombre = nombre;
		this.pedido = pedido;
	}

	@Override
	public void run() {
		Bandeja.cogerComanda(pedido);

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

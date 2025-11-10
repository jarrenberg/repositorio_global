package ejercicio;

import java.util.Objects;

public class Cliente {

	private Integer codigoCliente;
	private String nombre;
	private String telefono;

	private boolean numeroTelefonoValido(String telefono) {
		boolean valido = false;
		if (telefono.matches("^[679][0-9]{8}$")) {
			valido = true;
		}
		return valido;
	}

	public Cliente(String nombre, String telefono) throws IllegalArgumentException {
		if (!numeroTelefonoValido(telefono)) {
			throw new IllegalArgumentException("El numero de telefono no es valido");
		}
		this.codigoCliente = null;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public Cliente(String nombre) {
		this.codigoCliente = null;
		this.nombre = nombre;
		this.telefono = null;
	}

	@Override
	public String toString() {
		return "Cliente [codigoCliente=" + codigoCliente + ", nombre=" + nombre + ", telefono=" + telefono + "]";
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (numeroTelefonoValido(telefono)) {
			this.telefono = telefono;
		}
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoCliente, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(codigoCliente, other.codigoCliente) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}

}

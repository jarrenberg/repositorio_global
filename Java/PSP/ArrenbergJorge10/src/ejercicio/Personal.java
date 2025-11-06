package ejercicio;

import java.util.Objects;

public class Personal extends Thread {

	private static boolean jefeAqui = false;

	private final String nombre;
	private Saludo saludo;
	private boolean esJefe;

	public Personal(String nombre, Saludo saludo, boolean esJefe) {
		this.nombre = nombre;
		this.saludo = saludo;
		this.esJefe = esJefe;
	}

	public void saludoEmpleado(String nombre) throws InterruptedException {
		synchronized (saludo) {
			while (!jefeAqui) {
				saludo.wait();
			}
			System.out.printf("%s: Buenos dias jefe\n", nombre);
		}
	}

	public void saludoJefe(String nombre) {
		synchronized (saludo) {
			System.out.printf("*** %s: Buenos dias empleados ***\n", nombre);
			saludo.notifyAll();
			jefeAqui = true;
		}
	}

	@Override
	public void run() {
		if (!this.esJefe) {
			try {
				saludoEmpleado(nombre);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			}
		} else {
			saludoJefe(nombre);
		}
	}

	public Saludo getSaludo() {
		return saludo;
	}

	public void setSaludo(Saludo saludo) {
		this.saludo = saludo;
	}

	public boolean isEsJefe() {
		return esJefe;
	}

	public void setEsJefe(boolean esJefe) {
		this.esJefe = esJefe;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personal other = (Personal) obj;
		return Objects.equals(nombre, other.nombre);
	}
}
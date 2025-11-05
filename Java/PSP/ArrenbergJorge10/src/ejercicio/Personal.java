package ejercicio;

import java.util.Objects;

public class Personal extends Thread {
	private final String nombre;
	private Saludo saludo;
	private boolean esJefe;

	public Personal(String nombre, Saludo saludo, boolean esJefe) {
		this.nombre = nombre;
		this.saludo = saludo;
		this.esJefe = esJefe;
	}

	@Override
	public synchronized void run() {
		synchronized (saludo) {
			if (!this.esJefe) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					System.out.println(e.toString());
				}
				System.out.printf("%s: SALUDOS JEFE\n", this.nombre);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(e.toString());
				}
			} else {
				System.out.printf("*** %s: SALUDOS EMPLEADOS ***\n", this.nombre);
				try {
					this.notifyAll();
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(e.toString());
				}
			}
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

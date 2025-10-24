package mobiliario;

public interface Ajustable {
	public int obtenerPosicion();

	public int subirPosicion() throws IllegalStateException;

	public int bajarPosicion() throws IllegalStateException;
}

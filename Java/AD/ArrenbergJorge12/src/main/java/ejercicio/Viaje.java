package ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

//APARTADO 3
public class Viaje {

	// Atributos de la clase viaje
	private Integer codigo;
	private String destino;
	private Integer plazasDisponibles;

	// Constructor con todos los parametros de la clase viaje
	public Viaje(String destino, Integer plazasDisponibles) {
		this.codigo = null;
		this.destino = destino;
		this.plazasDisponibles = plazasDisponibles;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Integer getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setPlazasDisponibles(Integer plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, destino, plazasDisponibles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viaje other = (Viaje) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(destino, other.destino)
				&& Objects.equals(plazasDisponibles, other.plazasDisponibles);
	}

	@Override
	public String toString() {
		return "Viaje [codigo=" + codigo + ", destino=" + destino + ", plazasDisponibles=" + plazasDisponibles + "]";
	}

}

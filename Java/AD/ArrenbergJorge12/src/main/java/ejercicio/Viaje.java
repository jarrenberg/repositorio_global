package ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

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

	// Metodo que devuelve el numero de plazas libres en el destino del viaje pasado
	// como parametro
	public int plazasLibres() {
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		int plazasLibres = -1;
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps = con
						.prepareStatement("SELECT plazas_disponibles from viajes where destino = ?")) {

			ps.setString(1, this.destino);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				plazasLibres = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return plazasLibres;
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

package ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

//APARTADO 3
public class Reserva {
	private Integer numeroReserva;
	private Integer codigoViaje;
	private Integer codigoCliente;
	private Integer plazasReservadas;
	private String estado;

	public Reserva(Integer codigoViaje, Integer codigoCliente, Integer plazasReservadas)
			throws IllegalArgumentException {
		ArrayList<Integer> listaCodigosViaje = Reserva.listaCodigosViaje();
		ArrayList<Integer> listaCodigosCliente = Reserva.listaCodigosCliente();
		boolean invalidoCodigoCliente = true;
		boolean invalidoCodigoViaje = true;
		
		// APARTADO 10
		for (Integer entero : listaCodigosViaje) {
			if (entero.equals(codigoViaje)) {
				invalidoCodigoViaje = false;
			}
		}
		for (Integer entero : listaCodigosCliente) {
			if (entero.equals(codigoCliente)) {
				invalidoCodigoCliente = false;
			}
		}
		if (invalidoCodigoCliente) {
			throw new IllegalArgumentException(
					"No se puede crear una reserva con el codigo de cliente: " + codigoCliente);
		}
		if (invalidoCodigoViaje) {
			throw new IllegalArgumentException("No se puede crear una reserva con el codigo de viaje: " + codigoViaje);
		}
		if (plazasReservadas < 1) {
			throw new IllegalArgumentException(
					"No se puede crear una reserva con un numero menor a 1 de plazas reservadass");
		}
		// FIN APARTADO 10
		this.numeroReserva = null;
		this.codigoViaje = codigoViaje;
		this.codigoCliente = codigoCliente;
		this.plazasReservadas = plazasReservadas;
		this.estado = null;
	}

	private static ArrayList<Integer> listaCodigosViaje() {
		ArrayList<Integer> resultado = new ArrayList<>();
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("SELECT codigo from viajes")) {
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				resultado.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}

	private static ArrayList<Integer> listaCodigosCliente() {
		ArrayList<Integer> resultado = new ArrayList<>();
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("SELECT codigo_cliente from clientes")) {
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				resultado.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}

	public Integer getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(Integer numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public Integer getCodigoViaje() {
		return codigoViaje;
	}

	public void setCodigoViaje(Integer codigoViaje) {
		this.codigoViaje = codigoViaje;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getPlazasReservadas() {
		return plazasReservadas;
	}

	public void setPlazasReservadas(Integer plazasReservadas) {
		this.plazasReservadas = plazasReservadas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoCliente, codigoViaje, estado, numeroReserva, plazasReservadas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(codigoCliente, other.codigoCliente) && Objects.equals(codigoViaje, other.codigoViaje)
				&& Objects.equals(estado, other.estado) && Objects.equals(numeroReserva, other.numeroReserva)
				&& Objects.equals(plazasReservadas, other.plazasReservadas);

	}

	@Override
	public String toString() {
		return "Reserva [numeroReserva=" + numeroReserva + ", codigoViaje=" + codigoViaje + ", codigoCliente="
				+ codigoCliente + ", plazasReservadas=" + plazasReservadas + ", estado=" + estado + "]";
	}

}

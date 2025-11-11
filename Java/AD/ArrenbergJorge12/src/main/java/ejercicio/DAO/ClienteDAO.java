package ejercicio.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ejercicio.Cliente;
import ejercicio.UtilidadesSQL;

//APARTADO 4
public class ClienteDAO {

	public static Cliente getCliente(Integer codigo) throws NullPointerException {
		Cliente c = new Cliente("");
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("SELECT * from clientes where codigo_cliente=?")) {
			ps1.setInt(1, codigo);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				c.setCodigoCliente(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setTelefono(rs.getString(3));
			}
			if (c.getNombre() == "") {
				throw new NullPointerException("No existe un cliente con dicho codigo");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

	public static boolean insertarCliente(Cliente c) {
		boolean insertado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("INSERT INTO clientes VALUES (?,?,?)");
				PreparedStatement ps2 = con.prepareStatement("SELECT codigo_cliente from clientes where nombre = ?")) {
			ps1.setNull(1, Types.INTEGER);
			ps1.setString(2, c.getNombre());
			if (c.getTelefono() == null) {
				ps1.setNull(3, Types.VARCHAR);
			} else {
				ps1.setString(3, c.getTelefono());
			}

			ps2.setString(1, c.getNombre());

			int rs = ps1.executeUpdate();
			if (rs > 0) {
				insertado = true;
			}

			ResultSet rs1 = ps2.executeQuery();
			int codigo = -1;
			while (rs1.next()) {
				codigo = rs1.getInt(1);
			}
			if (codigo != (-1)) {
				c.setCodigoCliente(codigo);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return insertado;
	}

	public static boolean eliminarCliente(Cliente c) {
		boolean eliminado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con
						.prepareStatement("DELETE FROM clientes where codigo_cliente = ? and nombre = ?")) {
			ps1.setInt(1, c.getCodigoCliente());
			ps1.setString(2, c.getNombre());
			int rs = ps1.executeUpdate();
			if (rs > 0) {
				eliminado = true;
			}
			c = null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return eliminado;
	}

	public static boolean actualizarCliente(Cliente c) {
		boolean actualizado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con
						.prepareStatement("UPDATE clientes SET nombre=?, telefono=? where codigo_cliente = ?")) {

			ps1.setString(1, c.getNombre());
			if (c.getTelefono() == null) {
				ps1.setNull(2, Types.VARCHAR);
			} else {
				ps1.setString(2, c.getTelefono());
			}
			ps1.setInt(3, c.getCodigoCliente());

			int rs = ps1.executeUpdate();
			if (rs > 0) {
				actualizado = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return actualizado;
	}
}

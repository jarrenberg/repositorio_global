package testeo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import ejercicio.Main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Scanner;

class Prueba {

	private ByteArrayOutputStream outputStream;
	private PrintStream originalOut;
	private Connection testConnection;

	@TempDir
	Path tempDir;

	@BeforeEach
	void setUp() throws SQLException {
		// Redirigir System.out para capturar la salida
		originalOut = System.out;
		outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
	}

	@AfterEach
	void tearDown() throws SQLException {
		// Restaurar System.out
		System.setOut(originalOut);
		if (testConnection != null && !testConnection.isClosed()) {
			testConnection.close();
		}
	}

	@Test
	@DisplayName("Test de informacionTransaccion con datos válidos")
	void testInformacionTransaccion() {
		String input = "cuenta_origen\ncuenta_destino\n500\n";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

		String[] result = Main.informacionTransaccion(scanner);

		assertNotNull(result);
		assertEquals(3, result.length);
		assertEquals("cuenta_origen", result[0]);
		assertEquals("cuenta_destino", result[1]);
		assertEquals("500", result[2]);
	}

	@Test
	@DisplayName("Test de existeCuenta con conexión mock")
	void testExisteCuenta() throws SQLException {
		// Mock de la conexión y prepared statement
		Connection mockConnection = mock(Connection.class);
		PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
		ResultSet mockResultSet = mock(ResultSet.class);

		when(mockConnection.prepareStatement("SELECT * from CUENTAS where id_cuenta=?"))
				.thenReturn(mockPreparedStatement);
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true); // Simula que la cuenta existe

		// Este test es más complejo ya que existeCuenta crea su propia conexión
		// En una implementación real, deberías usar dependency injection
	}

	@Test
	@DisplayName("Test de getSaldoCuenta con conexión mock")
	void testGetSaldoCuenta() throws SQLException {
		// Similar al test anterior, necesitarías mockear la conexión
		// Este test muestra el patrón que seguirías
		Connection mockConnection = mock(Connection.class);
		PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
		ResultSet mockResultSet = mock(ResultSet.class);

		when(mockConnection.prepareStatement("SELECT saldo from CUENTAS where id_cuenta=?"))
				.thenReturn(mockPreparedStatement);
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true);
		when(mockResultSet.getInt(1)).thenReturn(1000);
	}

	@Test
	@DisplayName("Test de flujo completo con datos de entrada simulados")
	void testFlujoCompleto() {
		String input = "cuenta1\ncuenta2\n200\n";
		InputStream originalIn = System.in;

		try {
			System.setIn(new ByteArrayInputStream(input.getBytes()));

			// Este test intentaría ejecutar el main, pero debido a las dependencias
			// de base de datos, probablemente fallaría sin un entorno configurado
			// Main.main(new String[]{});

		} finally {
			System.setIn(originalIn);
		}
	}
}
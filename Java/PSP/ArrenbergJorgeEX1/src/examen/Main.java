
package examen;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException { // Creacion y lanzamiento del
																						// proceso padre, el cual va a
																						// llamar al proceso hijo
		// que se lanza al ejecutar el archivo ProcesoHijo.jar
		System.out.println("Creando el proceso padre que lanza al proceso hijo ...");
		ProcessBuilder pbPadre = new ProcessBuilder("java", "-jar", "ProcesoHijo.jar");
		Process pPadre = pbPadre.start();

		try {
			pPadre.waitFor();
			// Mostramos la informacion que nos devuelve el proceso hijo
			BufferedReader reader = new BufferedReader(new InputStreamReader(pPadre.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}




		
		System.out.println("Terminado proceso padre");

	}
}
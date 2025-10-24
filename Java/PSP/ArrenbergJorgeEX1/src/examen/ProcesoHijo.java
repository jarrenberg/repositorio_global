package examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcesoHijo {

	public static void main(String[] args) throws IOException, InterruptedException {

		// Creacion y lanzamiento del proceso hijo
		ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DIR");
		System.out.println("Lanzando el proceso hijo ...\n");
		Process p = pb.start();

		// Esperamos a que el proceso hijo termine para mostrar la informacion
		try {
			p.waitFor();
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}

		// Mostramos la informacion que nos devuelve el proceso hijo
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

}

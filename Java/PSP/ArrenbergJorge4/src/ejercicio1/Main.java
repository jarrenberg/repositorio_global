package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		List<String> artistas = new ArrayList<>();
		artistas.add("Green Day");
		artistas.add("Extremoduro");
		artistas.add("AC/DC");

		for (String cantante : artistas) {
			try {
				ProcessBuilder pb = new ProcessBuilder("CMD", "/c","java","-jar","clientemusical2.jar", cantante);
				Process p = pb.start();
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				System.out.println("Salida del proceso:");
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
				p.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

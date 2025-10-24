package ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		List<String> artistas = new ArrayList<>();
		List<Process> procesos = new ArrayList<>();

		File f = new File("artistas.txt");
		try (Scanner sc = new Scanner(f)) {
			while (sc.hasNext()) {
				String s = sc.nextLine();
				artistas.add(s);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		for (String cantante : artistas) {
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "java", "-jar", "clientemusical2.jar", cantante);
			Process p = null;
			try {
				p = pb.start();
				p.waitFor();
				procesos.add(p);

			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (MissingFormatArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		for (Process p : procesos) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (MissingFormatArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

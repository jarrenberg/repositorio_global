package ejercicio;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Servidor {
		// Initialize socket and input stream
		private Socket s = null;
		private ServerSocket ss = null;
		private String mensaje = "";
		private DataOutputStream m = null;

		// Constructor with port
		public Servidor(int port) {

			// Starts server and waits for a connection
			try {
				ss = new ServerSocket(port);
				System.out.println("Server started");

				System.out.println("Waiting for a client ...");

				s = ss.accept();
				System.out.println("Client accepted");

				mensaje = getQOTD(new File("frases.txt"));
				System.out.println("mensaje. "+mensaje);
				
				m = new DataOutputStream(s.getOutputStream());
				m.writeChars(mensaje);

				System.out.println("Closing connection");

				// Close connection
				s.close();
				m.close();
			} catch (IOException i) {
				System.out.println(i.getMessage());
			}
		}
		public static String getQOTD(File f) {
			String frase = "";
			ArrayList<String> listaFrases = new ArrayList<String>();
			try (Scanner sc = new Scanner(f)) {
				while (sc.hasNext()) {
					String linea = sc.nextLine();
					listaFrases.add(linea);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			frase = listaFrases.get(numeroAleatorioEnRango(0, listaFrases.size()));
			return frase;
		}

		public static int numeroAleatorioEnRango(int min, int max) {
			int resultado = (int) (Math.random() * max);
			return resultado;
		}

	}
package ejercicio;

import ejercicio.DAO.ReservaDAO;

public class Main {
	public static void main(String[] args) {
		try {
			Reserva r = ReservaDAO.getReserva(49);
			ReservaDAO.cancelarReserva(r);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}

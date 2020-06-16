package entities;

import org.junit.Before;

public class App {
		public static void main(String[] args) {
		Aventura miAventura = new Aventura("Aventuras/escenario1.json");
		Player jugador =  miAventura.getJugador();
		System.out.println(jugador.moverHacia("sur"));

	
	}
	public static void ejecutarCommand(String accion) {
		String[] a = accion.split(" ");
		Accion ac = new Accion();
		Broker broker = new Broker();
		switch (a[0]) {
		case "agarrar":
			Agarrar takeOrder = new Agarrar(ac);
			broker.takeOrder(takeOrder);
			break;
		case "mirar":
			Mirar seeOrder = new Mirar(ac);
			broker.takeOrder(seeOrder);
			break;
		case "mover":
			Mover moveOrder = new Mover(ac, a[1]);
			broker.takeOrder(moveOrder);
			break;
		}
		broker.placeOrders();
	}
}

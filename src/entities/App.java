package entities;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String comando;
		Aventura miAventura = new Aventura("Aventuras/escenario1.json");
		Player jugador = miAventura.getJugador();
		while (true) {
			comando = teclado.nextLine();
			ejecutarCommand(jugador, comando);
		}
		// ejecutarCommand("mover sur");
		// ejecutarCommand("agarrar barreta");
		// ejecutarCommand(jugador,"agarrar barreta");
		// ejecutarCommand(jugador,"mover este");
		// ejecutarCommand(jugador,"usar barreta pirata");
		// ejecutarCommand(jugador,"agarrar barreta");
		// ejecutarCommand(jugador,"usar espejo pirata");
		// ejecutarCommand(jugador, "mirar alrededor");
		// ejecutarCommand(jugador,"mirar alrededor");
	}
		
	public static void ejecutarCommand(Player player, String accion) {
		String[] a = accion.split(" ");
		Accion ac = new Accion(player);
		Broker broker = new Broker();
		switch (a[0]) {
		case "agarrar":
			Agarrar takeOrder = new Agarrar(ac, a[1]);
			broker.takeOrder(takeOrder);
			break;
		case "mirar":
			Mirar seeOrder = new Mirar(ac, a[1]);
			broker.takeOrder(seeOrder);
			break;
		case "mover":
			Mover moveOrder = new Mover(ac, a[1]);
			broker.takeOrder(moveOrder);
			break;
		case "usar":
			Usar useOrder = new Usar(ac, a[1], a[2]);
			broker.takeOrder(useOrder);
			break;
		}
		broker.placeOrders();
	}
}

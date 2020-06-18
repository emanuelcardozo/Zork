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
		String[] a = accion.toLowerCase().split(" ");
		Accion ac = new Accion(player);
		Broker broker = new Broker();
		switch (a[0]) {
		case "agarrar":
			if(a.length<2) { System.out.println("Que quieres agarrar?(COMANDO: AGARRAR ITEM)");}else {
			Agarrar takeOrder = new Agarrar(ac, a[1]);
			broker.takeOrder(takeOrder);
			}
			break;
		case "mirar":
			if(a.length<2) { System.out.println("Que quieres mirar?(COMANDO: MIRAR INVENTARIO O MIRAR ALREDEDOR)");}else {
			Mirar seeOrder = new Mirar(ac, a[1]);
			broker.takeOrder(seeOrder);
			}
			break;
		case "mover":
			if(a.length<2) { System.out.println("Hacia donde te quieres mover?(MOVER NORTE-SUR-ESTE-OESTE)");}else {
			Mover moveOrder = new Mover(ac, a[1]);
			broker.takeOrder(moveOrder);
			}
			break;
		case "usar":
			if(a.length < 2) {
				System.out.println("Que queres usar? Contra quien?(USAR ITEM NPC)");
			}else {
				Usar useOrder = new Usar(ac, a[1], a[2]);
			broker.takeOrder(useOrder);
			}
			break;
		case "hablar":
			if(a.length < 3) {
				System.out.println("Con quien quieres hablar?(COMANDO: HABLAR CON PIRATA FANTASMA)");
			}else {
				Hablar talkOrder = new Hablar(ac, a[2]);
			broker.takeOrder(talkOrder);
			}
			break;
		default:
			DefaultAction useDefault = new DefaultAction(ac);
			broker.takeOrder(useDefault);
			break;
		}
		broker.placeOrders();
	}
}

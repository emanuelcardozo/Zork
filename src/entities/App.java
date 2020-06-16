package entities;

public class App {
		public static void main(String[] args) {
		Aventura miAventura = new Aventura("Aventuras/escenario1.json");
		Player jugador =  miAventura.getJugador();
		//ejecutarCommand("mover sur");
		//ejecutarCommand("agarrar barreta");
		ejecutarCommand(jugador,"agarrar barreta");
		//ejecutarCommand(jugador,"mover este");
		//ejecutarCommand(jugador,"usar barreta pirata");
		ejecutarCommand(jugador,"agarrar barreta");
		ejecutarCommand(jugador,"usar espejo pirata");
		ejecutarCommand(jugador, "mirar alrededor");

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
			Mirar seeOrder = new Mirar(ac);
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

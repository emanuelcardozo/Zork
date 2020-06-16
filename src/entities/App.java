package entities;

import org.junit.Before;

public class App {
		public static void main(String[] args) {
		//ejecutarCommand("mover sur");
		//ejecutarCommand("agarrar barreta");
		ejecutarCommand("agarrar barreta");
		ejecutarCommand("mover este");
		ejecutarCommand("usar barreta pirata");

	
	}
	public static void ejecutarCommand(String accion) {
		String[] a = accion.split(" ");
		Accion ac = new Accion();
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

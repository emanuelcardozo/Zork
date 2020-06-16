package entities;

import org.junit.Before;

public class App {
		public static void main(String[] args) {
		String[] a = "agarrar pete".split(" ");
		//String[] m = "mirar pete".split(" ");
		String mo = "mover norte";
		//ejecutarCommand(a);
		//ejecutarCommand(m[0]);
		ejecutarCommand(mo);
	
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

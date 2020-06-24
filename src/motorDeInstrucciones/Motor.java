package motorDeInstrucciones;

import java.util.Scanner;

import entities.Player;

import motorDeInstrucciones.actions.Accion;


public class Motor {
	private Scanner teclado;
	private Accion accion;
	private Broker broker;
	private boolean running;

	public Motor( Player player ) {
		this.broker = new Broker();
		this.accion = new Accion(player);
		this.teclado = new Scanner(System.in);
	}

	public void start() {
		String comando = teclado.nextLine();
		running = true;

		while ( !comando.equalsIgnoreCase("salir") && running) {
			ejecutarComando(comando);
			if( running )
				comando = teclado.nextLine();
		}

		teclado.close();
	}

	public void ejecutarComando(String comando) {		
		ComandoParser parser = new ComandoParser(comando);
		Order order;
		
		accion.setVerbo(parser.getVerbo());
		accion.setSustantivos(parser.getSustantivos());
		order = accion.createOrder();
		broker.takeOrder(order);
		broker.placeOrders();
	}

	public void stop() {
		running = false;
	}
}

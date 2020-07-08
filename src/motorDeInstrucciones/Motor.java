package motorDeInstrucciones;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import entities.Player;
import motorDeInstrucciones.actions.Accion;


public class Motor {
	private Scanner teclado;
	private Accion accion;
	private Broker broker;
	private boolean running;
	private Player player;

	public Motor( Player player ) {
		this.player = player;
		this.broker = new Broker();
		this.accion = new Accion(player);
		this.teclado = new Scanner(System.in);
	}

	public void start() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File("PartidasGuardadas/"+player.getName()+".log"));
		String comando = teclado.nextLine();
		running = true;
		while ( !comando.equalsIgnoreCase("salir") && running) {
			pw.println(player.getName()+" dice: "+comando);
			pw.println("Respuesta: "+ejecutarComando(comando));
			if( running )
				comando = teclado.nextLine();
		}
		pw.close();
		teclado.close();
	}

	public String ejecutarComando(String comando) {
		ComandoParser parser = new ComandoParser(comando);
		String respuesta = "";
		Order order;

		accion.setVerbo(parser.getVerbo());
		accion.setSustantivos(parser.getSustantivos());
		order = accion.createOrder();
		broker.takeOrder(order);
		respuesta = broker.placeOrders();
		return respuesta;
	}

	public void stop() {
		running = false;
	}
}

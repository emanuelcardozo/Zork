package motorDeInstrucciones;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import entities.Player;
import io.InOutputable;
import motorDeInstrucciones.actions.Accion;


public class Motor {
	private Scanner teclado;
	private Accion accion;
	private Broker broker;
	private boolean running;
	private Player player;
	private InOutputable io;

	public Motor( InOutputable io, Player player ) {
		this.io = io;
		this.player = player;
		this.broker = new Broker();
		this.accion = new Accion(player, io);
		this.teclado = new Scanner(System.in);
	}

	public void start() {
		
		String comando = io.getValue(null);
		running = true;
		
		while ( !comando.equalsIgnoreCase("salir") && running) {

			
			io.showMessage(ejecutarComando(comando));
			
			if( running )
				comando = io.getValue(null);
		}
		
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

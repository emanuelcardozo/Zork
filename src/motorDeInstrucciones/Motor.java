package motorDeInstrucciones;

import java.util.Scanner;
import entities.Player;
import motorDeInstrucciones.actions.Acariciar;
import motorDeInstrucciones.actions.Accion;
import motorDeInstrucciones.actions.Acuchillar;
import motorDeInstrucciones.actions.Agarrar;
import motorDeInstrucciones.actions.Correr;
import motorDeInstrucciones.actions.DefaultAction;
import motorDeInstrucciones.actions.Golpear;
import motorDeInstrucciones.actions.Hablar;
import motorDeInstrucciones.actions.Mirar;
import motorDeInstrucciones.actions.Mover;
import motorDeInstrucciones.actions.Tirar;
import motorDeInstrucciones.actions.Usar;

public class Motor {
	private Scanner teclado;
	private Accion accion;
	private Broker broker;

	public Motor( Player player ) {
		this.broker = new Broker();
		this.accion = new Accion(player);
		this.teclado = new Scanner(System.in);
	}

	public void start() {
		String comando = teclado.nextLine();

		while ( !comando.equalsIgnoreCase("salir")) {
			ejecutarComando(comando);
			comando = teclado.nextLine();
		}

		teclado.close();
	}

	public void ejecutarComando(String comando) {
		String[] comandoArray = comando.toLowerCase().split(" ");

		switch (comandoArray[0]) {
			case "agarrar":
				if(comandoArray.length != 2) {
					System.out.println("Que quieres agarrar?(COMANDO: AGARRAR ITEM)");
				} else {
					Agarrar takeOrder = new Agarrar(accion, comandoArray[1]);
					broker.takeOrder(takeOrder);
				}
				break;
			case "mirar":
				if(comandoArray.length != 2) {
					System.out.println("Que quieres mirar?(COMANDO: MIRAR INVENTARIO O MIRAR ALREDEDOR)");
				} else {
					Mirar seeOrder = new Mirar(accion, comandoArray[1]);
					broker.takeOrder(seeOrder);
				}
				break;
			case "mover":
				if(comandoArray.length != 2) { 
					System.out.println("Hacia donde te quieres mover?(COMANDO: MOVER NORTE-SUR-ESTE-OESTE)");
				} else {
					Mover moveOrder = new Mover(accion, comandoArray[1]);
					broker.takeOrder(moveOrder);
				}
				break;
			case "usar":
				if(comandoArray.length != 4) {
					System.out.println("Que queres usar? Contra quien?(COMANDO: USAR ITEM NPC)");
				} else {
					Usar useOrder = new Usar(accion, comandoArray[1], comandoArray[3]);
					broker.takeOrder(useOrder);
				}
				break;
			case "hablar":
				if(comandoArray.length != 3) {
					System.out.println("Con quien quieres hablar?(COMANDO: HABLAR CON PIRATA FANTASMA)");
				} else {
					Hablar talkOrder = new Hablar(accion, comandoArray[2]);
					broker.takeOrder(talkOrder);
				}
				break;
			case "golpear":
				if(comandoArray.length != 2) {
					System.out.println("A que o quien queres golpear?(COMANDO: GOLPEAR PUERTA");
				}else {
					Golpear hitOrder = new Golpear(accion, comandoArray[1]);
				broker.takeOrder(hitOrder);
				}
				break;
			case "acariciar":
				if(comandoArray.length != 2) {
					System.out.println("A que o quien queres acariciar?(COMANDO: ACARICIAR PERRO");
				}else {
					Acariciar acariciarOrder = new Acariciar(accion, comandoArray[1]);
				broker.takeOrder(acariciarOrder);
				}
				break;
			case "tirar":
				if(comandoArray.length != 2) {
					System.out.println("Que queres tirar?(COMANDO: TIRAR LLAVE");
				}else {
					Tirar dropOrder = new Tirar(accion, comandoArray[1]);
				broker.takeOrder(dropOrder);
				}
				break;
			case "acuchillar":
				if(comandoArray.length != 2) {
					System.out.println("A quien queres acuchillar?(COMANDO: ACUCHILLAR COLEGA");
				}else {
					Acuchillar killOrder = new Acuchillar(accion, comandoArray[1]);
				broker.takeOrder(killOrder);
				}
				break;
			case "correr":
				if(comandoArray.length != 2) {
					System.out.println("Que queres correr?(COMANDO: CORRER MUEBLE");
				}else {
					Correr correrOrder = new Correr(accion, comandoArray[1]);
				broker.takeOrder(correrOrder);
				}
				break;
			default:
				DefaultAction useDefault = new DefaultAction(accion);
				broker.takeOrder(useDefault);
				break;
		}

		broker.placeOrders();
	}
}

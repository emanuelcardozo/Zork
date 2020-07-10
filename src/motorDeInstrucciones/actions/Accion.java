package motorDeInstrucciones.actions;

import entities.Item;
import entities.NPC;
import entities.Player;
import io.InOutputable;
import motorDeInstrucciones.Order;

public class Accion {
	private Player jugador;
	private String verbo;
	private String[] sustantivos;
	private InOutputable io;

	public Accion(Player jugador, InOutputable io) {
		this.jugador = jugador;
		this.io = io;
	}

	public String acariciar(String quien) {
		NPC npc = jugador.buscarNpc(quien);
		return jugador.acariciar(npc);
	}


	public String acuchillar(String objetivo) {
		if( objetivo == null )
			return "A quien queres acuchillar?(COMANDO: ACUCHILLAR ______)";

		NPC npc = jugador.buscarNpc(objetivo);

		if(npc == null)
			return "No existe nadie con ese nombre.";

		return jugador.acuchillar(npc);
	}


	public String ayuda() {
		return "            Comandos del juego\n" + "Golpear: (GOLPEAR _____)\n"+ "Hablar: (COMANDO: HABLAR CON ______)\n"+"Ir: (COMANDO:IR (NORTE-SUR-ESTE-OESTE))\n"+ 
				"Mover: (COMANDO: MOVER _______)\n"+"Mostrar: (COMANDO: MOSTRAR _______)\n"+ "Mirar: (COMANDO: MIRAR ______)\n"+"Agarrar (COMANDO: AGARRAR ______)\n"+
				"Tirar: (COMANDO: TIRAR ______)\n"+ "Usar: (COMANDO: USAR ______ EN ______)\n"+ "Acuchillar (COMANDO: ACUCHILLAR ______)\n"+"Acariciar: (COMANDO: ACARICIAR ______)\n";
	}


	public String golpear(String itemName) {
		if( itemName == null )
			return "Que queres golpear?(COMANDO: GOLPEAR RADIO)";

		Item item = jugador.buscarItemInventario(itemName);

		if(item == null)
			return "No existe ese item en tu inventario.";

		return jugador.golpear(item);
	}


	public String hablar(String quien, InOutputable io) {
		if( quien == null )
			return "Con quien quieres hablar?(COMANDO: HABLAR CON ______)";

		NPC npc = jugador.buscarNpc(quien);

		return jugador.hablarCon(npc, io);
	}


	public String ir(String hacia) {
		if( hacia == null )
			return "Hacia donde quieres ir?(COMANDO: IR (NORTE-SUR-ESTE-OESTE))";

		return jugador.moverHacia(hacia);
	}


	public String mover(String objetivo) {
		if( objetivo == null )
			return "Que quieres mover? (COMANDO: MOVER _______)";

		NPC npc = jugador.buscarNpc(objetivo);

		if(npc == null)
			return "No existe nada con ese nombre.";

		return jugador.correr(npc);
	}

	public String mostrar(String que) {
		if(que.equals("inventario"))
			return jugador.listarInventario();

		return "No puedes mostrar " + que + ".";
	}


	public String observar(String donde) {
		if( donde == null )
			return "Que quieres mirar?(COMANDO: MIRAR ______)";

		if(donde.equals("alrededor"))
			return jugador.mirarAlrededor();

 	 	NPC npc = jugador.buscarNpc(donde);

		if(npc != null)
			return jugador.mirarNpc(npc);

		Item item = jugador.buscarItemInventario(donde);

		if(item != null)
		{String respuesta = jugador.mirarItem(item);
			if(respuesta != null)
				return respuesta;
			else return "No hay nada que decir.";
		}

		return "No puedes mirar hacia alli.";
	}


	public String recoger(String item) {
		if( item == null )
			return "Que quieres agarrar?(COMANDO: AGARRAR ______)";

		return jugador.agarrarItem(item);
	}

	public String tirar(String item) {
		if(item == null )
			return "Que queres tirar?(COMANDO: TIRAR LLAVE";

		return jugador.tirarItem(item);
	}


	public String usar(String itemName, String objetivo) {

		if( itemName == null )
			return "Que queres usar?(COMANDO: USAR ______ EN ______)";

		Item item = jugador.buscarItemInventario(itemName);

		if( item == null )
			return "Que queres usar?(COMANDO: USAR ______ EN ______)";

		if( objetivo == null )
			return "Contra quien queres usar " + item.getArticulo() + " " + item.getName() + "?(COMANDO: USAR ______ EN ______)";

		if(objetivo.contentEquals("mi"))
			return jugador.usarItemEnMi(item);

		NPC npc = jugador.buscarNpc(objetivo);

		return jugador.usarItemEnNpc(item, npc);
	}

	public String defaultAccion() {
		return "No entiendo esa instruccion.";
	}


	public String getVerbo() {
		return verbo;
	}

	public void setVerbo(String verbo) {
		this.verbo = verbo;
	}

	public String[] getSustantivos() {
		return sustantivos;
	}

	public void setSustantivos(String[] sustantivos) {
		this.sustantivos = sustantivos;
	}


	public Order createOrder() {

		if(verbo == null) return new DefaultAction(this);

		Order order = null;

		switch(verbo) {
			case "ACARICIAR":
				order = new Acariciar(this, sustantivos[0]);
				break;

			case "ACUCHILLAR":
				order = new Acuchillar(this, sustantivos[0]);
				break;

			case "AYUDA":
				order = new Ayuda(this);
				break;

			case "GOLPEAR":
				order = new Golpear(this, sustantivos[0]);
				break;

			case "HABLAR":
				order = new Hablar(this, sustantivos[0], io);
				break;

			case "IR":
				order = new Ir(this, sustantivos[0]);
				break;

			case "MOVER":
				order = new Mover(this, sustantivos[0]);
				break;

			case "MOSTRAR":
				order = new Mostrar(this, sustantivos[0]);
				break;

			case "OBSERVAR":
				order = new Observar(this, sustantivos[0]);
				break;

			case "RECOGER":
				order = new Recoger(this, sustantivos[0]);
				break;

			case "USAR":
				order = new Usar(this, sustantivos[0], sustantivos[1]);
				break;

			case "TIRAR":
				order = new Tirar(this, sustantivos[0]);
				break;
		}

		return order;
	}
}

/*
tomar llave
usar llave en puerta
ir norte
ir este
tomar cuchillo
ir oeste
acuchillar vecino
*/

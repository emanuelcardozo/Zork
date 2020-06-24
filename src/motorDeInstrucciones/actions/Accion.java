package motorDeInstrucciones.actions;

import entities.Item;
import entities.NPC;
import entities.Player;
import motorDeInstrucciones.Order;

public class Accion {
	private Player jugador;
	private String verbo;
	private String[] sustantivos;

	public Accion(Player jugador) {
		this.jugador = jugador;
	}
	
	public String acariciar(String quien) {
		NPC npc = jugador.buscarNpc(quien);
		return jugador.acariciar(npc);
	}
	
	
	public String acuchillar(String objetivo) {
		if( objetivo == null )
			return "A quien queres acuchillar?(COMANDO: ACUCHILLAR COLEGA";
		
		NPC npc = jugador.buscarNpc(objetivo);
		
		if(npc == null)
			return "No existe nadie con ese nombre.";
		
		return jugador.acuchillar(npc);
	}
	
	
	public String golpear(String itemName, String objetivo) {
		if( itemName == null )
			return "Que queres golpear?(COMANDO: GOLPEAR RADIO CONTRA MUEBLE";
		
		if( objetivo == null )
			return "Contra que queres golpear?(COMANDO: GOLPEAR RADIO CONTRA MUEBLE";
		
		Item item = jugador.buscarItemInventario(itemName);
		NPC npc = jugador.buscarNpc(objetivo);
		
		if(npc == null)
			return "No existe nadie con ese nombre.";
		
		if(item == null)
			return "No existe ese item en tu inventario.";
		
		return jugador.golpear(item, npc);
	}
	
	
	public String hablar(String quien) {
		if( quien == null ) 
			return "Con quien quieres hablar?(COMANDO: HABLAR CON PIRATA FANTASMA)";
		
		NPC npc = jugador.buscarNpc(quien);

		return jugador.hablarCon(npc);
	}
	
	
	public String ir(String hacia) {
		if( hacia == null )
			return "Hacia donde te quieres mover?(COMANDO: MOVER NORTE-SUR-ESTE-OESTE)";
		
		return jugador.moverHacia(hacia);
	}
	
	
	public String mover(String objetivo) {
		if( objetivo == null )
			return "Que quieres mover";
		
		NPC npc = jugador.buscarNpc(objetivo);
		
		if(npc == null)
			return "No existe nadie con ese nombre.";
		
		return jugador.correr(npc);
	}
	

	public String observar(String donde) {
		if( donde == null )
			return "Que quieres mirar?(COMANDO: MIRAR INVENTARIO O MIRAR ALREDEDOR)";
		
		if(donde.equals("alrededor"))
			return jugador.mirarAlrededor();

		if(donde.equals("inventario"))
			return jugador.listarInventario();

		NPC npc = jugador.buscarNpc(donde);
		
		if(npc != null)
			return jugador.mirarNpc(npc);
		
		Item item = jugador.buscarItemInventario(donde);
		
		if(item != null)
			return jugador.mirarItem(item);
		
		return "No puedes mirar hacia alli.";
	}

	
	public String recoger(String item) {
		if( item == null )
			return "Que quieres agarrar?(COMANDO: AGARRAR ITEM)";
		
		return jugador.agarrarItem(item);
	}
	
	public String tirar(String item) {
		if(item == null ) 
			return "Que queres tirar?(COMANDO: TIRAR LLAVE";
		
		return jugador.tirarItem(item);
	}
	
	
	public String usar(String itemName, String objetivo) {		
		if( itemName == null )
			return "Que queres usar?(COMANDO: USAR ITEM NPC)";
		
		Item item = jugador.buscarItemInventario(itemName);
		
		if( objetivo == null )
			return "Contra quien queres usar " + item.getArticulo() + " " + item.getName() + "?(COMANDO: USAR ITEM NPC)";						
		
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
			
			case "GOLPEAR":
				order = new Golpear(this, sustantivos[0], sustantivos[1]);
				break;
			
			case "HABLAR":
				order = new Hablar(this, sustantivos[0]);
				break;
			
			case "IR":
				order = new Ir(this, sustantivos[0]);
				break;
			
			case "MOVER":
				order = new Mover(this, sustantivos[0]);
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

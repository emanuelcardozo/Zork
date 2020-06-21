package motorDeInstrucciones.actions;

import entities.Item;
import entities.NPC;
import entities.Player;

public class Accion {
	private Player jugador;
	private String accionPart1;
	private String accionPart2;

	public Accion(Player jugador) {
		this.jugador = jugador;
	}

	public String mirar() {
		if(accionPart1.equals("alrededor"))
			return jugador.mirarAlrededor();

		if(accionPart1.equals("inventario"))
			return jugador.listarInventario();

		NPC npc = jugador.buscarNpc(accionPart1);
			return jugador.mirarNpc(npc);
	}

	public String agarrar() {
		return jugador.agarrarItem(accionPart1);
	}

	public String mover() {
		return jugador.moverHacia(accionPart1);
	}

	public String usar() {
		Item item = jugador.buscarItemInventario(accionPart1);

		return jugador.usarItem(item, accionPart2);
	}

	public String hablar() {
		NPC npc = jugador.buscarNpc(accionPart1);

		return jugador.hablarCon(npc);
	}

	public String defaultAccion() {
		return "No entiendo esa instruccion.";
	}

	public String golpear() {
		NPC npc = jugador.buscarNpc(accionPart1);
		return jugador.golpear(npc);
	}
	
	public String acariciar() {
		NPC npc = jugador.buscarNpc(accionPart1);
		return jugador.acariciar(npc);
	}

	public void setAccionPart1(String accionPart1) {
		this.accionPart1 = accionPart1;
	}

	public void setAccionPart2(String accionPart2) {
		this.accionPart2 = accionPart2;
	}

}

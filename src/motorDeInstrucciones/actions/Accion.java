package motorDeInstrucciones.actions;

import entities.Item;
import entities.NPC;
import entities.Player;

public class Accion {
	private Player jugador;
	private String where;
	private String where2;

	public Accion(Player jugador) {
		this.jugador = jugador;
	}
	
	public void mirar() {
		if(where.equals("alrededor")) System.out.println(jugador.mirarAlrededor());
		else if(where.equals("inventario")) System.out.println(jugador.listarInventario());
		else System.out.println("No entiendo esa instruccion.");
	}

	public void agarrar() {
		System.out.println(jugador.agarrarItem(where));
	}
	
	public void mover() {
		System.out.println(jugador.moverHacia(where));
	}
	
	public void usar() {
		Item item;
		item = jugador.buscarItemInventario(where);
		System.out.println(jugador.usarItem(item, where2));
	}

	public void hablar() {
		NPC np = jugador.buscarNpc(where);
		System.out.println(jugador.hablarCon(np));
	}
	
	public void defaultAccion() {
		System.out.println("No entiendo esa instruccion.");
	}
	
	public void setWhere(String where) {
		this.where = where;
	}
	
	public void setWhere2(String where2) {
		this.where2 = where2;
	}

}

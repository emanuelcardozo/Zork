package entities;

public class Accion {
	private Player jugador;
	private String where;
	private String where2;

	public Accion(Player jugador) {
		this.jugador = jugador;
	}
	
	public void mirar() {
		System.out.println(jugador.mirarAlrededor());
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

	public void setWhere(String where) {
		this.where = where;
	}
	
	public void setWhere2(String where2) {
		this.where2 = where2;
	}
}

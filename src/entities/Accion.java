package entities;

public class Accion {
	Aventura miAventura = new Aventura("Aventuras/escenario1.json");
	Player jugador =  miAventura.getJugador();
	private String where;
	private String where2;

	public void mirar() {
		jugador.mirarAlrededor();
	}

	public void agarrar() {
		System.out.println("Estoy agarrando");
		System.out.println(jugador.agarrarItem(where));
	}
	
	public void mover() {
		System.out.println("Me estoy moviendo");
		System.out.println(jugador.moverHacia(where));
	}
	
	public void usar() {
		Item item;
		System.out.println("Estoy usando");
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

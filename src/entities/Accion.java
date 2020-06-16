package entities;

public class Accion {
	private Aventura miAventura = new Aventura("Aventuras/escenario1.json");
	private Player jugador =  miAventura.getJugador();
	private String where;

	public void mirar() {
		jugador.mirarAlrededor();
	}

	public void agarrar() {
		System.out.println("Estoy agarrando");
	}
	
	public void mover() {
		System.out.println("Me estoy moviendo");
		//jugador.moverHacia(where);
	}

	public void setWhere(String where) {
		this.where = where;
	}
}

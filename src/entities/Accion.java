package entities;

public class Accion {

	private String where;

	public void mirar() {
		jugador.mirarAlrededor();
	}

	public void agarrar() {
		System.out.println("Estoy agarrando");
	}
	
	public void mover() {
		System.out.println("Me estoy moviendo");
		System.out.println(jugador.moverHacia(where));
	}

	public void setWhere(String where) {
		this.where = where;
	}
}

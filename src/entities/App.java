package entities;

public class App {

	public static void main(String[] args) {

		Aventura miAventura = new Aventura("escenario1.json");
		Player jugador = miAventura.getJugador();

		System.out.println("(MIRAR ALREDEDOR)");
		System.out.println(jugador.mirar());

		System.out.println(jugador.moverHacia("sur"));
		System.out.println(jugador.moverHacia("este"));
		System.out.println(jugador.moverHacia("taberna"));

	}

}

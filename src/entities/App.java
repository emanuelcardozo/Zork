package entities;

public class App {

	public static void main(String[] args) {

		Aventura miAventura = new Aventura("escenario1.json");
		Player jugador = miAventura.getJugador();

		System.out.println("--- Mira alrededor ---");
		System.out.println(jugador.mirar());

		System.out.println("--- Ir hacia el sur ---");
		System.out.println(jugador.moverHacia("sur"));

		System.out.println("--- Ir hacia el sur ---");
		System.out.println(jugador.moverHacia("muelle"));
		System.out.println(
				jugador.getPosicionActual().getPlace("suelo").extractItem("espejo").getEndGame().getDescription());

	}

}

package entities;

public class App {

	public static void main(String[] args) {

		Aventura miAventura = new Aventura("escenario1.json");
		Player jugador = miAventura.getJugador();

		System.out.println("\n(VER INVENTARIO)");
		System.out.println(jugador.listarInventario());

		System.out.println("\n(MIRAR ALREDEDOR)");
		System.out.println(jugador.mirar());

		System.out.println("\n(IR AL NORTE)");
		System.out.println(jugador.moverHacia("norte"));

		System.out.println("\n(IR AL SUR )");
		System.out.println(jugador.moverHacia("sur"));

		System.out.println("\n(AGARRAR BARRETA)");
		System.out.println(jugador.agarrarItem("barreta"));

		System.out.println("\n(MIRAR ALREDEDOR)");
		System.out.println(jugador.mirar());

		System.out.println("\n(AGARRAR ESPEJO)");
		System.out.println(jugador.agarrarItem("espejo"));

		System.out.println("\n(AGARRAR BANANA)");
		System.out.println(jugador.agarrarItem("banana"));

		System.out.println("\n(MIRAR ALREDEDOR)");
		System.out.println(jugador.mirar());

		System.out.println("\n(AGARRAR ROCIADOR)");
		System.out.println(jugador.agarrarItem("rociador con cerveza de raiz"));

		System.out.println("\n(MIRAR ALREDEDOR)");
		System.out.println(jugador.mirar());

		System.out.println("\n(USAR BARRETA EN PIRATA)");
		System.out.println(jugador.usarItem("barreta", "pirata fantasma"));

		System.out.println("\n(USAR ESPEJO EN PIRATA)");
		System.out.println(jugador.usarItem("espejo", "pirata fantasma"));

		System.out.println("\n(USAR PIEDRA EN PIRATA)");
		System.out.println(jugador.usarItem("piedra", "pirata fantasma"));

		System.out.println("\n(USAR ESPEJO EN CONEJO)");
		System.out.println(jugador.usarItem("espejo", "conejo"));

		System.out.println("\n(USAR ESPEJO EN MI)");
		System.out.println(jugador.usarItem("espejo", "mi"));

		System.out.println("\n(USAR ROCIADOR EN PIRATA)");
		System.out.println(jugador.usarItem("rociador con cerveza de raiz", "pirata fantasma"));

		System.out.println("\n(IR AL SUR - TABERNA)");
		System.out.println(jugador.moverHacia("sur"));

	}

}

package entities;

public class App {

	public static void main(String[] args) {

		Aventura miAventura = new Aventura("escenario1.json");
		Player jugador = miAventura.getJugador();
		
		System.out.println("(VER INVENTARIO)");
		System.out.println(jugador.listarInventario());

		System.out.println("(MIRAR ALREDEDOR)");
		System.out.println(jugador.mirar());
		
		
		System.out.println("(AGARRAR BARRETA)");
		System.out.println(jugador.agarrarItem("barreta"));
		
		System.out.println("(MIRAR ALREDEDOR)");
		System.out.println(jugador.mirar());
		
		System.out.println("(AGARRAR ESPEJO)");
		System.out.println(jugador.agarrarItem("espejo"));
		
		System.out.println("(MIRAR ALREDEDOR)");
		System.out.println(jugador.mirar());
		
		System.out.println("(AGARRAR ROCIADOR)");
		System.out.println(jugador.agarrarItem("rociador con cerveza de raiz"));
		
		System.out.println("(MIRAR ALREDEDOR)");
		System.out.println(jugador.mirar());
		
		System.out.println("(IR AL SUR - ESTE - TABERNA)");
		System.out.println(jugador.moverHacia("sur"));
		System.out.println(jugador.moverHacia("este"));
		System.out.println(jugador.moverHacia("taberna"));

	}

}

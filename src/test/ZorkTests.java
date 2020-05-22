package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import entities.Aventura;
import entities.Player;

public class ZorkTests {
	private Aventura miAventura;
	private Player jugador;

	@Before
	public void config() {
		miAventura = new Aventura("escenario1.json");
		jugador = miAventura.getJugador();
	}

	@Test
	public void testInventario() {
		assertEquals(
				"Estas en un muelle. En el suelo hay un rociador con cerveza de raiz, una barreta y un espejo. Hay un pirata fantasma. Al sur se puede ir hacia taberna.",
				jugador.mirar());
		assertEquals("No tienes objetos en tu inventario.", jugador.listarInventario());
		assertEquals("Tienes una barreta", jugador.agarrarItem("barreta"));
		assertEquals("Tienes una barreta", jugador.listarInventario());
		assertEquals("No existe ese item!", jugador.agarrarItem("barreta"));
		assertEquals("Tienes un rociador con cerveza de raiz y una barreta.",
				jugador.agarrarItem("rociador con cerveza de raiz"));
	}

	@Test
	public void testMovilidad() {
		assertEquals("No existe esa ubicacion.", jugador.moverHacia("este"));
		assertEquals("No puedes pasar!(El pirata fantasma no te dejara pasar).", jugador.moverHacia("sur"));
	}

}

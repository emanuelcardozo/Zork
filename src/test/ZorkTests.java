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
		// Estos son los elementos que hay en el lugar.
		assertEquals("Estas en un muelle. En el suelo hay un rociador con cerveza de raiz, una barreta y un espejo. Hay un pirata fantasma. Al sur se puede ir hacia taberna.",jugador.mirar());
		// Inventario vacio inicialmente.
		assertEquals("No tienes objetos en tu inventario.", jugador.listarInventario());
		// Agarra un item.
		assertEquals("Tienes una barreta", jugador.agarrarItem("barreta"));
		// Lista inventario
		assertEquals("Tienes una barreta", jugador.listarInventario());
		// Agarro otro item
		assertEquals("Tienes una barreta y un espejo.", jugador.agarrarItem("espejo"));
		// Listo inventario
		assertEquals("Tienes una barreta y un espejo.", jugador.listarInventario());
	}

	@Test
	public void testMover() {
		// Posicion actual.
		assertEquals("muelle", jugador.getPosicionActual().getName());
		// Intento moverme a una posicion que no hay conexion.
		assertEquals("No existe esa ubicacion.", jugador.moverHacia("este"));
		// Intento moverme a una posicion con conexion pero con un obstaculo.
		assertEquals("No puedes pasar!(El pirata fantasma no te dejara pasar).", jugador.moverHacia("sur"));
		// Agarro item para elminar obstaculo.
		jugador.agarrarItem("rociador con cerveza de raiz");
		// Elimino el obstaculo.
		jugador.usarItem("rociador con cerveza de raiz", "pirata fantasma");
		// Me muevo nuevamente.
		System.out.println(jugador.moverHacia("sur"));
		assertEquals("taberna", jugador.getPosicionActual().getName());
	}

	@Test
	public void testMirar() {
		//Miro alrededor.
		assertEquals("Estas en un muelle. En el suelo hay un rociador con cerveza de raiz, una barreta y un espejo. Hay un pirata fantasma. Al sur se puede ir hacia taberna.",jugador.mirar());
		//Agarro un item del lugar.
		jugador.agarrarItem("barreta");
		//Vuelvo a mirar y el item ya no se encuentra en la descripcion.
		assertEquals("Estas en un muelle. En el suelo hay un rociador con cerveza de raiz y un espejo. Hay un pirata fantasma. Al sur se puede ir hacia taberna.",jugador.mirar());
		//Agarro otro elemento del lugar.
		jugador.agarrarItem("espejo");
		//Ahora solo se muestra un item en la descripcion.
		assertEquals("Estas en un muelle. En el suelo hay un rociador con cerveza de raiz. Hay un pirata fantasma. Al sur se puede ir hacia taberna.",jugador.mirar());
	}
	
	@Test
	public void testAgarrar() {
		// Agarra un item.
		assertEquals("Tienes una barreta", jugador.agarrarItem("barreta"));
		// Intento agarrar el item nuevamente. Pero ya no existe debido a que ya lo agarro antes.
		assertEquals("No existe ese item!.", jugador.agarrarItem("barreta"));
		// Intento agarrar un item que no existe.
		assertEquals("No existe ese item!.", jugador.agarrarItem("espada"));
	}
	
	@Test
	public void testHablarCon() {
		// Intento hablar con un NPC que no existe.
		assertEquals("No se puede hablar con marinero.",jugador.hablarCon("marinero"));
		// Hablo con un NPC existente.
		assertEquals("No hay nada que me digas que me haga cambiar de opinion!",jugador.hablarCon("pirata fantasma"));
	}
	
	@Test
	public void testUsarEn() {
		// Intento usar un item que no tengo.
		assertEquals("No se encontro barreta en tu inventario.",jugador.usarItem("barreta", "pirata fantasma"));
		// Agarro item.
		jugador.agarrarItem("barreta");
		// Intento usar item contra un NPC no existente.
		assertEquals("No hay conejo en muelle.",jugador.usarItem("barreta", "conejo"));
		// Agarro otro item.
		jugador.agarrarItem("rociador con cerveza de raiz");
		// Intento usar item contra NPC existente pero no le hace da�o.
		assertEquals("Eso no ha servido de nada. ",jugador.usarItem("barreta", "pirata fantasma"));
		// Uso item existente contra NPC existente y si le hace da�o.
		assertEquals("Me encanta la cerveza de raiz!'(El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzo a desintegrarse. La mitad de arriba de su cuerpo se desvanecio, y las piernas inmediatamente echaron a correr).",jugador.usarItem("rociador con cerveza de raiz", "pirata fantasma"));
	}
	
	@Test
	public void testEndGames() {
		jugador.agarrarItem("espejo");
		// Detecta final al mirar espejo.
		assertEquals("Oh, no! Acabas de descubrir que tú también eres un pirata fantasma... ¡el horror!",jugador.usarItem("espejo", "mi"));
		// Detecta final a llegar a la taberna.
		jugador.agarrarItem("rociador con cerveza de raiz");
		jugador.usarItem("rociador con cerveza de raiz", "pirata fantasma");
		assertEquals("Enhorabuena! Llegaste a la taberna, donde te espera una noche de borrachera con Grog y otros colegas piratas.",jugador.moverHacia("sur"));
	}
	
}

package test;

import static org.junit.Assert.*;

import org.junit.Assert;

import entities.Aventura;
import entities.Player;

public class Test {
	private Aventura miAventura;
	private Player jugador;
	
	public Test() {
		this.miAventura = new Aventura("escenario1.json");
		this.jugador = miAventura.getJugador();
	}
	@org.junit.Test
	public void test() {
		Assert.assertEquals("Estas en un muelle. En el suelo hay un rociador con cerveza de raiz, una barreta y un espejo. Hay un pirata fantasma. Al sur se puede ir hacia taberna.", jugador.mirar());
		Assert.assertEquals("No tienes objetos en tu inventario.",jugador.listarInventario());
		Assert.assertEquals("Tienes una barreta",jugador.agarrarItem("barreta"));
		Assert.assertEquals("Tienes una barreta",jugador.listarInventario());
		Assert.assertEquals("No existe ese item!",jugador.agarrarItem("barreta"));
		Assert.assertEquals("Tienes un rociador con cerveza de raiz y una barreta.",jugador.agarrarItem("rociador con cerveza de raiz"));
		Assert.assertEquals("No existe esa ubicacion.",jugador.moverHacia("este"));
		Assert.assertEquals("No puedes pasar!(El pirata fantasma no te dejara pasar).",jugador.moverHacia("sur"));
	}

}

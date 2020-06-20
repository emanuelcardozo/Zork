package app;

import entities.Aventura;
import entities.Player;
import motorDeInstrucciones.Motor;

public class App {
	public static void main(String[] args) {
		Aventura miAventura = new Aventura("Aventuras/escenario3.json");
		Player jugador = miAventura.getJugador();
		Motor motorInstrucciones = new Motor(jugador);
		motorInstrucciones.start();
	}
}

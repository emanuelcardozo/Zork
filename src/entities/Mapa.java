package entities;

import java.util.Map;

import entities.Location;

public class Mapa {

	private Character jugador;
	private String inicio;
	private Map<String, Location> locations;

	public Mapa(Character jugador, String inicio, Map<String, Location> locations) {
		super();
		this.jugador = jugador;
		this.locations = locations;
	}

}

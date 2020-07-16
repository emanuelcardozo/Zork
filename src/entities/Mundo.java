package entities;

import java.util.Map;

public class Mundo {

	private String inicio;
	private Map<String, Location> locations;

	public Mundo(String inicio, Map<String, Location> locations) {
		super();
		this.inicio = inicio;
		this.locations = locations;
	}

	public Location getInicio() {
		return getLocation(inicio);
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public Location getLocation(String locationName) {
		return locations.get(locationName);
	}
}

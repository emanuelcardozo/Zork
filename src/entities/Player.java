package entities;

import java.util.Map;

public class Player {
	private String name;
	private Inventory inventario;
	private Location posicionActual;
	private Mundo mundo;

	public Player(String name, Mundo mundo) {
		this.posicionActual = mundo.getInicio();
		this.mundo = mundo;
		this.name = name;
		this.inventario = new Inventory();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Location posicionActual) {
		this.posicionActual = posicionActual;
	}

	public String mirar() {
		String observacion = posicionActual.getDescription() + ".";
		observacion += " " + posicionActual.describirLugaresConItems();
		observacion += " " + posicionActual.describirNPCs();
		observacion += " " + posicionActual.describirConnections();

		return observacion;
	}

	public String moverHacia(String where) {
		String locationName;
		String message = null;

		if (posicionActual.sePuedeMoverHacia(where)) {
			locationName = posicionActual.moverHacia(where);
			posicionActual = mundo.getLocation(locationName);

			if (posicionActual.getEndGame() != null)
				message = posicionActual.getEndGame().getDescription();
			else
				message = posicionActual.getDescription();
		} else {
			message = posicionActual.porqueNoPuedoIrHacia(where);
		}

		return message;
	}

	public String agarrarItem(String itemName) {
		Item item = null;
		Map<String, Place> places = posicionActual.getPlacesMap();

		for (String key : places.keySet()) {
			Place place = places.get(key);
			item = place.extractItem(itemName);
		}

		if (item != null) {
			inventario.agregarItem(item);
			return inventario.listarInventario();
		}

		return "No existe ese item!";
	}

	public String listarInventario() {
		return inventario.listarInventario();
	}
}

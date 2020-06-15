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
	
	public String mirar() {
		String observacion = posicionActual.getDescription() + ".";
		observacion += " " + posicionActual.describirLugaresConItems();
		observacion += " " + posicionActual.describirNPCs();
		observacion += " " + posicionActual.describirConnections();
		return observacion;
	}

	public String moverHacia(Direccion where) {
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

	public String agarrarItem(Item itemAgarrar) {
		Item item = null;
		Map<String, Place> places = posicionActual.getPlacesMap();

		for (String key : places.keySet()) {
			Place place = places.get(key);
			item = place.extractItem(itemAgarrar);
		}

		if (item != null) {
			inventario.agregarItem(item);
			return inventario.listarInventario();
		}

		return "No existe ese item!.";
	}

	public String listarInventario() {
		return inventario.listarInventario();
	}

	public Item getItem(Item item) {
		return inventario.getItem(item.getName());
	}

	public String usarItem(Item itemUsado, String receptor) {
		Item item = inventario.getItem(itemUsado.getName());
		String[] acciones;

		if (item == null)
			return "No se encontro " + itemUsado.getName() + " en tu inventario.";

		if (receptor == "mi") {
			acciones = item.usarEnMi();
			return acciones[0];
		}

		if (!posicionActual.contieneNPC(receptor))
			return "No hay " + receptor + " en " + posicionActual.getName() + ".";

		acciones = item.usarEnNPC(posicionActual.getNPC(receptor));

		if (acciones.length > 1 && acciones[1].equals("remove")) {
			posicionActual.eliminarObstaculo(receptor);
		}

		return acciones[0];
	}

	public String hablarCon(NPC objectName) {
		NPC npc = posicionActual.getNPC(objectName.getName());
		String message = "No se puede hablar con " + objectName + ".";
		return npc != null ? npc.hablar() : message;
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
}

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
	
	public String mirarAlrededor() {
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

		return "No existe ese item!.";
	}

	public String listarInventario() {
		return inventario.listarInventario();
	}

	public Item getItem(String item) {
		return inventario.getItem(item);
	}

	public Item buscarItemInventario(String name) {
		Item item = inventario.getItem(name);
		return item;
	}
	
	public String usarItem(Item item, String where) {
		
		String[] acciones;

		if (item == null)
			return "No se encontro el item en tu inventario.";

		if (where.equals("mi")) {
			acciones = item.usarEnMi();
			return acciones[0];
		}

		if (!posicionActual.contieneNPC(where))
			return "No hay " + where + " en " + posicionActual.getName()+".";

		acciones = item.usarEnNPC(posicionActual.getNPC(where));

		if (acciones.length > 1 && acciones[1].equals("remove")) {
			posicionActual.eliminarObstaculo(where);
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

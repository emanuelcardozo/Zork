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
		String observacion = "";
		boolean hay = false;
		if(!posicionActual.describirLugaresConItems().equals("")) {
			observacion += 	posicionActual.describirLugaresConItems();
			hay = true;
		}
		if(!posicionActual.describirNPCs().equals("")) {
			if(hay)
			observacion += " " + posicionActual.describirNPCs();
			else
				observacion += posicionActual.describirNPCs();
			hay = true;
		}
		if(hay)
		observacion += " " + posicionActual.describirConnections();
		else
			observacion += posicionActual.describirConnections();
		return observacion;
	}
	
	public String moverHacia(String where) {
		
		if (posicionActual.sePuedeMoverHacia(where)) {
			String locationName = posicionActual.moverHacia(where);
			posicionActual = mundo.getLocation(locationName);
			
			return posicionActual.moverA(where);
		}
		
		return posicionActual.porqueNoPuedoIrHacia(where);
	}

	public String agarrarItem(String itemName) {
		Item item = null;
		Map<String, Place> places = posicionActual.getPlacesMap();

		for (String key : places.keySet()) {
			Place place = places.get(key);
			item = place.extractItem(itemName);
			if (item != null) {
				inventario.agregarItem(item);
				return "Item agregado a tu inventario!";
			}
		}

		return "No existe ese item!.";
	}
	

	//Falta arreglar pq toma el endgame como accion
	public String usarItemEnMi(Item item) {
		String[] acciones;
		if(item == null)
			return "El item no se encuentra en tu inventario";
		acciones = item.usarEnMi();
		if(acciones[0] != null)
		return acciones[0];
		else
			return "Eso no ha servido de nada.";
	}
	
	public String usarItemEnNpc(Item item, NPC npc) {
		if (item == null)
			return "El item no se encuentra en tu inventario.";
		if (npc == null)
			return "No puedes usarlo contra lo que deseas.";
		Trigger reaccion = npc.reaccionAItem(item);
		if (item != null && npc != null && reaccion != null) {
			if (reaccion.getAfter_trigger().equals("remove"))
				posicionActual.eliminarObstaculo(npc.getName());
			return reaccion.getOn_trigger();
		}
		return "Eso no ha servido de nada.";
	}

	public String acariciar(NPC np) {
		Trigger t;
		if(np != null) {
		t =  np.serAcariciado();
		return t.getOn_trigger();
		}
		return "No es posible hacer eso.";
	}
	
	public String correr(NPC np) {
		Trigger t;
		if (np != null) {
			t = np.serCorrido();
			if (t != null)
				return t.getOn_trigger();
		}

		return "No es posible hacer eso.";
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
	
	public NPC buscarNpc(String objectName) {
		NPC npc = posicionActual.getNPC(objectName);
		return npc;
	}
	
	public String hablarCon(NPC objectName) {
		NPC npc = objectName;
		String message = "No hay nadie para hablar con ese nombre.";
		return npc != null ? npc.hablar() : message;
	}
	
	public String mirarNpc(NPC objectName) {
		NPC npc = objectName;
		String message = "No hay nadie para mirar con ese nombre.";
		return npc != null ? npc.mirar() : message;
	}
	
	public String mirarItem(Item itemName) {
		return itemName.serObservado();
	}
	
	public String tirarItem(String itemName) {
		Item item;
		item = this.buscarItemInventario(itemName);
		if(item == null)
			return "Ese item no se encuentra en tu inventario.";
		else {
			posicionActual.getPlace("suelo").addItem(item);
			inventario.eliminarItem(itemName);
			return "Has tirado el item.";
		}
	}

	public String acuchillar(NPC np) {
		Trigger t;
		t = np.serAcuchillado();
		if(!inventario.estaEnInventario("cuchillo"))
			return "No tienes al cuchillo en tu inventario.";
		if( t != null)
			return t.getOn_trigger();
			
		return "Ni lo intentes!";
	}
	
	public String golpear(Item item, NPC np) {
		Trigger t;
		if(!item.tieneAccion("golpear"))
			return "No es posible hacer eso.";
		if(np != null) {
		t =  np.serGolpeadoCon(item);
		if(t != null)
		return t.getOn_trigger();
		}
		return "No es posible hacer eso.";
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

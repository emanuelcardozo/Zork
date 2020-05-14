package src;

import java.util.ArrayList;

public class Character {
	private String name;
	private ArrayList<String> inventario;
	private Location posicion;
	private Setting partida;

	public Character(Setting j) {
		this.posicion = j.getLocations().get(0);
		this.name = j.getCharacter();
		this.inventario = j.getInventario();
		this.partida = j;
	}

	public String mirar() {
		return "Estas en un " + posicion.getName() + ". " + "En el suelo hay " + posicion.listarItems() + ". "
				+ "Hay un " + posicion.listarNpcs() + posicion.listarConexiones() + ". ";
	}

	public String name() {
		return "Tu nombre es " + name;
	}

	public String inventario() {

		if (inventario.isEmpty())
			return "No hay elementos en tu inventario";

		String inventary = "Tienes ";

		for (String s : inventario)
			inventary += s.toString() + " ";
		inventary += "en tu inventario";

		return inventary;
	}

	public void mover(String direccion) {
		Connection c = hayConexion(direccion);
		if (c != null) {
			if (!c.getObstacle().isEmpty()) {
				NPC np;
				np = buscarNPC(c.getObstacle());
				System.out.println(np.getDescription());
			}
			else {
				posicion = buscarLocalizacion(c.getLocation());
				System.out.println("Usted esta en " + posicion.getName());
			}
		} else
			System.out.println("No es posible moverse a esa posicion");
	}
	
	public void hablarCon(String name) {
		NPC npc = existeNPC(name);
		if (npc != null) {
			System.out.println(npc.getTalk());
		} else
			System.out.println("No existe el personaje con quien queres hablar.");
	}

	public void agarrarItem(String item) {
		for (String i : posicion.getItems())
			if (i.equals(item))
				inventario.add(i);
	}

	private Connection hayConexion(String direccion) {
		for (Connection c : posicion.getConnections())
			if (c.getDirection().equals(direccion))
				return c;

		return null;
	}

	private Location buscarLocalizacion(String name) {
		for (Location l : partida.getLocations())
			if (l.getName().equals(name))
				return l;
		return null;
	}
	
	private NPC buscarNPC(String npc) {
		for(NPC np : partida.getNpcs()) {
			if(np.name.equals(npc))
				return np;
		}
		return null;
	}
	
	private NPC existeNPC(String name) {
		NPC npc = buscarNPC(name);
		if (npc != null) {
			for (String np : posicion.getNpcs()) {
				if (np.equals(npc.name))
					return npc;
			}
		}
		return null;
	}
}

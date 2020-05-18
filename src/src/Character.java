package src;

import java.util.ArrayList;

public class Character {
	public String name;
	private ArrayList<String> inventario;
	private Location posicion;
	private Setting partida;

	public Character(Setting j) {
		this.posicion = j.getLocations().get(0);
		this.name = j.getCharacter();
		this.inventario = j.getInventario();
		this.partida = j;
	}

	// Retorna un String con la localizacion y los items al alcance del personaje.
	public String mirar() {
		return "Estas en un " + posicion.getName() + ". " + "En el suelo hay " + posicion.listarItems() + ". "
				+ "Hay un " + posicion.listarNpcs() + posicion.listarConexiones() + ". ";
	}

	// Retorna el nombre del personaje.
	public String name() {
		return "Tu nombre es " + name;
	}

	// Retorna un String con tu inventario actual.
	// (Falta arreglar la salida)
	public String inventario() {

		if (inventario.isEmpty())
			return "No hay elementos en tu inventario";

		String inventary = "Tienes: ";

		for (String s : inventario)
			inventary += "(x)" +s.toString() + " ";
		inventary += ". En tu inventario";

		return inventary;
	}

	// Si hay conexion con la direccion indicada deja pasar al personaje a la otra.
	// localizacion salvo que haya un obstaculo.
	public String mover(String direccion) {
		Connection c = hayConexion(direccion);
		if (c != null) {
			if (c.getObstacle() != null && existeNPC(c.getObstacle())) {
				NPC np;
				np = buscarNPC(c.getObstacle());
				return np.getDescription();
			} else {
				posicion = buscarLocalizacion(c.getLocation());
				System.out.println(posicion.getName());
				return "Usted esta en " + posicion.getName();
			}
		} else
			return "No es posible moverse a esa posicion";
	}

	// Si este personaje se encuentra en la localizacion del personaje retorna su
	// talk.
	public String hablarCon(String name) {
		if (existeNPC(name)) {
			NPC npc = buscarNPC(name);
			if (npc != null) {
				return npc.getTalk();
			}
		}
		return "No existe el personaje con quien queres hablar.";
	}

	// Agrega al inventario el item si este se encuentra en la localizacion del
	// personaje.
	public void agarrarItem(String item) {
		for (String i : posicion.getItems())
			if (i.equals(item))
				inventario.add(i);
	}
	
	// Deberia poder usar un elemento del inventario contra un NPC y ver si le hace
		// efecto o no.
		// Si logra eliminarlo al NPC con este elemento deberia retornar el on_trigger.
		// Sino deberia retornar una leyenda de "Eso no ha servido de nada".
		public String usarEn(String elemento, String npc) {
			String respuesta = "";
			if (existeItemEnInventario(elemento) && existeNPC(npc)) {
				//Aca se debe implementar la funcion del trigger para que devuelva los dialogos correspondientes.
				posicion.getNpcs().remove(npc);
				respuesta = "Has vencido al " + npc+".";
			} else if (!existeNPC(npc)) {
				respuesta = "No existe nadie con ese nombre.";
			} else if (!existeItemEnInventario(elemento))
				respuesta = "No posees ese item en tu inventario.";
			return respuesta;
		}


	// Retorna una conexion si en esa direccion indicada se puede ir hacia algun
	// lado.
	private Connection hayConexion(String direccion) {
		for (Connection c : posicion.getConnections())
			if (c.getDirection().equals(direccion))
				return c;

		return null;
	}

	// Retorna la localizacion si esta existe en la partida.
	private Location buscarLocalizacion(String name) {
		for (Location l : partida.getLocations())
			if (l.getName().equals(name))
				return l;
		return null;
	}

	// Retorna el NPC buscado si este se encuentra en la partida.
	private NPC buscarNPC(String npc) {
		if (existeNPC(npc)) {
			for (NPC np : partida.getNpcs()) {
				if (np.name.equals(npc))
					return np;
			}
		}
		return null;
	}

	// Retorna true si el NPC existe en la posicion actual del personaje.
	private boolean existeNPC(String name) {
		for (String i : posicion.getNpcs()) {
			if (i.equals(name))
				return true;
		}
		return false;
	}

	//Retorna true si existe el item en el inventario del personaje.
	private boolean existeItemEnInventario(String elemento) {
		for(String e:inventario) {
			if(e.equals(elemento))
			return true;
		}
		return false;
	}

}

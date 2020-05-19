package src;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entities.Noun;

public class Location extends Noun {
	private String description;
	private ArrayList<Place> places = new ArrayList<Place>();
	private ArrayList<String> items = new ArrayList<String>();
	private ArrayList<String> npcs = new ArrayList<String>();
	private ArrayList<Connection> connections = new ArrayList<Connection>();

	public Location(JSONObject locationJSON) {
		super((String) locationJSON.get("name"), (String) locationJSON.get("gender"),
				(String) locationJSON.get("number"));
		description = (String) locationJSON.get("description");

		if (locationJSON.containsKey("places"))
			buildPlaces((JSONArray) locationJSON.get("places"));

		if (locationJSON.containsKey("npcs"))
			buildNPCs((JSONArray) locationJSON.get("npcs"));

		if (locationJSON.containsKey("connections"))
			buildConnections((JSONArray) locationJSON.get("connections"));
	}

	private void buildConnections(JSONArray connectionsJSON) {
		for (Object connectionObj : connectionsJSON) {
			JSONObject connectionJSON = (JSONObject) connectionObj;
			connections.add(new Connection(connectionJSON));
		}
	}

	private void buildNPCs(JSONArray npcsJSON) {
		for (Object npcObj : npcsJSON) {
			npcs.add((String) npcObj);
		}
	}

	private void buildPlaces(JSONArray placesJSON) {
		for (Object placeObj : placesJSON) {
			JSONObject placeJSON = (JSONObject) placeObj;
			places.add(new Place(placeJSON));

			if (placeJSON.containsKey("items")) {
				JSONArray itemsJSON = (JSONArray) placeJSON.get("items");
				for (Object obItem : itemsJSON) {
					items.add((String) obItem);
				}
			} // TODO: items pertenecen a un place, hay que moverlo a esa clase
		}

	}

	public Location(String name, String gender, String number, String description) {
		super(name, gender, number);
		this.description = description;
	}

	public ArrayList<Place> getPlaces() {
		return places;
	}

	public void addPlace(Place place) {
		places.add(place);
	}

	public ArrayList<String> getItems() {
		return items;
	}

	public void addItem(String item) {
		items.add(item);
	}

	public ArrayList<String> getNpcs() {
		return npcs;
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	public String listarItems() {
		String lista = "", cantidad, unidor = ", ";
		if (!items.isEmpty()) {
			int anteUltimoItem = items.size() - 2;

			for (String item : items) {
				if (esFemenino(item))
					cantidad = "una ";
				else
					cantidad = "un ";

				if (item.equals(items.get(anteUltimoItem)))
					unidor = " y ";
				else if (item.equals(items.get(anteUltimoItem + 1)))
					unidor = "";

				lista += cantidad + item + unidor;
			}
		}

		return lista;
	}

	private boolean esFemenino(String item) {

		String palabra = item.split(" ")[0];
		char ultimaLetra = palabra.charAt(palabra.length() - 1);

		return ultimaLetra == 'a' || ultimaLetra == 'A';
	}
	
	public String retornarConectorGenero() {
		String conector = "";
		if (this.gender.equals("female"))
			conector = "una";
		else if(this.gender.equals("male"))
			conector = "un";
		return conector;
	}

	public String listarNpcs() {
		String np = "";
		for (String s : npcs)
			np += s + " ";
		return np;
	}

	public String listarConexiones() {
		String connect = "";
		for (Connection cn : connections)
			connect = "Al " + cn.getDirectionChanged() + " se puede ir hacia "+cn.generoConnection()+" "+ cn.getLocation();
		return connect;
	}

	@Override
	public String toString() {
		return "Location [description=" + description + ", places=" + places + ", items=" + items + ", npcs=" + npcs
				+ ", connections=" + connections + "]";
	}

}

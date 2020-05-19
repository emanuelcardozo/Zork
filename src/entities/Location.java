package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entities.Place;

public class Location extends Noun {
	private String description;
	private Map<String, Place> placesMap = new HashMap<String, Place>();
	private Map<String, NPC> npcsMap = new HashMap<String, NPC>();
	private ArrayList<Connection> connectionsArray = new ArrayList<Connection>();
	
	public Location(String name, String gender, String number, String description, HashMap<String, Place> places, HashMap<String, NPC> npcs, ArrayList<Connection> connections) {
		super(name, gender, number);
		this.description = description;
		this.placesMap = places;
		this.npcsMap = npcs;
		this.connectionsArray = connections;
	}

	public Location(JSONObject locationJSON, HashMap<String, Item> items , Map<String, NPC> npcs) {
		super((String) locationJSON.get("name"), (String) locationJSON.get("gender"), (String) locationJSON.get("number"));
		this.description = (String) locationJSON.get("description");

		if (locationJSON.containsKey("places"))
			buildPlaces((JSONArray) locationJSON.get("places"), items);

		if (locationJSON.containsKey("npcs"))
			buildNPCS((JSONArray) locationJSON.get("npcs"), npcs);
		
		if (locationJSON.containsKey("connections"))
			buildConnections((JSONArray) locationJSON.get("connections"));
			
	}

	private void buildPlaces(JSONArray placesJson, Map<String, Item> items) {
		for (Object placeObj : placesJson) {
			JSONObject place = (JSONObject) placeObj;
			String placeName = (String) place.get("name");
			placesMap.put(placeName, new Place(place, items));
		}
	}
	
	private void buildNPCS(JSONArray npcsJson, Map<String, NPC> npcs) {
		for (Object npcObject : npcsJson) {
			JSONObject npc = (JSONObject) npcObject;
			String npcName = (String) npc.get("name");
			npcsMap.put(npcName, npcs.get(npcName));
		}
	}
	
	private void buildConnections(JSONArray jsonConnections) {
		for (Object connectionObject : jsonConnections) {
			JSONObject connection = (JSONObject) connectionObject;
			connectionsArray.add(new Connection(connection));
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Place> getPlacesMap() {
		return placesMap;
	}

	public void setPlacesMap(Map<String, Place> placesMap) {
		this.placesMap = placesMap;
	}

	public Map<String, NPC> getNpcsMap() {
		return npcsMap;
	}

	public void setNpcsMap(Map<String, NPC> npcsMap) {
		this.npcsMap = npcsMap;
	}

	public ArrayList<Connection> getConnectionsArray() {
		return connectionsArray;
	}

	public void setConnectionsArray(ArrayList<Connection> connectionsArray) {
		this.connectionsArray = connectionsArray;
	}
}

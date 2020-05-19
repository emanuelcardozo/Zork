package entities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entities.Place;

public class Location extends Noun {
	private String description;
	private HashMap<String, Place> placesMap = new HashMap<String, Place>();
	private HashMap<String, NPC> npcsMap = new HashMap<String, NPC>();
	
	public Location(String name, String gender, String number, String description, HashMap<String, Place> places, HashMap<String, NPC> npcs) {
		super(name, gender, number);
		this.description = description;
		this.placesMap = places;
		this.npcsMap = npcs;
	}

	public Location(JSONObject locationJSON, HashMap<String, Item> items , Map<String, NPC> npcs) {
		super((String) locationJSON.get("name"), (String) locationJSON.get("gender"), (String) locationJSON.get("number"));
		this.description = (String) locationJSON.get("description");

		if (locationJSON.containsKey("places"))
			buildPlaces((JSONArray) locationJSON.get("places"), items);

		if (locationJSON.containsKey("npcs"))
			buildNPCS((JSONArray) locationJSON.get("npcs"), npcs);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<String, Place> getPlacesMap() {
		return placesMap;
	}

	public void setPlacesMap(HashMap<String, Place> placesMap) {
		this.placesMap = placesMap;
	}

	public HashMap<String, NPC> getNpcsMap() {
		return npcsMap;
	}

	public void setNpcsMap(HashMap<String, NPC> npcsMap) {
		this.npcsMap = npcsMap;
	}
}

package src;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Place extends Noun {
	private ArrayList<String> items = new ArrayList<String>();

	public Place(String name, String gender, String number) {
		super(name, gender, number);
	}

	public Place(JSONObject placeJSON) {
		super((String) placeJSON.get("name"), (String) placeJSON.get("gender"), (String) placeJSON.get("number"));
	}

	public ArrayList<String> getItems() {
		return items;
	}
}

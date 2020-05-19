package src;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Item extends Noun {
	private ArrayList<String> actions = new ArrayList<String>();
	private ArrayList<String> effects_over = new ArrayList<String>();

	public Item(String name, String gender, String number) {
		super(name, gender, number);

	}

	public Item(JSONObject itemJSON) {
		super((String) itemJSON.get("name"), (String) itemJSON.get("gender"), (String) itemJSON.get("number"));

		if (itemJSON.containsKey("actions")) {
			buildActions((JSONArray) itemJSON.get("actions"));
		}

		if (itemJSON.containsKey("effects_over")) {
			buildEffectsOver((JSONArray) itemJSON.get("effects_over"));
		}
	}

	private void buildEffectsOver(JSONArray effectsJSON) {
		for (Object effectObj : effectsJSON) {
			effects_over.add((String) effectObj);
		}

	}

	private void buildActions(JSONArray actionsJSON) {
		for (Object actionsObj : actionsJSON) {
			actions.add((String) actionsObj);
		}
	}

	public ArrayList<String> getActions() {
		return actions;
	}

	public ArrayList<String> getEffects_over() {
		return effects_over;
	}
	
	public boolean esFemenino() {
		return this.gender.equals("femenino");
	}
	@Override
	public String toString() {
		return "Item [name=" + name + ", gender=" + gender + ", number=" + number + " actions= " + actions
				+ " effects_over: " + effects_over + "]";
	}
}

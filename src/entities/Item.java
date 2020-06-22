package entities;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Item extends Noun {
	private ArrayList<String> actions = new ArrayList<String>();
	private ArrayList<String> effects_over = new ArrayList<String>();
	private String description;
	private EndGame endGame;

	public Item(String name, String gender, String number) {
		super(name, gender, number);

	}

	public Item(JSONObject itemJSON) {
		super((String) itemJSON.get("name"), (String) itemJSON.get("gender"), (String) itemJSON.get("number"));
		this.description = (String) itemJSON.get("description");
		if (itemJSON.containsKey("actions")) {
			buildActions((JSONArray) itemJSON.get("actions"));
		}

		if (itemJSON.containsKey("effects_over")) {
			buildEffectsOver((JSONArray) itemJSON.get("effects_over"));
		}
	}

	public boolean afectaA(String targetName) {
		return effects_over.contains(targetName);
	}

	public EndGame getEndGame() {
		return endGame;
	}

	public void setEndGame(EndGame endGame) {
		this.endGame = endGame;
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

	public String[] usarEnMi() {
		String[] message = { "Eso no ha servido de nada. " };
		if (afectaA("self")) {
			message[0] = getEndGame().getDescription();
		}
		
		return message;
	}
	
	public String serObservado() {
		return this.description;
	}
	
	public boolean tieneAccion(String accion) {
		for (String a : actions) {
			if (a.equals(accion)) {
				for (String npc : effects_over)
					if (npc.equals("npc"))
						return true;
			}
		}
		return false;
	}
}

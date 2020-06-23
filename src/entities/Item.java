package entities;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Item extends Noun implements Triggerable {
	private ArrayList<String> actions = new ArrayList<String>();
	private ArrayList<String> effects_over = new ArrayList<String>();

	private boolean hasTrigger;
	private Aventura aventura;
	private String description;

	public Item(String name, String gender, String number, Aventura aventura, boolean hasTrigger) {
		super(name, gender, number);

		this.aventura = aventura;
		this.hasTrigger = hasTrigger;
	}

	public Item(JSONObject itemJSON, Aventura aventura) {
		super((String) itemJSON.get("name"), (String) itemJSON.get("gender"), (String) itemJSON.get("number"));
		this.aventura = aventura;
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

	public boolean hasTrigger() {
		return hasTrigger;
	}

	public void setHastrigger(boolean hasTrigger) {
		this.hasTrigger = hasTrigger;
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
			String endDescription = executeTrigger();

			if( endDescription != null) message[0] = endDescription;
		}

		return message;
	}

	@Override
	public String executeTrigger() {
		return aventura.ejecutarFinal(new Trigger( "action", name, null, null ));
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

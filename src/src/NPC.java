package src;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NPC extends Noun {
	private String description;
	private String talk;
	private ArrayList<Trigger> triggers = new ArrayList<Trigger>();

	public NPC(String name, String gender, String number, String description, String talk) {
		super(name, gender, number);
		this.description = description;
		this.talk = talk;
	}

	public NPC(JSONObject npcJSON) {
		super((String) npcJSON.get("name"), (String) npcJSON.get("gender"), (String) npcJSON.get("number"));
		description = (String) npcJSON.get("description");
		talk = (String) npcJSON.get("talk");

		if (npcJSON.containsKey("triggers")) {
			buildTriggers((JSONArray) npcJSON.get("triggers"));
		}
	}

	private void buildTriggers(JSONArray triggersJSON) {
		for (Object triggerObj : triggersJSON) {

			JSONObject triggerJSON = (JSONObject) triggerObj;
			triggers.add(new Trigger(triggerJSON));
		}

	}

	public ArrayList<Trigger> getTriggers() {
		return triggers;
	}

	public String getDescription() {
		return description;
	}

	public String getTalk() {
		return talk;
	}

	@Override
	public String toString() {
		return "Npcs [name=" + name + ", gender=" + gender + ", number=" + number + " + description=" + description
				+ " talk= " + talk + " triggers= " + triggers + "]";
	}

}

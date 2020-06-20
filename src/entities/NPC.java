package entities;

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

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTalk(String talk) {
		this.talk = talk;
	}

	public void setTriggers(ArrayList<Trigger> triggers) {
		this.triggers = triggers;
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

	public boolean reaccionaConItem(String itemName) {
		for (Trigger trigger : triggers) {
			if (trigger.getThing().equals(itemName))
				return true;
		}
		return false;
	}

	public String[] ejecutarTrigger(String itemName) {
		String[] response = new String[2];// = null, afterTrigger = null;

		for (Trigger trigger : triggers) {
			if (trigger.getThing().equals(itemName)) {
				response[0] = trigger.getOn_trigger();
				response[1] = trigger.getAfter_trigger();
			}
		}

		return response;
	}

	public String hablar() {
		return getTalk();
	}
	
	public String mirar() {
		return getDescription();
	}
	
}

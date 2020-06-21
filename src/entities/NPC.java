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
	
	public Trigger reaccionAItem(Item item) {
		for (Trigger t : triggers) {
			if (t.getThing().equals(item.getName()))
				return t;
		}
		return null;
	}
	
	public String hablar() {
		return getTalk();
	}
	
	public String mirar() {
		return getDescription();
	}

	public String serAcariciado() {
		for(Trigger t : triggers) {
			if(t.getType().equals("acariciar"))
				return t.getOn_trigger();
		}
		return "Eso no esta permitido.";
	}
	
}

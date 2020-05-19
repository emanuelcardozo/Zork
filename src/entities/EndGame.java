package entities;

import org.json.simple.JSONObject;

public class EndGame {
	private String condition;
	private String action;
	private String thing;
	private String description;

	public EndGame(String condition, String action, String thing, String description) {
		this.condition = condition;
		this.action = action;
		this.thing = thing;
		this.description = description;
	}

	public EndGame(JSONObject endGameJSON) {
		condition = (String) endGameJSON.get("condition");
		action = (String) endGameJSON.get("action");
		thing = (String) endGameJSON.get("thing");
		description = (String) endGameJSON.get("description");
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getThing() {
		return thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "EndGame [condition=" + condition + ", action=" + action + ", thing=" + thing + ", description="
				+ description + "]";
	}
}

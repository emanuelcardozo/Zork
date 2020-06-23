package entities;

import org.json.simple.JSONObject;

public class EndGame {
	private String condition;
	private String action;
	private String thing;
	private String description;
	private EndGame nextEndGame;

	public EndGame(String condition, String action, String thing, String description, EndGame nextEndGame ) {
		this.condition = condition;
		this.action = action;
		this.thing = thing;
		this.description = description;
		this.nextEndGame = nextEndGame;
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
	
	public void setNextEndGame(EndGame nextEndGame) {
		this.nextEndGame = nextEndGame;
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
	
	public String execute(Trigger trigger) {
		
		if( trigger.getThing().equals(thing) && 
				(trigger.getType().equals( condition ) || 
						trigger.getType().equals("item") && condition.equals("action")) )
			return description;
		
		return nextEndGame != null ? nextEndGame.execute(trigger) : null;
	}
}

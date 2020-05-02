package src;

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

	@Override
	public String toString() {
		return "EndGame [condition=" + condition + ", action=" + action + ", thing=" + thing + ", description="
				+ description + "]";
	}

}

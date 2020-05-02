package src;

public class Trigger {
	private String type;
	private String thing;
	private String on_trigger;
	private String after_trigger;

	public Trigger(String type, String thing, String on_trigger, String after_trigger) {
		this.type = type;
		this.thing = thing;
		this.on_trigger = on_trigger;
		this.after_trigger = after_trigger;
	}

	@Override
	public String toString() {
		return "Trigger [type=" + type + ", thing=" + thing + ", on_trigger=" + on_trigger + ", after_trigger="
				+ after_trigger + "]";
	}

}

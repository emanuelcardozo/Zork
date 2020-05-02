package src;

import java.util.ArrayList;

public class Item extends Noun {
	private ArrayList<String> actions = new ArrayList<String>();
	private ArrayList<String> effects_over = new ArrayList<String>();

	public Item(String name, String gender, String number) {
		super(name, gender, number);

	}

	public ArrayList<String> getActions() {
		return actions;
	}

	public ArrayList<String> getEffects_over() {
		return effects_over;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", gender=" + gender + ", number=" + number +" actions= "+ actions+" effects_over: "+effects_over+ "]";
	}
}

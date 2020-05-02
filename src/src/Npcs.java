package src;

import java.util.ArrayList;

public class Npcs extends Noun{
	private String description;
	private String talk;
	private ArrayList<Trigger>triggers = new ArrayList<Trigger>();
	
	public Npcs(String name, String gender, String number, String description, String talk) {
		super(name, gender, number);
		this.description = description;
		this.talk = talk;
	}
	
	public ArrayList<Trigger> getTriggers() {
		return triggers;
	}

	@Override
	public String toString() {
		return "Npcs [name=" + name + ", gender=" + gender + ", number=" + number + " + description=" + description
				+ " talk= " + talk + " triggers= " + triggers + "]";
	}
		
}

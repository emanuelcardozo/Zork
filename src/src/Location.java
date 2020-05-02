package src;

import java.util.ArrayList;

public class Location extends Noun {
	private String description;
	private ArrayList<Place> places = new ArrayList<Place>();
	private ArrayList<String> items = new ArrayList<String>();
	private ArrayList<String> npcs = new ArrayList<String>();
	private ArrayList<Connection>connections = new ArrayList<Connection>();
	public Location(String name, String gender, String number, String description) {
		super(name, gender, number);
		this.description = description;
	}
	
	public ArrayList<Place> getPlaces() {
		return places;
	}
	
	public ArrayList<String> getItems() {
		return items;
	}
	
	public ArrayList<String> getNpcs() {
		return npcs;
	}
	
	public ArrayList<Connection> getConnections() {
		return connections;
	}

	@Override
	public String toString() {
		return "Location [description=" + description + ", places=" + places + ", items=" + items + ", npcs=" + npcs
				+ ", connections=" + connections + "]";
	}

}

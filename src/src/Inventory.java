package src;

import java.util.HashMap;
import java.util.Map;

import entities.Item;

public class Inventory {
	private Map<String, Item> items;

	public Inventory() {
		super();
		this.items = new HashMap<String, Item>();
	}

	public void agregarItem(Item item) {
		items.put(item.getName(), item);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

}

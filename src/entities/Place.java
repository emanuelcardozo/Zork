package entities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import src.Noun;

public class Place extends Noun {
	private Map<String, Item> itemsMap = new HashMap<String, Item>();

	public Place(String name, String gender, String number) {
		super(name, gender, number);
	}

	public Place(JSONObject placeJSON, Map<String, Item> allItemsMap) {
		super((String) placeJSON.get("name"), (String) placeJSON.get("gender"), (String) placeJSON.get("number"));

		if (placeJSON.containsKey("items")) {
			buildItemsMap((JSONArray) placeJSON.get("items"), allItemsMap);
		}
	}

	private void buildItemsMap(JSONArray itemsJSON, Map<String, Item> allItemsMap) {
		for (Object itemObj : itemsJSON) {
			String itemName = (String) itemObj;
			itemsMap.put(itemName, allItemsMap.get(itemName));
		}
	}

	public void addItem(Item item) {
		itemsMap.put(item.getName(), item);
	}

	public Item extractItem(String name) {
		Item item = itemsMap.get(name);
		itemsMap.remove(name);

		return item;
	}
}

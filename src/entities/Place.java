package entities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

	public String listarItems() {
		String[] palabras = new String[itemsMap.keySet().size()];
		Item item;
		int index = 0;

		for (Map.Entry<String, Item> entry : itemsMap.entrySet()) {
			item = entry.getValue();
			palabras[index++] = item.getCantidad() + " " + item.getName();
		}

		return listarPalabras(palabras);
	}
}

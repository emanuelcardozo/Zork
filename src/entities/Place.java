package entities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Place extends Noun {
	private Map<String, Item> itemsMap = new HashMap<String, Item>();
	private Location location;

	public Place(String name, String gender, String number) {
		super(name, gender, number);
	}

	public Place(JSONObject placeJSON, Map<String, Item> allItemsMap, Location location) {
		super((String) placeJSON.get("name"), (String) placeJSON.get("gender"), (String) placeJSON.get("number"));
		
		this.location = location;

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
		location.actualizarEscenario();
		String path = "./General/Sound/tirar.wav";
		Sonido sonido = new Sonido(path);
		sonido.reproducir();
	}

	public Map<String, Item> getItemsMap() {
		return itemsMap;
	}

	public void setItemsMap(Map<String, Item> itemsMap) {
		this.itemsMap = itemsMap;
	}
	
	public int getItemsInPlace() {
		return itemsMap.size();
	}

	public Item extractItem(String name) {
		String keyName = null;
		
		for(String key : itemsMap.keySet()) {
			if( key.startsWith(name) ) {
				keyName = key;
				break;
			}				
		}
		
		Item item = itemsMap.get(keyName);
		
		if(item != null) {
			itemsMap.remove(item.getName());
			location.actualizarEscenario();
			
			String path = "./General/Sound/agarrar.wav";
			Sonido sonido = new Sonido(path);
			sonido.reproducir();
		}
			
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
	
	public Item[] getAllItems() {
		
		Item[] items = new Item[itemsMap.size()];
		int i=0;
		
		for (Map.Entry<String, Item> entry : itemsMap.entrySet()) {
			items[i++] = entry.getValue();
		}
		return items;		
	}
}

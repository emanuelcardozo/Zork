package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Setting {
	private String welcome;
	private String character;
	private ArrayList<Location> locations = new ArrayList<Location>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<NPC> npcs = new ArrayList<NPC>();
	private ArrayList<String> inventario = new ArrayList<String>();
	private ArrayList<EndGame> endGames = new ArrayList<EndGame>();
	private String path;

	public Setting(String path) throws FileNotFoundException, IOException, ParseException {
		this.path = path;
		construirAventura();
		mostrarBienvenida();

	}

	public void construirAventura() throws FileNotFoundException, IOException, ParseException {
		JSONParser parserJSON = new JSONParser();
		JSONObject archivoJSON = (JSONObject) parserJSON.parse(new FileReader(path));
		JSONObject settingJSON = (JSONObject) archivoJSON.get("settings");
		this.welcome = (String) settingJSON.get("welcome");
		this.character = (String) settingJSON.get("character");

		JSONArray locations = (JSONArray) archivoJSON.get("locations");

		for (Object locationObj : locations) {
			JSONObject locationJSON = (JSONObject) locationObj;
			this.locations.add(new Location(locationJSON));
		}

		JSONArray npcsJSON = (JSONArray) archivoJSON.get("npcs");

		for (Object npcObj : npcsJSON) {
			JSONObject npcJSON = (JSONObject) npcObj;
			this.npcs.add(new NPC(npcJSON));
		}

		JSONArray itemsJSON = (JSONArray) archivoJSON.get("items");

		for (Object itemObj : itemsJSON) {
			JSONObject itemJSON = (JSONObject) itemObj;
			this.items.add(new Item(itemJSON));
		}

		JSONArray endsGameJSON = (JSONArray) archivoJSON.get("endgames");

		for (Object endGameObj : endsGameJSON) {
			JSONObject endGameJSON = (JSONObject) endGameObj;
			this.endGames.add(new EndGame(endGameJSON));
		}
	}

	public void mostrarItems() {
		for (Item it : this.items)
			System.out.println(it);
	}

	public void mostrarNpcs() {
		for (NPC np : this.npcs)
			System.out.println(np);
	}

	public void mostrarEndGames() {
		for (EndGame eg : this.endGames)
			System.out.println(eg);
	}

	public void mostrarLocations() {
		for (Location lc : this.locations)
			System.out.println(lc.getName());
	}

	public void mostrarBienvenida() {
		System.out.println(this.welcome);
	}

	public String getWelcome() {
		return welcome;
	}

	public String getCharacter() {
		return character;
	}

	public void mostrarSettting() {
		System.out.println(this);
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public ArrayList<String> getInventario() {
		return inventario;
	}

	@Override
	public String toString() {
		return "Setting [welcome=" + welcome + ", character=" + character + ", locations=" + locations + ", items="
				+ items + ", npcs=" + npcs + ", inventario=" + inventario + ", endGames=" + endGames + "]";
	}

}

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
		JSONParser parser = new JSONParser();
		Location location;
		Place place;
		NPC npcs_nuevo;
		Trigger trigger;
		Connection connection;
		Item item;
		EndGame endGame;

		Object obj = parser.parse(new FileReader(path));
		JSONObject jsonObject = (JSONObject) obj;
		JSONObject setting = (JSONObject) jsonObject.get("settings");
		this.welcome = (String) setting.get("welcome");
		this.character = (String) setting.get("character");
		JSONArray locations = (JSONArray) jsonObject.get("locations");

		for (Object locationObj : locations) {
			JSONObject locationJSON = (JSONObject) locationObj;
			this.locations.add(new Location(locationJSON));
		}

		JSONArray npcsJSON = (JSONArray) jsonObject.get("npcs");

		for (Object npcObj : npcsJSON) {
			JSONObject npcJSON = (JSONObject) npcObj;
			this.npcs.add(new NPC(npcJSON));
		}

		JSONArray itemsJSON = (JSONArray) jsonObject.get("items");

		for (Object itemObj : itemsJSON) {
			JSONObject itemJSON = (JSONObject) itemObj;
			this.items.add(new Item(itemJSON));
		}

		JSONArray end_games = (JSONArray) jsonObject.get("endgames");

		for (Object endGames : end_games) {
			JSONObject end_game = (JSONObject) endGames;
			endGame = new EndGame((String) end_game.get("condition"), (String) end_game.get("action"),
					(String) end_game.get("thing"), (String) end_game.get("description"));
			this.endGames.add(endGame);
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

package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Setting {
	private String welcome;
	private String character;
	private ArrayList<Location> locations = new ArrayList<Location>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Npcs> npcs = new ArrayList<Npcs>();
	private ArrayList<String> inventario = new ArrayList<String>();
	private ArrayList<EndGame> endGames = new ArrayList<EndGame>();

	public void leerAventura() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Location location;
		Place place;
		Npcs npcs_nuevo;
		Trigger trigger;
		Connection connection;
		Item item;
		EndGame endGame;
		Object obj = parser.parse(new FileReader("escenario1.json"));
		JSONObject jsonObject = (JSONObject) obj;
		JSONObject setting = (JSONObject) jsonObject.get("settings");
		welcome = (String) setting.get("welcome");
		character = (String) setting.get("character");
		JSONArray locations = (JSONArray) jsonObject.get("locations");
		for (Object obLocation : locations) {
			JSONObject ob = (JSONObject) obLocation;
			location = new Location((String) ob.get("name"), (String) ob.get("gender"), (String) ob.get("number"),
					(String) ob.get("description"));
			if (ob.containsKey("places")) {
				JSONArray places = (JSONArray) ob.get("places");
				for (Object obPlace : places) {
					JSONObject obP = (JSONObject) obPlace;
					place = new Place((String) obP.get("name"), (String) obP.get("gender"), (String) ob.get("number"));
					location.getPlaces().add(place);
					if (obP.containsKey("items")) {
						JSONArray items = (JSONArray) obP.get("items");
						for (Object obItem : items) {
							location.getItems().add((String) obItem);
						}
					}
				}
			}
			if (ob.containsKey("npcs")) {
				JSONArray npcs = (JSONArray) ob.get("npcs");
				for (Object obNpcs : npcs) {
					location.getNpcs().add((String) obNpcs);
				}
			}
			if (ob.containsKey("connections")) {
				JSONArray connections = (JSONArray) ob.get("connections");
				for (Object obConnection : connections) {
					JSONObject obConnect = (JSONObject) obConnection;
					connection = new Connection((String) obConnect.get("direction"), (String) obConnect.get("location"),
							(String) obConnect.get("obstacles"));
					location.getConnections().add(connection);
				}
			}
			this.locations.add(location);
		}
		JSONArray npcs_part = (JSONArray) jsonObject.get("npcs");
		for (Object npcsP : npcs_part) {
			JSONObject npcs_partid = (JSONObject) npcsP;
			npcs_nuevo = new Npcs((String) npcs_partid.get("name"), (String) npcs_partid.get("gender"),
					(String) npcs_partid.get("number"), (String) npcs_partid.get("description"),
					(String) npcs_partid.get("talk"));
			if (npcs_partid.containsKey("triggers")) {
				JSONArray triggers = (JSONArray) npcs_partid.get("triggers");
				for (Object triger : triggers) {
					JSONObject trigger_part = (JSONObject) triger;
					trigger = new Trigger((String) trigger_part.get("type"), (String) trigger_part.get("thing"),
							(String) trigger_part.get("on_trigger"), (String) trigger_part.get("after_trigger"));
					npcs_nuevo.getTriggers().add(trigger);
				}
			}
			this.npcs.add(npcs_nuevo);
		}
		JSONArray items_part = (JSONArray) jsonObject.get("items");
		for (Object itemPart : items_part) {
			JSONObject items_partida = (JSONObject) itemPart;
			item = new Item((String) items_partida.get("name"), (String) items_partida.get("gender"),
					(String) items_partida.get("number"));
			if (items_partida.containsKey("actions")) {
				JSONArray actions_item = (JSONArray) items_partida.get("actions");
				for (Object actionsItem : actions_item) {
					item.getActions().add((String) actionsItem);
				}
			}
			if (items_partida.containsKey("effects_over")) {
				JSONArray effects_over_item = (JSONArray) items_partida.get("effects_over");
				for (Object effectsOverItem : effects_over_item) {
					item.getEffects_over().add((String) effectsOverItem);
				}
			}

			this.items.add(item);
		}
		/*
		 * JSONArray inventory_part = (JSONArray) jsonObject.get("inventory"); for
		 * (Object itemPart : inventory_part) { System.out.println(); }
		 */
		JSONArray end_games = (JSONArray) jsonObject.get("endgames");
		for (Object endGames : end_games) {
			JSONObject end_game = (JSONObject) endGames;
			endGame = new EndGame((String) end_game.get("condition"), (String) end_game.get("action"),
					(String) end_game.get("thing"), (String) end_game.get("description"));
			this.endGames.add(endGame);
		}
		System.out.println();

	}

	public void mostrarItems() {
		for (Item it : this.items)
			System.out.println(it);
	}

	public void mostrarNpcs() {
		for (Npcs np : this.npcs)
			System.out.println(np);
	}

	public void mostrarEndGames() {
		for (EndGame eg : this.endGames)
			System.out.println(eg);
	}

	public void mostrarLocations() {
		for (Location lc : this.locations)
			System.out.println(lc);
	}

	@Override
	public String toString() {
		return "Setting [welcome=" + welcome + ", character=" + character + ", locations=" + locations + ", items="
				+ items + ", npcs=" + npcs + "]";
	}
}

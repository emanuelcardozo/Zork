package entities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Aventura {

	private ArrayList<EndGame> finales;
	private Player jugador;

	private Map<String, Item> itemsMap;
	private Map<String, NPC> npcsMap;

	public Aventura(String path) {
		try {
			construirAventura(path);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se pudo encontrar el archivo.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: Error con el archivo.");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ERROR: No se pudo parsear correctamente el archivo.");
			e.printStackTrace();
		}
	}

	public void construirAventura(String path) throws FileNotFoundException, IOException, ParseException {
		JSONParser parserJSON = new JSONParser();
		JSONObject archivoJSON = (JSONObject) parserJSON.parse(new FileReader(path));
		JSONObject settingJSON = (JSONObject) archivoJSON.get("settings");
		System.out.println((String) settingJSON.get("welcome"));

		JSONArray npcsJSON = (JSONArray) archivoJSON.get("npcs");
		JSONArray itemsJSON = (JSONArray) archivoJSON.get("items");

//		JSONArray endsGameJSON = (JSONArray) archivoJSON.get("endgames");
//		seteoGenericoDeLista("endGames", endsGameJSON);

		JSONArray locationsJSON = (JSONArray) archivoJSON.get("locations");
		String inicio = (String) ((JSONObject) locationsJSON.get(0)).get("name");

		crearItemMap(itemsJSON);
		crearNPCMap(npcsJSON);
		Map<String, Location> mapaLocation = crearLocationMap(locationsJSON);

		String name = (String) settingJSON.get("character");
		this.jugador = new Player(name, new Mundo(inicio, mapaLocation));
	}

	private void crearItemMap(JSONArray itemsJSON) {
		this.itemsMap = new HashMap<String, Item>();
		Item item;

		for (Object itemObj : itemsJSON) {
			item = new Item((JSONObject) itemObj);
			itemsMap.put(item.getName(), item);
		}
	}

	private void crearNPCMap(JSONArray npcsJSON) {
		this.npcsMap = new HashMap<String, NPC>();
		NPC npc;

		for (Object npcObj : npcsJSON) {
			npc = new NPC((JSONObject) npcObj);
			npcsMap.put(npc.getName(), npc);
		}
	}

	private Map<String, Location> crearLocationMap(JSONArray locationsJSON) {
		Map<String, Location> mapa = new HashMap<String, Location>();
		Location location;

		for (Object locationObj : locationsJSON) {
			location = new Location((JSONObject) locationObj, itemsMap, npcsMap);
			mapa.put(location.getName(), location);
		}

		return mapa;
	}

	public Player getJugador() {
		return jugador;
	}

	public void setJugador(Player jugador) {
		this.jugador = jugador;
	}

}

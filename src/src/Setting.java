package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entities.EndGame;
import entities.Location;
import entities.NPC;

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

		JSONArray locationsJSON = (JSONArray) archivoJSON.get("locations");
		seteoGenericoDeLista("locations", locationsJSON);

		JSONArray npcsJSON = (JSONArray) archivoJSON.get("npcs");
		seteoGenericoDeLista("npcs", npcsJSON);

		JSONArray itemsJSON = (JSONArray) archivoJSON.get("items");
		seteoGenericoDeLista("items", itemsJSON);

		JSONArray endsGameJSON = (JSONArray) archivoJSON.get("endgames");
		seteoGenericoDeLista("endGames", endsGameJSON);
	}

	// Este metodo recibe un array de JSON y el nombre de la lista a asignarla
	// creando los objetos correctos dependiendo la clase
	private void seteoGenericoDeLista(String fieldName, JSONArray jsonArray) {
		try {
			Field field = this.getClass().getDeclaredField(fieldName);
			Method add = ArrayList.class.getDeclaredMethod("add", Object.class);

			ParameterizedType fieldType = (ParameterizedType) field.getGenericType();
			Class<?> fieldTypeClass = (Class<?>) fieldType.getActualTypeArguments()[0];

			for (Object obj : jsonArray) {
				add.invoke(field.get(this),
						fieldTypeClass.getConstructor(JSONObject.class).newInstance((JSONObject) obj));
			}
		} catch (NoSuchFieldException e) {
			System.out.println("No encontro el atributo.");
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("No encontro el metodo.");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Argumentos incorrectos.");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		System.out.println("BIENVENIDO A ZORK: " + this.character);
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
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public ArrayList<NPC> getNpcs() {
		return npcs;
	}

	@Override
	public String toString() {
		return "Setting [welcome=" + welcome + ", character=" + character + ", locations=" + locations + ", items="
				+ items + ", npcs=" + npcs + ", inventario=" + inventario + ", endGames=" + endGames + "]";
	}
	

}

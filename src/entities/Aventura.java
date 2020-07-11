package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.FileLogger;
import io.InOutputable;
import motorDeInstrucciones.Motor;

public class Aventura {

	private EndGame endGame;
	private Motor motorInstrucciones;
	private Player jugador;
	private String welcome;
	private InOutputable ioComponent;

	private Map<String, Item> itemsMap;
	private Map<String, NPC> npcsMap;
	private Map<String, EndGame> triggerEndGameMap; // Key: Thing, Value: EndGame

	public Aventura( InOutputable ioComponent ){
		this.ioComponent = ioComponent;
		initialize();
	}

	public void initialize(){
		try {
			this.ioComponent.addFileLogger(new FileLogger("User"));
			String nombreEscenario = seleccionarEscenario();
			construirAventura("./Aventuras/"+ nombreEscenario );
			pedirNombreUsuario();
			saludar();
			motorInstrucciones = new Motor( ioComponent, jugador);
			motorInstrucciones.start();
		} catch (FileNotFoundException e) {
			ioComponent.showError("No se ha encontrado el archivo");
			e.printStackTrace();
		}
		despedir();
	}
	
	private String seleccionarEscenario() {
		File carpeta = new File("./Aventuras");
		String[] escenarios = carpeta.list();
		
		if (escenarios == null) {
			ioComponent.showError("No hay ninguna aventura para jugar");
			return null;
		}
		
		ioComponent.showMessage("Estas son las aventuras disponibles:");
		
		for (int i = 0; i < escenarios.length; i++) {
			ioComponent.showMessage(i + " - " + escenarios[i]);
		}
		String escenarioString = ioComponent.getValue("Seleccione un escenario del 0 al "+(escenarios.length-1)+" por favor:");
		int nroEscenario = Integer.parseInt(escenarioString);
		
		while( nroEscenario < 0 || nroEscenario >= escenarios.length) {
			escenarioString = ioComponent.getValue("Opcion Incorrecta. Por favor, seleccione un escenario del 0 al "+(escenarios.length-1)+" por favor:");
			nroEscenario = Integer.parseInt(escenarioString);
		} 
			
		return escenarios[nroEscenario];
	}

	private void pedirNombreUsuario() {
		String nombre = ioComponent.getValue("Ingrese su nombre por favor:");

		jugador.setName(nombre);
	}

	private void saludar() {
		ioComponent.showMessage("\nBienvenido a Zork " + jugador.getName() + "!\n");
		ioComponent.showMessage(welcome);
	}

	private void despedir() {
		ioComponent.showEnd("Gracias por jugar a Zork " + jugador.getName() + ", hasta luego!");
	}

	public void construirAventura(String path) {
		try {
			JSONParser parserJSON = new JSONParser();
			JSONObject archivoJSON = (JSONObject) parserJSON.parse(new FileReader(path));
			JSONObject settingJSON = (JSONObject) archivoJSON.get("settings");
			this.welcome = (String) settingJSON.get("welcome");

			JSONArray npcsJSON = (JSONArray) archivoJSON.get("npcs");
			JSONArray itemsJSON = (JSONArray) archivoJSON.get("items");
			JSONArray endsGameJSON = (JSONArray) archivoJSON.get("endgames");

			JSONArray locationsJSON = (JSONArray) archivoJSON.get("locations");
			String inicio = (String) ((JSONObject) locationsJSON.get(0)).get("name");

			crearFinales(endsGameJSON);
			crearItemMap(itemsJSON);
			crearNPCMap(npcsJSON);
			Map<String, Location> mapaLocation = crearLocationMap(locationsJSON);

			String name = (String) settingJSON.get("character");
			this.jugador = new Player(name, new Mundo(inicio, mapaLocation));

		} catch (FileNotFoundException e) {
			ioComponent.showMessage("ERROR: No se pudo encontrar el archivo.");
			e.printStackTrace();
		} catch (IOException e) {
			ioComponent.showMessage("ERROR: No se pudo parsear correctamente el archivo.");
			e.printStackTrace();
		} catch (ParseException e) {
			ioComponent.showMessage("ERROR: No se pudo parsear correctamente el archivo.");
			e.printStackTrace();
		}
	}

	private void crearFinales(JSONArray endsGameJSON) {
		triggerEndGameMap = new HashMap<String, EndGame>();
		EndGame endPrev = null, endNext;

		for (Object endGameObj : endsGameJSON) {
			endNext = new EndGame((JSONObject) endGameObj);
			triggerEndGameMap.put(endNext.getThing(), endNext);

			if( endPrev != null )
				endPrev.setNextEndGame( endNext );
			else
				endGame = endNext;

			endPrev = endNext;
		}
	}

	private void crearItemMap(JSONArray itemsJSON) {
		this.itemsMap = new HashMap<String, Item>();
		Item item;

		for (Object itemObj : itemsJSON) {
			item = new Item((JSONObject) itemObj, this);

			if (triggerEndGameMap.containsKey(item.getName()))
				item.setHastrigger(true);

			itemsMap.put(item.getName(), item);
		}
	}

	private void crearNPCMap(JSONArray npcsJSON) {
		this.npcsMap = new HashMap<String, NPC>();
		NPC npc;

		for (Object npcObj : npcsJSON) {
			npc = new NPC((JSONObject) npcObj, this);
			npcsMap.put(npc.getName(), npc);
		}
	}

	private Map<String, Location> crearLocationMap(JSONArray locationsJSON) {
		Map<String, Location> mapa = new HashMap<String, Location>();
		Location location;

		for (Object locationObj : locationsJSON) {
			location = new Location((JSONObject) locationObj, itemsMap, npcsMap, false, this);

			if (triggerEndGameMap.containsKey(location.getName())) {
				location.setHasTrigger(true);
			}

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

	public String ejecutarFinal(Trigger trigger) {
		String message = endGame.execute(trigger);

		if( message != null) motorInstrucciones.stop();

		return message;
	}
}

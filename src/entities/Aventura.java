package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import constantes.GameState;
import io.FileLogger;
import io.InOutputable;
import motorDeInstrucciones.Motor;

public class Aventura {

	private EndGame endGame;
	private Motor motorInstrucciones;
	private Player jugador;
	private String welcome;
	private InOutputable ioComponent;
	private String nombreEscenario;
	private GameState estadoDelJuego;

	private Map<String, Item> itemsMap;
	private Map<String, NPC> npcsMap;
	private Map<String, EndGame> triggerEndGameMap; // Key: Thing, Value: EndGame

	public Aventura( InOutputable ioComponent ){
		this.ioComponent = ioComponent;
		this.estadoDelJuego = GameState.JUGANDO;
		
		initialize();
	}

	public void initialize(){
		try {
			this.ioComponent.addFileLogger(new FileLogger("User"));
			nombreEscenario = seleccionarEscenario();
			construirAventura();
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
		String[] escenarios = carpeta.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		String message = "";
		
		if (escenarios == null) {
			ioComponent.showError("No hay ninguna aventura para jugar");
			return null;
		}
		
		message += "Estas son las aventuras disponibles:\n";
		
		for (int i = 1; i <= escenarios.length; i++) {
			message += i + " - " + escenarios[i-1] + "\n";
		}
		
		message += "Seleccione un escenario del 1 al "+(escenarios.length)+" por favor:";
		
		String escenarioString = "";
		int nroEscenario = -1;
		
		while( nroEscenario < 1 || nroEscenario > escenarios.length) {
			try {
				escenarioString = ioComponent.getValue(message);
				nroEscenario = Integer.parseInt(escenarioString);
			} catch (NumberFormatException e) {
				ioComponent.showError("Opcion Invalida.");
			}
			
			if(nroEscenario < 1 || nroEscenario > escenarios.length)
				ioComponent.showError("No existe la opcion " + nroEscenario);
		} 
			
		return escenarios[nroEscenario-1];
	}

	private void pedirNombreUsuario() {
		String nombre = ioComponent.getValue("Ingrese su nombre por favor:");

		jugador.setName(nombre);
	}

	private void saludar() {
		ioComponent.showMessage("Bienvenido a Zork " + jugador.getName() + "!\n" + welcome);
	}

	private void despedir() {
		String path = "./General/Sound/"+ this.estadoDelJuego.toString().toLowerCase() + ".wav";
		Sonido sonidoFinal = new Sonido(path);
		sonidoFinal.reproducir();
		ioComponent.showEnd("Gracias por jugar a Zork " + jugador.getName() + ", hasta luego!");
	}

	public void construirAventura() {
		try {
			String path = "./Aventuras/"+ nombreEscenario + "/" + nombreEscenario + ".json";
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
			this.jugador = new Player(name, new Mundo(inicio, mapaLocation), this);
			changeLocation(jugador.getPosicionActual());

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
	
	public void changeLocation(Location location) {
		ioComponent.changeLocation(location, nombreEscenario);
		actualizarEscenario();
	}

	public Player getJugador() {
		return jugador;
	}
	
	public String getEscenario() {
		return nombreEscenario.split(".json")[0];
	}

	public void setJugador(Player jugador) {
		this.jugador = jugador;
	}

	public String ejecutarFinal(Trigger trigger) {
		String message = endGame.execute(trigger);

		if( message != null) {
			motorInstrucciones.stop();
			
			if( message.indexOf("Enhorabuena") != -1)
				this.estadoDelJuego = GameState.GANADO;
			else
				this.estadoDelJuego = GameState.PERDIDO;
		}

		return message;
	}
	
	public void actualizarEscenario() {
		ioComponent.refresh();
	}
}

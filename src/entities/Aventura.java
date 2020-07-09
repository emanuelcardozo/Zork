package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import motorDeInstrucciones.Motor;
import visual.Ventana;

public class Aventura {

	private EndGame endGame;
	private Motor motorInstrucciones;
	private Player jugador;
	private String welcome;
	private Ventana ventana;

	private Map<String, Item> itemsMap;
	private Map<String, NPC> npcsMap;
	private Map<String, EndGame> triggerEndGameMap; // Key: Thing, Value: EndGame

	public Aventura() throws FileNotFoundException {
		initialize();
	}

	private void initialize() throws FileNotFoundException{
		ventana = new Ventana();
		String escenario = "";
		escenario = seleccionDeEscenario();
		if(escenario == null) {
			ventana.setText("No hay ninguna aventura para jugar");
			System.out.println("No hay ninguna aventura para jugar");	
		} else {
		construirAventura("./Aventuras/"+escenario);
		pedirNombreUsuario();
		saludar();
		motorInstrucciones = new Motor(jugador);
		motorInstrucciones.start();
		despedir();
		}
	}

	private void pedirNombreUsuario() {
		System.out.println("Ingrese su nombre por favor:");
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		String nombre = teclado.nextLine();

		jugador.setName(nombre);
	}

	private void saludar() {
		System.out.println("Bienvenido a Zork " + jugador.getName() + "!");
		System.out.println(welcome);
	}

	private void despedir() {
		System.out.println("Gracias por jugar a Zork " + jugador.getName() + ", hasta luego!");
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
	
	
	private String seleccionDeEscenario() {
		File carpeta = new File("./Aventuras");
		String[] escenarios = carpeta.list();
		int numero = 1000000000;
		
		if (escenarios == null)
			return null;
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		
		while (numero > escenarios.length - 1  || numero < 0) {
			ventana.setText("Seleccione un escenario valido del 0 al "+(escenarios.length-1)+" por favor: ");
			System.out.println("Seleccione un escenario valido del 0 al "+(escenarios.length-1)+" por favor: ");
			mostrarEscenarios(escenarios);
			
			if(teclado.hasNextInt())
				numero = teclado.nextInt();
			else 
				teclado = new Scanner(System.in);
		}
		return escenarios[numero];
	}
	
	private void mostrarEscenarios(String[] escenarios) {
		if (escenarios.length == 0) return;
		
		for (int i = 0; i < escenarios.length; i++) {
			ventana.setText(i + " - " + escenarios[i]);
			System.out.println(i + " - " + escenarios[i]);
		}
	}

}

package entities;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import io.InOutputable;

public class NPC extends Noun implements Triggerable, Drawable {
	private String description;
	private String talk;
	private Aventura aventura;
	private Posicion posicion;
	private Dibujador dibujador;
	private ArrayList<Talk> talks = new ArrayList<Talk>();
	private ArrayList<Trigger> triggers = new ArrayList<Trigger>();

	public NPC(String name, String gender, String number, String description, String talk) {
		super(name, gender, number);
		this.description = description;
		this.talk = talk;
	}

	public NPC(JSONObject npcJSON, Aventura aventura) {
		super((String) npcJSON.get("name"), (String) npcJSON.get("gender"), (String) npcJSON.get("number"));
		
		this.description = (String) npcJSON.get("description");
		this.talk = (String) npcJSON.get("talk");
		this.aventura = aventura;
		this.posicion = new Posicion(Integer.parseInt((String)npcJSON.get("ejex")), Integer.parseInt((String)npcJSON.get("ejey")), Integer.parseInt((String)npcJSON.get("ancho")), Integer.parseInt((String)npcJSON.get("alto")));
		this.dibujador = new Dibujador();
		
		if(npcJSON.containsKey("talks"))
			buildTalks((JSONArray) npcJSON.get("talks"));
		if (npcJSON.containsKey("triggers"))
			buildTriggers((JSONArray) npcJSON.get("triggers"));
	}

	private void buildTriggers(JSONArray triggersJSON) {
		for (Object triggerObj : triggersJSON) {
			JSONObject triggerJSON = (JSONObject) triggerObj;
			triggers.add(new Trigger(triggerJSON));
		}
	}

	private void buildTalks(JSONArray talksJSON) {
		for (Object talk : talksJSON) {
			JSONObject talkJSON = (JSONObject) talk;
			talks.add(new Talk(talkJSON));
		}
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setTalk(String talk) {
		this.talk = talk;
	}

	public void setTriggers(ArrayList<Trigger> triggers) {
		this.triggers = triggers;
	}

	public ArrayList<Trigger> getTriggers() {
		return triggers;
	}

	public String getDescription() {
		return description;
	}

	public String getTalk() {
		return talk;
	}
	
	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public Trigger reaccionAItem(Item item) {
		for (Trigger t : triggers) {
			if (t.getThing().equals(item.getName()))
				return t;
		}
		return null;
	}

	public String hablar(String playerName, InOutputable io) {
		int numero = talks.size();
		boolean salir = false;
		String message;
		if (talks.isEmpty()) return getTalk();
		
		while (!salir) {
			mostrarDialogos(io);
			message = "";
			
			try {
				numero = Integer.parseInt(io.getValue(null));
				
				if (numero == 7) 
					salir = true;
				else
					if (numero >= 0 && numero < talks.size()) {
						message += playerName.toUpperCase() + ": " + talks.get(numero).getYou() + "\n";
						message += name.toUpperCase() + ": " + talks.get(numero).getNpc();
						io.showMessage( message );
					} else {
						throw new NumberFormatException();
					}				
				
			} catch (NumberFormatException e) {
				io.showError("No es posible esa respuesta.");
			}			
		}
		return "Has terminado la charla. Sigue investigando!";
	}

	private void mostrarDialogos(InOutputable io) {
		String message = "-------- SELECCIONA UN DIALOGO ---------\n"; 
		for (int i = 0; i < talks.size(); i++) {
			message += i + " - " + talks.get(i).getYou() + "\n";
		}
		message += 7 + " - " + "Salir\n";
		message += "----------------------------------------";
		io.showMessage(message);		
	}

	public String mirar() {
		String message = getDescription();
		
		for (Trigger t : triggers) {
			if (t.getType().equals("observar"))
				return message + "\n" + executeTrigger(t);
		}
		
		return message;
	}

	public String serAcariciado() {
		for(Trigger t : triggers) {
			if(t.getType().equals("acariciar"))
				return t.getOn_trigger();
		}
		return null;
	}

	public String serGolpeadoCon(Item item) {
		for(Trigger t : triggers) {
			if(t.getType().equals("golpear") && t.getThing().equals(item.getName()))
				return executeTrigger( t );
		}
		return null;
	}


	public String serAcuchillado() {
		for(Trigger t : triggers) {
			if(t.getType().equals("atacar") && t.getThing().equals("cuchillo") )
				return executeTrigger( t );
		}
		return null;
	}

	public Trigger serCorrido() {
		for(Trigger t : triggers) {
			if(t.getType().equals("correr"))
				return t;
		}
		return null;
	}

	@Override
	public String executeTrigger(Trigger trigger) {
		return aventura.ejecutarFinal(trigger);
	}

	@Override
	public String executeTrigger() {
		return aventura.ejecutarFinal(new Trigger("action", name, null, null));
	}

	public void executeSound() {
		String path = "./Aventuras/"+ aventura.getEscenario() + "/sound/" + getName() + ".wav";
		Sonido sonido = new Sonido(path);
		sonido.reproducir();
	}

	@Override
	public void draw(Graphics g, JPanel panel) {
    	String path = "./Aventuras/"+ aventura.getEscenario() +"/images/npcs/" + getName() +".png";
    	dibujador.dibujar(g, panel, path, posicion);		
	}

}

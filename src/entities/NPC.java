package entities;

import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NPC extends Noun implements Triggerable {
	private String description;
	private String talk;
	private Aventura aventura;
	private ArrayList<Talk> talks = new ArrayList<Talk>();
	private ArrayList<Trigger> triggers = new ArrayList<Trigger>();
	private Scanner teclado;

	public NPC(String name, String gender, String number, String description, String talk) {
		super(name, gender, number);
		this.description = description;
		this.talk = talk;
	}

	public NPC(JSONObject npcJSON, Aventura aventura) {
		super((String) npcJSON.get("name"), (String) npcJSON.get("gender"), (String) npcJSON.get("number"));
		description = (String) npcJSON.get("description");
		talk = (String) npcJSON.get("talk");
		this.aventura = aventura;
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

	public Trigger reaccionAItem(Item item) {
		for (Trigger t : triggers) {
			if (t.getThing().equals(item.getName()))
				return t;
		}
		return null;
	}

	public String hablar() {
		int numero = talks.size();
		boolean salir = false;
		if (talks.isEmpty())
			return getTalk();
		else {
			System.out.println("----------------------------------------");
			for (int i = 0; i < talks.size(); i++) {
				System.out.println(i + "- " + talks.get(i).getYou());
			}
			System.out.println(7 + "- " + "Salir");
			System.out.println("----------------------------------------");
			while (!salir) {
				numero = talks.size();
				teclado = new Scanner(System.in);
				if (!teclado.hasNextInt())
					System.out.println("No es posible esa respuesta.");
				else {
					numero = teclado.nextInt();
					if (numero >= 0 && numero < talks.size())
						System.out.println(talks.get(numero).getNpc());
					else if (numero == 7)
						salir = true;
					else
						System.out.println("No es posible esa respuesta.");
				}

			}
			return "Has terminado la charla. Sigue investigando!";
		}
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

}

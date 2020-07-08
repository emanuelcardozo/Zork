package entities;

import org.json.simple.JSONObject;

public class Talk {
	private String you;
	private String npc;

	public Talk(JSONObject talkJSON) {
		this.you = (String) talkJSON.get("you");
		this.npc = (String) talkJSON.get("npc");
	}

	public String getYou() {
		return you;
	}

	public void setYou(String you) {
		this.you = you;
	}

	public String getNpc() {
		return npc;
	}

	public void setNpc(String npc) {
		this.npc = npc;
	}
}

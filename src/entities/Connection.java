package entities;

import org.json.simple.JSONObject;

public class Connection {
	private Direccion direction;
	private String location;
	private String obstacle;

	public Connection(String direction, String location, String obstacle) {
		this.direction = Enum.valueOf(Direccion.class, getDirection(direction).toUpperCase());
		this.location = location;
		this.obstacle = obstacle;
	}

	public Connection(JSONObject connectionJSON) {
		this.direction = Enum.valueOf(Direccion.class, (getDirection((String)connectionJSON.get("direction"))).toUpperCase());
		this.location = (String) connectionJSON.get("location");

		if (connectionJSON.containsKey("obstacles"))
			this.obstacle = (String) connectionJSON.get("obstacles");
	}

	public String getDirection(String direc) {
		switch (direc.toLowerCase()) {
		case "north":
			return "norte";
		case "east":
			return "este";
		case "west":
			return "oeste";
		default:
			return "sur";
		}

	}

	public Direccion getDirection() {
		return direction;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getObstacle() {
		return obstacle;
	}

	public void setObstacle(String obstacle) {
		this.obstacle = obstacle;
	}
}

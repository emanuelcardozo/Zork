package entities;

import org.json.simple.JSONObject;

public class Connection {
	private String direction;
	private String location;
	private String obstacle;

	public Connection(String direction, String location, String obstacle) {
		this.direction = direction;
		this.location = location;
		this.obstacle = obstacle;
	}

	public Connection(JSONObject connectionJSON) {
		this.direction = (String) connectionJSON.get("direction");
		this.location = (String) connectionJSON.get("location");

		if (connectionJSON.containsKey("obstacles"))
			this.obstacle = (String) connectionJSON.get("obstacles");
	}

	public String getDirection() {
		switch (direction.toLowerCase()) {
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

	public void setDirection(String direction) {
		this.direction = direction;
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

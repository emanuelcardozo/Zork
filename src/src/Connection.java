package src;

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
		direction = (String) connectionJSON.get("direction");
		location = (String) connectionJSON.get("location");
		obstacle = (String) connectionJSON.get("obstacles");
	}

	public String getDirection() {
		return this.changeDirection();
	}

	public String getLocation() {
		return location;
	}

	public String getObstacle() {
		return obstacle;
	}

	private String changeDirection() {
		switch(direction)
		{
			case "south": 
				return "sur";
			case "north":
				return "norte";
			case "east":
				return "este";
			case "west":
				return "oeste";
		}
		return direction;
	}
	
	@Override
	public String toString() {
		return "Connection [direction=" + direction + ", location=" + location + ", obstacle=" + obstacle + "]";
	}
}

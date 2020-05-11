package src;

public class Connection {
	private String direction;
	private String location;
	private String obstacle;

	public Connection(String direction, String location, String obstacle) {
		this.direction = direction;
		this.location = location;
		this.obstacle = obstacle;
	}

	public String getDirection() {
		return direction;
	}



	public String getLocation() {
		return location;
	}



	public String getObstacle() {
		return obstacle;
	}



	@Override
	public String toString() {
		return "Connection [direction=" + direction + ", location=" + location + ", obstacle=" + obstacle + "]";
	}
}

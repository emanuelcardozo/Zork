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

	@Override
	public String toString() {
		return "Connection [direction=" + direction + ", location=" + location + ", obstacle=" + obstacle + "]";
	}
}

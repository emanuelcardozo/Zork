package entities;

public enum Direccion {
	NORTE("N"), SUR("S"), ESTE("E"), OESTE("O") ;

	private String dir;

	private Direccion(String dir) {
		this.dir = dir;
	}

	public String getDirection() {
		return this.dir;
	}
}

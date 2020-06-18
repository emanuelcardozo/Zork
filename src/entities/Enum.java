package entities;

public enum Enum {
}

enum Numero {
	SINGULAR("S"), PLURAL("P");

	private String num;

	private Numero(String n) {
		this.num = n;
	}

	public String getNumber() {
		return this.num;
	}
}

enum Genero {
	MALE("M"), FEMALE("F");

	private String gen;

	private Genero(String g) {
		this.gen = g;
	}

	public String getGender() {
		return this.gen;
	}
}



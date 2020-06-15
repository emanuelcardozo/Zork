package entities;

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

package entities;

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
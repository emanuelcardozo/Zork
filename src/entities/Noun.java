package entities;

public class Noun {
	protected String name;
	protected String gender;
	protected String number;

	public Noun(String name, String gender, String number) {
		this.name = name;
		this.gender = gender;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getNumber() {
		return number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean esFemenino() {
		return gender.equalsIgnoreCase("Femenino");
	}

	public boolean esSingular() {
		return gender.equalsIgnoreCase("Singular");
	}

	public String getArticulo() {
		if (esSingular())
			return esFemenino() ? "la" : "el";

		return esFemenino() ? "las" : "los";
	}

	public String getCantidad() {
		if (esSingular())
			return esFemenino() ? "una" : "un";

		return esFemenino() ? "unas" : "unos";
	}

	@Override
	public String toString() {
		return "Noun [name=" + name + ", gender=" + gender + ", number=" + number + "]";
	}
}
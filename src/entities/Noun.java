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
		return gender.equalsIgnoreCase("female");
	}

	public boolean esSingular() {
		return number.equalsIgnoreCase("Singular");
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

	public String listarPalabras(String[] palabras) {
		String listado = "";

		if (palabras.length == 0)
			return listado;

		if (palabras.length == 1)
			return palabras[0] + ".";

		for (String palabra : palabras) {
			listado += palabra + ", ";
		}

		listado = listado.substring(0, listado.length() - 2);
		int ultimaComa = listado.lastIndexOf(',');

		return listado.substring(0, ultimaComa) + " y" + listado.substring(ultimaComa + 1) + ".";
	}
}

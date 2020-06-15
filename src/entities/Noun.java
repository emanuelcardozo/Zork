package entities;

public class Noun {
	protected String name;
	Genero genero;
	Numero numero;

	public Noun(String name, String genero, String numero) {
		this.name = name;
		this.genero = Enum.valueOf(Genero.class, genero.toUpperCase());
		this.numero = Enum.valueOf(Numero.class, numero.toUpperCase());
	}

	public String getName() {
		return name;
	}

//	public String getGender() {
//		return gender;
//	}
//
//	public String getNumber() {
//		return number;
//	}

	public void setName(String name) {
		this.name = name;
	}

//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public void setNumber(String number) {
//		this.number = number;
//	}
//
//	public boolean esFemenino() {
//		return gender.equalsIgnoreCase("female");
//	}
//
//	public boolean esSingular() {
//		return number.equalsIgnoreCase("Singular");
//	}
//
	public String getArticulo() {
		if (this.numero.name().equals("SINGULAR"))
			return this.genero.name().equals("FEMALE") ? "la" : "el";

		return this.genero.name().equals("FEMALE") ? "las" : "los";
	}

	public String getCantidad() {
		if (this.numero.name().equals("SINGULAR"))
			return this.genero.name().equals("FEMALE") ? "una" : "un";

		return this.genero.name().equals("FEMALE") ? "unas" : "unos";
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

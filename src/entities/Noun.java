package entities;

import constantes.Genero;
import constantes.Numero;

public class Noun {
	protected String name;
	Genero genero;
	Numero numero;

	public Noun(String name, String genero, String numero) {
		this.name = name;
		this.genero = Genero.get(genero);
		this.numero = Numero.get(numero);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArticulo() {
		if (numero.equals("SINGULAR"))
			return genero.equals("FEMALE") ? "la" : "el";

		return genero.equals("FEMALE") ? "las" : "los";
	}

	public String getCantidad() {
		if (numero.equals("SINGULAR"))
			return genero.equals("FEMALE") ? "una" : "un";

		return genero.equals("FEMALE") ? "unas" : "unos";
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

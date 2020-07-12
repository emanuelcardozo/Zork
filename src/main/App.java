package main;

import entities.Aventura;
import io.Consola;
import io.Ventana;

public class App {
	public static void main(String[] args) {
		//new Aventura(new Consola());
		new Aventura(new Ventana());
	}
}

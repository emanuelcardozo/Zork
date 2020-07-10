package main;

import java.io.FileNotFoundException;

import entities.Aventura;
import io.Consola;
import io.Ventana;

public class App {
	public static void main(String[] args) throws FileNotFoundException {
		new Aventura(new Consola());
//		new Aventura(new Ventana());
	}
}

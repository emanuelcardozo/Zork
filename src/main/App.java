package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import entities.Aventura;
import io.Consola;
import io.Ventana;

public class App {
	public static void main(String[] args) throws MalformedURLException, IOException {
		//new Aventura(new Consola());
		new Aventura(new Ventana());
	}
}

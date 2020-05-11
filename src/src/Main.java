package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		Setting jugar = new Setting("escenario1.json");
		Character c = new Character(jugar);
		System.out.println("-----MIRAR-----");
		System.out.println(c.mirar());
		c.mover("south");
		c.agarrarItem("barreta");
		c.agarrarItem("rociador con cerveza de raiz");
		System.out.println(c.inventario());
		c.mirar();
//		System.out.println(c.name());
//		System.out.println(c.inventario(jugar));
//		jugar.mostrarLocations();
	}
}

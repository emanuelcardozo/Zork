package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		try {
			Setting jugar = new Setting("escenario1.json");
			Character character = new Character(jugar);
			System.out.println("-----MIRAR-----");
			System.out.println(character.mirar());
			character.mover("south");
			character.agarrarItem("barreta");
			character.agarrarItem("rociador con cerveza de raiz");
			System.out.println(character.inventario());
			character.mirar();

		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo Settings");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Hay problemas con el archivo Settings");
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			System.out.println("Hay problemas parseando el archivo Settings");
			e.printStackTrace();
		}

//		System.out.println(c.name());
//		System.out.println(c.inventario(jugar));
//		jugar.mostrarLocations();
	}
}

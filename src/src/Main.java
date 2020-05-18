package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Main {
	
	public static void main(String[] args)
			throws FileNotFoundException, IOException, ParseException {

		try {
			Setting jugar = new Setting("escenario1.json");
			Character character = new Character(jugar);
			System.out.println("-----MIRAR-----");
			System.out.println(character.name());
			System.out.println(character.mirar());
			System.out.println(character.mover("south"));
			System.out.println(character.hablarCon("pirata fantasma"));
			//System.out.println(character.inventario());
			//System.out.println(character.hablarCon("pirata fantama"));
			character.usarEn("barreta", "pirata fantasma");
			character.mover("south");
			System.out.println(character.mirar());
			//System.out.println(character.mover("south"));
			//System.out.println(character.mirar());
			//System.out.println(character.mover("south"));
			System.out.println(character.mover("north"));
			System.out.println(character.mirar());
			character.mover("south");
			System.out.println(character.mirar());
			//System.out.println(character.mover("east"));
			//System.out.println(character.mirar());
			//System.out.println(character.mover("north"));
			//character.mover("north");
			//character.usarEn("barreta", "pirata fantasma");
			//character.agarrarItem("barreta");
			//character.agarrarItem("rociador con cerveza de raiz");
			//character.agarrarItem("espejo");
			//System.out.println(character.inventario());
			//character.mirar();
			//character.hablarCon("pirata fantasm");
			//character.hablarCon("pirata fantasma");
//			character.usarEn("barreta", "pirata fantasma");
//			character.mover("south");

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
//Character c= new Character(jugar);
//System.out.println(c.name());
//		System.out.println(c.inventario(jugar));
//		jugar.mostrarLocations();
	}
}


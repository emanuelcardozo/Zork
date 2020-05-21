package entities;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
	private Map<String, Item> items;

	public Inventory() {
		this.items = new HashMap<String, Item>();
	}

	public void agregarItem(Item item) {
		items.put(item.getName(), item);
	}

	public void eliminarItem(String itemName) {
		items.remove(itemName);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public String listarInventario() {
		String[] palabras = new String[items.size()];
		Item item;
		int index = 0;
		String listado = "Tienes ";

		for (Map.Entry<String, Item> entry : items.entrySet()) {
			item = entry.getValue();
			palabras[index++] = item.getCantidad() + " " + item.getName();
		}

		if (palabras.length == 0) {
			return "No tienes objetos en tu inventario.";
		} else if (palabras.length == 1) {
			return listado += palabras[0];
		}

		for (String palabra : palabras)
			listado += palabra + ", ";

		listado = listado.substring(0, listado.length() - 2);
		int ultimaComa = listado.lastIndexOf(',');

		return listado.substring(0, ultimaComa) + " y" + listado.substring(ultimaComa + 1) + ".";
	}
}

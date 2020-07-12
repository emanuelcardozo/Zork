package io;

import java.util.LinkedList;
import java.util.List;

public class Historial {
	
	private List<String> instruccion;
	private int currentIndex;
	
	public Historial() {
		currentIndex = 0;
		instruccion = new LinkedList<String>();
	}
	
	public void add(String command) {
		currentIndex = 0;
		instruccion.add(currentIndex, command);
	}
	
	public String getPrevious() {  
		return currentIndex < instruccion.size() ? instruccion.get(currentIndex++) : "";
	}
	
	public String getNext() {
		return currentIndex > 0 ? instruccion.get(--currentIndex) : "";
	}

	public void resetCurrentIndex() {
		currentIndex = 0;		
	}
}

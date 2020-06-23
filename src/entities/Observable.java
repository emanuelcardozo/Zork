package entities;

import java.util.List;

public abstract class Observable {
	protected List<Observer> observadores;
	protected String state;
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void agregarObservador(Observer observador) {
		observadores.add(observador);
	}
	
	public void notificarAObservadores() {
		observadores.forEach(a -> a.actualizar());
	}
}

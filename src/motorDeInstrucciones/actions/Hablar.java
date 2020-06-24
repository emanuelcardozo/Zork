package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Hablar implements Order {
	private Accion accion;
	private String objetivo;
	
	public Hablar(Accion st, String objetivo) {
		this.accion = st;
		this.objetivo = objetivo;
	}
	@Override
	public String execute() {
		return accion.hablar(objetivo);		
	}
}

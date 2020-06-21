package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Hablar implements Order {
	private Accion accion;
	
	public Hablar(Accion st, String where) {
		this.accion = st;
		accion.setAccionPart1(where);
	}
	@Override
	public String execute() {
		return accion.hablar();		
	}
}

package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Correr implements Order{
	private Accion accion;
	
	public Correr(Accion st, String where) {
		this.accion = st;
		accion.setAccionPart1(where);
	}
	@Override
	public String execute() {
		return accion.correr();	
	}
}

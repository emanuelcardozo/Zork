package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Usar implements Order{
	private Accion accion;
	
	public Usar(Accion st, String where, String where2) {
		this.accion = st;
		accion.setAccionPart1(where);
		accion.setAccionPart2(where2);
	}
	@Override
	public String execute() {
		return accion.usar();
	}
	
}

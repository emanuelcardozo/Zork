package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Acariciar implements Order{
	private Accion accion;
	
	public Acariciar(Accion st, String where) {
		this.accion = st;
		accion.setAccionPart1(where);
	}
	@Override
	public String execute() {
		return accion.acariciar();	
	}
}

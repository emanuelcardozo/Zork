package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Agarrar implements Order {
	private Accion accion;
	
	public Agarrar(Accion st, String where) {
		this.accion = st;
		accion.setAccionPart1(where);
	}

	@Override
	public String execute() {
		return accion.agarrar();
	}
}

package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Mover implements Order{
	private Accion accion;
	
	public Mover(Accion st, String where) {
		this.accion = st;
		this.accion.setAccionPart1(where);
	}

	@Override
	public String execute() {
		return accion.mover();
	}
}

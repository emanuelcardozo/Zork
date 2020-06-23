package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Acuchillar implements Order {
	private Accion accion;

	public Acuchillar(Accion st, String where) {
		this.accion = st;
		accion.setAccionPart1(where);
	}

	@Override
	public String execute() {
		return accion.acuchillar();
	}
}

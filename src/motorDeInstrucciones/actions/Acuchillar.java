package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Acuchillar implements Order {
	private Accion accion;
	private String objetivo;

	public Acuchillar(Accion st, String objetivo) {
		this.accion = st;
		this.objetivo = objetivo;
	}

	@Override
	public String execute() {
		return accion.acuchillar(objetivo);
	}
}

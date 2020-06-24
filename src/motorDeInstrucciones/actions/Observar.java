package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Observar implements Order {
	private Accion accion;
	private String donde;

	public Observar(Accion st, String donde) {
		this.accion = st;
		this.donde = donde;
	}

	@Override
	public String execute() {
		return accion.observar(donde);
	}
}

package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Ir implements Order{
	private Accion accion;
	private String donde;
	
	public Ir(Accion st, String donde) {
		this.accion = st;
		this.donde = donde;
	}

	@Override
	public String execute() {
		return accion.ir(donde);
	}
}

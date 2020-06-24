package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Recoger implements Order {
	private Accion accion;
	private String item;
	
	public Recoger(Accion st, String item) {
		this.accion = st;
		this.item = item;
	}

	@Override
	public String execute() {
		return accion.recoger(item);
	}
}

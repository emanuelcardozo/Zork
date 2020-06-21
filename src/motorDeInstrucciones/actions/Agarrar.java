package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Agarrar implements Order {
	private Accion ac;
	public Agarrar(Accion st, String where) {
		this.ac = st;
		ac.setAccionPart1(where);
	}

	@Override
	public String execute() {
		return ac.agarrar();
	}
}

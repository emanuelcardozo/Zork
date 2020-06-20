package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Agarrar implements Order {
	private Accion ac;
	public Agarrar(Accion st, String where) {
		this.ac = st;
		ac.setWhere(where);
	}

	@Override
	public void execute() {
		ac.agarrar();
	}
}

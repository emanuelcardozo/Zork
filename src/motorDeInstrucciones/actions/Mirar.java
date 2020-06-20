package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Mirar implements Order {
	private Accion ac;

	public Mirar(Accion st, String where) {
		this.ac = st;
		st.setWhere(where);
	}

	@Override
	public void execute() {
		ac.mirar();
	}
}

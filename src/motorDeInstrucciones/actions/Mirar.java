package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Mirar implements Order {
	private Accion ac;

	public Mirar(Accion st, String where) {
		this.ac = st;
		st.setAccionPart1(where);
	}

	@Override
	public String execute() {
		return ac.mirar();
	}
}

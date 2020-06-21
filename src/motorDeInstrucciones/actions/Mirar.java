package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Mirar implements Order {
	private Accion accion;

	public Mirar(Accion st, String where) {
		this.accion = st;
		st.setAccionPart1(where);
	}

	@Override
	public String execute() {
		return accion.mirar();
	}
}

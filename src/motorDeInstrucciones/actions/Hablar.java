package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Hablar implements Order {
	private Accion ac;
	public Hablar(Accion st, String where) {
		this.ac = st;
		ac.setWhere(where);
	}
	@Override
	public void execute() {
		ac.hablar();		
	}
}

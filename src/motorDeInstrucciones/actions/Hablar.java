package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Hablar implements Order {
	private Accion ac;
	public Hablar(Accion st, String where) {
		this.ac = st;
		ac.setAccionPart1(where);
	}
	@Override
	public String execute() {
		return ac.hablar();		
	}
}

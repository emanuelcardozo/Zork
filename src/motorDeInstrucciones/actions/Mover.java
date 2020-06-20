package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Mover implements Order{
	private Accion ac;
	
	public Mover(Accion st, String where) {
		this.ac = st;
		this.ac.setWhere(where);
	}

	@Override
	public void execute() {
		ac.mover();
	}
}

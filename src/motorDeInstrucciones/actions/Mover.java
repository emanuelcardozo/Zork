package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Mover implements Order{
	private Accion ac;
	
	public Mover(Accion st, String where) {
		this.ac = st;
		this.ac.setAccionPart1(where);
	}

	@Override
	public String execute() {
		return ac.mover();
	}
}

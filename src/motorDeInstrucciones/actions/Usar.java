package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Usar implements Order{
	private Accion ac;
	public Usar(Accion st, String where, String where2) {
		this.ac = st;
		ac.setAccionPart1(where);
		ac.setAccionPart2(where2);
	}
	@Override
	public String execute() {
		return ac.usar();
	}
	
}

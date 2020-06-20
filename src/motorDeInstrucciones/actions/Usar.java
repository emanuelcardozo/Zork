package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Usar implements Order{
	private Accion ac;
	public Usar(Accion st, String where, String where2) {
		this.ac = st;
		ac.setWhere(where);
		ac.setWhere2(where2);
	}
	@Override
	public void execute() {
		ac.usar();
	}
	
}

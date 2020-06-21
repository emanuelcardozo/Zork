package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Acariciar implements Order{
	private Accion ac;
	
	public Acariciar(Accion st, String where) {
		this.ac = st;
		ac.setAccionPart1(where);
	}
	@Override
	public String execute() {
		return ac.acariciar();	
	}
}

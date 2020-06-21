package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class DefaultAction implements Order{
	private Accion ac;
	public DefaultAction(Accion st) {
		this.ac = st;
	}

	@Override
	public String execute() {
		return ac.defaultAccion();
	}
}

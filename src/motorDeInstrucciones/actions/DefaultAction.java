package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class DefaultAction implements Order{
	private Accion accion;
	
	public DefaultAction(Accion st) {
		this.accion = st;
	}

	@Override
	public String execute() {
		return accion.defaultAccion();
	}
}

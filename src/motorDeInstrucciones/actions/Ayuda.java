package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Ayuda implements Order{
	private Accion accion;
	
	public Ayuda(Accion st) {
		this.accion = st;
	}
	@Override
	public String execute() {
		return accion.ayuda();		
	}
}

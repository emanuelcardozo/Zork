package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Acariciar implements Order{
	private Accion accion;
	private String quien;
	
	public Acariciar(Accion st, String quien) {
		this.accion = st;
		this.quien = quien;
	}
	@Override
	public String execute() {
		return accion.acariciar(quien);	
	}
}

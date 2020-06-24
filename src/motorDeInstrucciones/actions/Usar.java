package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Usar implements Order{
	private Accion accion;
	private String item;
	private String objetivo;
	
	public Usar(Accion st, String item, String objetivo) {
		this.accion = st;
		this.item = item;
		this.objetivo = objetivo;
	}
	@Override
	public String execute() {
		return accion.usar(item, objetivo);
	}
	
}

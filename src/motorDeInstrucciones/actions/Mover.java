package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Mover implements Order{
	private Accion accion;
	private String objetivo;
	
	public Mover(Accion st, String objetivo) {
		this.accion = st;
		this.objetivo = objetivo;
	}
	@Override
	public String execute() {
		return accion.mover(objetivo);	
	}
}

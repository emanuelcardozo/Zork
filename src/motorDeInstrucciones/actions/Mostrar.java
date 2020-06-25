package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Mostrar implements Order{
	private Accion accion;
	private String objetivo;
	
	public Mostrar(Accion st, String objetivo) {
		this.accion = st;
		this.objetivo = objetivo;
	}
	@Override
	public String execute() {
		return accion.mostrar(objetivo);	
	}
}

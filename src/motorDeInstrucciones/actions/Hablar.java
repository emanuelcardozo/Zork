package motorDeInstrucciones.actions;

import io.InOutputable;
import motorDeInstrucciones.Order;

public class Hablar implements Order {
	private Accion accion;
	private String objetivo;
	private InOutputable io;
	
	public Hablar(Accion st, String objetivo, InOutputable io) {
		this.accion = st;
		this.objetivo = objetivo;
		this.io = io;
	}
	@Override
	public String execute() {
		return accion.hablar(objetivo, io);		
	}
}

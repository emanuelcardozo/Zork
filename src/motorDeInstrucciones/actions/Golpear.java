package motorDeInstrucciones.actions;
import motorDeInstrucciones.Order;

public class Golpear implements Order {
	private Accion accion;
	
	public Golpear(Accion st, String where) {
		this.accion = st;
		accion.setAccionPart1(where);
	}
	@Override
	public String execute() {
		return accion.golpear();		
	}
}

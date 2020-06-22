package motorDeInstrucciones.actions;
import motorDeInstrucciones.Order;

public class Golpear implements Order {
	private Accion accion;
	
	public Golpear(Accion st, String where, String where2) {
		this.accion = st;
		accion.setAccionPart1(where);
		accion.setAccionPart2(where2);
	}
	@Override
	public String execute() {
		return accion.golpear();		
	}
}

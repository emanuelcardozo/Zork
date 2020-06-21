package motorDeInstrucciones.actions;
import motorDeInstrucciones.Order;

public class Golpear implements Order {
	private Accion ac;
	public Golpear(Accion st, String where) {
		this.ac = st;
		ac.setAccionPart1(where);
	}
	@Override
	public String execute() {
		return ac.golpear();		
	}
}

package motorDeInstrucciones.actions;
import motorDeInstrucciones.Order;

public class Golpear implements Order {
	private Accion ac;
	public Golpear(Accion st, String where) {
		this.ac = st;
		ac.setWhere(where);
	}
	@Override
	public void execute() {
		ac.golpear();		
	}
}

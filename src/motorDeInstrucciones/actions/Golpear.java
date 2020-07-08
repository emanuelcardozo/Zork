package motorDeInstrucciones.actions;
import motorDeInstrucciones.Order;

public class Golpear implements Order {
	private Accion accion;
	private String item;
	
	public Golpear(Accion st, String item) {
		this.accion = st;
		this.item = item;
	}
	@Override
	public String execute() {
		return accion.golpear(item);		
	}
}

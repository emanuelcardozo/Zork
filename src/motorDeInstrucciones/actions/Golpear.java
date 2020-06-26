package motorDeInstrucciones.actions;
import motorDeInstrucciones.Order;

public class Golpear implements Order {
	private Accion accion;
	private String item;
//	private String objetivo;
	
	public Golpear(Accion st, String item) {
		this.accion = st;
		this.item = item;
//		this.objetivo = objetivo;
	}
	@Override
	public String execute() {
		return accion.golpear(item);		
	}
}

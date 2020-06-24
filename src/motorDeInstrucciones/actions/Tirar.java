package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Tirar implements Order {
		private Accion accion;
		private String item;
		
		public Tirar(Accion st, String item) {
			this.accion = st;
			this.item = item;
		}

		@Override
		public String execute() {
			return accion.tirar(item);
		}
}

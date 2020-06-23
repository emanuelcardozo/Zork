package motorDeInstrucciones.actions;

import motorDeInstrucciones.Order;

public class Tirar implements Order {
		private Accion accion;
		
		public Tirar(Accion st, String where) {
			this.accion = st;
			this.accion.setAccionPart1(where);
		}

		@Override
		public String execute() {
			return accion.tirar();
		}
}

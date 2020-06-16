package entities;

public class Agarrar implements Order {
	private Accion ac;
	public Agarrar(Accion st) {
		this.ac = st;
	}

	@Override
	public void execute() {
		ac.agarrar();
	}
}

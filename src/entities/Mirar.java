package entities;

public class Mirar implements Order {
	private Accion ac;

	public Mirar(Accion st) {
		this.ac = st;
	}

	@Override
	public void execute() {
		ac.mirar();
	}
}

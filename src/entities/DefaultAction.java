package entities;

public class DefaultAction implements Order{
	private Accion ac;
	public DefaultAction(Accion st) {
		this.ac = st;
	}

	@Override
	public void execute() {
		ac.defaultAccion();
	}
}

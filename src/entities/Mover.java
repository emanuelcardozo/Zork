package entities;

public class Mover implements Order{
	private Accion ac;
	private String where;
	public Mover(Accion st, String where) {
		this.ac = st;
		this.ac.setWhere(where);
	}

	@Override
	public void execute() {
		ac.mover();
	}
}

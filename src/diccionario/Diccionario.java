package diccionario;

public class Diccionario {
	
	public Verbo buscarVerbo(String alias) {
		return Verbo.buscarSinonimo(alias);
	}
	
	public boolean esArticulo(String alias) {
		return Modificador.buscarSinonimo(alias) != null;
	}
	
	public boolean esVerbo(String alias) {
		return Verbo.buscarSinonimo(alias) != null;
	}
	
	public boolean esSustantivo(String alias) {
		// TODO 
		// return esItem || esLocation || esPlace || esNPC
		return false;
	}
}

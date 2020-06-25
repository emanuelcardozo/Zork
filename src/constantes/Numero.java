package constantes;

public enum Numero {
	SINGULAR, 
	PLURAL;
	
	public static Numero get(String numero) {
		try {
			return valueOf(numero.toUpperCase());
		} catch ( IllegalArgumentException e ) {
			return null;
		}				
	}
	
	public boolean equals(String someValue) {
		return this.name().equalsIgnoreCase(someValue);
	}
}
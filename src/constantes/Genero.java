package constantes;

public enum Genero {
	MALE, 
	FEMALE;
	
	public static Genero get(String genero) {
		try {
			return valueOf(genero.toUpperCase());
		} catch ( IllegalArgumentException e ) {
			return null;
		}				
	}
	
	public boolean equals(String someValue) {
		return this.name().equalsIgnoreCase(someValue);
	}
}
package motorDeInstrucciones;

import motorDeInstrucciones.diccionario.Diccionario;

public class ComandoParser {
	
	private String[] palabrasComando;
	private Diccionario diccionario;
	
	public ComandoParser(String comando) {
		this.palabrasComando = comando.toLowerCase().split(" ");
		this.diccionario = new Diccionario();
	}

	public String[] getSustantivos() {
		int i=0, nroSustantivo=0;
		boolean sustantivoEncontrado = false;
		String[] sustantivos = new String[2];
		String palabra;
		
		while( i < palabrasComando.length ) {
			palabra = palabrasComando[i];
			sustantivoEncontrado = diccionario.esSustantivo(palabra);
			
			while( sustantivoEncontrado ) {				
				if( sustantivos[nroSustantivo] != null )
					sustantivos[nroSustantivo] += " " + palabra;
				else
					sustantivos[nroSustantivo] = palabra;
				
				i++;
				if( i < palabrasComando.length ) {
					palabra = palabrasComando[i];
					boolean extiendeSustantivo = palabra.equalsIgnoreCase("con") || palabra.equalsIgnoreCase("de");
					sustantivoEncontrado = extiendeSustantivo || diccionario.esSustantivo(palabra);
				} else
					sustantivoEncontrado = false;
			}
			
			if(sustantivos[nroSustantivo] != null && nroSustantivo < 1) nroSustantivo++;
			i++;
		}
		
		return sustantivos;
	}

	public String getVerbo() {
		for(String palabra : palabrasComando) {
			if( diccionario.esVerbo(palabra))
				return diccionario.buscarVerbo(palabra).toString();
		}
		
		return null;
	}
}

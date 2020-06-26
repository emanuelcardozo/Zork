package motorDeInstrucciones.diccionario;

import java.util.HashMap;
import java.util.Map;

public enum Verbo {
	// VERBOS ( A - Z)
	
	ACARICIAR("acariciar", "tocar"),
	ACUCHILLAR("acuchillar", "apunalar", "apunialar"),		
//	CORRER("correr", "trotar", "sprintar"), se pisan mover y correr en significados
	
	GOLPEAR("golpear", "atacar", "pegar", "agredir"),
	HABLAR("hablar", "conversar", "dialogar", "charlar"),
	IR("ir", "dirigir", "caminar"),
	
	MOSTRAR("mostrar", "listar", "ensenar", "enseniar", "presentar"),
	MOVER("mover", "correr", "desplazar"),
	OBSERVAR("observar", "mirar", "ver", "listar"),
	
	RECOGER("recoger", "tomar", "levantar", "agarrar"),
    
	TIRAR("tirar", "descartar", "eliminar"),
	USAR("usar", "utilizar", "emplear");
    	
	// FIN ACCIONES

    private String[] sinonimo;

    private static Map<String, Verbo> sinonimos = new HashMap<>();
    static {
        for (Verbo b : Verbo.values()) {
            for (String alias : b.sinonimo) {
            	sinonimos.put(alias, b);
            }
        }
    }

    private Verbo(String ... aliases) {
        this.sinonimo = aliases;
    }

    public static Verbo buscarSinonimo(String alias) {
    	Verbo b = sinonimos.get(alias.toLowerCase());
        return b;
    }
	
	
}

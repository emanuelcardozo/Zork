package test;

import static org.junit.Assert.*;

import org.junit.Test;

import motorDeInstrucciones.diccionario.Diccionario;

public class DiccionarioTest {

	@Test
	public void test() {
		Diccionario d = new Diccionario();
		
		System.out.println(d.buscarVerbo("ir"));
		System.out.println(d.buscarVerbo("utilizar"));
	}

}

package test;

import static org.junit.Assert.*;

import org.junit.Test;

import motorDeInstrucciones.ComandoParser;

public class ComandoParserTest {
	
	ComandoParser cp;

	@Test
	public void testVerbo() {
		cp = new ComandoParser("Hablar con pirata fantasma");
		assertEquals("hablar", cp.getVerbo());
	}
	
	@Test
	public void testSustantivos() {
		cp = new ComandoParser("Hablar con pirata fantasma");
		assertEquals("pirata fantasma", cp.getSustantivos()[0]);
	}
	
	@Test
	public void testSinSustantivos() {
		cp = new ComandoParser("Hablar por hablar");
		assertNull(cp.getSustantivos());
	}
	
	@Test
	public void test2Sustantivos() {
		cp = new ComandoParser("Usar llave en puerta");
		assertEquals( "llave", cp.getSustantivos()[0]);
		assertEquals( "puerta", cp.getSustantivos()[1]);
	}

}

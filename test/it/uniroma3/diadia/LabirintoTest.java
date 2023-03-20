package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	private Labirinto labirintoTest;
	private Stanza nuovaStanzaVincente;
	@Before
	public void setUp() throws Exception {
		this.labirintoTest = new Labirinto();
		this.nuovaStanzaVincente = new Stanza("nuovaStanzaVincente");
	}

	@Test
	public void testSetStanzaVincente() {
		this.labirintoTest.setStanzaVincente(this.nuovaStanzaVincente);
		assertSame(nuovaStanzaVincente, this.labirintoTest.getStanzaVincente());
	}

}
